function check() {
    var userName = document.getElementsByClassName("inText")[0].value;
    var tmp = document.getElementsByClassName("tips")[0];
    if(userName.length===0){
        tmp.innerHTML="用户名不能为空";
    }else{
        tmp.innerHTML="";
    }
}

function submitChange() {
    check();
}


function changeHead(id) {
    if(id==null){
        alert("我知道这是null")
    }else{
        alert("userid "+id);
    }
}