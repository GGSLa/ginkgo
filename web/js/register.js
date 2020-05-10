var oriName;

function check() {
    var flag=true;
    var userName = document.getElementsByClassName("inText")[0].value;
    if(userName.length===0){
        flag=false;
        writeNameMessage("用户名不能为空")
    }else if(userName.length>15){
        flag=false;
        writeNameMessage("用户名过长")
    }
    else{
        var tmp = document.getElementsByClassName("tips")[0];
        if(oriName!==userName)
        tmp.innerHTML="";
    }

    var password1 =document.getElementsByClassName("inText")[1].value;
    var password2 =document.getElementsByClassName("inText")[2].value;


    if(password1.length<6){
        flag=false;
        var tmp = document.getElementsByClassName("tips")[1];
        tmp.innerHTML="密码长度太短!";
    }
    else{
        var tmp = document.getElementsByClassName("tips")[1];
        if(userName!==oriName){
            tmp.innerHTML="";
        }
    }
    if(password2!==password1){
        flag=false;
        if(password1.length>=6){
            var tmp = document.getElementsByClassName("tips")[2];
            tmp.innerHTML="两次密码不一致!";
        }
    }
    else{
        var tmp = document.getElementsByClassName("tips")[2];
        tmp.innerHTML="";
    }
    return flag;
}
function submitChange() {
    if(check()){
        var tmp=document.getElementById("submitButton");
        tmp.setAttribute("class","submitRegister1");
    }
    else{
        var tmp=document.getElementById("submitButton");
        tmp.setAttribute("class","submitRegister2");
    }
}

function writeNameMessage(s) {
    var tmp = document.getElementsByClassName("tips")[0];
    oriName = document.getElementsByClassName("inText")[0].value;
    tmp.innerHTML=s;
}