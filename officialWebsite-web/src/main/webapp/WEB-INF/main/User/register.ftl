
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>袋鼠钱包</title>
    <link type="image/x-icon" rel="shortcut icon" href="/resources/img/favicon16.ico" />
    <script type="text/javascript" src="/resources/js/sea.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.js"></script>
    <script type="text/javascript" src="/resources/js/seajs-text.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/foundation/base.css">
    <link rel="stylesheet" href="../../../resources/css/web/loginTop.css">
    <link rel="stylesheet" href="../../../resources/css/web/register.css">
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
    <#include "/CommonComponent/loginTop.ftl">
    <div class="main">
        <div class="register_main">
        <input type="hidden" id="subflag" value=${registerIp}/>
        <input type="hidden" id="submit" value="${registerIp}/api/user/pcRegister.htm"/>
        <div class="zhuce_1">
            <form  id="zhuce_1" method="post">
            <dl>
                
                <dd>
                    <span>手机号码</span>
                    <input placeholder="输入你常用的手机号" id="mobilePhone" maxlength="11" name="mobile" type="text">
                    <p class="msg_tip">
                    </p>
                </dd>
                <dd>
                    <span>登录密码</span>
                    <input placeholder="登录密码为6到16位字符，包括字母和数字"  id="password" name="password" type="password">
                    <p class="msg_tip">
                    </p>
                </dd>
                <dd>
                    <span>确认密码</span>
                    <input placeholder="再次确认登录密码" name="confirmPassword" type="password">
                    <p class="msg_tip">
                    </p>
                </dd>
                
                <dd class="yanzheng validateCode">
                    <span>图形验证码</span>
                    <div>
                        <input placeholder="请输入验证码" name="validateCode" id="validateCode"  type="text">
                        <img id="yanzheng" src="${registerIp}/modules/imgCode/generate.htm" class="valicode_img" alt=""
                                            class="valicode_img" onclick="this.src='${registerIp}/modules/imgCode/generate.htm?t=' + Math.random();"
                                            align="absmiddle" />
                    </div>
                    <p class="msg_tip">
                    </p>
                </dd>
                <dd class="yanzheng">
                    <span>手机验证码</span>
                    <div>
                    <input placeholder="请输入手机验证码" name="code" id="code" type="text">
                    <a id="getPhoneVcode">获取验证码</a>
                    </div>
                     <p class="msg_tip">
                    </p>
                </dd>
                <dd style="height:35px;line-height:35px;margin-bottom:17px;">
                    <p class="agree">
                        <input id="dianji" name="agree" type="checkbox" checked="checked"/>
                        我已经阅读并同意 <a href="#" name="agreement">《用户平台注册协议》</a>
                    </p>
                    <p class="msg_tip">
                    </p>
                </dd>
                <dd style="height:50px;line-height:50px;margin-bottom:3px;"><input type="submit" class="enter" value="立即注册"></dd>
            </dl>
            </form>
        </div>
        </div>
        <div class="register_success">
            <img src="../../../resources/img/login/success.png" >
            <h2>恭喜你，注册成功</h2>
            <p>
                <b class="remain_time">请扫描二维码下载APP，并在APP上进行贷款申请！</b> 
            </p>
            <ul>
                <li>
                   <img src="/resources/img/home/QD.png" alt="">
                   <p>安卓下载</p>
                </li>
                <li>
                   <img src="/resources/img/home/QD.png" alt="">
                   <p>IOS下载</p>
                </li>
            </ul>
        </div>
        <div class="register-bottom">
           <img src="/resources/img/home/business-bg.png" alt="">
        </div>
    </div>
    <#include "/CommonComponent/loginfooter.ftl">
    <script type="text/javascript">
        seajs.use("/resources/js/page/register.js");
        
    </script>
    
</body>

<html>
