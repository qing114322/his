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
            background-color: #f7ffea;
            margin-left: 100px;
        }
        select{
            width: 150px;
            height: 350px;
            padding: 10px;
            font-size: 10px;
            background-color: #f242ff;color: white;
        }
        select option{
            height: 30px;
            line-height: 30px;
            vertical-align: middle;
            padding-left: 10px;
            padding-top: 10px;
        }
        .title0{
            display: inline-block;
            font-size: 30px;
            padding: 20px 0px;
            margin-left: 20px;
        }
        .title1{
            display: inline-block;
            font-size: 30px;
            padding: 20px 0px;
            margin-left: -170px;
        }
        .title2{
            display: inline-block;
            font-size: 30px;
            padding: 20px 0px;
            margin-left: 100px;
            /*margin-top: -200px;*/
        }
        .title3{
            display: inline-block;
            font-size: 30px;
            padding: 20px 0px;
            margin-left: 200px;
            /*margin-top: -200px;*/
        }
        #left{
            margin-left: 100px;
        }
        #main{
            margin-left: 320px;
            margin-top: 100px;
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
            background-color: #8aec2c;color: white;border: 0px;
        }
        #taocan button{
            width: 150px;
            height: 30px;
            border-width: 1px;
            border-radius: 3px;
            padding-left: 2px;
            background-color: #d18fec;
            color: white;
            margin-left: 20px;

        }
        #patientInfo{
            /*border: 1px solid ;*/
            height: 300px;
            width: 600px;
            margin-left: 400px;
            margin-top: -400px;
        }
        #patientInfo span{
            display: inline-block;
            font-size: 20px;
        }
        #patientInfo input,button{
            width: 150px;
            height: 30px;
            border-width: 1px;
            border-radius: 3px;
            padding-left: 2px;
            background-color: #d18fec;
            color: white;
        }
        h2{
            text-align: center;
        }

    </style>
    <script type="text/javascript">
        $(function(){
            var menuId;
            $(".butmenuId").click(function () {
                //获取药品id
                 menuId=$(this).val();
                //已拥有药品
                var $left=$("#left2");
                //为拥有药品
                var $right=$("#right");
                $.ajax({
                    type: "GET",
                    url: "${path}/doctorMenuMedicine/ajaxListLeft.action",
                    async: false,
                    data:{menuId:menuId},
                    dataType:"json",
                    success: function (data) {
                        $left.empty();
                        $(data).each(function () {
                            var $option=$("<option value='"+this.medicineCode.codeId+":"+this.amt+"'>"+this.medicineCode.medicineName+":"+this.amt+"</option>")
                            $option.appendTo($left)
                        })

                    }
                })
                $.ajax({
                    type: "GET",
                    url: "${path}/doctorMenuMedicine/ajaxListRight.action",
                    async: false,
                    data:{menuId:menuId},
                    dataType:"json",
                    success: function (data) {
                        $right.empty();
                        $(data).each(function () {
                            var $option=$("<option value='"+this.codeId+":"+this.doctorMenuMedicine.amt+"'>"+this.medicineName+":"+this.doctorMenuMedicine.amt+"</option>")
                            $option.appendTo($right)
                        })

                    }
                })
            })


            $(".buttons button:eq(0)").click(function(){
                var $selected = $("#left2 option:selected");
                $selected.remove();
                var $right = $("#right");
                $selected.appendTo($right);
            })
            $(".buttons button:eq(1)").click(function(){
                var $selected = $("#left2 option");
                $selected.remove();
                var $right = $("#right");
                $selected.appendTo($right);
            })
            $(".buttons button:eq(2)").click(function(){
                var $selected = $("#right option:selected");
                $selected.remove();
                var $left = $("#left2");
                $selected.appendTo($left);
            })
            $(".buttons button:eq(3)").click(function(){
                var $selected = $("#right option");
                $selected.remove();
                var $left = $("#left2");
                $selected.appendTo($left);
            })
            $(".buttons button:eq(4)").click(function(){
                var symptom=$("#symptom").val();
                var advice=$("#advice").val();
                var url = "${path}/doctorMenu/distributeUpdate.action?advice="+advice+"&symptom="+symptom+"&menuId="+menuId;
                $("#left2 option").each(function () {
                    url+="&medicineInfo="+$(this).val();
                })
                location.href=url;
            })
            $("#left2").delegate("option","dblclick",function(){

                var opVal = $(this).val();
                var opHtml = $(this).html();
                var amt = prompt("请输入药品数量","1");
                opVal = opVal.substring(0,opVal.indexOf(":")+1)+amt;
                opHtml = opHtml.substring(0,opHtml.indexOf(":")+1)+amt;
                $(this).val(opVal).html(opHtml);
            })
            $("#but1").click(function() {
                $("#but2").css('display','none')
                $("#home").css('display','block');
            });
            $("#but2").click(function() {
                $("#but1").css('display','none')
                $("#mainright").css('display','block');
            });
            $(".butt").click(function () {
                var patientId =$(this).val();
                location.href="${path}/basePatientInfo/Listinfo.action?patientId="+patientId;
            })
        })

    </script>
