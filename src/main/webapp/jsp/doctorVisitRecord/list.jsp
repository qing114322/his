<%-- 
  Created by IntelliJ IDEA. 
  2018/12/26 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%>
<style>
    #patientInfo{

    }
    #patientInfo span{
       font-weight: 400;
        font-size: 20px;

    }
    .search1 span button{
        margin-left: 30px;
        text-align: center;
        width: 80px;
        height: 30px;
        border-width: 0px;
        border-radius: 5px;
        background-color: #d18fec;
        color:white;
        font-size: 14px;
    }
    .search1 input{
        margin-left: 30px;
        text-align: center;
        width: 80px;
        height: 30px;
        border-width: 0px;
        border-radius: 5px;
        background-color: #d18fec;
        color:white;
        font-size: 14px;
    }
</style>
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () {
            //获取下拉框里的医生信息
           var $select= $("#select");
            $.ajax({
                type: "GET",
                url: "${path}/baseUser/ajaxList2.action",
                async: false,
                dataType:"json",
                success: function (data) {
                    $(data).each(function () {
                        var $option=$("<option value='"+this.userId+"'>"+this.cname+"</option>")
                        $option.appendTo($select)
                    })
                }
            });
            $("#select").change(function () {
                var  userId=$(this).val();
                //当医生发生变化时，通过id查找挂号在这个医生名下的病人
                var $div= $(".search");
                $.ajax({
                    type: "GET",
                    url: "${path}/doctorVisitRecord/listuserId.action",
                    data: {userId:userId},
                    dataType:"json",
                    success: function (data) {
                        $div.empty();
                        $(data).each(function () {
                            var $span=$('<span><button value="'+this.patientRegisterRecord.basePatientInfo.patientId+'">'+this.patientRegisterRecord.basePatientInfo.patientName+'</button></span></br>')
//                            var $span=$('<button value="'+this.patientRegisterRecord.patientId+">'+this.patientRegisterRecord.patientName+'</span><br>')
                            $span.appendTo($div)
                        })
                        //开始写点击病人触发事件
                        $(".search button").click(function () {
                            $("#patientInfo").css('display','block');
                            var val=$(this).val();
                            location.href="${path}/basePatientInfo/listpatientId.action?patientId="+val;

                        })
                    }
                });


            })
            $("#input").click(function () {
                var patientId=$("#patientId").val();
                var  symptom=$("#symptom").val();
                var advice =$("#advice").val();
                if(symptom!=''&&advice!=''){
                    location.href="${path}/doctorVisitRecord/insert.action?patientId="+patientId+"&symptom="+symptom+"&advice="+advice;

                }else {
                    alert("信息不能为空！")
                }


            })
            $("#but").click(function () {
                window.open('${path}/jsp/doctorVisitRecord/distribute.jsp','更改套餐信息','width=800,height=500,top=200,left=300')
            })

        })  
    </script> 
</head>
<body class="main">
<div>
    请选择要就诊的医生：<select id="select"></select>
</div>
<div class="search" style="margin-left: -50px">
      <%--<span>--%>
            <%--<button value="${doctorVisitRecord.basePatientInfo.patientId}">${doctorVisitRecord.basePatientInfo.patientName}</button><br>--%>
        <%--</span><br>--%>
</div>



<div id="patientInfo" style="height: 600px;width: 600px;margin-left: 400px;">

            <span>完善病人的详细信息:</span><br>
             <input  id="patientId" type="hidden" name="patientId" value="${basePatientInfo.patientId}">
            <span>病人姓名：${basePatientInfo.patientName}</span><br>
            <span>出生日期：${basePatientInfo.birth}</span><br>
            <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：${basePatientInfo.sex}</span><br>
            <span>家庭住址：${basePatientInfo.address}</span><br>
            <span>电话号码：${basePatientInfo.phonenum}</span><br>
            <span>身份证号：${basePatientInfo.personid}</span><br>
            <span>病人症状：<input type="text" id="symptom" name="symptom"><br></span>
            <span>医生嘱托：<input type="text" id="advice" name="advice"></span>
    <div class="search1" style="margin-left: -50px;margin-top: 10px">
    <input id="input" type="button" value="确认就诊" >
    </div>
    <%--<div class="search1" style="margin-left: -50px;margin-top: 10px">--%>
        <%--<span>--%>

        <%--<button id="bu" style="font-size: 11px;margin-left: 200px" value="">确认就诊</button><br>--%>
        <%--</span>--%>
    <%--</div>--%>
    <%--<div class="search1" style="margin-left: -50px;margin-top: 0px">--%>
        <%--<span>--%>
        <%--<button id="but" style="font-size: 11px;margin-left: 300px;margin-top: -30px" value="">点击查看套餐</button><br>--%>
        <%--</span>--%>
    <%--</div>--%>
</div>

</body> 
</html> 
