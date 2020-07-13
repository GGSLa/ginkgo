var path1;
var nodeId1;
var userId1;
var preNodeId;
var sortAsNameAsc = 1;
var sortAsNameDesc = 2;
var sortAsUploadTimeAsc = 3;
var sortAsUploadTimeDesc = 4;
var sortAsFileSizeAsc = 5;
var sortAsFileSizeDesc = 6;
var sortType=sortAsNameAsc;

function getPanList(userId,nodeId,path){
    path1 =path;
    nodeId1 = nodeId;
    userId1 = userId;
    $(".pan-table  tr:not(:first)").remove();
    $(".pan-limit  div").remove();

    if(userId === null){
        writePanTips("您还没有登录哦");
        return;
    }

    if(nodeId!==0){
        makePanFormFirst();
    }



    $.ajax( {
        url:path+"/getPanList",
        type:"POST",
        data:"nodeId="+nodeId+"&sortType="+sortType,
        success:function (result) {
            if(result.code===3){
                writePanTips("没有信息呢");
            }else if(result.code===89){
                location.href="login.jsp";
            }else if(result.code===0){
                $.each(result.result,function (i,item) {
                    makePanForm(i,item);
                })

            }else{
                alert("未知错误!");
            }
        }
    });


   getSize();


}

function getSize() {
    $.ajax( {
        url:path1+"/userSize",
        type:"POST",
        success:function (result) {
            $("#capacity-total").html(result.total);
            $("#capacity-used").html(result.used);
            $("#capacity-remain").html(result.remain)
        }
    });

}

function writePanTips(message) {
    var div = $(".pan-limit")[0];
    var divElement=$("<div></div>");
    divElement.attr("style","text-align:center");
    divElement.append(message);
    divElement.appendTo(div);
}

function getPreNodeId(nodeId1) {
    $.ajax({
        url:path1+"/parentid",
        type:"POST",
        data:"nodeId="+nodeId1,
        async:false,
        success:function (item) {
            pid = item;
            preNodeId = pid;
        }
    });
}

function makePanFormFirst() {
    var tr = $("<tr></tr>");
    tr.append(makeTH("-"));

    var fileNameTH = makeTH("..");

    getPreNodeId(nodeId1);
    fileNameTH.addClass("folder-display");
    fileNameTH.attr("onclick","getPanList("+userId1+","+preNodeId+",'"+path1+"')");

    tr.append(fileNameTH);

    tr.append(makeTH("--"));
    tr.append(makeTH("--"));
    tr.append(makeTH("--"));

    tr.addClass("bg1");

    tr.appendTo($(".pan-table"));
}

function makePanForm(i,item) {
    var tr = $("<tr></tr>");
    tr.append(makeTH(i+1));

    var fileNameTH = makeTH(item.name);

    if(item.fileSize==="--"){
        fileNameTH.addClass("folder-display");
        fileNameTH.attr("onclick","getPanList("+userId1+","+item.nodeId+",'"+path1+"')");
    }

    tr.append(fileNameTH);
    tr.append(makeTH(item.uploadTime));
    if(item.fileSize==="--"){
        tr.append(makeTH("文件夹"));
        tr.append(makeTH("--"));
    }else{
        tr.append(makeTH(item.fileSize));
        tr.append(makeTH("<a href='#' onclick='downloadFile("+item.nodeId+")'>下载</a>"));
    }
    tr.append(makeTH("<a href='#' onclick='deleteFile("+item.nodeId+")'>删除</a>"));


    var remain;
    if(nodeId1===0){
        remain=0;
    }else{
        remain=1;
    }
    if(i%2===remain){
        tr.addClass("bg1");
    }else{
        tr.addClass("bg2");
    }

    tr.appendTo($(".pan-table"));

}

function downloadFile(node_id) {

    $.ajax({
        url:path1+"/download/check/"+node_id,
        type: "POST",
        success:function (code) {
            if(code===0){
                location.href=path1+"/download/"+node_id;
            }else if(code===7){
                alert("访问受限");
            }else if(code===8){
                alert("资源受损");
            }else{
                alert("服务器异常");
            }
        }
    })
    getPanList(userId1,nodeId1,path1);
}

function deleteFile(node_id) {

    if(!confirm("确认要删除吗?")){
        return;
    }

    $.ajax({
        url:path1+"/delete/"+node_id,
        type:"POST",
        async:false,
        success:function (result) {
            if(result===9){
                alert("文件夹不为空")
            }else if(result!==0){
                alert("删除失败");
            }
        }
    });
    getPanList(userId1,nodeId1,path1);
}

function makeTH(message) {
    var td = $("<td></td>");
    td.append(message);
    return td;
}

function onNameClick() {
    if(sortType===sortAsNameAsc){
        sortType=sortAsNameDesc;
    }else {
        sortType=sortAsNameAsc;
    }

    $("#fileNameHead").text("名称"+(sortType===sortAsNameAsc?'▲':'▼'));
    $("#uploadTimeHead").text("上传时间");
    $("#fileSizeHead").text("文件大小");
    getPanList(userId1,nodeId1,path1);
}

function onUploadTimeClick() {
    if(sortType===sortAsUploadTimeAsc){
        sortType=sortAsUploadTimeDesc;
    }else {
        sortType=sortAsUploadTimeAsc;
    }
    $("#fileNameHead").text("名称");
    $("#uploadTimeHead").text("上传时间"+(sortType===sortAsUploadTimeAsc?'▲':'▼'));
    $("#fileSizeHead").text("文件大小");
    getPanList(userId1,nodeId1,path1);
}


function onFileSizeClick() {
    if(sortType===sortAsFileSizeAsc){
        sortType=sortAsFileSizeDesc;
    }else {
        sortType=sortAsFileSizeAsc;
    }

    $("#fileNameHead").text("名称");
    $("#uploadTimeHead").text("上传时间");
    $("#fileSizeHead").text("文件大小"+(sortType===sortAsFileSizeAsc?'▲':'▼'));
    getPanList(userId1,nodeId1,path1);

}

function uploadFile1() {
    $("#node-input").val(nodeId1);
    $("#file-submit").click();
}

function uploadFileClick() {
    $("#upload-input").click();
}

function buildFolder() {
    var folderName=prompt("请输入文件夹名称","新建文件夹");
    /*在页面上再一次弹出提示对话框，*/
    if(folderName==null){
        return;
    }

    $.ajax({
        url:path1+"/create/folder",
        method:"POST",
        data: "folderName="+folderName+"&parentId="+nodeId1,
        async:false,
        success:function (result) {
            if(result!==0){
                alert("新建失败");
            }
        }
    });

    getPanList(userId1,nodeId1,path1);

}

function panRefresh() {
    getPanList(userId1,nodeId1,path1);
}