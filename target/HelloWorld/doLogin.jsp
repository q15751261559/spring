<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Hinoki
  Date: 2021/3/15
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录验证中……</title>
</head>
<body>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String status = request.getParameter("status");
    String name = request.getParameter("name");

    try {
        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2.获得连接实例
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?serverTimezone=UTC&characterEncoding=utf-8", "root", "root");) {
            // 3.预编译SQL语句
            if ("login".equals(status)) {
                String sql = "select * from borrow_card where username = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql);) {
                    statement.setString(1, username);
                    // 4.执行查询
                    try (ResultSet resultSet = statement.executeQuery();) {
                        // 5.遍历ResultSet
                        if (resultSet.next()) {
                            if (password.equals(resultSet.getString("password"))) {
                                // 执行跳转
                                response.sendRedirect("./main.jsp");
                            } else {
                                // 返回首页
                                response.sendRedirect("./index.jsp?error=0");
                            }
                        } else {
                            // 查无此人
                            response.sendRedirect("./index.jsp?error=1");
                        }
                    }
                }
            } else {
                String sql = "insert borrow_card(`username`,`reader`,`password`,`status`)" +
                        "values(?,?,?,'1')";
                try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
                    pstmt.setString(1, username);
                    pstmt.setString(2, name);
                    pstmt.setString(3, password);
                    int i =pstmt.executeUpdate();
                    if (i == 1) {
                        response.sendRedirect("./index.jsp?register=0");
                    } else {
                        response.sendRedirect("./index.jsp?register=1");
                    }
                }
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
%>
</body>
</html>
