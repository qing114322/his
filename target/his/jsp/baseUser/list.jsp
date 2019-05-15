<%--
  Created by IntelliJ IDEA.
  User: tp
  Date: 2018/11/26
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<script>
    $(function () {
        $("#addBtn").click(function () {
            location.href="add.jsp";
        })
        $("#search").click(function () {
            var cname=$("#cname").val();
            var sex=$("#sex").val();
            location.href="${path}/baseUser/search.action?cname="+cname+"&sex="+sex;
        })
        $(".edit").click(function () {
            var userId=$(this).attr("name");
            location.href="${path}/baseUser/update.action?userId="+userId;
        })
        $(".del").click(function () {
            var userId=$(this).attr("name");
            location.href="${path}/baseUser/delete.action?userId="+userId;
        })
        $(".details").click(function () {
            var userId=$(this).attr("name");
            location.href="${path}/baseUser/details.action?userId="+userId;
        })

    })
</script>
<body class="main">
<div class="search">
        <span>
            姓名：<input type="text" id="cname" value="${searchObject.cname}">
        </span>
    <span>
            性别：<input type="text" id="sex" value="${searchObject.sex}">
        </span>
    <span>
            <button id="search">查询</button>
        </span>
    <span>
            <button id="addBtn">增加</button>
        </span>
</div>
<table >
    <thead>
    <td>序号</td>
    <td>用户名</td>
    <td>姓名</td>
    <td>性别</td>
    <td>所属部门</td>
    <td>职务管理</td>
    <td>修改</td>
    <td>删除</td>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="l" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${l.userName}</td>
            <td>${l.cname}</td>
            <td>${l.sex}</td>
            <td>${l.baseDept.deptName}</td>
            <td><img src="${path}/images/distribute.gif" name="${l.userId}" class="details"></td>
            <td><img src="${path}/images/edit.gif" name="${l.userId}" class="edit"></td>
            <td><img src="${path}/images/del.gif" name="${l.userId}" class="del"></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
