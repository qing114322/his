<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/29 0029
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>下拉框</title>
    <style type="text/css">
        body{
            background-color: #d7ff45;
        }
        select{
            width: 150px;
            height: 350px;
            padding: 10px;
            font-size: 10px;
            background-color: #bfbf2b;color: white;
        }
        select option{
            height: 30px;
            line-height: 30px;
            vertical-align: middle;
            padding-left: 10px;
            padding-top: 10px;
        }
        .title{
            display: inline-block;
            width: 45%;
            font-size: 30px;text-align: center;
            padding: 20px 0px;
        }
        #left{
            margin-left: 100px;
        }
        #right{
            margin-left: 20px;
        }
        .buttons{
            display: inline-block;height: 350px;width: 200px;
            vertical-align: top;
            text-align: center;
        }
        .buttons button{
            text-align: center;
            width: 160px;
            height: 40px;
            border-radius: 5px;
            margin-top: 20px;
            background-color: #a440ec;color: white;border: 0px;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            var $left=$("#left");
            var $right=$("#right");
            $.ajax({
                type: "GET",
                url: "${path}/patientRegisterRecord/ajaxListLeft.action",
                async: false,
                dataType:"json",
                success: function (data) {
                    $(data).each(function () {
                        var $option=$("<option value='"+this.patientId+"'>"+this.patientName+"</option>")
                        $option.appendTo($left)
                    })

                }
            });
            $.ajax({
                type: "GET",
                url: "${path}/patientRegisterRecord/ajaxListRight.action",
                async: false,
                dataType:"json",
                success: function (data) {
                    $(data).each(function () {
                        var $option=$("<option value='"+this.patientId+"'>"+this.basePatientInfo.patientName+"</option>")
                        $option.appendTo($right)
                    })

                }
            });


                <%--$.get("${path}/patientRegisterRecord/ajaxListLeft.action","async: false",function (data) {--%>
                    <%--alert(data)--%>
                    <%--$(data).each(function () {--%>
                        <%--var $option=$("<option value='"+this.patientId+"'>"+this.basePatientInfo.patientName+"</option>")--%>
                        <%--$option.appendTo($left)--%>
                    <%--})--%>
                <%--},"json")--%>


                <%--$.get("${path}/patientRegisterRecord/ajaxListRight.action",function (data) {--%>
                    <%--alert(data)--%>
                    <%--$(data).each(function () {--%>
                        <%--var $option=$("<option value='"+this.patientId+"'>"+this.basePatientInfo.patientName+"</option>")--%>
                        <%--$option.appendTo($right)--%>
                    <%--})--%>
                <%--},"json")--%>

            $(".buttons button:eq(0)").click(function(){
                var $selected = $("#left option:selected");
                $selected.remove();
                var $right = $("#right");
                $selected.appendTo($right);
            })
            $(".buttons button:eq(1)").click(function(){
                var $selected = $("#left option");
                $selected.remove();
                var $right = $("#right");
                $selected.appendTo($right);
            })
            $(".buttons button:eq(2)").click(function(){
                var $selected = $("#right option:selected");
                $selected.remove();
                var $left = $("#left");
                $selected.appendTo($left);
            })
            $(".buttons button:eq(3)").click(function(){
                var $selected = $("#right option");
                $selected.remove();
                var $left = $("#left");
                $selected.appendTo($left);
            })
            $(".buttons button:eq(4)").click(function(){
                var $selected = $("#right option");
                var patientId="";
                $("#right option").each(function () {
                    patientId+=$(this).val()+",";
                })
                location.href= "${path}/patientRegisterRecord/distributeUpdate.action?patientId="+patientId+"";
            })


        })

    </script>
</head>
<body>

<div>
    <span class="title">未办卡用户</span>
    <span class="title">已办卡用户</span>
</div>
<select multiple="multiple" id="left" >
    <%--<c:forEach items="${list1}" var="i">--%>
        <%--<option value="${i.rid}" ><a href="#">${i.rname}</a></option>--%>
        <%--&lt;%&ndash;<span><a href="#">${i.baseRole.rname}</a></span>&ndash;%&gt;--%>
    <%--</c:forEach>--%>
    <%--<option value="1">医生</option>--%>
</select>
<div class="buttons">
    <button>></button>
    <button>>></button>
    <button><</button>
    <button><<</button>
    <button>提交</button>
</div>
<select multiple="multiple" id="right" >
    <%--<c:forEach items="${list}" var="i">--%>
        <%--<option value="${i.rid}"><a href="#">${i.rname}</a></option>--%>
        <%--&lt;%&ndash;<span><a href="#">${i.baseRole.rname}</a></span>&ndash;%&gt;--%>
    <%--</c:forEach>--%>
    <%--<option value="8">药房主管</option>--%>
</select>

</body>
</html>
