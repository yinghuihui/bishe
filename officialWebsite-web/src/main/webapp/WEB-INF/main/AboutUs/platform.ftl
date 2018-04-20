<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8" />
    <title>袋鼠钱包</title>
    <link type="image/x-icon" rel="shortcut icon" href="/resources/img/favicon16.ico" />
    <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/seajs-text.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/foundation/base.css">
    <link rel="stylesheet" href="../../../resources/css/web/aboutus.css">
    <script>
        var _hmt = _hmt || [];
        (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?fa25c438387de19323aea686b825824d";
        var s = document.getElementsByTagName("script")[0]; 
        s.parentNode.insertBefore(hm, s);
        })();
    </script>
</head>

<#assign aboutNid="platform">

<body>
    <!--头部-->
    <#include "/CommonComponent/top.ftl">
    <input type="hidden" id="subflg">
    <div id="main" class="main">
    <div class="aboutus">
        <div class="tabs">
            <#-- <#include "/AboutUs/aboutUsNav.ftl"> -->
        </div>
        <div id="tabmainAbUs">
            <ul class="clearfix tabmainAbUs-ul">
               
            </ul>
            <div class="content">      
                 <#-- <img src="/resources/img/home/AbUs-img.png" alt="">
                <p>袋鼠钱包成立于2017年8月，公司由多位曾在外资银行、中资银行、一线互联网公司服务多年的精英们共同组成，是一家专注于零售金融领域的金融服务公司。面向有资金需求和理财需求的用户，袋鼠钱包搭建了一个公正、公开、透明、诚信、实现更低借款成本和更稳健投资收益的投资理财交易平台。</p>
                <p>袋鼠钱包致力于让更多的用户通过袋鼠钱包获取到所需资金，让更多的用户实现财富的增长，我们将传统金融以互联网“开放、平等、分享、共赢”的精神带给更多的中国用户。</p>                  -->
            </div>
        </div>
    </div>
   </div>
    <#include "/CommonComponent/footer.ftl">
    <script type="text/javascript">
    	seajs.use("/resources/js/page/home/platform.js");
    </script>
    
</body>
     
</html>