<%-- 
  Created by IntelliJ IDEA. 
  2018/12/04 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <title>Title</title>
</head>
<%
    String cid=request.getParameter("cid");
    request.setAttribute("cid",cid);
%>
<script>
    $(function () {
        $.get("${path}/telvisit/ajaxList.action",function (data) {
            var $visitreason=$("#visitreason")
            var $visittype=$("#visittype")
            $(data).each(function () {
                var $option=$("<option value='"+this.visitreason+"'>"+this.visitreason+"</option>")
                var $option1=$("<option value='"+this.visittype+"'>"+this.visittype+"</option>")
                $option.appendTo($visitreason)
                $option1.appendTo($visittype)
            })
        },"json")
    })
</script>

<body class="main"> 
<form action="${path}/telvisit/insert.action"> 
    <h1>添加回访信息</h1>

    <div class="update"> 
        <div class="left"> 
            <span>回访人姓名</span> 
            <input type="text" name="telname" value=""> 
        </div> 
        <div class="right"> 
            <span>回访时间</span> 
            <input type="date" name="teltime" value=""> 
        </div> 
        <div class="left"> 
            <span>回访分类</span> 
            <%--<input type="text" name="visitreason" value=""> --%>
            <select name="visitreason" id="visitreason">

            </select>
        </div> 
        <div class="right"> 
            <span>回访结果</span> 
            <input type="text" name="visitreturn" value=""> 
        </div> 
        <div class="left"> 
            <span>回访方式</span> 
            <%--<input type="text" name="visittype" value=""> --%>
            <select name="visittype" id="visittype">

            </select>
        </div> 
        <div class="right"> 
            <span>所属公司编号</span> 
            <input type="text" name="cid" value="${cid}" readonly="readonly">
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
 
