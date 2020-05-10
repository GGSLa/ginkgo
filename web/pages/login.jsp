<%--
  Created by IntelliJ IDEA.
  User: ZGQ
  Date: 2020/5/5
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
    <script src="../js/jquery-3.4.1.js"></script>
    <link rel="stylesheet" href="../css/register.css">
    <link rel="stylesheet" href="../css/init.css">
    <link rel="shortcut icon" href="../img/ico/favicon.ico"/>
    <link rel="stylesheet" href="../css/title.css">
    <script src="../js/login.js"></script>
    <script src="../js/util/md5.js"></script>
    <link rel="stylesheet" href="../css/main.css">

</head>
<body>
<%@include file="tinypages/banner.jsp"%>
<div class="title-line"><span class="tit" style="font-size: 38px;">登录</span></div>
<div class="inner">
    <div class="inDiv">
        <input type="text" class="inText" placeholder="请输入用户名" onmouseleave="submitChange()" >
        <text class="tips"></text>
    </div>
    <div class="inDiv">
        <input type="password" class="inText" placeholder="请输入密码" onmouseleave="submitChange()" >
    </div>
    <div class="inDiv">
        <text class="tips" style="margin-left: 200px"></text>

    </div>

    <div class="inDiv">
        <input type="button" class="submitRegister1" id="submitButton" value ="登录" onclick="submitRegister()">
        <text class="tips"></text>
    </div>
</div>
<%@include file="tinypages/footer.jsp"%>
</body>
</html>

<script type="text/javascript">
$(document).onload(function () {
    setHeadMessage(<%=session.getAttribute("userId")%>,"<%=session.getAttribute("username")%>","");
})

function submitRegister() {
    var username = document.getElementsByClassName("inText")[0].value;
    var password = document.getElementsByClassName("inText")[1].value;
    password=$.md5(password);
    $.ajax({
        url:"<%=request.getContextPath()%>/login",
        type:"POST",
        data:"username="+username+"&password="+password,
        success:function (result) {
            if(result.code===2){
                var tmp = document.getElementsByClassName("tips")[1];
                tmp.innerHTML="用户名不存在或密码错误";
            }else{
                location.href="../index.jsp";
            }
        }
    })

}


</script>
