<%--
  Created by IntelliJ IDEA.
  User: 33285
  Date: 2021/5/30
  Time: 22:26
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
</head>
<body>
<form class="layui-form" action="/user/info" method="post" id="content">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block layui-disabled">
            <input type="text" name="username" readonly="readonly" required lay-verify="required"  autocomplete="off"
                   class="layui-input" value="${sessionScope.user.username}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-block">
            <input type="text" name="nickname" readonly="readonly" required lay-verify="required"  autocomplete="off"
                   class="layui-input" value="${sessionScope.user.reader}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机</label>
        <div class="layui-input-block">
            <input type="text" name="phone" readonly="readonly" required lay-verify="required"  autocomplete="off"
                   class="layui-input" value="${sessionScope.user.phone}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" readonly="readonly" required lay-verify="required"  autocomplete="off"
                   class="layui-input" value="${sessionScope.user.email}">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">个人介绍</label>
        <div class="layui-input-block">
            <textarea name="introduce" readonly="readonly"  class="layui-textarea">${sessionScope.user.describe}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="text" name="sex" readonly="readonly" required lay-verify="required"  autocomplete="off"
                   class="layui-input" value="${sessionScope.user.xingbie}">
        </div>
    </div>
</form>
</body>
</html>
