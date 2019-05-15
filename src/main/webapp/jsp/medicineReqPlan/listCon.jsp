<%--
  Created by IntelliJ IDEA.
  User: tp
  Date: 2018/12/5
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        $(function () {
            $("#addBtn").click(function () {
                location.href="${path}/jsp/medicineReqPlan/add.jsp";
            })
            $(".update").click(function () {
                var reqplnno = $(this).attr("name");
                location.href="${path}/medicineReqPlan/load.action?reqplnno="+reqplnno;
            })
            $(".delete").click(function () {
                var reqplnno = $(this).attr("name");
                location.href="${path}/medicineReqPlan/delete.action?reqplnno="+reqplnno;
            })
            $("#search").click(function () {
                var medicineCodeid = $("#medicineCodeid").val();
                location.href = "${path}/medicineReqPlan/search.action?medicineCodeid="+medicineCodeid;
            })
            $("#butn").click(function () {
                var apprvUserid=$("#apprvUserid").val();
                var reqplnno="";
                $(":checked").each(function () {
                    reqplnno+=$(this).val()+","
                })
                location.href = "${path}/medicineReqPlan/loadCon.action?reqplnno="+reqplnno+"&apprvUserid="+apprvUserid;
            })
        })
    </script>
</head>
<body class="main">
<div class="search">
       <span>
            <button id="butn">审批</button>
        </span>
    <input type="hidden" name="apprvUserid" id="apprvUserid" value="${sessionUser.userId}">
        <span>
            药品：<input type="text" id="medicineCodeid" value="${searchObject.medicineCodeid}">
        </span>
    <span>
            <button id="search">查询</button>
        </span>
    <span>
            <button id="addBtn">增加</button>
        </span>

</div>
<table>
    <thead>
    <td></td>
    <td>序号</td>
    <td>药品</td>
    <td>需求数量</td>
    <td>申请人</td>
    <td>申请日期</td>
    <td>用途</td>
    <td>状态</td>
    <%--<td>审批人</td>--%>
    <td>审批日期</td>
    <td>编辑</td>
    <td>删除</td>
    </thead>
    <c:forEach items="${list}" var="medicineReqPlan" varStatus="status">
        <tr>
            <c:if test="${medicineReqPlan.status==1}">
                <td><input type="checkbox" value="${medicineReqPlan.reqplnno}"></td>
            </c:if>
            <c:if test="${medicineReqPlan.status!=1}">
                <td></td>
            </c:if>
            <td>${status.index+1}</td>
            <td>${medicineReqPlan.medicineCode.medicineName}</td>
            <td>${medicineReqPlan.reqamt}</td>
            <td>${medicineReqPlan.baseUser.cname}</td>
            <td>${medicineReqPlan.appDate}</td>
            <td>${medicineReqPlan.purpose}</td>
            <c:choose>
                <c:when test="${medicineReqPlan.status==1}">
                    <td>未审批</td>
                </c:when>
                <c:when test="${medicineReqPlan.status==2}">
                    <td>已审批</td>
                </c:when>
                <c:when test="${medicineReqPlan.status==3}">
                    <td>已汇总</td>
                </c:when>
                <c:when test="${medicineReqPlan.status==4}">
                    <td>已入库</td>
                </c:when>
            </c:choose>
            <%--<td>${medicineReqPlan.baseUser.cname}</td>--%>
            <td>${medicineReqPlan.apprvDate}</td>
            <td><img src="${path}/images/edit.gif" class="update" name="${medicineReqPlan.reqplnno}"></td>
            <td><img src="${path}/images/del.gif" class="delete" name="${medicineReqPlan.reqplnno}"></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
