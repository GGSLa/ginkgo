<%@ page import="club.uglyland.dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: ZGQ
  Date: 2020/5/4
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  Integer originalId = (Integer) session.getAttribute("userId");
  Cookie[] cookies=request.getCookies();
  if(originalId!=null){
    if(originalId==-10086){
      Cookie cookie = new Cookie("userId",null);
      cookie.setMaxAge(0);
      response.addCookie(cookie);
      session.setAttribute("userId",null);
    }else {
      Cookie cookie1 = new Cookie("userId", String.valueOf(session.getAttribute("userId")));
      Cookie cookie2 = new Cookie("username", (String) session.getAttribute("username"));
      cookie1.setMaxAge(3600 * 24 * 7);
      cookie2.setMaxAge(3600 * 24 * 7);
      response.addCookie(cookie1);
      response.addCookie(cookie2);
    }
  }




  Integer userId = null;
  String username = null;
  for(Cookie cookie:cookies){
    if(cookie.getName().equals("userId")){
      userId=Integer.parseInt(cookie.getValue());
    }else if(cookie.getName().equals("username")){
      username=cookie.getValue();
    }
  }
  if(originalId==null) {
    session.setAttribute("userId", userId);
    session.setAttribute("username", username);
  }

%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>ZGQ`blog</title>
  <link rel="shortcut icon" href="img/ico/favicon.ico"/>
  <link rel="stylesheet" type="text/css" href="css/index.css">
  <script src="js/indexOnload.js"></script>
  <script src="js/pictureSlide.js"></script>
  <link rel="stylesheet" href="css/pictureSlide.css">
  <link rel="stylesheet" href="css/curTime.css">
  <script src="js/curTime.js"></script>
  <script src="js/checklogin.js"></script>
  <link rel="stylesheet" href="css/title.css">
  <link rel="stylesheet" href="css/main.css">
  <link rel="stylesheet" href="css/init.css">
</head>
<body>

<%@ include file="pages/tinypages/banner.jsp"%>

<div class="focus">
  <div class="nav2">
    <ul>
      <li><a href="https://user.qzone.qq.com/2290713181/main">QQ空间</a></li>
      <li><a href="https://weibo.com/u/6385337257">新浪微博</a></li>
      <li><a href="https://www.zhihu.com/people/duan-tui-san-lang/activities">知乎</a></li>
      <li><a href="https://blog.csdn.net/qq_41575950">CSDN</a></li>
      <li><a href="http://www.cnblogs.com/ZGQblogs/">博客园</a></li>
      <li><a href="https://codeforces.com/profile/ZGQ">Codeforce</a></li>
    </ul>

  </div>
  <div class="cen">
    <div class="car">
      <div class="sdiv">
        <a href="https://live.bilibili.com/14047" id="slink"><img src="img/pic1.png" alt="导播" class="spicture"
                                                                  id="focusImg"></a>
      </div>
      <div class="sDot">
        <ul class="focusBox">
          <li onclick="showPic(1);"></li>
          <li onclick="showPic(2);"></li>
          <li onclick="showPic(3);"></li>
        </ul>
      </div>

    </div>

    <div class="cal">
      <p class="yearMonth"></p>
      <p class="nDay"></p>
      <p class="nTime"></p>
    </div>
  </div>
</div>


<div class="container">
  <div class="cen">
    <div class="choose">
      <a href="blog.html" class="chos"><img src="img/blog.jpg" alt="博客"></a>
    </div>
    <div class="choose">
      <a href="pages/showtask.jsp" class="chos"><img src="img/getcolor.png" alt=""></a>
    </div>
    <div class="choose">
      <a href="numberChange.html" class="chos"><img src="img/numbeiChange.jpg" alt=""></a>
    </div>
    <div class="choose">
      <a href="numberhd.html" class="chos"><img src="img/numberhd.jpg" alt=""></a>
    </div>
    <div class="choose">
      <a href="pages/pan.jsp" class="chos"><img src="img/panCloud.jpg" alt=""></a>
    </div>
    <div class="choose">
      <a href="404.html" class="chos"><img src="img/waiting.JPG" alt=""></a>
    </div>
    <div class="choose">
      <a href="404.html" class="chos"><img src="img/waiting.JPG" alt=""></a>
    </div>
    <div class="choose">
      <a href="404.html" class="chos"><img src="img/waiting.JPG" alt=""></a>
    </div>
    <div class="choose">
      <a href="404.html" class="chos"><img src="img/waiting.JPG" alt=""></a>
    </div>

  </div>
</div>

<%@include file="pages/tinypages/footer.jsp"%>

</body>
</html>

<script>
  function getHeadMessage(){
    setHeadMessage(<%=session.getAttribute("userId")%>,"<%=session.getAttribute("username")%>","pages/");
  }
</script>
