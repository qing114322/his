<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/3 0003
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <style>
        span{
            display: inline-block;
            width: 30%;
        }
        input[type='text'],input[type='datetime']{
            margin-top: 10px;
        }
    </style>
    <script>
        $(function () {
            $("#oldPassword").blur(function () {
                var oldPassword =$("#oldPassword").val();
                $.get("${path}/baseUser/updatePassword.action",{password:oldPassword,userId:"${sessionUser.userId}"},function (data) {
                    if(data=='T'){
                        $("#info").html("");
                    }else {
                        $("#info").html("您输入的密码有误！").css("color","red");
                    }
                })
            })
            $("#repeatPassword").blur(function () {
                var password =$("#password").val();
                var repeatPassword =$("#repeatPassword").val();
                if(password!=repeatPassword){
                    $("#info1").html("您两次输入的密码不同！").css("color","red");
                }
            })
            $("#submit").click(function () {
                var oldPassword =$("#oldPassword").val();
                var password =$("#password").val();
                var repeatPassword =$("#repeatPassword").val();
                var info= $("#info").html();
                var info1= $("#info1").html();
                if(!oldPassword ||!password || !repeatPassword || info=="" || info1==""){
                    $.get("${path}/baseUser/newPassword.action",{password:password,userId:"${sessionUser.userId}"},function (data) {
                        alert("修改成功！");
                        window.close();
                       window.opener.location.href="${path}/jsp/baseUser/login.jsp"
                    })
                }
            })
        })
    </script>
</head>

<body class="main">
    <h1>修改密码</h1>
    <%--<form action="${path}/baseUser/updatePassword.action">--%>
        <div class="update">
            <div class="left">
                <span>请输入旧密码</span>
                <input type="text" id="oldPassword">
            </div>
            <div class="right">
                <span id="info"></span>
            </div>
            <div class="left">
                <span>新密码</span>
                <input type="password" name="password" id="password">
            </div>
            <div class="right">
            </div>
            <div class="left">
                <span>确认密码</span>
                <input type="password" id="repeatPassword">
            </div>
            <div class="right">
                <span id="info1"></span>
            </div>
            <div class="buttons">
                <input type="button" value="提交" id="submit">
                <input type="button" onclick="history.back()" value="返回">
            </div>
        </div>
    <%--</form>--%>
</body>

</html>
