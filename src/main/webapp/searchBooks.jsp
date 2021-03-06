<%--
  Created by IntelliJ IDEA.
  User: Hinoki
  Date: 2021/3/15
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
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
<body style="height: 100%">

<div class="layui-nav-item demoTable"
     style="display: flex;justify-content: flex-end;">
    <input id="keyword" type="text" class="layui-input"
           style="padding: 0;display: inline;width: 300px;"
           placeholder="请输入搜索信息..."/>
    <button id="search" class="layui-btn" data-type="getCheckLength"
            style="margin-left: 20px;">搜索
    </button>
    <button id="reset" class="layui-btn" data-type="getCheckLength"
            style="margin-left: 20px;">重置
    </button>
</div>

<div class="layui-form" id="content">
    <table class="layui-table" style="table-layout:fixed">
        <colgroup>
            <col width="150">
            <col width="150">
            <col width="200">
            <col>
            <col width="220">
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
        <c:forEach var="book" items="${sessionScope.books}"
                   varStatus="status">
            <tr>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.sort}</td>
                <td class="wrap-td">
                    <div class="wrap-div">${book.description}</div>
                </td>
                <td>
                    <button
                            class="layui-btn layui-btn-primary layui-btn-xs detail"
                            id="info" index="${status.index}">查看
                    </button>
<%--                    <button class="layui-btn layui-btn-xs borrow"--%>
<%--                            id="borrow" index="${status.index}">借阅--%>
<%--                    </button>--%>
                    <input type="button" class="layui-btn layui-btn-xs borrow" id="borrow"
                           index="${book.id}" value="${book.borrow?"归还":"借阅"}">
<%--                    <button class="layui-btn layui-btn-xs borrow"--%>
<%--                            id="store" index="${book.id}">--%>
<%--                            ${book.store?"已收藏":"收藏"}--%>
<%--                    </button>--%>
                    <input type="button" class="layui-btn layui-btn-xs borrow" id="store" index="${book.id}" value="${book.store?"已收藏":"收藏"}">
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div id="page" style="display: flex;justify-content: center;">
</div>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
    layui.use(['laypage', 'layer', 'element'], function () {
            var laypage = layui.laypage
                , layer = layui.layer, element =
                layui.element;
            var $ = layui.$;
            var count = 0, current = 1, limit = 5;


            //查看按钮的点击事件
            $(document).on('click', '#info', function () {
                //可以获取第一列的内容，也就是name的值
                var name = $(this).parents("tr").find("td").eq(0).text();
                //也可以获取属性中的值
                layer.msg(name)
            })

            $(document).on('click', '#store', function () {
                //可以获取第一列的内容，也就是name的值
                var button_store = $(this).val();
                var _this=$(this)
                var data = {'book_id':($(this).attr("index")),"button_store":button_store};
                $.ajax({
                    type: 'POST',
                    url: "/book/store",
                    async: false, //开启同步请求，为了保证先得到count再渲染表格
                    data:data,
                    success: function (data) {
                        let rs=data.split(' ');
                        let result=rs[0];
                        let buttonVal=rs[1];
                        _this.val(buttonVal);
                        alert(result)
                    }
                });
            })

            //借阅按钮的点击事件
            $(document).on('click', '#borrow', function () {
                //可以获取第一列的内容，也就是name的值
                var button_borrow = $(this).val();
                var _this=$(this)
                var data = {'book_id':($(this).attr("index")),"button_borrow":button_borrow};
                $.ajax({
                    type: 'POST',
                    url: "/book/borrow",
                    async: false, //开启同步请求，为了保证先得到count再渲染表格
                    data:data,
                    success: function (data) {
                        let rs=data.split(' ');
                        let result=rs[0];
                        let buttonVal=rs[1];
                        _this.val(buttonVal);
                        alert(result)
                    }
                });
            })


            $('#search').click(function () {
                var keyword = $('#keyword').val();
                getContent(keyword,1, limit);
                laypage.render({
                    elem: 'page',
                    count: count,
                    curr: current,
                    limits: [5, 10, 15, 20],
                    limit: limit,
                    layout: ['count', 'prev', 'page', 'next', 'limit'],
                    jump: function (obj, first) {
                        if (!first) {
                            getContent(keyword,obj.curr, obj.limit);
                            //更新当前页码和当前每页显示条数
                            current = obj.curr;
                            limit = obj.limit;
                        }
                    }
                });
            });

        $('#reset').click(function () {
            location.reload();
        });

            $(document).ready(function () {
                let search=null;
                //进入页面先加载数据
                getContent(search,1, limit);
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
                            getContent(search,obj.curr, obj.limit);
                            //更新当前页码和当前每页显示条数
                            current = obj.curr;
                            limit = obj.limit;
                        }
                    }
                });
            });

            function getContent(search,page, size) {
                $.ajax({
                    type: 'POST',
                    url: "/book/search",
                    async: false, //开启同步请求，为了保证先得到count再渲染表格
                    data: JSON.stringify({
                        search:search,
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