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
    <script src="../js/pan.js"></script>
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/pan.css">
</head>
<body>

<%@include file="tinypages/banner.jsp"%>

<div class="focus" style="height: 50px;">
    <div class="nav2">
        <div class="just-do-it">
            网盘服务
        </div>
    </div>
</div>

<div class="pan-profile-border">
    <div class="pan-profile-limit">
        <div class="pan-profile-capacity" id="capacity-total" style="color: #1f7fbb">
            总容量:-- B(-- KB)&nbsp;
        </div>
        <div class="pan-profile-capacity" id="capacity-used" style="color: #410093 ">
            已使用:-- B(-- KB)&nbsp;
        </div>
        <div class="pan-profile-capacity" id="capacity-remain" style="color:#000;">
            剩余: -- B(-- KB)
        </div>

        <div class="pan-profile-button">
            <form enctype="multipart/form-data" id="fileForm" method="post" action="<%=request.getContextPath()%>/upload" style="display: none" target="target">
                <input type="file" id="upload-input" name="uploadFile" onchange="uploadFile1()" >
                <input type="text" name="parentId" id="node-input" value="0"/>
                <input type="submit" id="file-submit">
                <iframe name="target" id="target" onload="panRefresh()"></iframe>
            </form>

            <button id="upload-button" onclick="uploadFileClick()">上传文件</button>
        </div>
        <div class="pan-profile-button" onclick="buildFolder()">
            <button>新建文件夹</button>
        </div>
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
                <th style="width:60px">下载</th>
                <th style="width:60px">删除</th>
            </tr>
            <tr class="bg1">
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
        getPanList(<%=session.getAttribute("userId")%>,0,"<%=request.getContextPath()%>");
    });
</script>