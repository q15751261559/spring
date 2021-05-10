<%--
  Created by IntelliJ IDEA.
  User: 石振宇
  Date: 2021/3/29
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String message=request.getParameter("message");
    if (message!=null&&!message.equals("")){
%>
<script>
    layui.msg("<%= message%>")
</script>
<% }%>
</body>
</html>
