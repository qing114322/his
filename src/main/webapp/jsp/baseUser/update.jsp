<%@ page import="com.qhit.baseUser.pojo.BaseUser" %><%--
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
    <script>
        $(function () {
            var $keshi=$("#keshi")
            $.get("${path}/baseDept/ajaxList.action",function (data) {
                $(data).each(function () {
                    var $option=$("<option value='"+this.deptId+"'>"+this.deptName+"</option>")
                    $option.appendTo($keshi);
                })
            },"json")
        })
    </script>

</head>

<body class="main">
<h1>修改用户</h1>
<form action="${path}/baseUser/update1.action">
    <input type="hidden" name="userId" value="${baseUser.userId}">
    <div class="update">
        <div class="left">
            <span>用户名</span>
            <input type="text" name="userName" value="${baseUser.userName}">
        </div>
        <div class="right">
            <span>密码</span>
            <input type="text" name="password" value="${baseUser.password}">
        </div>
        <div class="left">
            <span>姓名</span>
            <input type="text" name="cname" value="${baseUser.cname}">
        </div>
        <div class="right">
            <span>性别</span>
            <input type="text" name="sex" value="${baseUser.sex}">
        </div>
        <div id="error"></div>
        <div class="right">
            <span>所在科室</span>
            <%--<input type="text" name="deptId">--%>
            <select id="keshi" name="deptId">

            </select>
        </div>
        <div id="ke"></div>
        <div class="buttons">
            <input type="submit" value="提交">
            <input type="button" onclick="history.back()" value="返回">
        </div>
    </div>
</form>
</body>

</html>
