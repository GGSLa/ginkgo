function onvertCurrency(){
    var num1=document.getElementsByClassName("textInput")[0].value;
    var jin1=document.getElementsByClassName("textInput")[1].value;
    var jin2=document.getElementsByClassName("textInput")[3].value;

    var num = numberten(num1,jin1);

    if(num===-1){
        alert("数字格式有误!")
    }
    else if(num===-2){
        alert("字符长度不能超过8");
    }
    else{
        var ans = renumber(num,jin2);
        var num2 = document.getElementsByClassName("textInput")[2];
        num2.value=ans;
    }

}

function numform(dig){
    if(dig>='0'&&dig<='9'){
        return dig-'0';
    }
    else if(dig>='a'&&dig<='z'){
        return dig.charCodeAt(0)-'a'.charCodeAt(0)+10;
    }else if(dig>='A'&&dig<='Z'){
        return dig.charCodeAt(0)-'A'.charCodeAt(0)+10;
    }
    else return -1;
}function renumform(dig){
    if(dig>=0&&dig<=9){
        return dig;
    }
    else if(dig===10){
        return 'a';
    }else if(dig===11){
        return 'b';
    }else if(dig===12){
        return 'c';
    }else if(dig===13){
        return 'd';
    }else if(dig===14){
        return 'e';
    }else if(dig===15){
        return 'f';
    }else if(dig===16){
        return 'g';
    }

}

function numberten(num1,jin1) {
    var len=num1.length;
    if(len>8){return -2;}
    var ans=0;
    for(var i=0;i<len;i++){
        if(numform(num1[i])>=jin1){
            return -1;
        }
        ans=ans*jin1+numform(num1[i]);
    }
    return ans;
}

function renumber(num,jin2) {
    var ans="";
    while (num!==0){

        ans+=renumform(parseInt(num)%parseInt(jin2));
        num=parseInt(num/jin2);
    }

    var rets="";
    var len=ans.length;
    for(var i=len-1;i>=0;i--){
        rets+=ans[i];
    }
    return rets;
}
