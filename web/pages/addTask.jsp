<%--
  Created by IntelliJ IDEA.
  User: ZGQ
  Date: 2020/5/9
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task</title>
    <link rel="shortcut icon" href="../img/ico/favicon.ico"/>
    <script src="../js/jquery-3.4.1.js"></script>
    <script src="../js/checklogin.js"></script>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/addTask.css">
<%--    <script src="../js/addTask.js"></script>--%>
</head>
<body>

<%@include file="tinypages/banner.jsp"%>

<div class="focus" style="height: 50px;">
    <div class="nav2">
        <div class="just-do-it">
            Just do it!
        </div>
    </div>
</div>


<div class="addTaskBorder">
    <div class="addTaskLimit">
        <form action="<%=request.getContextPath()%>/addTask" method="post">
            任务详情<input type="text" name="desc" style=" width:300px;"><br>
            计划时间<input type="date" name="time" ><br>
            重复方式
            <select name="repeat">
                <option value="1">仅一次</option>
                <option value="2">每天</option>
                <option value="3">每周</option>
                <option value="4">每月</option>
                <option value="5">每年</option>
            </select><br>
            <input type="submit" value="提交" class="addTaskSubmit">
        </form>
    </div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<%@include file="tinypages/footer.jsp"%>

</body>
</html>
<script>

    $(document).ready(function () {
        setHeadMessage(<%=session.getAttribute("userId")%>,"<%=session.getAttribute("username")%>","");
    });
</script>