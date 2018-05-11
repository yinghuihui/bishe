<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8" />
    <title>袋鼠钱包</title>
    <link type="image/x-icon" rel="shortcut icon" href="/resources/img/favicon16.ico" />
    <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/page/borrowindex.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/foundation/base.css">
    <link rel="stylesheet" href="../../../resources/css/web/borrowindex.css">
    <link rel="stylesheet" href="../../../resources/css/web/loginfooter.css">
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
<body>
    <!--头部-->
    <#include "/CommonComponent/top.ftl">
   <div class="main">
         <div class="credit_main">
        <input type="hidden" id="usersession" value=${Session.user}>
        <input type="hidden" id="isborrow" value=${Session.isborrow}>
        <input type="hidden" id="isauth" value=${Session.isAuth}>
        <input type="hidden" id="login_phone" value=${Session.loginName}>
          <div class = "borrow_index">
               <form  id="borrowindex_1" method="post">
            <dl>
                   
                <dd>
                    <span>总额度</span>
                    <span  id="total"  name="total" ></span>
                    <p class="msg_tip">
                    </p>
                </dd>
                 <dd>
                    <span>可用额度</span>
                    <span  id="unuse"  name="unuse" ></span>
                    <p class="msg_tip">
                    </p>
                </dd>
                 <dd>
                    <span>选择借款金额</span>
                    <select class="creditList" name ="amount">
                   <!--  <option value ="1000" >1000</option>
                    <option value ="2000" >2000</option>
                    <option value ="3000">3000</option>
                    <option value="4000">4000</option>
                    <option value="5000">5000</option>-->
                    </select> (元)
                </dd>
                  <dd>
                    <span>选择借款用途</span>
                    <select class="borrowuse" name ="borrowuse">
                    <option value ="请选择" >请选择</option>
                    <option value ="旅游" >旅游</option>
                    <option value ="学习" >学习</option>
                    <option value ="教育">教育</option>
                    </select>
                </dd>
                 <dd>
                    <span>还款期限</span>
                    <select class="timelimit" name ="timelimit">
                   <!-- <option value ="117" >7</option>
                    <option value ="1114" >14</option>
                    <option value ="1128">28</option>
                    <option value="1130">30</option>-->
                    </select> （天）
                </dd>
                <dd class= "amount_z">
                    <span class="realamountz">到账金额(元)</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span class="repayamountz">还款金额(元)</span>
                </dd>
                <dd>
                    <span class="realamount"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span class="repayamount"></span>
                </dd>
                <dd style="height:50px;line-height:50px;margin-bottom:3px;"><input type="button" class="borrowenter" value="我要借款"></dd>
            </dl>
            </form>
           </div>
            <div class = "borrow_confirm">
               <form  id="borrowconfirm_1" method="post">
            <dl>
                <a href=javascript:void(0) class="return">返回</a>
                <dd>
                    <span>借款金额</span>
                   <input id="camount"  name="camount" type="text"  disabled="disabled" value="5000">元
                    
                </dd>
                 <dd>
                    <span>借款用途</span>
                   <input id="cborrowuse"  name="cborrowuse" type="text"  disabled="disabled" value="学习">
                    
                </dd>
                <dd>
                    <span>借款期限</span>
                   <input id="ctimelimit"  name="ctimelimit" type="text"  disabled="disabled" value="14">天
                    
                </dd>
                <dd>
                    <span>到账金额</span>
                   <input id="crealamount"  name="crealamount" type="text"  disabled="disabled" value="5000">元
                    
                </dd>
                <input id="ccardId"  name="ccardId" type="hidden"   value="11">
                <dd>
                    <span>到账银行</span>
                   <input id="cbank"  name="cbank" type="text"  disabled="disabled" value="中国银行">
                    
                </dd>
                <dd>
                    <span>取现卡号</span>
                   <input id="ccard"  name="ccard" type="text"  disabled="disabled"  value="5454****15454">
                  
                </dd>
                <dd>
                    <span>还款金额</span>
                   <input  id="crepayamount"  name="crepayamount" type="text"  disabled="disabled"  value="5520">
                    
                </dd>
            <!-- <dd>
                    <span>所在城市</span>
                   <input  id="caddress"  name="caddress" type="text"  disabled="disabled" value="杭州市">
                    
                </dd>  -->
                <dd style="height:50px;line-height:50px;margin-bottom:3px;"><input type="button" class="confirmenter" value="确认借款"></dd>
            </dl>
            </form>
           </div>
            <div class = "borrow_progress">
               <form  id="borrowprogress_1" method="post">
            <dl >
                 <a href=javascript:void(0) class="return">返回</a>
                <dd>
                    <span class="title">借款进度</span>       
                </dd>
                <dd>
                    <span >借款订单号:&nbsp;</span>
                     <text  id="borderno"  name="borderno">20180716548915</text> 
                </dd>
                <div id="progressList">
         <!--    <div class ="bor_progress">
                    <span class="state_remark" >审核未通过</span> 
                    <p class="bpstate">很遗憾您的信息审核不通过</p>
                    <p class="bpstate">2018-04-20 16:45</p>   
                </div>
                 <div class ="bor_progress">
                    <span class="state_remark" >提交申请成功</span> 
                    <p class="str">申请1000，期限14天，利息14元</p>
                    <p class="creat_time">2018-04-20 16:45</p>   
                </div>
                 <div class ="bor_progress">
                    <span class="state_remark" >提交申请成功</span>
                    <p>申请1000，期限14天，利息14元</p>
                    <p>2018-04-20 16:45</p>   
                </div> -->
                </div>
            
            </dl>
            </form>
           </div>

        </div>
        <div class="credit-bottom">
              <img class ="bottom-pic" src="/resources/img/home/business-bg.png" alt="">
        </div>
   </div>
    <#include "/CommonComponent/loginfooter.ftl">
    <script type="text/javascript">
    	seajs.use("/resources/js/page/borrowindex.js");
    </script>
</body>

</html>