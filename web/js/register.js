var oriName;
var oriMail;
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

    var email = document.getElementsByClassName("inText")[1].value;
    if(!checkEmail(email)){
        flag=false;
        var tmp = document.getElementsByClassName("tips")[1];
        tmp.innerHTML="邮箱格式错误";
        verifyButtonChange(false);
    }else{
        var tmp = document.getElementsByClassName("tips")[1];
        verifyButtonChange(true);
        if(email!==oriMail)
        tmp.innerHTML="";
    }


    var password1 =document.getElementsByClassName("inText")[2].value;
    var password2 =document.getElementsByClassName("inText")[3].value;


    if(password1.length<6){
        flag=false;
        var tmp = document.getElementsByClassName("tips")[2];
        tmp.innerHTML="密码长度太短!";
    }
    else{
        var tmp = document.getElementsByClassName("tips")[2];
        tmp.innerHTML="";
    }
    if(password2!==password1){
        flag=false;
        if(password1.length>=6){
            var tmp = document.getElementsByClassName("tips")[3];
            tmp.innerHTML="两次密码不一致!";
        }
    }
    else{
        var tmp = document.getElementsByClassName("tips")[3];
        tmp.innerHTML="";
    }

    var verifyCode =document.getElementsByClassName("inText")[4].value;
    if(verifyCode.length===0){
        var tmp = document.getElementsByClassName("tips")[4];
        tmp.innerHTML="请输入验证码";
        flag=false;
    }else{
        var tmp = document.getElementsByClassName("tips")[4];
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

function verifyButtonChange(flag) {
    if(flag===true){
        var tmp=document.getElementById("verifyButton");
        tmp.setAttribute("class","submitRegister1");
    }else{
        var tmp=document.getElementById("verifyButton");
        tmp.setAttribute("class","submitRegister2");
    }
}

function writeNameMessage(s) {
    var tmp = document.getElementsByClassName("tips")[0];
    oriName = document.getElementsByClassName("inText")[0].value;
    tmp.innerHTML=s;
}
function writeMailMessage(s) {
    var tmp = document.getElementsByClassName("tips")[1];
    oriMail = document.getElementsByClassName("inText")[1].value;
    tmp.innerHTML=s;
}

function checkEmail(str){
    var
        re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
    if(re.test(str)){
        return true;
    }else{
        return false;
    }
}