</head>
<body>

<div>
    <span class="title0">待就诊病人</span>

    <!-- <span class="title1">已拥有药品</span> -->
    <!-- <span class="title2">未拥有药品</span> -->
</div>
<div style="height: 350px; width: 200px;">
    <c:forEach items="${list}" var="doctorMenuMedicine">
        <button value="${doctorMenuMedicine.basePatientInfo.patientId}" class="butt">${doctorMenuMedicine.basePatientInfo.patientName}</button>
    </c:forEach>
</div>
<%--<select multiple="multiple" id="left" >--%>
    <%--<c:forEach items="${list}" var="doctorMenuMedicine">--%>
        <%--<option value="${doctorMenuMedicine.basePatientInfo.patientId}">${doctorMenuMedicine.basePatientInfo.patientName}</option>--%>
    <%--</c:forEach>--%>
<%--</select>--%>
<div id="patientInfo">
    <h2>完善病人的详细信息:</h2>
    <input  id="patientId" type="hidden" name="patientId" value="${basePatientInfo.patientId}">
    <span>病人姓名：<input type="" name="" value="${basePatientInfo.patientName}"></span>
    <span>出生日期：<input type="" name="" value="${basePatientInfo.birth}"></span><br><br>
    <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="" name="" value="${basePatientInfo.sex}"></span>
    <span>家庭住址：<input type="" name="" value="${basePatientInfo.address}"></span><br><br>
    <span>电话号码：<input type="" name="" value="${basePatientInfo.phonenum}"></span>
    <span>身份证号：<input type="" name="" value="${basePatientInfo.personid}"></span><br><br>
    <span>病人症状：<input type="text" id="symptom" name="symptom" value=""></span>
    <span>医生嘱托：<input type="text" id="advice" name="advice" value=""></span>
    <span>开药方式：</span>&nbsp;&nbsp;&nbsp; <button type="" id="but1">套餐开药</button>&nbsp;&nbsp;&nbsp;<button type="" id="but2">普通开药</button>


</div>
<div id="home" style="display: none;">
    <div style="margin-top: 100px;margin-left: 220px">
        <span class="title1">可用套餐</span>
    </div>
    <div id="taocan" style="border: 1px solid;height: 300px;width: 200px;">
        <c:forEach items="${doctorMenuList}" var="dm">
            <button type="" value="${dm.menuId}" class="butmenuId">${dm.menuName}</button><br><br>
        </c:forEach>
        <%--<button type="">套餐一</button><br><br>--%>
        <%--<button type="">套餐二</button><br><br>--%>
        <%--<button type="">套餐三</button><br><br>--%>
    </div>
    <div id="taocanright" style="margin-left: 240px;margin-top: -380px;">
        <div>
            <span class="title2">已拥有药品</span>
            <span class="title3">未拥有药品</span>
        </div>
        <select multiple="multiple" id="left2" >
            <%--<c:forEach items="${leftList}" var="doctorMenuMedicine">--%>
                <%--<option value="${doctorMenuMedicine.codeId} ${doctorMenuMedicine.amt}">${doctorMenuMedicine.medicineName} 数量${doctorMenuMedicine.amt}</option>--%>
            <%--</c:forEach>--%>
        </select>
        <div class="buttons">
            <button>></button>
            <button>>></button>
            <button><</button>
            <button><<</button>
            <button>提交</button>
        </div>

        <select multiple="multiple" id="right" >
            <%--<c:forEach items="${rightList}" var="doctorMenuMedicine">--%>
                <%--<option value="${doctorMenuMedicine.codeId} ${doctorMenuMedicine.amt}">${doctorMenuMedicine.medicineName} 数量${doctorMenuMedicine.amt}</option>--%>
            <%--</c:forEach>--%>
        </select>
    </div>

</div>
<div id="mainright" style="border: 1px solid;height: 300px;width: 200px;margin-left: 950px;margin-top: 100px;display: none;">
    <div>
        <span class="title4">可供开的药品</span>
    </div>
    <div>


    </div>
</div>


<!-- <select multiple="multiple" id="left" >
    <c:forEach items="${leftList}" var="doctorMenuMedicine">
        <option value="${doctorMenuMedicine.codeId} ${doctorMenuMedicine.amt}">${doctorMenuMedicine.medicineName} 数量${doctorMenuMedicine.amt}</option>
    </c:forEach>
</select>
<div class="buttons">
    <button>></button>
    <button>>></button>
    <button><</button>
    <button><<</button>
    <button>提交</button>
</div>
<select multiple="multiple" id="right" >
    <c:forEach items="${rightList}" var="doctorMenuMedicine">
        <option value="${doctorMenuMedicine.codeId} ${doctorMenuMedicine.amt}">${doctorMenuMedicine.medicineName} 数量${doctorMenuMedicine.amt}</option>
    </c:forEach>
</select>

</body>
</html>
