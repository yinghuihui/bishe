  
  define(function (require, exports, module) {
    require('jquery');
  //获取一级
    $.ajax({
        url: '/modules/web/programa/listbyparentid.htm',
        type: 'get',
        data: {
            id: 0,
            remark:10
        },
        success: function (result) {
            var data = result.data
            var str = ""
            var AbNav = ""
            var  footNav=""
            for (var i = 0; i < data.length; i++) {
                if(data[i].programaName == "关于我们"){
                       ids = data[i].id;
                       str +="<span><a data-id="+data[i].id+" href=" + data[i].href+ ">" + data[i].programaName + "</a></span>"
                       AbNav += "<a data-id="+data[i].id+" href=" + data[i].href + ">" + data[i].programaName + "</a>"
                       footNav+="|<a class='jump' data-id="+data[i].id+" href=" + data[i].href+ ">"+data[i].programaName+"</a>"
                       continue
                }
                if (data[i].programaName == "APP下载") {
                   
                    continue
                }
                if (data[i].programaName == "首页") {
                    $(".logo a").attr("href",data[i].href)
                    footNav+="<a class='ec-jump' data-id="+data[i].id+" href=" + data[i].href+">"+data[i].programaName+"</a>"
                    str += "<span><a data-id="+data[i].id+" href=" + data[i].href +">" + data[i].programaName + "</a></span>"
                    continue
                } else {
                    if(data[i].programaName !="我要借款"){
                    AbNav += "<a data-id="+data[i].id+" href=" + data[i].href + "?id="+data[i].id+">" + data[i].programaName + "</a>"
                    }      
            }
            if(data[i].programaName == "网站公告"){
                 continue
             }
             footNav+="|<a class='jump' data-id="+data[i].id+" href=" + data[i].href+ "?id=" + data[i].id+ ">"+data[i].programaName+"</a>"    
             str += "<span><a data-id="+data[i].id+" href=" + data[i].href + "?id="+data[i].id+">" + data[i].programaName + "</a></span>"

            }
            $("#nav").html(str)
            $(".tabs").html(AbNav)
            $(".foot-left-up").html(footNav)
            $("#nav span a[data-id=" + getid() + "]").addClass("hover")
            $(".tabs a[data-id=" + getid() + "]").addClass("hover")

        }
    })
    var getid = function () {
        var url = window.location.href;
        var index = url.indexOf("?id=")
        if(index ==-1){
            return null
        }
        var id = url.substring(index + 4)
        return id
    }
   //跳转到确认借款
 $(".borrowenter").click(function () {
   var isborr = $(".isborrow").val()
   var usero =  $("#usersession").val();
  if(usero==null||usero==undefined||usero==""){
     window.location.href = "/modules/web/User/register.htm";
  }else if(isborr=="0"||(isborr==0)){
      $(".borrow_index").css("display","none")
      $(".borrow_progress").css("display","block")
  }else {
      $(".borrow_index").css("display","none")
      $(".borrow_confirm").css("display","block") 
  }
 });
 //跳转到借款进度
 $(".confirmenter").click(function () {
   $(".borrow_confirm").css("display","none");
   $(".borrow_progress").css("display","block")
 });
 //跳转到借款首页
  $(".return").click(function () {
   $(".borrow_confirm").css("display","none")
   $(".borrow_index").css("display","block")
 });


 });