define(function (require, exports, module) {
    require('jquery');


      $(".login").click(function(e){
          e.stopPropagation(); 
      $(".loginForm").toggle();
  });

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
$(".login-out").click(function(){
    $.ajax({
        url:"/module/user/loginOut.htm",
        type: "get", 
        dataType: 'json',
        success: function (result) {
            alert(result.code);
            window.location.href ="/modules/web/home.htm";
        }
   
     });

})


});