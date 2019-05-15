<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/29 0029
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<%
    String mid= request.getParameter("mid");
    request.setAttribute("mid",mid);
%>
<head>
    <script type="text/javascript">
        $(function () {
            //左侧li目录
            $.get("${path}/baseFunction/baseFunctionLeftlist.action",{mid:"${mid}",userId:"${sessionUser.userId}"},function (data) {
                var $ul = $(".left_nav");
                $(data).each(function () {
                    var $li=$("<li name='"+this.url+"'>"+this.fname+"</li>")
                    $li.appendTo($ul);
                })
                $("li").click(function () {
                    $("li").removeClass("current");
                    $(this).toggleClass("current");
                    var url="${path}/"+$(this).attr("name")+".action";
                    window.parent.mainFrame.location.href = url;
                })
            },"json")

        })
    </script>
</head>
<body class="left">
<div class="page">
    <div class="margin_div">
        <div class="page_main">
            <ul class="left_nav">
                <%--<li class="current">用户管理</li>--%>
                <%--<li>部门管理</li>--%>
                <%--<li>岗位管理</li>--%>
                <%--<li id="baseCom">功能管理</li>--%>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
