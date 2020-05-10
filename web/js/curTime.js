function showTime(){
    var date = new Date();
    var s="";
    s+=date.getFullYear();
    s+="年";
    s+=date.getMonth()+1;
    s+="月&nbsp;&nbsp;&nbsp;星期";
    s+=get_day(date.getDay());
    document.getElementsByClassName("yearMonth")[0].innerHTML=s;

    s="";
    s+=date.getDate();
    document.getElementsByClassName("nDay")[0].innerHTML=s;

    s="";
    if(date.getHours()<10){s+="0";}
    s+=date.getHours();
    s+=":";
    if(date.getMinutes()<10){s+="0";}
    s+=date.getMinutes();
    s+=":";
    if(date.getSeconds()<10){s+="0";}
    s+=date.getSeconds();
    var kbrs=changeKbrs();
    s+=" &nbsp;&nbsp;&nbsp;<a href=\""+kbrs+"\"> 随机链接</a>";
    document.getElementsByClassName("nTime")[0].innerHTML=s;


}


function changeKbrs() {
    var links=[
    "http://bailian.openjudge.cn/",
    "http://poj.org/",
    "http://codeforces.com",
    "https://ac.nowcoder.com/acm/contest/vip-index?&headNav=www",
    "http://10.64.70.166",
    "https://vjudge.net",
    "http://acm.csu.edu.cn:20080/?tdsourcetag=s_pcqq_aiomsg",
    "http://acm.hdu.edu.cn",
    "https://www.luogu.org/",
    "http://acm.zju.edu.cn/onlinejudge/",
    "https://nanti.jisuanke.com/acm",
    "https://www.zhixincode.com/contests",
    "https://blog.csdn.net/yiqzq",
    "http://www.cnblogs.com/ZGQblogs/",
    "https://www.zhihu.com/",
    "https://translate.google.com/",
    "https://www.bilibili.com/"
    ];

    var p=Math.random()*1000%links.length;
    return links[parseInt(p)];
}

function get_day(curday) {
    var days=["一","二","三","四","五","六","日"];
    return days[curday];
}
