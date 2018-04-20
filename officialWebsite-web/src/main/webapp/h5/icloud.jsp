<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
  <title>首页</title>
  <style>
    html{
      font-family: "微软雅黑";
    }
    .clearfix:after{
      content: ".";
      display: block;
      height: 0;
      clear: both;
      visibility: hidden;
    }
    html,.probody,.proView{
      width: 100%;
      height: 100%;
      margin: 0px;
      padding: 0px;
    }
    .proTop{
      position: relative;
    }
    .proTop .icloud{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnLeft{
      position: absolute;
      bottom: 8%;
      left: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnLeft img{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnRight{
      position: absolute;
      bottom: 8%;
      right: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnRight img{
      width: 100%;
      height: auto;
      display: block;
    }
    .noBtn{
      position: absolute;
      bottom: 7%;
      left: 50%;
      width: 16.4%;
      margin-left: -8.2%;
      height: auto;
    }
    .noBtn img{
      width: 100%;
      height: auto;
      display: block;
    }
    .proBtm{
      width: 100%;
      background: #fdfae2;
      padding-top: 1.5em;
    }
    .txtTop{
      position: relative;
      border: 1px solid #ea316a;
      padding: 2em 1em 1.5em;
      background: #fff;
      border-radius: 5px;
      margin: 0 6%;
    }
    .txtTop img{
      width: 50%;
      height: auto;
      display: block;
      position: absolute;
      left: 50%;
      top: -1.2em;
      margin-left: -25%;
    }
    .txtTop p{
      margin: 0;
      padding: 0;
      color: #333;
      line-height: 20px;
      font-size: 14px;
    }
    .txtBtm{
      position: relative;
      border: 1px solid #ea316a;
      padding: 4em 1em 0;
      background: #fff;
      border-radius: 5px;
      margin: 1em 6%;
    }
    .txtBtm .txtBtmImg{
      width: 50%;
      height: auto;
      display: block;
      position: absolute;
      left: 50%;
      top: 1em;
      margin-left: -25%;
    }
    .txtBtm ul{
      margin: 0;
      padding: 0;
      list-style: none;
    }
    .txtBtm ul li{
      border-bottom: 1px dashed #eee;
      padding: 0.2em 0;
    }
    .txtBtm ul li:last-child{
      border-bottom: 0 none;
    }
    .txtBtm ul li img{
      width: 12%;
      height: auto;
      display: block;
      margin: 0.5em 0.5em 0.5em 1em;
      float: left;
    }
    .txtBtm ul li .txtBtmTxt{
      float: left;
      width: 75%;
      padding-left: 0.4em;
      padding-top: 0.3em;
    }
    .txtBtm ul li .txtBtmTxt h3{
      margin: 0;
      padding: 0;
      color: #eb0b50;
      font-weight: 500;
      font-size: 14px;
    }
    .txtBtm ul li .txtBtmTxt p{
      margin: 0;
      padding: 0;
      color: #333;
      font-size: 12px;
    }
    .proView .regBtn-a{
      width: 100%;
      text-align: center;
      background: #eb0b50;
      display: block;
      height: 48px;
      line-height: 48px;
      color: #fff;
      font-size: 16px;
      text-decoration: initial;
    }
  </style>
</head>
<body class="probody">
  <div class="proView">
      <div class="proTop">
        <img src=" " alt="" class="icloud icloudIndex1" />
        <img src=" " alt="" class="icloud icloudIndex2" />
        <img src=" " alt="" class="icloud icloudIndex3" />
        <img src=" " alt="" class="icloud icloudIndex4" />
        <img src=" " alt="" class="icloud icloudIndex5" />
        <img src=" " alt="" class="icloud icloudIndex6" />
        <img src=" " alt="" class="icloud icloudIndex7" />
  </div>
  <script src="<%=basePath%>static/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>static/js/config.js" ></script>
  <script>
    $('.icloudIndex1').attr('src',getIcloud_img1()); //图片
    $('.icloudIndex2').attr('src',getIcloud_img2()); //图片
    $('.icloudIndex3').attr('src',getIcloud_img3()); //图片
    $('.icloudIndex4').attr('src',getIcloud_img4()); //图片
    $('.icloudIndex5').attr('src',getIcloud_img5()); //图片
    $('.icloudIndex6').attr('src',getIcloud_img6()); //图片
    $('.icloudIndex7').attr('src',getIcloud_img7()); //图片
  </script>
</body>
</html>