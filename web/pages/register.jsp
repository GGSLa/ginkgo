<%--
  Created by IntelliJ IDEA.
  User: ZGQ
  Date: 2020/5/4
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<!doctype html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>register</title>
    <link rel="stylesheet" href="../css/register.css">
    <link rel="shortcut icon" href="../img/ico/favicon.ico"/>
    <link rel="stylesheet" href="../css/init.css">
    <script src="../js/jquery-3.4.1.js"></script>
    <script src="../js/register.js"></script>
    <link rel="stylesheet" href="../css/title.css">
    <link rel="stylesheet" href="../css/main.css">
    <script src="../js/util/md5.js" ></script>
</head>
<body>

<%@include file="tinypages/banner.jsp"%>
<div data-v-beceb64a="" class="title-line"><span class="tit" style="font-size: 38px;">注册</span></div>
<form class="inner" method="post">
    <div class="inDiv">
        <input type="text" class="inText" placeholder="请输入用户名" onmouseleave="submitChange()">
        <text class="tips"></text>
    </div>
    <div class="inDiv">
        <input type="password" class="inText" placeholder="请输入密码" onmouseleave="submitChange()">
        <text class="tips"></text>
    </div>
    <div class="inDiv">
        <input type="password" class="inText" placeholder="确认密码" onmouseleave="submitChange()">
        <text class="tips"></text>
    </div>
    <div class="inDiv">
        <input type="button" class="submitRegister2" id="submitButton" value ="注册" onclick="submitRegister()">
        <text class="tips"></text>
    </div>
</form>
<%@include file="tinypages/footer.jsp"%>


</body>
</html>

<script type="text/javascript">

function submitRegister() {
    if(!check()){
        return false;
    }


    var username = $(".inText")[0].value;
    var password = $(".inText")[1].value;
    password = $.md5(password);
    $.ajax({
        url:"<%=path%>/register",
        type:"POST",
        data:"username="+username+"&password="+password,
        success:function (result) {
            if(result.code===1){
                writeNameMessage("用户名已存在");
            }else if(result.code===0){
                location.href="login.jsp";
            }
        }
    })
    return true;

}


</script>