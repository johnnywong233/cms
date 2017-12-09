<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户登录</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
</head>
<body bgcolor="#e3e3e3">
<center>
    <%--<form id="login" action="${pageContext.request.contextPath}/login" method="post">--%>
    <table>
        <caption>用户登录</caption>
        <tr>
            <td>用户名：</td>
            <td><input id="username" type="text" name="username" size="20"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input id="password" type="password" name="pwd" size="21"/></td>
        </tr>
        <tr>
            <td><input type="submit" onclick="submitLogin()" value="登录"/></td>
            <td><input type="reset" value="重置"/></td>
        </tr>
    </table>
    <%--</form>--%>
    如果您还没有注册，请单击<a href="register.jsp">这里</a>注册！
</center>

<script type="text/javascript">

    function submitLogin() {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username === "" || password === "") {
            alert("用户名或者密码为空！");
        }
        $.ajax({
            url: "/login",
            type: "POST",
            data: JSON.stringify({
                "username": username,
                "userpassword": password
            }),
            timeout: 100000,
            contentType: "application/json",
            success: function (res) {
                if (res) {
                    console.log(res);
                    window.location.href = "index.jsp";
                } else {
                    alert('An error has occurred!! :-(');
                }
            }
        });
    }
</script>
</body>
</html>