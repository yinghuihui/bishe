<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <script src="/static/js/flexable.js"></script>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#7CD88E">
    <link rel="stylesheet" href="/static/css/style.css"/>
    <title>连连</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            min-width: 320px;
            max-width: 480px;
            min-height: 100%;
            margin: 0 auto;
            font-family: '微软雅黑';
            font-size: 15px;
            color: #666;
        }

        #form {
            width: 100%;
            height: 100%;
        }

        #title {
            width: 90%;
            text-align: center;
            height: 50px;
            line-height: 50px;
            border-bottom: 1px solid rgb(34, 66, 149);
            color: rgb(34, 66, 149);
            margin: 0 auto;
        }

        #text,
        #bottom {
            width: 80%;
            margin: 0 auto;
        }

        .text1 {
            height: 50px;
            line-height: 50px;
        }

        .input {
            float: right;
            height: 30px;
            margin-top: 10px;
            border: 1px solid #ccc;
        }

        p {
            font-size: 12px;
            font-style: italic;
            color: red;
            text-align: center;
            display: none;
        }

        #button {
            display: block;
            width: 70%;
            height: 30px;
            margin: 10px auto;
            background: rgb(34, 66, 149);
            border-radius: 5px;
            border: 0px;
            color: #fff;
        }
    </style>
</head>

<body>
    <div id="form">
        <div id="title">
            <h3>在线</h3>
        </div>
        <div id="text">
            <div class="text1"><span>姓名: </span><input onblur="juge(0)" class='input' type="text"></div>
            <p>不能为空!</p>
            <div class="text1"><span>手机号码: </span><input onblur="juge(1)" class='input' type="text"></div>
            <p>不能为空!</p>
            <div class="text1"><span>身份证号码: </span><input onblur="juge(2)" class='input' type="text"></div>
            <p>不能为空!</p>
            <div class="text1"><span>还款金额: </span><input onblur="juge(3)" class='input' type="text"></div>
            <p>不能为空!</p>
            <div class="text1"><span>备注: </span><input onblur="juge(4)" class='input' type="textarea"></div>
-           <p>不能为空!</p>
        </div>
        <div id='bottom'><input id="button" type="submit"></div>
    </div>
    <div class="popup tips" style="display:none">
        <div class="overlay"></div>
        <div class="dialog">
        <span class="close"></span>
        <h2 id="confirm">...</h2>
        <p>
            <a href="javascript:;" class="yes">确定</a>
        </p>
      </div>
    </div>
    <form action="https://wap.lianlianpay.com/authpay.htm" method="post" id="frm1">
        <input id="req_data" type="hidden" name="req_data">
    </form>
    <script src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/signup.js" ></script>

</body>

</html>
