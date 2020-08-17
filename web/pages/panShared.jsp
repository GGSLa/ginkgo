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
    <title>Pan</title>
    <link rel="shortcut icon" href="../img/ico/favicon.ico"/>
    <script src="../js/jquery-3.4.1.js"></script>
    <script src="../js/checklogin.js"></script>
    <script src="../js/panShared.js"></script>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/pan.css">
</head>
<body>

<%@include file="tinypages/banner.jsp"%>

<div class="focus" style="height: 50px;">
    <div class="nav2">
        <div class="just-do-it" onclick="goUnShared()">
            网盘服务(共享)
        </div>
    </div>
</div>

<div class="pan-profile-border">
    <div class="pan-profile-limit">

        <div class="pan-profile-button" onclick="panRefresh()">
            <button>刷新</button>
        </div>

    </div>
</div>

<div class="pan-border">
    <div class="pan-limit">
        <table class="pan-table">
            <tr class="bg-head">
                <th style=" width:60px">序号</th>
                <th style="width:600px;cursor:pointer" onclick="onNameClick()" id="fileNameHead">名称▲</th>
                <th style="width:200px;cursor:pointer" onclick="onUploadTimeClick()" id="uploadTimeHead">上传时间</th>
                <th style="width:120px;cursor:pointer" onclick="onFileSizeClick()" id="fileSizeHead">文件大小</th>
                <th style="width:120px;cursor:pointer" onclick="onOwnerClick()" id="fileOwner">上传者</th>
                <th style="width:60px">下载</th>
            </tr>
            <tr class="bg1">
                <th>nice</th>
                <th>nice</th>
                <th>nice</th>
                <th>nice</th>
                <th>nice</th>
                <th>nice</th>
                <th>nice</th>
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
        getPanShareList("<%=request.getContextPath()%>");
    });
</script>