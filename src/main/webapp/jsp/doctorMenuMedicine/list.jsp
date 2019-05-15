<%-- 
  Created by IntelliJ IDEA. 
  2018/12/19 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () { 
            $("#addBtn").click(function () {
                var menuId1 = $("#menuId1").val();
                location.href="${path}/jsp/doctorMenuMedicine/add.jsp?menuId="+menuId1;
             })
            $(".update").click(function () {
                var menuId1 = $("#menuId1").val();
                var mdId = $(this).attr("name");
                location.href="${path}/doctorMenuMedicine/load.action?mdId="+mdId+"&menuId="+menuId1;
             })
            $(".delete").click(function () { 
                var mdId = $(this).attr("name");
                location.href="${path}/doctorMenuMedicine/delete.action?mdId="+mdId;
             })
            $("#search").click(function () { 
                var menuId = $("#menuId").val();
                location.href = "${path}/doctorMenuMedicine/search.action?menuId="+menuId;
             })
            $("#searchAll").click(function () {
                location.href = "${path}/doctorMenuMedicine/list.action";
            })

        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search"> 
        <span> 
            套餐：<input type="text" id="menuId" value="${searchObject.menuId}"> 
        </span>
        <input type="hidden" value="${menuId}" id="menuId1">

    <%--<input type="hidden" id="menuId1" value="${doctorMenuMedicine.menuId}">--%>
        <span> 
            <button id="search">查询</button> 
        </span>
        <span>
            <button id="searchAll">查询所有套餐信息</button>
        </span>
        <span> 
            <button id="addBtn">增加</button> 
        </span> 
    </div> 
    <table> 
        <thead> 
            <td>序号</td> 
            <td>套餐</td> 
            <td>药品</td> 
            <td>数量</td> 
            <td>编辑</td> 
            <td>删除</td> 
        </thead> 
        <c:forEach items="${list}" var="doctorMenuMedicine" varStatus="status"> 
            <tr>
                <td>${status.index+1}</td>
                <td>${doctorMenuMedicine.doctorMenu.menuName}</td>
                <td>${doctorMenuMedicine.medicineCode.medicineName}</td>
                <td>${doctorMenuMedicine.amt}</td> 
                <td><img src="${path}/images/edit.gif" class="update" name="${doctorMenuMedicine.mdId}"></td> 
                <td><img src="${path}/images/del.gif" class="delete" name="${doctorMenuMedicine.mdId}"></td> 
            </tr> 
        </c:forEach> 
    </table> 
</body> 
</html> 
