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
    <script>
        $(function () {
            //未办卡用户下拉框
            var $select =$("#patientId");
            $.get("${path}/basePatientInfo/ajaxName.action",function (data) {
                $(data).each(function () {
                    var $option=$("<option value='"+this.patientId+"'>"+this.patientName+"</option>");
                    $option.appendTo($select);
                })
            },"json")
            //部门id下拉框
            var deptId=$("#deptId");
            $.get("${path}/baseDept/ajaxList.action",function (data) {
                $(data).each(function () {
                    var $option=$("<option value='"+this.deptId+"'>"+this.deptName+"</option>");
                    $option.appendTo(deptId);
                })
            },"json")
        })
    </script>
</head> 

<body class="main"> 
<form action="${path}/patientRegisterRecord/insert.action"> 
    <h1>添加办理就诊卡</h1> 
    <div class="update"> 
        <div class="left"> 
            <span>未办卡病人</span>
            <%--<input type="text" name="patientId" value=""> --%>
            <select id="patientId" name="patientId">

            </select>
        </div> 
        <div class="right"> 
            <span>部门</span> 
            <%--<input type="text" name="deptId" value=""> --%>
            <select id="deptId" name="deptId">

            </select>
        </div> 
        <%--<div class="left"> --%>
            <%--<span>挂号时间</span> --%>
            <input type="hidden" name="registerDate" value="">
        <%--</div> --%>
        <%--<div class="right"> --%>
            <%--<span>挂号人</span> --%>
            <input type="hidden" name="recordUser" value="">
        <%--</div> --%>
        <%--<div class="left"> --%>
            <%--<span>医生</span> --%>
            <input type="hidden" name="doctorId" value="">
        <%--</div> --%>
        <div class="right"> 
            <span>状态</span> 
            <%--<input type="text" name="status" value=""> --%>
            <select name="status">
                <option value="1">未挂号</option>
                <option value="2">已挂号</option>
            </select>
        </div> 
        <div id="error"></div> 
        <div class="buttons"> 
            <input type="submit" value="提交"> 
            <input type="button" onclick="history.back()" value="返回"> 
        </div> 
    </div> 
</form> 
</body> 
</html> 
 
