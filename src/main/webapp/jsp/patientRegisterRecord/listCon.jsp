<%--
  Created by IntelliJ IDEA.
  2018/12/21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <script type="text/javascript">
        $(function () {
            $(".update").click(function () {
                var registerId = $(this).attr("name");
                location.href="${path}/patientRegisterRecord/load.action?registerId="+registerId;
            })
            $(".delete").click(function () {
                var registerId = $(this).attr("name");
                location.href="${path}/patientRegisterRecord/delete.action?registerId="+registerId;
            })
            $("#search").click(function () {
                var patientId = $("#patientId").val();
                location.href = "${path}/patientRegisterRecord/search.action?patientId="+patientId;
            })
            $(".details").click(function () {
                var registerId = $(this).attr("name");
                location.href="${path}/jsp/patientRegisterRecord/details.jsp?registerId="+registerId;
            })
        })
    </script>
</head>
<body class="main">
<div class="search">
        <span>
            病人：<input type="text" id="patientId" value="${searchObject.patientId}">
        </span>
    <span>
            <button id="search">查询</button>
        </span>
</div>
<table>
    <thead>
    <td>序号</td>
    <td>病人</td>
    <td>部门</td>
    <td>挂号时间</td>
    <%--<td>挂号人</td> --%>
    <td>医生</td>
    <td>状态</td>
    <td>选择部门</td>
    <td>编辑</td>
    <td>删除</td>
    </thead>
    <c:forEach items="${list}" var="patientRegisterRecord" varStatus="status">
        <tr>
            <td>${status.index+1}</td>
            <td>${patientRegisterRecord.basePatientInfo.patientName}</td>
            <td>${patientRegisterRecord.baseDept.deptName}</td>
                <td>${patientRegisterRecord.registerDate}</td>
                <%--<td>${patientRegisterRecord.recordUser}</td> --%>
                <td>${patientRegisterRecord.doctorId}</td>
            <c:choose>
                <c:when test="${patientRegisterRecord.status==1}"><td>未办卡</td></c:when>
                <c:when test="${patientRegisterRecord.status==2}"><td>已办卡</td></c:when>
                <c:when test="${patientRegisterRecord.status==3}"><td>已挂号</td></c:when>
            </c:choose>
            <%--<td><select>--%>
                <%--<option>1</option>--%>
                <%--<option>2</option>--%>
            <%--</select></td>--%>
            <td><img src="${path}/images/distribute.gif" name="${patientRegisterRecord.registerId}" class="details"></td>
            <td><img src="${path}/images/edit.gif" class="update" name="${patientRegisterRecord.registerId}"></td>
            <td><img src="${path}/images/del.gif" class="delete" name="${patientRegisterRecord.registerId}"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
