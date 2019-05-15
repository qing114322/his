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
            location.href="${path}/jsp/baseModule/add.jsp";
        })
        $("#search").click(function () {
            var cname=$("#cname").val();
            var sex=$("#sex").val();
            location.href="${path}/baseModule/search.action?cname="+cname+"&sex="+sex;
        })
        $(".edit").click(function () {
            var mid=$(this).attr("name");
            location.href="${path}/baseModule/update.action?mid="+mid;
        })
        $(".del").click(function () {
            var mid=$(this).attr("name");
            location.href="${path}/baseModule/delete.action?mid="+mid;
        })

    })
</script>
<body class="main">
<div class="search">
    <span>
            功能名称：<input type="text" id="cname" >
    </span>
    <span>
            模块名称：<input type="text" id="sex" >
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
    <td>模块名称</td>
    <td>修改</td>
    <td>删除</td>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="l" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${l.mname}</td>
            <td><img src="${path}/images/edit.gif" name="${l.mid}" class="edit"></td>
            <td><img src="${path}/images/del.gif" name="${l.mid}" class="del"></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
