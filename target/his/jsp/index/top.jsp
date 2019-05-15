
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
<head>
    <script type="text/javascript">
        $(function () {
            $("#logout").click(function () {
                window.parent.location.href="${path}/baseUser/logout.action";
            })
            $("#updatePassword").click(function () {
                window.parent.open('${path}/jsp/baseUser/changePassword.jsp', '修改密码', 'height=500, width=500, top=200, left=400');
            })
            //头部
            $.get("${path}/baseModule/baseModuleToplist.action",function (data) {
                var $div = $(".page .nav");
                $(data).each(function () {
                    var $span=$('<a href="#" name="'+this.mid+'"  target="_self"><SPAN class=STYLE2>'+this.mname+'</SPAN></a>')
                    $span.appendTo($div);
                })
                $(".nav a").click(function () {
                    var mid=$(this).attr("name");
                    window.parent.leftFrame.location.href ="${path}/jsp/index/left.jsp?mid="+mid
                })
            },"json")
        })

    </script>
    <style>
        span{
            margin-left: 20px;
        }
    </style>
</head>
<body>
<div class="page">
        <div class="nav">
                <%--<a href="#"  target="_self"><SPAN class=STYLE2>收费管理</SPAN></a>--%>
                <%--<a href="#"  target="_self"><SPAN class=STYLE2>药房管理</SPAN></a>--%>
                <%--<a href="#"  target="_self"><SPAN class=STYLE2>医生工作站</SPAN></a>--%>
                <%--<a href="#"  target="_self"><SPAN class=STYLE2>基础信息</SPAN></a>--%>
                <%--<a href="#"  target="_self"><SPAN class=STYLE2>统计分析</SPAN></a>--%>
        </div>
</div>
<div class="userInfo">
   <span> 管理员:${sessionUser.cname}</span>  <span>${format}</span> <span><a href="#" id="logout">退出</a></span>
    <span><a href="#" id="updatePassword">修改密码</a></span>
</div>

</body>

</html>
