<%-- 
  Created by IntelliJ IDEA. 
  2018/12/08 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () { 
            $("#addBtn").click(function () { 
                location.href="${path}/jsp/medicineInstock/add.jsp";
             })
            $(".update").click(function () { 
                var instockId = $(this).attr("name");
                location.href="${path}/medicineInstock/load.action?instockId="+instockId;
             })
            $(".delete").click(function () { 
                var instockId = $(this).attr("name");
                location.href="${path}/medicineInstock/delete.action?instockId="+instockId;
             })
            $("#search").click(function () { 
                var invno = $("#invno").val();
                location.href = "${path}/medicineInstock/search.action?invno="+invno;
             })
            $("#btun").click(function () {
//                var instockId =$(".checbox:checked");
                //获取入库信息的id
                var instockId="";
                $(":checked").each(function () {
                    instockId+=$(this).val()+","
                })
                //获取发票号
                var invno="";
                $(".invno").each(function () {
                    invno+=$(this).val()+","
                });
                if(instockId!=''){
                    alert(invno)
                    alert(instockId)
                    location.href = "${path}/medicineInstock/insertCon.action?instockId="+instockId+"&invno="+invno;
                }else {
                    alert("入库的信息和发票号不能为空!")
                }
            })
            $("#btunt").click(function () {
                location.href="${path}/medicineStockinfo/listCon.action"
            })
        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search">
         <span>
            <button id="btunt">查看库存表</button>
        </span>
        <span>
            <button id="btun">入库</button>
        </span>
        <span> 
            发票号：<input type="text" id="invno" value="${searchObject.invno}"> 
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
            <td>入库数量</td> 
            <td>入库单价</td> 
            <td>入库总金额</td> 
            <td>入库人</td> 
            <td>入库日期</td> 
            <td>供应商</td>
            <td>发票号(必填)</td>
            <td>编辑</td> 
            <td>删除</td> 
        </thead> 
        <c:forEach items="${list}" var="medicineCode" varStatus="status">
            <tr>
                <td><input type="checkbox" value="${medicineCode.codeId}"></td>
                <td>${status.index+1}</td>
                <td>${medicineCode.medicineName}</td>
                <td>${medicineCode.sumreqamt}</td>
                <td>${medicineCode.retailPrice}</td>
                <td>${medicineCode.sumtotal}</td>
                <td>${medicineCode.baseUser.cname}</td>
                <td>${medicineCode.medicineInstock.instockDate}</td>
                <%--<td>${medicineCode.medicineReqPlan.apprvDate}</td>--%>
                <td>${medicineCode.manChnName}</td>
                <td><input type="text"  class="invno"></td>
                <td><img src="${path}/images/edit.gif" class="update" name="${medicineInstock.instockId}"></td> 
                <td><img src="${path}/images/del.gif" class="delete" name="${medicineInstock.instockId}"></td> 
            </tr> 
        </c:forEach> 
    </table> 
</body> 
</html> 
