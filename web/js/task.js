var path1;

function getTaskList(userId,path) {
    path1 =path;
    $(".task-table  tr:not(:first)").remove();
    $(".task-limit  div").remove();
    if(userId==null){
        writeTips("您还没有登录哦");
        return;
    }

    var state = $("input[name='state']:checked").val();
    var planningTime ="";
    $("input[name='planningTime']:checked").each(
        function () {
            planningTime+=$(this).val();
        }
    );
    var nextTime = $("input[name='nextTime']:checked").val();
    var search = $("input[name='search']").val();

    $.ajax({
        url:path+"/getTask",
        type:"POST",
        data:"state="+state+"&nextTime="+nextTime+"&search="+search+"&repeat="+planningTime,
        success:function (result) {
            if(result.code===3){
                writeTips("没有信息呢");
            }else if(result.code===0){
                $.each(result.list,function (i,item) {
                    makeForm(i,item);
                })
            }
        }

    })
}


function writeTips(message) {
    var div = $(".task-limit")[0];
    var divElement=$("<div></div>");
    divElement.attr("style","text-align:center");
    divElement.append(message);
    divElement.appendTo(div);
}

function makeForm(i,item) {
    var tr = $("<tr></tr>");
    tr.append(makeTH(i+1));
    tr.append(makeTH(item.desc));
    var date = new Date(item.time);

    var s="";
    s += date.getFullYear()+"/";
    s+=(date.getMonth()+1)+"/";
    s+=(date.getDate());
    tr.append(makeTH(s));
    tr.append(makeTH(item.frequency));

    s="";
    if(item.repeat===1){
        s="仅一次";
    }else if(item.repeat===2){
        s="每天";
    }else if(item.repeat===3){
        s="每周";
    }else if(item.repeat===4){
        s="每月";
    }else if(item.repeat===5){
        s="每年";
    }

    tr.append(makeTH(s));
    var thatId = item.id;
    if(thatId===-1){
        tr.append(makeTH("--"));
        tr.append(makeTH("--"));
    }else{
        tr.append(makeTH("<a href='"+path1+"/complete?id="+thatId+"'>完成</a>"));
        tr.append(makeTH("<a href='"+path1+"/delete?id="+thatId+"'>删除</a>"));
    }
    if(i%2===0){
        tr.addClass("bg1");
    }else{
        tr.addClass("bg2");
    }
    tr.appendTo($(".task-table"));
}

function makeTH(message) {
    var td = $("<td></td>");
    td.append(message);
    return td;
}