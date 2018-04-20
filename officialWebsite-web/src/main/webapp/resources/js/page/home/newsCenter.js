define(function (require, exports, module) {
    require('jquery');
    // var comfn = require('/resources/js/commonFn.js');

  
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
    //  $.ajax({
    //       url: '/modules/web/programa/listbyparentid.htm',
    //       type: 'get',
    //       data:{
    //       id :0
    //     },
    //       success: function(result) {
    //             var data =result.data
    //             var str=""
    //             for(var i=0;i<data.length;i++){
    //                 str+="<a  href='"+data[i].href+"'>"+data[i].programaName+"</a>"
    //             }
    //             $(".tabs").html(str)
    //       }
    //  })

    // $.ajax({
    //     url: '/modules/web/programa/listbyparentid.htm',
    //     type: 'get',
    //     data: {
    //         id: 22
    //     },
    //     success: function (result) {
    //         var data = result.data
    //         var str = ""
    //         for (var i = 0; i < data.length; i++) {
    //             if (i != data.length - 1) {
    //                 str += "<li>" + data[i].programaName + "</li>"
    //             } else {
    //                 str += "<li class='ab-hover' data-id='" + data[i].id + "'>" + data[i].programaName + "</li>"
    //             }

    //         }
    //         $(".tabmainAbUs-ul").html(str)
    //     }
    // })
    // var getList = function () {
    //     var url = window.location.href;
    //     var index = url.indexOf("?id=")
    //     var id = parseInt(url.substring(index + 4))
    //     $.ajax({
    //         url: "/modules/web/content/listbyprogramaid.htm",
    //         type: "get",
    //         data: { programaId: id, current: 1, pageSize: 5 },
    //         success: function (result) {
    //             var data = result.data.list
    //             var str = '';
    //             for (var i = 0; i < data.length; i++) {
    //                 str += "<li><img src='/resources/img/home/read.jpg'><div class='mTitle'><p><a href=/modules/web/content/find.htm?id=" + data[i].id + ">" + data[i].title + "</a></p><h3>" + data[i].addTime + "</h3><p class='mContent'>" + data[i].description + "</p></div></li>"
    //             }
    //             $(".arcContent").html(str)
    //         }
    //     })
    // }
    // getList()

    $.ajax({
        url: '/modules/web/programa/listbyparentid.htm',
        type: 'get',
        data: {
            id: 22,
            remark:10
        },
        success: function (result) {
            var data = result.data
            var str = ""
            for (var i = 0; i < data.length; i++) {
                if (i != data.length - 1) {
                    str += "<li>" + data[i].programaName + "</li>"
                } else {
                    str += "<li class='ab-hover' data-id='" + data[i].id + "'>" + data[i].programaName + "</li>"
                }

            }
            $(".tabmainAbUs-ul").html(str)
            //  getThird(data[data.length - 1].id)
        }
    })

   


});