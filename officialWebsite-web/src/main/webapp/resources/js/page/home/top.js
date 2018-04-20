define(function (require, exports, module) {
    require('jquery');


      $(".login").click(function(e){
          e.stopPropagation(); 
      $(".loginForm").toggle();
  });

//登录
$(".login-button").click(function(){
    $.ajax({
        url:"/module/user/login.htm",
        type: "post", 
        dataType: 'json',
        success: function (result) {
            alert(result.code);
            window.location.href ="/modules/web/home.htm";
        }
   
     });

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