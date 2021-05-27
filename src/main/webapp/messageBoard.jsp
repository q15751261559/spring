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
    <title>Title</title>
</head>
<meta charset="utf-8">
<title>Layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="./layui/css/layui.css"
      media="all">
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
<body>
<table class="layui-table" style="table-layout:fixed">
    <thead>
    <tr>
        <th>用户</th>
        <th>留言内容</th>
        <th>时间</th>
    </tr>
    </thead>
</table>
</body>
</html>
