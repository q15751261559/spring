<%--
  Created by IntelliJ IDEA.
  User: 33285
  Date: 2021/5/30
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="./layui/css/layui.css"
          media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style>
        .wrap-div {
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 3;
            overflow: hidden;
            float: left;
            width: 100%;
            word-break: break-all;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<div class="layui-form" id="content">
    <table class="layui-table" style="table-layout:fixed">
        <colgroup>
            <col width="150">
            <col width="150">
            <col width="200">
            <col>
            <col width="120">
        </colgroup>
        <thead>
        <tr>
            <th>书名</th>
            <th>作者</th>
            <th>分类</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${sessionScope.favoriteListBooks}"
                   varStatus="status">
            <tr>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.sort}</td>
                <td class="wrap-td">
                    <div class="wrap-div">${book.description}</div>
                </td>
                <td>
                    <input type="button" class="layui-btn layui-btn-xs borrow" id="store" index="${book.id}" value="取消收藏">
                </td>
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
        var count = 0, current = 1, limit = 20;

        $(document).on('click', '#store', function () {
            //可以获取第一列的内容，也就是name的值
            var button_store = "已收藏";
            var data = {'book_id':($(this).attr("index")),"button_store":button_store};
            $.ajax({
                type: 'POST',
                url: "/book/store",
                async: false, //开启同步请求，为了保证先得到count再渲染表格
                data:data,
                success: function (data) {
                }
            });
            location.reload();
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
                url: "/FavoriteListServlet",
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
    });
</script>
</body>
</html>
