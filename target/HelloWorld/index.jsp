<%@ page import="javax.swing.plaf.LayerUI" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="layer/layer.js"></script>
    <style>
        * {
            padding: 0;
            margin: 0;
        }
        body {
            background: cadetblue;
            /*background: url(bg.jpg) no-repeat;*/
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .main {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 300px;
            width: 400px;
            background-color: rgba(255,255,255,.3);
            border-radius: 20px;

        }
        .main .title {
            margin-bottom: 30px;
            font-size: 24px;
            font-weight: bold;
        }
        .main .login {
            margin-left: -16%;
        }
        input {
            background-color: rgba(255,255,255,.0);
            border: 0;
            border-bottom: #0C0C0C solid 1px;
            height: 35px;
        }
        input::placeholder{
            color: #0C0C0C;
        }
        #register {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: row;
            margin-top: 40px;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="title">图书馆</div>
    <div class="login">
        <form class="layui-form" action="login" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input type="text" name="username" required lay-verify="required" placeholder="请输入账号" autocomplete="off"
                           class="" onfocus="this.placeholder=''" onblur="this.placeholder='请输入账号'">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="" onfocus="this.placeholder=''" onblur="this.placeholder='请输入密码'">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">管理员登录</label>
                    <div class="layui-input-block">
                        <input type="checkbox" lay-skin="switch" lay-filter="admin">
                    </div>
                </div>
                <div class="layui-input-block">
                    <button class="layui-btn">登录</button>
                    <button type="button" class="layui-btn layui-btn-primary" onclick="userRegister()">注册</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="layui-row" id="userRegister" style="display:none;">
    <div class="layui-col-md11" id="register">
        <form class="layui-form" action="register" method="post">
            <div class="layui-form-item" >
                <label class="layui-form-label">账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" required lay-verify="required" placeholder="请输入账号" autocomplete="off"
                           class="layui-input" onfocus="this.placeholder=''" onblur="this.placeholder='请输入账号'">
                </div>
            </div><div class="layui-form-item" >
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off"
                           class="layui-input" onfocus="this.placeholder=''" onblur="this.placeholder='请输入姓名'">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                           autocomplete="off" class="layui-input" onfocus="this.placeholder=''" onblur="this.placeholder='请输入密码'">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" required lay-verify="required" placeholder="请确认密码"
                           autocomplete="off" class="layui-input" onfocus="this.placeholder=''" onblur="this.placeholder='请确认密码'">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal">注册</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%
    String error = request.getParameter("error");
    String status = request.getParameter("register");
    if ("0".equals(error)) {
        out.print("<script>layer.msg('密码错误!!');</script>");
    } else if ("1".equals(error)) {
        out.print("<script>layer.msg('查无此人!!');</script>");
    }
    if ("0".equals(status)) {
        out.print("<script>layer.msg('注册成功!!');</script>");
    } else if ("1".equals(status)) {
        out.print("<script>layer.msg('注册失败!!');</script>");
    }
%>
</body>
</html>
<script src="./layui/layui.js"></script>
<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
        });
    });

    function userRegister(){
        var $ = layui.$;
        layer.open({
            type: 1,
            title: '注册',
            area: ['360px', '360px'],
            skin: 'layui-layer-rim', //加上边框
            content:$("#userRegister").html()
        });
    }
</script>