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
            location.href="${path}/jsp/baseRole/add.jsp";
        })
        <%--$("#search").click(function () {--%>
            <%--var fname=$("#fname").val();--%>
            <%--var mid=$("#mname").val();--%>
            <%--location.href="${path}/baseFunction/search.action?fname="+fname+"&mid="+mid;--%>
        <%--})--%>
        $(".edit").click(function () {
            var rid=$(this).attr("name");
            location.href="${path}/baseRole/update.action?rid="+rid;
        })
        $(".del").click(function () {
            var rid=$(this).attr("name");
            location.href="${path}/baseRole/delete.action?rid="+rid;
        })
        $(".details").click(function () {
            var rid=$(this).attr("name");
            location.href="${path}/baseRole/details.action?rid="+rid;
        })

    })
</script>
<body class="main">
<div class="search">
    <span>
             角色id：<input type="text" id="rid">
    </span>
    <span>
           角色名称：<input type="text" id="rname">
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
    <td>角色id</td>
    <td>角色名称</td>
    <td>修改</td>
    <td>删除</td>
    <td>角色管理</td>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="l" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${l.rname}</td>
            <td><img src="${path}/images/edit.gif" name="${l.rid}" class="edit"></td>
            <td><img src="${path}/images/del.gif" name="${l.rid}" class="del"></td>
            <td><img src="${path}/images/distribute.gif" name="${l.rid}" class="details"></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
