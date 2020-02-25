<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>欢迎登录后台管理系统--珞樱善联</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="../js/jquery.js"></script>
    <script src="../js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });
    </script>

</head>

<body style="background-color:#1c77ac; background-image:url(../images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
<%
    String path = request.getContextPath();
%>
<%
    session.removeAttribute("User");
%>

<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a></li>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">

        <ul>
            <li><input id="username" name="" type="text" class="loginuser" value="admin"
                       onclick="JavaScript:this.value=''"/></li>
            <li><input id="password" name="" type="text" class="loginpwd" value="密码"
                       onclick="JavaScript:this.value=''"/></li>
            <li><input name="" type="button" class="loginbtn" value="登录"
                       onclick="login()"/>
                <label><input name="" type="checkbox" value="" checked="checked"/>记住密码</label>
                <label> <a href="#">忘记密码？</a></label></li>
        </ul>
    </div>
</div>


<div class="loginbm">版权所有 2020 珞樱善联</div>
<script>
    function login() {
        window.location.href = '/mainx';
        // var un = document.getElementById("username");//id获取元素
        // var pw = document.getElementById("password");//id获取元素
        // $.ajax({
        //     type: "GET",
        //     url: "/demand/verifyDemand?demandId=" + id + "&verify=DISAPPROVED",
        //     data: {
        //         username: un,
        //         password: pw
        //     },
        //     contentType: "application/json", //必须有
        //     success: function (jsonResult) {
        //         window.location.href = '/mainx';
        //     }
        // });
    }
</script>
</body>
</html>
