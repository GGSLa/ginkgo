function setHeadMessage(id,username,path){
    if(id==null){
        var div1= document.getElementsByClassName("font_nav")[0];
        div1.innerHTML="<a href=\""+path+"register.jsp\">注册</a>";
        var div2= document.getElementsByClassName("font_nav")[1];
        div2.innerHTML="<a href=\""+path+"login.jsp\">登录</a>";
    }else{
        if(path===""){
            path="../";
        }else{
            path="";
        }
        var div1= document.getElementsByClassName("font_nav")[0];
        div1.innerHTML="<a href=\""+path+"logout?userId="+id+"\">注销</a>";
        var div2= document.getElementsByClassName("font_nav")[1];
        div2.innerHTML=username;
    }
}