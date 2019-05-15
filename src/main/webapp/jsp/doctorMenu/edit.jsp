<%-- 
  Created by IntelliJ IDEA. 
  2018/12/19 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <title>Title</title>
    <script>
        $(function () {
            <%--var select=$("#type");--%>
            <%--$.get("${path}/doctorMenu/ajaxList.action",function (data) {--%>
                <%--var $option=$("<option>"+this.tpee+"</option>")--%>
            <%--},"json")--%>
            $("#type").val(${doctorMenu.type});
        })
    </script>
</head> 

<body class="main"> 
<form action="${path}/doctorMenu/update.action"> 
    <h1>修改套餐管理</h1> 
    <input type="hidden" name="menuId" value="${doctorMenu.menuId}"> 
    <div class="update"> 
        <div class="left"> 
            <span>套餐名称</span> 
            <input type="text" name="menuName" value="${doctorMenu.menuName}"> 
        </div> 
        <div class="right"> 
            <span>用户</span> 
            <input type="text" name="userId" value="${doctorMenu.userId}"> 
        </div> 
        <div class="left"> 
            <span>套餐描述</span> 
            <input type="text" name="description" value="${doctorMenu.description}"> 
        </div> 
        <div class="right"> 
            <span>套餐类型</span> 
            <%--<input type="text" name="type" value="${doctorMenu.type}"> --%>
            <select id="type" name="type">
                <option value="1">套餐仅自己可用</option>
                <option value="2">套餐全科室都可用</option>
                <option value="3">套餐所有医生都可用 </option>
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
 
