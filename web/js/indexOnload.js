window.onload = function () {
    showPic(1);
    showTime();
    getHeadMessage();
}
window.setInterval("setCurrentPic()",3000);
window.setInterval("showTime()",500);
