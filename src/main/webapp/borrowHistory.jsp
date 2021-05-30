<%--
  Created by IntelliJ IDEA.
  User: 33285
  Date: 2021/5/30
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Layui</title>
</head>
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
            <th>书名</th>
            <th>作者</th>
            <th>分类</th>
            <th>借阅时间</th>
            <th>归还时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="borrowHistory" items="${sessionScope.borrowHistoryBooks}" varStatus="status">
            <tr>
                <td>${borrowHistory.name}</td>
                <td>${borrowHistory.author}</td>
                <td>${borrowHistory.sort}</td>
                <td>${borrowHistory.borrow_date}</td>
                <td>${borrowHistory.guihuan}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div id="page" style="display: flex;justify-content: center;">
</div>
<script src="layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['laypage', 'layer', 'element'], function () {
            var laypage = layui.laypage
                , layer = layui.layer, element =
                layui.element;
            var $ = layui.$;
            var count = 0, current = 1, limit = 5;

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
                    url: "/borrowHistory",
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
