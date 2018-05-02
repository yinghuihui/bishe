<link rel="stylesheet" href="/resources/css/web/top.css">
<div class="header_con_wrapper">
    <div class="header_con">
        <div class="logo">
            <a  href=""><img src="../../../resources/img/home/logo.png"></a>  
        </div>
        <div id="nav">
                         <#-- <span >
			              <a  href="${nav.url}">首页</a>
			            </span>
                        <span >
			              <a  href="${nav.url}">关于我们</a>
			            </span>	
                        <span >
			              <a  href="${nav.url}">新闻中心</a>
			            </span>							
                        <span >
			              <a  href="${nav.url}">联系我们</a>
			            </span>	 -->
        </div>
        <div id="user">
    <!--    <#if Session.userId?exists>  

           <span class = "login-name">
           <#if Session.user?exists>
           欢迎用户，${Session.user}
           <#else>
           欢迎新用户 
           </#if>
           </span><span>&nbsp;|</span>  <a class = "login-out" href="javascript:void(0)">注销</a>   

       <#else> 
             <span>
              <a class="register" href="/modules/web/User/register.htm">注册</a>
            </span>
            <span>
              <a class="login" href="javascript:void(0)">登录</a>
            </span>	
      </#if>  -->		
        </div>
        <div class = "loginForm">
            <form>
                <div class="login-input-box">
                    <span class="icon icon-user"></span>
                    <input class="user_login_name" type="text" placeholder="账号">
                </div>
                <div class="login-input-box">
                    <span class="icon icon-password"></span>
                    <input class="user_login_pwd" type="password" placeholder="密码">
                </div>
            </form>
            <div class="remember-box">
                <label>
                    <input type="checkbox">&nbsp;记住密码
                </label>
            </div>
            <div class="login-button-box">
                <button class ="login-button">登录</button>
            </div>
            <div class="logon-box">
                <a href="">忘记密码</a>
                <a href="/modules/web/User/register.htm">注册</a>
            </div>
        </div>
    </div>
    <input type="hidden" id="tusersession" value=${Session.user}>
    <input type="hidden" id="tuserId" value=${Session.userId}>
</div>
<script type="text/javascript">
    	seajs.use("/resources/js/page/home/top.js");
</script>