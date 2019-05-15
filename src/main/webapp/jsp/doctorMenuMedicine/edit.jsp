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
</head> 

<body class="main"> 
<form action="${path}/doctorMenuMedicine/update.action"> 
    <h1>修改套餐-药品表</h1> 
    <input type="hidden" name="mdId" value="${doctorMenuMedicine.mdId}"> 
    <div class="update"> 
        <div class="left"> 
            <span>套餐</span> 
            <input type="text" name="menuId" value="${doctorMenuMedicine.menuId}"> 
        </div> 
        <div class="right"> 
            <span>药品</span> 
            <input type="text" name="codeId" value="${doctorMenuMedicine.codeId}"> 
        </div> 
        <div class="left"> 
            <span>数量</span> 
            <input type="text" name="amt" value="${doctorMenuMedicine.amt}"> 
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
 
