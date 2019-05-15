<%--
  Created by IntelliJ IDEA.
  User: tp
  Date: 2018/11/29
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>详情</title>
</head>
<body>
<div style="margin-left: 100px;margin-top: 100px">
    <div>
        <h4>已拥有的职业</h4>
        <c:forEach items="${list}" var="i">
        <span>${i.baseRole.rname}</span>
        </c:forEach>
        <h4>未拥有的职业</h4>
        <c:forEach items="${list1}" var="i">
            <span>${i.baseRole.rname}</span>
        </c:forEach>
    </div>
</div>

</body>
</html>
