  
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
    $.ajax({
        url:"/modules/user/borrow/index.htm",
        type: "get",
        data:"",
        cache: false,
        dataType: "json",
        success: function (result) {
          if(result.code==200){
              $("#total").html(result.data.total)
              $("#unuse").html(result.data.unuse)
              var dayList = result.data.dayList
              var creditList = result.data.creditList
              for(var i=0;i<dayList.length;i++){
                  $(".timelimit").append("<option value ="+dayList[i]+" >"+dayList[i]+"</option>")
              }
              for(var t=0;t<creditList.length;t++){
                $(".creditList").append("<option value ="+creditList[t]+" >"+creditList[t]+"</option>")
            }
            indexChange();
          }
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
    //确认借款爷们的数据获取
    var borrowConfirm = function(){
        $("#camount").val($(".creditList option:selected").val());
        $("#cborrowuse").val($(".borrowuse option:selected").val());
        $("#ctimelimit").val($(".timelimit option:selected").val());
        $("#crealamount").val($(".realamount").html());
        $("#crepayamount").val($(".repayamount").html());
        $.ajax({
            url:"/modules/user/bankcard/findcard.htm",
            type: "get",
            data:"",
            cache: false,
            dataType: "json",
            success: function (result) {
              if(result.code==200){
                $("#cbank").val(result.data.bank)
                $("#ccard").val(result.data.cardNo)
                $("#ccardId").val(result.data.id)
              }
            }
    
    
        })

    }
    //获取借款进度数据
 var borrowProgressData = function(){
    $.ajax({
        url:"/modules/user/borrow/findProgress.htm",
        type: "post",
        data:"",
        cache: false,
        dataType: "json",
        success: function (result) {
          if(result.code==200){
            $("#progressList").empty()
            $("#borderno").html(result.data.orderNo)
            var progressList = result.data.list
            for(var i=0;i<progressList.length;i++){
                 $("#progressList").append("<div class ='bor_progress'><span class='state_remark'>"+progressList[i].str+"</span> <p class='bpstate'>"+progressList[i].remark+"</p><p class='bpstate'>"+progressList[i].createTime+"</p></div>")
                 
            }
          }
        }


    })
}
   //跳转到确认借款
 $(".borrowenter").click(function () {
   var isborr = $("#isborrow").val()
   console.info(isborr)
   var loginphone =  $("#login_phone").val()
   console.info(loginphone)
   var isauth =  $("#isauth").val()
   //判断是否登录
  if(loginphone==null||loginphone==undefined||loginphone==""){
     window.location.href = "/modules/web/User/register.htm"
    //判断是否已借款
  }else if(isauth=="0"||isauth==0){
     window.location.href = "/modules/web/User/credit.htm"
  }else if(isborr=="0"||(isborr==0)){
    $(".borrow_index").css("display","none")
    $(".borrow_confirm").css("display","block")
    borrowConfirm();
  }else {
    $(".borrow_index").css("display","none")
    $(".borrow_progress").css("display","block")
    borrowProgressData();
  }
 });

 //跳转到借款进度
 $(".confirmenter").click(function () {
    var camount =$("#camount").val()
    var cborrowuse =$("#cborrowuse").val()
    var ctimelimit =$("#ctimelimit").val()
    var crealamount =$("#crealamount").val()
    var ccardId =$("#ccardId").val()
    var crepayamount=$("#crepayamount").val()
    var data={"camount":camount,"cborrowuse":cborrowuse,"ctimelimit":ctimelimit,"crealamount":crealamount,"ccardId":ccardId,"crepayamount":ccardId};
    console.info(data)
    $.ajax({
        url:"/modules/user/borrow/save.htm",
        type: "post",
        data:data,
        cache: false,
        dataType: "json",
        success: function (result) {
          if(result.code==200){
              alert(result.msg)
              $(".borrow_confirm").css("display","none");
              $(".borrow_progress").css("display","block")
              borrowProgressData();
          }
        }


    })
 });
 //跳转到借款首页
  $(".return").click(function () {
   $(".borrow_progress").css("display","none")
   $(".borrow_confirm").css("display","none")
   $(".borrow_index").css("display","block")
 });
 var indexChange = function(){
    var realamount = $(".creditList option:selected").val();
     var timelimit = $(".timelimit option:selected").val();
     var data ={"realamount":realamount,"timelimit":timelimit}
    $.ajax({
        url:"/modules/user/borrow/indexRepayAmount.htm",
        type: "post",
        data:data,
        cache: false,
        dataType: "json",
        success: function (result) {
          if(result.code==200){
              $(".realamount").html(result.data.realamount);
              $(".repayamount").html(result.data.repayamount);
          }
        }
    })
}
 $(".creditList").change(function(){
    indexChange();
});
$(".timelimit").change(function(){
    indexChange();
});


 });