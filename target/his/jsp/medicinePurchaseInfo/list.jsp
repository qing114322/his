<%-- 
  Created by IntelliJ IDEA. 
  2018/12/03 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () { 
            $("#addBtn").click(function () { 
                location.href="${path}/jsp/medicinePurchaseInfo/add.jsp";
             })
            $(".update").click(function () { 
                var pchId = $(this).attr("name");
                location.href="${path}/medicinePurchaseInfo/load.action?pchId="+pchId;
             })
            $(".delete").click(function () { 
                var pchId = $(this).attr("name");
                location.href="${path}/medicinePurchaseInfo/delete.action?pchId="+pchId;
             })
            $("#search").click(function () { 
                var medicineCodeid = $("#medicineCodeid").val();
                location.href = "${path}/medicinePurchaseInfo/search.action?medicineCodeid="+medicineCodeid;
             })
        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search"> 
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
            <td>序号</td> 
            <td>药品</td> 
            <td>供应商</td> 
            <td>采购数量</td> 
            <td>采购单价</td> 
            <td>采购总价</td>
            <td>状态</td>
            <td>汇总人</td>
            <td>汇总日期</td>
            <td>审批人</td> 
            <td>审批日期</td> 
            <td>编辑</td> 
            <td>删除</td> 
        </thead> 
        <c:forEach items="${list}" var="medicineCode" varStatus="status">
            <tr> 
                <td>${status.index+1}</td>
                <td>${medicineCode.medicineName}</td>
                <td>${medicineCode.manChnName}</td>
                <td>${medicineCode.sumreqamt}</td>
                <td>${medicineCode.retailPrice}</td>
                <td>${medicineCode.sumtotal}</td>
                <c:choose>
                    <c:when test="${medicineCode.medicineReqPlan.status==1}">
                        <td>未审批</td>
                    </c:when>
                    <c:when test="${medicineCode.medicineReqPlan.status==2}">
                        <td>已审批</td>
                    </c:when>
                    <c:when test="${medicineCode.medicineReqPlan.status==3}">
                        <td>已汇总</td>
                    </c:when>
                    <c:when test="${medicineCode.medicineReqPlan.status==4}">
                        <td>已采购</td>
                    </c:when>
                </c:choose>
                <td>${medicineCode.baseUser.cname}</td>
                <td>${medicineCode.medicineReqPlan.apprvDate}</td>
                <td>${medicineCode.baseUser.cname}</td>
                <td>${medicineCode.medicineReqPlan.apprvDate}</td>
                <td><img src="${path}/images/edit.gif" class="update" name="${medicineCode.codeId}"></td>
                <td><img src="${path}/images/del.gif" class="delete" name="${medicineCode.codeId}"></td>
            </tr> 
        </c:forEach> 
    </table> 
</body> 
</html> 
