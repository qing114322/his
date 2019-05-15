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
            $("#menuName").blur(function () {
                var menuName = $(this).val();
                $.get("${path}/doctorMenu/ajaxMenuName.action",{menuName:menuName},function (data) {
                    if (data.flag){
                        $("#info").html("套餐已存在").css("color","red");
                    }else{
                        $("#info").html("套餐可以添加").css("color","green");
                    }
                    var info=$("#info").html();
                        $("form").submit(function () {
                            if (info == "套餐已存在") {
                                alert("您输入的信息有误！")
                                return false;
                            } else {
                                return true;
                            }
                        })
                },"json")



            })
        })
    </script>
</head> 

<body class="main"> 
<form action="${path}/doctorMenu/insert.action" onsubmit="return check()">
    <h1>添加套餐管理</h1> 
    <div class="update"> 
        <div class="left"> 
            <span>套餐名称</span> 
            <input type="text" name="menuName" value="" id="menuName">
        </div>
        <div id="info"> </div>
        <%--<div class="right"> --%>
            <%--<span>用户</span> --%>
            <input type="hidden" name="userId" value="${sessionUser.userId}">
        <%--</div> --%>
        <div class="left"> 
            <span>套餐描述</span> 
            <input type="text" name="description" value=""> 
        </div> 
        <div class="right"> 
            <span>套餐类型</span> 
            <%--<input type="text" name="type" value=""> --%>
            <select id="type" name="type">
                <option value="1">套餐仅自己可用</option>
                <option value="2">套餐全科室都可用</option>
                <option value="3">套餐所有医生都可用 </option>
            </select>
        </div> 
        <div id="error"></div> 
        <div class="buttons"> 
            <input type="submit" value="提交" id="submit">
            <input type="button" onclick="history.back()" value="返回"> 
        </div> 
    </div> 
</form> 
</body> 
</html> 
 
