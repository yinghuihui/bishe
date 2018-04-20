<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8" />
    <title>平台公告</title>
    <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/seajs-text.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/foundation/base.css">
    <link rel="stylesheet" href="../../../resources/css/web/top.css">
    <link rel="stylesheet" href="../../../resources/css/web/footer.css">
    <link rel="stylesheet" href="../../../resources/css/web/aboutus.css">
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=bk2GxHHoQiqUKgwLj1wW1GQIbCpcnKri"></script>
</head>

<#assign aboutNid="announcement">
<body>
    <!--头部-->
    <#include "/CommonComponent/top.ftl">

    <div id="main" class="main">
    <div class="aboutus">
        <div class="tabs">
            <#include "/AboutUs/aboutUsNav.ftl">
        </div>
        <div id="tabmain">
            <div id="tab1">
                <h1>平台公告</h1>
                <div class="arcList" data-val="announcement">
                   <ul class="arcContent">
                   </ul>
                </div>
                <div id="kkpager"></div>
            </div>
          <#-- <div id="tab2" class="tab2">
           	    <ul>
                    <#list page.list as article>
                    ${article.siteId}
                        <li>
                            <h1>
                                <span>${article.title}</span><a href="" class="back_btn">返回列表</a>
                            </h1>
                            <p>
                                ${article.content}
                            </p>
                        </li>
                    </#list>
                </ul>
            </div>-->
        </div>
    </div>
   </div>
    <#include "/CommonComponent/footer.ftl">
    <script type="text/javascript">
        seajs.use("/resources/js/page/aboutUs/artical1");
    </script>
</body>

</html>