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

</head>

<#assign aboutNid="newsCenter">
<body>
    <!--头部-->
    <#include "/CommonComponent/top.ftl">

    <div id="main" class="main">
    <div class="aboutus">
        <div class="tabs">
          
        </div>
        <div id="tabmain">
         <div id="tab1">
            <div>
                <ul class="arcContent">
                
                </ul>
            </div>
            <div id="kkpager"></div>
         </div>
        </div>
    </div>
   </div>
    <#include "/CommonComponent/footer.ftl">
    <script type="text/javascript">
    	seajs.use("/resources/js/page/home/newsCenter.js");
        seajs.use("/resources/js/page/aboutUs/artical1.js");
    </script>
</body>

</html>