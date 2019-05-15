<%--
  Created by IntelliJ IDEA.
  User: 联想88
  Date: 2018/11/28
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<style>
    /*职务选定*/
    .baseUserRole div{
        height: 50%;
        float: left;
        margin: 20px;
    }
    .baseUserRole select{
        height: 400px;
    }

    .roleRight span{
        text-align: center;
        line-height: 30px;
        display: block;
        background-color: #CCDDFF;
        margin-top: 20px;

    }
    .roleMiddle button{
        margin: 10px;
    }
    .roleMiddle{
        padding-top: 150px;
    }
    .roleLeft span{
        text-align: center;
        line-height: 30px;
        display: block;
        background-color: #CCDDFF;
        margin-top: 20px;
    }

    .baseRoleFunction{
        margin-left: 10%;
        margin-top: 10%;
    }

</style>
<head>
    <title>baseUserRole</title>
    <script>

        $(function () {
            $("#removeRaseUserRole").click(function () {
                $(":checked").each(function () {
                    var rid=$(this).val();
                    alert(rid)
                    location.href="${path}/baseUserRole/checkox.action?rid="+rid;
                })
            })
        })

    </script>
</head>
<body class="main">

    <div class="baseUserRole">
        <div class="roleLeft">
            <h4>已拥有的职业</h4>
            <%--<select name="" id="AlreadyOwned" multiple="multiple">--%>
                <%----%>
            <%--</select>--%>
            <c:forEach items="${list}" var="i">
                <input type="checkbox" value="${i.baseRole.rid}">
                <span><a href="#">${i.baseRole.rname}</a></span>
            </c:forEach>
        </div>

        <div class="roleMiddle">
            <button id="removeRaseUserRole">→</button>
            <br/>
            <button id="addRaseUserRole">←</button>
            <br/>
            <%--<button id="removeAllRaseUserRole">→→</button>--%>
            <%--<br/>--%>
            <%--<button id="addAllRaseUserRole">←←</button>--%>
        </div>

        <div class="roleRight">
            <h4>未拥有的职业</h4>
            <c:forEach items="${list1}" var="i">
                <input type="checkbox">
                <span><a href="#">${i.baseRole.rname}</a></span>
            </c:forEach>

            <%--<select name="" id="ForPossession" multiple="multiple">--%>

            <%--</select>--%>
        </div>

        <center>
            <button id="close" style="margin-left: 70%">关闭</button>
        </center>
    </div>

</body>

</html>
