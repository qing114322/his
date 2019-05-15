<%--
  Created by IntelliJ IDEA.
  2018/12/21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <%
        String registerId = request.getParameter("registerId");
        request.setAttribute("registerId",registerId);
    %>
    <script>
        $(function () {
            //部门信息下拉框
            var deptId =$("#deptId");
            $.ajax({
                type: "GET",
                url: "${path}/baseDept/ajaxList.action",
                async: false,
                dataType:"json",
                success: function (data) {
                    var option=$("<option></option>")
                    option.appendTo(deptId)
                    $(data).each(function () {
                        var $option=$("<option value='"+this.deptId+"'>"+this.deptName+"</option>")
                        $option.appendTo(deptId)
                    })

                }
            });

           $("#deptId").change(function () {
               var deptId =$(this).val();
               //医生信息下拉框
               var doctorId=$("#doctorId");
               $.ajax({
                   type: "GET",
                   url: "${path}/baseUser/ajaxList.action",
                   async: false,
                   data: {deptId:deptId},
                   dataType:"json",
                   success: function (data) {
                       doctorId.empty();
                       var option=$("<option></option>")
                       option.appendTo(doctorId)

                       $(data).each(function () {
                           var $option=$("<option value='"+this.userId+"'>"+this.cname+"</option>")
                           $option.appendTo(doctorId)
                       })

                   }

               });

           })

        })
    </script>
</head>

<body class="main">
<form action="${path}/patientRegisterRecord/updateCon.action">
    <h1>选择挂号部门</h1>
    <input type="hidden" name="registerId" value="${registerId}">
    <div class="update">
        <div class="left">
            <span>病人</span>
            <input type="text" name="patientId" value="${registerId}">
        </div>
        <div class="right">
            <span>部门</span>
            <%--<input type="text" name="deptId" value="${patientRegisterRecord.deptId}">--%>
            <select id="deptId" name="deptId">

            </select>
        </div>
        <div class="left">
            <span>医生</span>
            <%--<input type="text" name="doctorId" value="${patientRegisterRecord.doctorId}">--%>
            <select id="doctorId" name="doctorId">

            </select>
        </div>
        <%--<div class="left">--%>
            <%--<span>挂号时间</span>--%>
            <%--<input type="date" name="registerDate" value="${patientRegisterRecord.registerDate}">--%>
        <%--</div>--%>
        <%--<div class="right">--%>
            <%--<span>挂号人</span>--%>
            <%--<input type="text" name="recordUser" value="${patientRegisterRecord.recordUser}">--%>
        <%--</div>--%>

        <%--<div class="right">--%>
            <%--<span>状态</span>--%>
            <%--<input type="text" name="status" value="${patientRegisterRecord.status}">--%>
        <%--</div>--%>
        <div id="error"></div>
        <div class="buttons">
            <input type="submit" value="提交">
            <input type="button" onclick="history.back()" value="返回">
        </div>
    </div>
</form>
</body>
</html>

