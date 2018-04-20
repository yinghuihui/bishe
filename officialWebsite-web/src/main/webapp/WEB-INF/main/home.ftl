<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8" />
    <title>袋鼠钱包</title>
    <link type="image/x-icon" rel="shortcut icon" href="/resources/img/favicon16.ico" />
     <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/seajs-text.js"></script>
    <link rel="stylesheet" href="../../resources/css/web/home.css">
    <link rel="stylesheet" href="../../resources/css/web/footer.css">
    <meta name="renderer" content="webkit" />
    <script type="text/javascript" src="/resources/js/config.js"></script>
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
<#assign siteLinkNid="首页">
<body>
    <!--头部-->
    <#include "/CommonComponent/top.ftl">
    <div id="main">
        <!--轮播图-->
        <div class="banner_wrap">
            <!--<img src="../../resources/img/home/banner.png">-->
            <div class="banner">
            <div class="flexslider sss">
                <ul class="banner_con slides">
                
                </ul>
            </div>
            </div> 
        </div>
        <!--核心优势-->
        <#-- <div class="advantage">
            <h1 class="bt-line bt-line-bgR">核心优势</h1>
            <div class="advantage_box">
                <ul class="clearfix">
                    <li>
                        <img src="../../resources/img/home/fast.png">
                        <p>高效迅速</p>
                        <div>
                         <p>在线申请</p>
                        <p>资金快速到账</p>
                        </div>
                        
                    </li>
                    <li class="trading">
                        <img src="../../resources/img/home/perfer.png">
                        <p>专业认证</p>
                        <div>
                        <p>金融机构</p>
                        <p>您的贷款管家</p>
                        </div>
                    </li>
                    <li>
                        <img src="../../resources/img/home/save.png">
                        <p>安全保障</p>
                        <div>
                           <p>银行监管</p>
                           <p>贷款安全无忧</p>
                        </div>
                    </li>
                    <li class="trading2">
                        <img src="../../resources/img/home/ser.png">
                        <p>优质服务</p>
                        <div>
                          <p>专业客服</p>
                          <p>24小时贴心服务</p>
                        </div>
                    </li>
                </ul>
            </div>
        </div> -->
        <div class="advantage">
            <h1 class="bt-line bt-line-bgR">核心优势</h1>
            
            <div class="advantage_box">
                <ul class="clearfix">
                    <#-- <li class="advantage-li">
                        <img src="../../resources/img/home/fast.png">
                        <p>高效迅速</p>
                        <div>
                         <p>在线申请</p>
                        <p>资金快速到账</p>
                        </div>
                        
                    </li>
                    <li class="advantage-li">
                        <img src="../../resources/img/home/perfer.png">
                        <p>专业认证</p>
                        <div>
                        <p>金融机构</p>
                        <p>您的贷款管家</p>
                        </div>
                    </li>
                    <li class="advantage-li">
                        <img src="../../resources/img/home/save.png">
                        <p>安全保障</p>
                        <div>
                           <p>银行监管</p>
                           <p>贷款安全无忧</p>
                        </div>
                    </li>
                    <li>
                        <img src="../../resources/img/home/ser.png">
                        <p>优质服务</p>
                        <div>
                          <p>专业客服</p>
                          <p>24小时贴心服务</p>
                        </div>
                    </li> -->
                </ul>
            </div>
        </div>
        <!--交易流程-->
        <#-- <div class="process">
            <div class="process_main">
                <h1 class="bt-line bt-line-bgW">贷款流程</h1>
                <ul>
                    <li>
                        <img src="../../resources/img/home/1.png">
                        <p>1、下载注册</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/2.png">
                        <p>2、资料认证</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/3.png">
                        <p>3、发起贷款</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/4.png">
                        <p>4、风控审核</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/5.png">
                        <p>5、成功放款</p>
                         <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/6.png">
                        <p>6、贷后管理</p>
                    </li>
                </ul>
            </div>
        </div> -->
        <input type="hidden" id="processSub"/>
        <div class="process">
            <div class="process_main">
                <h1 class="bt-line bt-line-bgW">贷款流程</h1>
                <ul>
                    <#-- <li>
                        <img src="../../resources/img/home/1.png">
                        <p>1、下载注册</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/2.png">
                        <p>2、资料认证</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/3.png">
                        <p>3、发起贷款</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/4.png">
                        <p>4、风控审核</p>
                        <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/5.png">
                        <p>5、成功放款</p>
                         <img class="jt" src="../../resources/img/home/process_9.png">
                    </li>
                    <li>
                        <img src="../../resources/img/home/6.png">
                        <p>6、贷后管理</p>
                    </li> -->
                </ul>
            </div>
        </div>
         
         <!--产品下载-->
        <#-- <div class="productDownLoad x-model-out">
            <div class="product-DownLoad x-model">
                <h1 class="bt-line bt-line-bgR">产品下载</h1>
                <ul>
                     <li class="proLi  pro-left">
                           <div>
                               <p >安卓下载</p>
                               <p >版本7.2.0.226(2017.07)</p>
                               <img src="../../resources/img/home/QD.png" alt="">
                           </div>
                     </li>
                     <li class="proLi pro-right">
                           <div >
                               <p >IOS下载</p>
                               <p >版本7.2.0.226(2017.07)</p>
                               <img src="../../resources/img/home/QD.png" alt="">
                           </div>
                     </li>
                </ul>
            </div>
        </div> -->
        <input type="hidden" id="proDown"/>
        <input type="hidden" id="APPID"/>
         <div class="productDownLoad x-model-out">
            <div class="product-DownLoad x-model">
                <h1 class="bt-line bt-line-bgR">产品下载</h1>
                <ul>
                     <#-- <li class="proLi  pro-left">
                           <div>
                               <p class="pro-t">安卓下载</p>
                               <p class="pro-c">版本7.2.0.226(2017.07)</p>
                               <img src="../../resources/img/home/QD.png" alt="">
                           </div>
                     </li>
                     <li class="proLi pro-right">
                           <div >
                               <p class="pro-t">IOS下载</p>
                               <p class="pro-c">版本7.2.0.226(2017.07)</p>
                               <img src="../../resources/img/home/QD.png" alt="">
                           </div>
                     </li> -->
                </ul>
            </div>
        </div>
        
        <input type="hidden" id="AbUsSub"/>
        <!--关于我们-->
        <#-- <div class="aboutus x-model-out">
            <div class="aboutus_main x-model">
                <h1 class="bt-line bt-line-bgR">关于我们</h1>
                <ul> 
                    
                     <li class="abUs installment">
                          <a>
                          <div>
                             <img src="../../resources/img/home/about-installmentIco.png" alt="">
                             <p>袋鼠分期</p>
                          </div>
                          </a>
                     </li>
                     
                     <li class="abUs science">
                          <a >
                           <div>
                              <img src="../../resources/img/home/about-sciIco.png" alt="">
                               <p>袋鼠科技</p>
                           </div>
                           </a>
                     </li>
                     
                     <li class="abUs wallet">
                           <a >
                           <div>
                              <img src="../../resources/img/home/about-walletIco.png" alt="">
                               <p>袋鼠钱包</p>
                           </div>
                           </a>
                     </li>
                  
                </ul>
            </div>
        </div> -->
        <div class="aboutus x-model-out">
            <div class="aboutus_main x-model">
                <h1 class="bt-line bt-line-bgR">关于我们</h1>
                <ul> 
                    
                     <#-- <li class="abUs installment">
                          <a>
                          <div>
                             <img src="../../resources/img/home/about-installmentIco.png" alt="">
                             <p>袋鼠分期</p>
                          </div>
                          </a>
                     </li>
                     
                     <li class="abUs science">
                          <a >
                           <div>
                              <img src="../../resources/img/home/about-sciIco.png" alt="">
                               <p>袋鼠科技</p>
                           </div>
                           </a>
                     </li>
                     
                     <li class="abUs wallet">
                           <a >
                           <div>
                              <img src="../../resources/img/home/about-walletIco.png" alt="">
                               <p>袋鼠钱包</p>
                           </div>
                           </a>
                     </li> -->
                  
                </ul>
            </div>
        </div>

        <!--招商加盟-->
        <div class="announcement">
            <div class="news">
                <p class="news-title"><span></span><a href="" class="news-more">查看更多</a></p>
                <ul>
                    <#-- <#list newsList as news>
                    <li>
                       
                        <#if news.picPath=="">
                           <img src="../../resources/img/home/news_1.png">                           
                           <div id=""></div>else>
                           <img src="/modules/cms/CmsArticleAction/read.htm?path=${news.picPath}" />
                        </#if>
                        <div>
                            <h1>
                                <a href="/AboutUs/detail.htm?id=${news.id}">${news.title}</a>
                            </h1>
                            <p>
                                ${news.introduction}
                            </p>
                            <h2>${news.createTime?string("yyyy-MM-dd HH:mm:ss")}</h2>
                        </div>
                    </li>
                    </#list> -->
                </ul>
            </div>
            <div class="bulletin">
                <!--<img src="../../resources/img/home/news_4.png" alt="">-->
                <p><span class="bulletin-title"></span><a href="" class="bulletin-more">查看更多</a></p>
                <ul>
                    <#-- <#list noticeList as notice>
                    <li>
                        <img src="../../resources/img/home/newsjt.png">
                        <div>
                            <p>
                                <a href="/AboutUs/detail.htm?id=${notice.id}">${notice.title}</a>
                            </p>
                            <h3>${notice.createTime?string("yyyy-MM-dd")}</h3>
                        </div>
                    </li>
                    </#list> -->
                </ul>
            </div>
        </div>
        <!--合作伙伴-->
         <#-- <div class="partner">
            <div class="partner_main">
                <h1>合作伙伴</h1>
                <ul>
                	<#list partnerList as partner>
                    <li>
                    	 <a href="${partner.url}">
                    	 	<img src="/modules/cms/CmsArticleAction/read.htm?path=${partner.picPath}">
                    	 </a>
                    </li>
                    </#list>
                </ul>
            </div>
        </div> -->
    </div> 

    <#include "/CommonComponent/footer.ftl">
    <script type="text/javascript">
    	seajs.use("/resources/js/page/home/home.js");
    </script>
    
</body>

</html>