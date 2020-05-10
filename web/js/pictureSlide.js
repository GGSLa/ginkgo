var sign = 2;
function showPic(index) {
    var focusImg = document.getElementById("focusImg");
    var imgSrc = "img/pic";
    imgSrc=imgSrc+index+".png";
    focusImg.src = imgSrc;

    var lis = document.getElementsByClassName("focusBox")[0].getElementsByTagName("li");
    for(var i=0;i<lis.length;i++){
        lis[i].className="";
    }
    lis[index-1].className="cur";

    var link = document.getElementById("slink");
    var links=["","https://live.bilibili.com/14047","https://live.bilibili.com/6","http://www.csust.edu.cn/tsg"];
    link.href=links[index];
}
function setCurrentPic() {
    showPic(sign);
    sign++;
    if(sign===4){
        sign=1;
    }
}
