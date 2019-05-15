<%-- 
  Created by IntelliJ IDEA. 
  2018/12/21 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () { 

            $(".delete").click(function () { 
                var fileid = $(this).attr("name");
                location.href="${path}/netstorage/delete.action?fileid="+fileid;
             })
            $("#search").click(function () { 
                var fileaname = $("#fileaname").val();
                location.href = "${path}/netstorage/search.action?fileaname="+fileaname;
             })

            $("#select").change(function () {
                var select=$(this).val();
                var $div=$(".update .left");
                $div.empty();
                for(var i=1;i<=select;i++){
                    var $input=$('<input type="text" name="fileaname" id="filename'+i+'" >');
                    $input.appendTo($div)
                }
            })
            $(".left").delegate("input","click",function () {
                var nameProp=$(this).attr("id");
                window.open('${path}/files/upload.jsp?nameProp='+nameProp,'文件上传','width=400,height=300')
            })
        })
    </script>
</head> 
<body class="main"> 
    <div class="search"> 
        <span> 
            文件名称：<input type="text" id="fileaname" value="${searchObject.fileaname}"> 
        </span> 
        <span> 
            <button id="search">查询</button> 
        </span>
    </div> 
    <table> 
        <thead> 
            <td>序号</td> 
            <td>文件名称</td> 
            <td>文件大小</td> 
            <td>上传日期</td>
            <td>删除</td> 
        </thead> 
        <c:forEach items="${list}" var="netstorage" varStatus="status"> 
            <tr> 
                <td>${status.index+1}</td> 
                <td>${netstorage.fileaname}</td> 
                <td>${netstorage.filesize}</td> 
                <td>${netstorage.uploaddate}</td>
                <td><img src="${path}/images/del.gif" class="delete" name="${netstorage.fileid}"></td> 
            </tr> 
        </c:forEach> 
    </table>
    <form action="${path}/netstorage/insert.action">
        <h1>添加文件上传</h1>
        上传文件的数量:<select id="select">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
                       </select>
        <div class="update">
            <div class="left">
                <input type="text" name="fileaname" id="filename1" >

            </div>
        </div>
        <input type="submit" value="上传">
    </form>
</body> 
</html> 
