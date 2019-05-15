<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/6
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <script>
        $(function () {
            var filename = "${filename}";
            var name = "${name}";
            window.opener.document.getElementById(name).value = filename;
            window.close();
        })
    </script>
</head>
<body>

</body>
</html>
