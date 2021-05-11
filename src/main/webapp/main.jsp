<%@ page import="cn.edu.niit.javabean.User" %><%--
  Created by IntelliJ IDEA.
  User: Hinoki
  Date: 2021/3/15
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
</head>
<body class="layui-layout-body">
<% User user=(User)request.getSession().getAttribute("user"); %>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    <%=user.getReader()%>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/logout">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">借阅服务</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;"
                               name="borrow"
                               title="查询图书"
                               content="./searchBooks.jsp"
                               id="1">查询图书</a></dd>
                        <dd><a href="javascript:;"
                               name="borrow"
                               title="借阅历史"
                               content="./userHistory.jsp"
                               id="2">借阅历史</a></dd>
                        <dd><a href="javascript:;"
                               name="borrow"
                               title="在借图书"
                               content="./borrowList.jsp"
                               id="3">在借图书</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-tab layui-tab-brief" lay-filter="tabTemp" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this"><i class="layui-icon">
                    &#xe68e
                </i>欢迎
                </li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show"></div>
                <div class="layui-tab-item">内容1</div>
                <div class="layui-tab-item">内容2</div>
                <div class="layui-tab-item">内容3</div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="./layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
        var $ = layui.$;
        $("[name=borrow]").click(function () {
            var id = $(this).attr("id");
            var content = $(this).attr("content");
            if ($("li[lay-id=" + id + "]").length == 0) {
                element.tabAdd("tabTemp", {
                    title: $(this).attr("title"),
                    content: "<iframe src='" + content + "' class='frame' frameborder='0'></iframe>",
                    id: id
                });
            }
            element.tabChange("tabTemp", id);
        });
    });
</script>
</body>
</html>