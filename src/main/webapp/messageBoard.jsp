<%--
  Created by IntelliJ IDEA.
  User: 石振宇
  Date: 2021/5/27
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Layui</title>
</head>
<meta charset="utf-8">
<title>Layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="./layui/css/layui.css"
      media="all">
<body>
<div class="layui-form" id="content">
<table class="layui-table" style="table-layout:fixed">
    <thead>
    <tr>
        <th>用户</th>
        <th>留言内容</th>
        <th>时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="messages" items="${sessionScope.messages}" varStatus="status">
        <tr>
            <td>${messages.reader}</td>
            <td>${messages.detail}</td>
            <td>${messages.date}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</div>

<div id="page" style="display: flex;justify-content: center;">
</div>
<form class="layui-form" action="">
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">留言板</label>
        <div class="layui-input-block">
            <textarea id="textarea" lay-verify="content" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
    </div>
</form>
<script src="layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['layedit','laypage', 'layer', 'element','form'], function () {
            var laypage = layui.laypage
                , layer = layui.layer, element =
                layui.element,layedit = layui.layedit,form=layui.form;
            var index=layedit.build('textarea',{height: 350}); //建立编辑器
            var $ = layui.$;
            var count = 0, current = 1, limit = 5;

        form.verify({
                //content富文本域中的lay-verify值
                content: function(value) {
                    return layedit.sync(index);
                }
        });
        form.on('submit(formDemo)', function () {
            var content = layedit.getContent(index);
            var data = {'message':content};
            $.ajax({
                type: 'POST',
                url: "/message/insert",
                async: false, //开启同步请求，为了保证先得到count再渲染表格
                data: data,
                success: function () {
                    alert('发表成功');
                }
            });
        })

            $(document).ready(function () {
                //进入页面先加载数据
                getContent(1, limit);
                //得到数量count后，渲染表格
                laypage.render({
                    elem: 'page',
                    count: count,
                    curr: current,
                    limits: [5, 10, 15, 20],
                    limit: limit,
                    layout: ['count', 'prev', 'page', 'next', 'limit'],
                    jump: function (obj, first) {
                        if (!first) {
                            getContent(obj.curr, obj.limit);
                            //更新当前页码和当前每页显示条数
                            current = obj.curr;
                            limit = obj.limit;
                        }
                    }
                });
            });

            function getContent(page, size) {
                $.ajax({
                    type: 'POST',
                    url: "/message/search",
                    async: false, //开启同步请求，为了保证先得到count再渲染表格
                    data: JSON.stringify({
                        pageNum: page,
                        pageSize: size
                    }),
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        $('#content').load(location.href + " #content");
                        //count从Servlet中得到
                        count = data;
                    }
                });
            }
        }
    );
</script>
</body>
</html>
