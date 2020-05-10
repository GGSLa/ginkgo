window.onload=function() {
    var tmp = document.getElementsByTagName("tr");
    var siz=tmp.length;
    for(var i=1;i<=siz;i++){
        var cur = tmp[i];
        if(i%2===1)cur.className="bgcolor1";
        else{
            cur.className="bgcolor2";
        }
    }
}

