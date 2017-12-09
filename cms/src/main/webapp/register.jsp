<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" %>
<%
    String path = request.getContextPath();
    //String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>

</head>
<body bgcolor="#e3e3e3">
<center>
    <%--<form action="/register" method="post" &lt;%&ndash;onSubmit="return isValid(this);"&ndash;%&gt;>--%>
    <!-- onSubmit事件会在表单中的确认按钮被点击时发生，也就是一旦被点击就会自动调用javascript代码判断是否符合标准-->
    <table>
        <caption>用户注册</caption>
        <tr>
            <td>用户名：</td>
            <td><input id="username" type="text" name="username"/></td>
        </tr>
        <tr>
            <td>密 码：</td>
            <td><input id="password" type="password" name="password"/></td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td><input id="repassword" type="password" name="repassword"/></td>
        </tr>
        <tr>
            <td><input type="submit" onclick="submitRegister()" value="注册"/></td>
            <td><input type="reset" value="重置"/></td>
        </tr>
    </table>
    <%--</form>--%>
</center>


<script type="text/javascript">
    /**
     * 检查表单元素的合理有效性
     */
    function isValid(form) {
        if (form.username.value === "") {
            alert("用户名不能为空");
            return false;
        }
        if (form.password.value !== form.repassword.value) {
            alert("两次输入的密码不同！");
            return false;
        }
        else if (form.password.value === "") {
            alert("用户密码不能为空！");
            return false;
        }
        else return true;
    }

    /**
     * 大写字母、小写字母、数字、特殊字符 满足其中三种即可
     */
    function checkStrong(value) {
        var strength = 0;
        if (value.length > 5 && value.match(/[\da-zA-Z]+/)) {
            if (value.match(/\d+/)) {
                strength++;
            }
            if (value.match(/[a-z]+/)) {
                strength++;
            }
            if (value.match(/[A-Z]+/)) {
                strength++;
            }
            if (value.match(/[!@*$-_()+=&￥]+/)) {
                strength++;
            }
        }
        return strength >= 3;
    }

    /**
     * 提交register post请求
     */
    function submitRegister() {
        var username = $("#username").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        if (username === "" || password === "" || repassword === "") {
            alert("用户名或者密码为空！");
            return;
        } else if (password !== repassword) {
            alert("两次输入的密码不同！");
            return;
        }
        if (!checkStrong(password)) {
            alert("密码设置强度不够！建议大小写字母数字及特殊符号混杂！");
            return;
        }
        $.ajax({
            url: "/register",
            type: "POST",
            data: JSON.stringify({
                "username": username,
                "userpassword": password
            }),
            timeout: 100000,
            contentType: "application/json",
            success: function (res) {
                console.log(res);
                window.location.href = "login.jsp";
            }
        });
    }

</script>
</body>
</html>