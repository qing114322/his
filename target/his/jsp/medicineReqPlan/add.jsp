<%-- 
  Created by IntelliJ IDEA. 
  2018/12/05 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <title>Title</title>
</head>
<script>
    $(function () {
        $.get("${path}/medicineCode/ajaxList.action",function (data) {
            var $medicineCodeid =$("#medicineCodeid")
            $(data).each(function () {
                var $option=$("<option value='"+this.codeId+"'>"+this.medicineName+"</option>")
                $option.appendTo($medicineCodeid);
            })
        },"json")
    })
</script>

<body class="main"> 
<form action="${path}/medicineReqPlan/insert.action"> 
    <h1>添加药品需求</h1> 
    <div class="update"> 
        <div class="left"> 
            <span>药品</span> 
            <%--<input type="text" name="medicineCodeid" value=""> --%>
            <select name="medicineCodeid" id="medicineCodeid">

            </select>
        </div> 
        <div class="right"> 
            <span>需求数量</span> 
            <input type="text" name="reqamt" value=""> 
        </div> 
        <%--<div class="left"> --%>
            <%--<span>申请人</span> --%>
            <input type="hidden" name="appUserid" value="${sessionUser.userId}">
        <%--</div> --%>
        <%--<div class="right"> --%>
            <%--<span>申请日期</span> --%>
            <%--<input type="date" name="appDate" value=""> --%>
        <%--</div> --%>
        <div class="left"> 
            <span>用途</span> 
            <input type="text" name="purpose" value="">
        </div> 
        <%--<div class="right"> --%>
            <%--<span>状态</span> --%>
            <%--<input type="text" name="status" value=""> --%>
        <%--</div> --%>
        <%--<div class="left">--%>
            <%--<span>审批人</span>--%>
            <%--<input type="text" name="apprvUserid" value="">--%>
        <%--</div>--%>
        <%--<div class="right">--%>
            <%--<span>审批日期</span>--%>
            <%--<input type="date" name="apprvDate" value="">--%>
        <%--</div>--%>
        <%--<div id="error"></div> --%>
        <div class="buttons"> 
            <input type="submit" value="提交"> 
            <input type="button" onclick="history.back()" value="返回"> 
        </div> 
    </div> 
</form> 
</body> 
</html> 
 
