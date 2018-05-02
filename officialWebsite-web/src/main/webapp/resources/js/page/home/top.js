define(function (require, exports, module) {
    require('jquery');
    $(document).ready(function(){
        loginorregist();
        })

//登录
$(".login-button").click(function(){
    require.async(['../../module/jquery.form.js',"../../jquery.md5.js"], function () {
    var data={};
    data.loginName=$(".user_login_name").val();
    data.pwd=$.md5($(".user_login_pwd").val());
    $.ajax({
        url:"/module/user/login.htm",
        data:data,
        type: "post", 
        dataType: 'json',
        success: function (result) {
            alert(result.msg);
            if(result.code==200){
                window.location.href ="/modules/web/home.htm";
            }else {
                layer.msg(result.msg);
            }
 
        }
   
     });
    })
})
//注销
// $(".login-out").click(function(){
//     $.ajax({
//         url:"/module/user/loginOut.htm",
//         type: "get", 
//         dataType: 'json',
//         success: function (result) {
//             alert(result.code);
//             window.location.href ="/modules/web/home.htm";
//         }
   
//      });

// })
var loginorregist = function(){
    var userId =$("#tuserId").val()
    var user =$("#tusersession").val()
    if(userId==0||userId=="0"||userId==undefined){
        $("#user").append(" <span><a class='register' href='/modules/web/User/register.htm'>注册</a></span><span><a class='login' href='javascript:void(0)'>登录</a></span>");
    }else if(user==""||user=="0"||user==undefined){
        $("#user").append("<span class = 'login-name'>欢迎新用户</span><span>&nbsp;|</span>  <a class = 'login-out' >注销</a>");

    }else{
        $("#user").append("<span class = 'login-name'>欢迎用户，"+user+"</span><span>&nbsp;|</span>  <a class = 'login-out' href='javascript:void(0)'>注销</a>");
    }
}
// $(".login").click(function(e){
//     //e.stopPropagation(); 
//     console.info(1111111111)
//  $(".loginForm").toggle();
// });
//显示登陆框
$("#user").delegate("a.login","click",function(){
    $(".loginForm").toggle();
  });
//注销
  $("#user").delegate("a.login-out","click",function(){
    $.ajax({
        url:"/module/user/loginOut.htm",
        type: "get", 
        dataType: 'json',
        success: function (result) {
            alert(result.code);
            window.location.href ="/modules/web/home.htm";
        }
   
     });
  });

});