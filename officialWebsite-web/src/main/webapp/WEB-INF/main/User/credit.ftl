<!DOCTYPE HTML>
<html>

<head>
    <meta charset="utf-8" />
    <title>袋鼠钱包</title>
    <link type="image/x-icon" rel="shortcut icon" href="/resources/img/favicon16.ico" />
    <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/page/credit.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/foundation/base.css">
    <link rel="stylesheet" href="../../../resources/css/web/credit.css">
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
           <ul>
             <li id="1" class="ab-hover">个人信息&nbsp;&nbsp;<span class ="state">(认证中)</span></li>
             <li id="2" ">绑银行卡&nbsp;&nbsp;<span class ="state">(认证中)</span></li>
             <li id="3" ">工作信息&nbsp;&nbsp;<span class ="state">(认证中)</span></li>
           </ul>
           <div class = "person_inform">
               <form  id="person_1" method="post">
            <dl>
                <dd class = "id_no_pic">
                <span>身份证正反面</span>
                    <img id="front_pic" src="/resources/img/home/business-bg.png" >
                    <input id="front_upload" name="frontFile" accept="image" type="file" style="display: none"/>
                    <img id="obverse_pic" src="/resources/img/home/business-bg.png" >
                    <input id="obverse_upload" name="obverseFile" accept="image" type="file" style="display: none"/>
                    <p class="msg_tip">
                    </p>
                </dd>
                <br>
                <dd>
                    <span>姓名</span>
                    <input placeholder="输入你的姓名" id="realname"  name="name" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                <dd>
                    <span>身份证号码</span>
                    <input placeholder="输入你的身份证号码"  id="idno" name="idNo" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                <dd>
                    <span>学历</span>
                    <select class="degree" name="degree">
                    <option value ="博士后" >博士后</option>
                    <option value ="博士" >博士</option>
                    <option value ="硕士">硕士</option>
                    <option value="本科">本科</option>
                    <option value="高职/大专">高职/大专</option>
                    <option value="中专/职高/高中">中专/职高/高中</option>
                    <option value="初中及小学以下">初中及小学以下</option>
                    </select>
                </dd>

                 <dd>
                    <span>居住地址</span>
                    <input placeholder="输入你的居住地址"  id="addres" name="address" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                
               
                <dd style="height:50px;line-height:50px;margin-bottom:3px;"><input type="submit" class="enter" value="保存认证"></dd>
            </dl>
            </form>
           </div>
            <div class = "bank_card">
               <form  id="bank_1" method="post">
            <dl>
                
                <dd>
                    <span>持卡人</span>
                    <input placeholder="输入持卡人姓名" id="name"  name="name" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                 <dd>
                    <span>选择银行</span>
                    <select class="bank_name" name ="bankname">
                    <option value ="中国银行" >中国银行</option>
                    <option value ="中国建设银行" >中国建设银行</option>
                    <option value ="交通银行">交通银行</option>
                    <option value="交通银行">交通银行</option>
                    <option value="招商银行">招商银行</option>
                    </select>
                </dd>
                <dd>
                    <span>卡号</span>
                    <input placeholder="输入你银行卡号"  id="carno" name="carNo" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                
               
                <dd style="height:50px;line-height:50px;margin-bottom:3px;"><input type="submit" class="enter" value="保存认证"></dd>
            </dl>
            </form>
           </div>
            <div class = "work_info">
               <form  id="work_1" method="post">
            <dl>
                
                <dd>
                    <span>单位名称</span>
                    <input placeholder="输入单位名称" id="companyname"  name="companyName" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                <dd>
                    <span>单位电话</span>
                    <input placeholder="输入单位电话"  id="companyphone" name="companyPhone" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                 <dd>
                    <span>单位所在地</span>
                    <input placeholder="输入单位所在地"  id="companyplace" name="companyPlace" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
               
                <dd style="height:50px;line-height:50px;margin-bottom:3px;"><input type="submit" class="enter" value="保存认证"></dd>
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
    	seajs.use("/resources/js/page/credit.js");
    </script>
</body>

</html>