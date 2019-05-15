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
            location.href="${path}/jsp/baseFunction/add.jsp";
        })
        $("#search").click(function () {
            var fname=$("#fname").val();
            var mid=$("#mname").val();
            location.href="${path}/baseFunction/search.action?fname="+fname+"&mid="+mid;
        })
        $(".edit").click(function () {
            var fid=$(this).attr("name");
            location.href="${path}/baseFunction/update.action?fid="+fid;
        })
        $(".del").click(function () {
            var fid=$(this).attr("name");
            location.href="${path}/baseFunction/delete.action?fid="+fid;
        })

    })
</script>
<body class="main">
<div class="search">
    <span>
            功能名称：<input type="text" id="fname" value="${searchObject.fname}">
    </span>
    <span>
            模块名称：<input type="text" id="mname" value="${searchObject.mid}">
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
    <td>功能名称</td>
    <td>模块名称</td>
    <td>url</td>
    <td>修改</td>
    <td>删除</td>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="l" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${l.fname}</td>
            <td>${l.baseModule.mname}</td>
            <td>${l.url}</td>
            <td><img src="${path}/images/edit.gif" name="${l.fid}" class="edit"></td>
            <td><img src="${path}/images/del.gif" name="${l.fid}" class="del"></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
