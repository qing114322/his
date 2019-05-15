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
    <%
        String menuId = request.getParameter("menuId");
        request.setAttribute("menuId",menuId);
        System.out.println(menuId);
    %>
    <script>
        $(function () {
            <%--alert(${menuId})--%>
        })
    </script>
</head> 

<body class="main"> 
<form action="${path}/doctorMenuMedicine/insert.action"> 
    <h1>添加套餐-药品表</h1> 
    <div class="update"> 
        <div class="left"> 
            <span>套餐</span>
            <input type="text" name="menuId" value="${menuId}" readonly>
        </div> 
        <div class="right"> 
            <span>药品</span> 
            <input type="text" name="codeId" value=""> 
        </div> 
        <div class="left"> 
            <span>数量</span> 
            <input type="text" name="amt" value=""> 
        </div> 
        <div class="right"> 
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
 
