<%--
  Created by IntelliJ IDEA.
  User: ZGQ
  Date: 2020/5/6
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task</title>
    <link rel="shortcut icon" href="../img/ico/favicon.ico"/>
    <script src="../js/jquery-3.4.1.js"></script>
    <script src="../js/checklogin.js"></script>
    <script src="../js/task.js"></script>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/task.css">
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

<div class="task-selector-border">
    <div class="task-selector-limit">
        完成状态:
        <input type="radio" name="state" value="1" checked="checked">全部
        <input type="radio" name="state" value="2" >已完成
        <input type="radio" name="state" value="3" >未完成
        <br>

        重复周期:
        <input type="checkbox" name="planningTime" value="1" checked="checked">仅一次
        <input type="checkbox" name="planningTime" value="2" checked="checked">每天
        <input type="checkbox" name="planningTime" value="3" checked="checked">每周
        <input type="checkbox" name="planningTime" value="4" checked="checked">每月
        <input type="checkbox" name="planningTime" value="5" checked="checked">每年
        <br>

        计划时间：
        <input type="radio" name="nextTime" value="1" >不限
        <input type="radio" name="nextTime" value="2" checked="checked">今天
        <text style="float: right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此处选择不限会使完成状态选项失效</text>
        <br>
        <input type="text" placeholder="输入信息搜索" name="search"/>
        <button id="submitChoice" onclick="getTaskList(<%=session.getAttribute("userId")%>,'<%=request.getContextPath()%>')">提交</button>
        <a href="addTask.jsp" style="float:right;">新增</a>
    </div>
</div>

<div class="task-border">
    <div class="task-limit">
        <table class="task-table">
            <tr class="bg-head">
                <th style="width: 60px;">序号</th>
                <th style="width: 480px;">任务</th>
                <th style="width: 120px;">计划时间</th>
                <th style="width: 100px;">完成次数</th>
                <th style="width: 100px;">重复方式</th>
                <th style="width: 60px;">开始</th>
                <th style="width: 60px;">删除</th>
            </tr>

        </table>

    </div>
</div>
<%@include file="tinypages/footer.jsp"%>
</body>
</html>
<script>

$(document).ready(function () {
    setHeadMessage(<%=session.getAttribute("userId")%>,"<%=session.getAttribute("username")%>","");
    getTaskList(<%=session.getAttribute("userId")%>,"<%=request.getContextPath()%>");
});
</script>