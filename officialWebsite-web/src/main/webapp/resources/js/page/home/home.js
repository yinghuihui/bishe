define(function (require, exports, module) {
    require('jquery');
    // var comfn = require('/resources/js/commonFn.js');

    // $.fn.extend({
    //     slideFn: function(options) {
    //         var defaults = {
    //             isTop: true, //是否
    //             slideTimer: "2000"
    //         };
    //         var options = $.extend(defaults, options);
    //         this.each(function() {
    //             var o = options;
    //             var obj = $(this);
    //             var oUl = obj.find("ul:first");
    //             var oLi = $("li", oUl);
    //             var Timer;
    //             obj.hover(function() {
    //                 clearInterval(Timer);
    //             }, function() {
    //                 Timer = setInterval(function() {
    //                     if (o.isTop == true) {
    //                         slideTop(oUl);
    //                     } else {
    //                         slideLeft(oUl);
    //                     }
    //                 }, o.slideTimer)
    //             }).trigger("mouseleave");
    //             var slideTop = function(box) {
    //                 var oLiHeight = box.find("li:first").height();
    //                 box.animate({
    //                     "marginTop": -oLiHeight + "px"
    //                 }, 800, function() {
    //                     box.css("marginTop", 0).find("li:first").appendTo(box);
    //                 })
    //             }; //上滚
    //             var slideLeft = function(box2) {
    //                 box2.css("width", ((oLi.width()) * (oLi.length)) + "px");
    //                 var oLiWidth = box2.find("li:first").width();
    //                 box2.animate({
    //                     "marginLeft": -oLiWidth + "px"
    //                 }, 800, function() {
    //                     box2.css("marginLeft", 0).find("li:first").appendTo(box2);
    //                 })
    //             }; //左滚
    //         })
    //     }
    // })

    $.ajax({
        url: '/modules/web/programa/listbyparentid.htm',
        type: 'get',
        data: {
            id: 0,
            remark: 10
        },
        success: function (result) {
            var data = result.data
            var str = ""
            var footNav = ""
            var pid = ""
            for (var i = 0; i < data.length; i++) {
                if (data[i].programaName == "关于我们") {
                    ids = data[i].id;
                    str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a></span>"
                    footNav += "|<a class='jump' data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a>"
                    continue
                }
                if (data[i].programaName == "APP下载") {
                    str += "<span><a style='cursor:pointer' class='APPdown' data-id=" + data[i].id + ">" + data[i].programaName + "</a></span>"
                    // $(".APPID").attr("name", data[i].id)
                    footNav += "|<a style='cursor:pointer' class='jump APPdown' data-id=" + data[i].id + ">" + data[i].programaName + "</a>"
                    continue
                }
                if (data[i].programaName == "网站公告") {
                    $(".bulletin .bulletin-title").html(data[i].programaName)
                    $(".bulletin .bulletin-title").attr("data-id", data[i].id)
                    $(".bulletin .bulletin-more").attr("href", data[i].href + "?id=" + data[i].id)
                    continue
                }

                if (data[i].programaName == "首页") {
                    pid = data[i].id
                    $(".logo a").attr("href", data[i].href)
                    footNav += "<a class='ec-jump' data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a>"
                    str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a></span>"
                    continue;
                }
                // if(data[i].programaName == "我要借款"){
                //      str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + "?"+data[i].id+">" + data[i].programaName + "</a></span>"
                //      footNav += "<a class='ec-jump' data-id=" + data[i].id + " href=" + data[i].href + "?"+data[i].id+">" + data[i].programaName + "</a>"
                //      continue;
                // }
                str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a></span>"

                footNav += "|<a class='jump' data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a>"
                if (data[i].programaName == "新闻中心") {
                    $(".news .news-title span").html(data[i].programaName)
                    $(".news .news-title span").attr("data-id", data[i].id)
                    $(".news .news-title span")
                    $(".news .news-more").attr("href", data[i].href + "?id=" + data[i].id)
                }

            }
            $("#nav").html(str)
            getLogo()
            $(".foot-left-up").html(footNav)
            if (getid() == null) {
                $("#nav span a:eq(0)").addClass("hover")
            }
            $("#nav span a[data-id=" + getid() + "]").addClass("hover")
            homeColumDown(pid) //得到所需加载栏目

            // setAbUs() //设置关于我们          
            $(".APPdown").on("click", function () {
                JumpAppDown(1)
            })


        }
    })

    //跳转到APP下载栏目
    var JumpAppDown = function (type) {
        if (type == 1) {
            var top = $(".productDownLoad").offset().top
            $(window).scrollTop(top)
        }
    }
    //获取LOGO
    var getLogo = function () {
        $.ajax({
            url: "/modules/web/content/getlogo.htm",
            type: "get",
            data: { remark: 10 },
            success: function (result) {
                var data = result.data;
                $(".logo a").html("<img src=" + data.thumbnail + ">")
            }
        })
    }
    //获取栏目
    var homeColumDown = function (id) {
//        var id = ""
        $.ajax({
            url: "/modules/web/programa/listbyparentid.htm",
            type: "get",
            data: { id: id?id:"0", remark: 10 },
            success: function (result) {
                var data = result.data
                for (var i = 0; i < data.length; i++) {
                    if (i == 0) {
                        id = data[i].id
                        getBanner(id)
                    }
                    if (i == 1) {
                        id = data[i].id
                        $(".advantage .bt-line-bgR").html(data[i].programaName)
                        getAdvantage(id)
                        continue
                    }
                    if (i == 2) {
                        $(".process .bt-line-bgW").html(data[i].programaName)
                        getProcess(data[i].id)
                        continue
                    }
                    if (i == 3) {
                        $(".productDownLoad .bt-line").html(data[i].programaName)
                        getProDown(data[i].id)
                        continue
                    }
                    if (i == 4) {
                        $(".aboutus .bt-line").html(data[i].programaName)
                        getAbUs(data[i].id)
                        continue
                    }
                }
            }
        })

    }

    //得到核心优势 -S
    var getAdvantage = function (id) {
        var id = id
        if (id == "") {
            console.log("advantageId无值")
            return;
        }
        $.ajax({
            url: "/modules/web/content/sortbyprogramaid.htm",
            type: "get",
            data: { current: 1, pageSize: 4, programaId: id, remark: 10 },
            success: function (result) {
                var data = result.data.list;
                var str = "";
                for (var i = 0; i < data.length; i++) {

                    if (i == data.length - 1) {
                        str += "<li><img src='" + data[i].thumbnail + "'><p>" + data[i].title + "</p><div>" + data[i].description + "</div></li>"
                    } else {
                        str += "<li class='advantage-li'> <img src='" + data[i].thumbnail + "'><p>" + data[i].title + "</p><div><pre>" + data[i].description + "</pre></div></li>"
                    }

                }

                $(".advantage_box ul").html(str)
            }

        })
    }
    //得到核心优势 -E

    //得到贷款流程
    var getProcess = function (id) {
        $.ajax({
            url: "/modules/web/content/sortbyprogramaid.htm",
            type: "get",
            data: { current: 1, pageSize: 6, programaId: id, remark: 10 },
            success: function (result) {
                var data = result.data.list;
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    if (i == data.length - 1) {
                        str += "<li><img src='" + data[i].thumbnail + "'><p>" + data[i].title + "</p></li>"
                        continue;
                    }
                    str += "<li><img src='" + data[i].thumbnail + "'><p>" + data[i].title + "</p><img class='jt' src='/resources/img/home/process_9.png'></li>"
                }

                $(".process_main ul").html(str)
            }

        })
    }

    $(window).on("resize", function () {
        setHAndW();
    })
    //设置图片长宽比

    var setHAndW = function () {
        var width = $(window).width();
        console.log(width)


        var w1200 = Math.floor(1200 / 1920 * 596)
        if (width > 1200) {
            width = Math.floor(width / 1920 * 596)
            $(".slides li").height(width)
        } else {
            $(".slides li").height(w1200)
        }
    }
    //得到产品下载
    var getProDown = function (id) {
        $.ajax({
            url: "/modules/web/content/sortbyprogramaid.htm",
            type: "get",
            data: { current: 1, pageSize: 2, programaId: id, remark: 10 },
            success: function (result) {
                var data = result.data.list;
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    if (data[i].title == "安卓下载") {
                        str += "<li class='proLi  pro-left'><div><p class='pro-t'>" + data[i].title + "</p><p class='pro-c'>" + data[i].description + "</p><img src='" + data[i].thumbnail + "'></div></li>"
                    } else {
                        str += "<li class='proLi  pro-right'><div><p class='pro-t'>" + data[i].title + "</p><p class='pro-c'>" + data[i].description + "</p><img src='" + data[i].thumbnail + "'></div></li>"
                    }
                }

                $(".product-DownLoad ul").html(str)
            }

        })
    }

    //得到关于我们 

    var getAbUs = function (id) {
        // var id =$("#AbUsSub").attr("name")
        $.ajax({
            url: "/modules/web/content/sortbyprogramaid.htm",
            type: "get",
            data: { current: 1, pageSize: 3, programaId: id, remark: 10 },
            success: function (result) {
                var data = result.data.list;
                var str = "";
                for (var i = 0; i < data.length; i++) {
                    if (i == 0) {
                        str += "<li class='abUs installment'><a><div><img src='" + data[i].thumbnail + "'><p>" + data[i].title + "</p></div></a></li>"
                    }
                    if (i == 1) {
                        str += "<li class='abUs science'><a><div><img src='" + data[i].thumbnail + "'><p>" + data[i].title + "</p></div></a></li>"
                    }
                    if (i == 2) {
                        str += "<li class='abUs wallet'><a><div><img src='" + data[i].thumbnail + "'><p>" + data[i].title + "</p></div></a></li>"
                    }

                }

                $(".aboutus_main ul").html(str)
            }

        })
    }

    var getid = function () {
        var url = window.location.href;
        var index = url.indexOf("?id=")
        if (index == -1) {
            return null
        }
        var id = url.substring(index + 4)
        return id
    }

    // var setAbUs = function (id) {
    //     $.ajax({
    //         url: "/modules/web/programa/listbyparentid.htm",
    //         type: 'get',
    //         data: {
    //             id: id,
    //             remark: 10
    //         },
    //         success: function (result) {
    //             var data = result.data;
    //             for (var i = 0; i < data.length; i++) {
    //                 $(".abUs:eq(" + i + ") a").attr("href", "/modules/web/AboutUs/platform.htm?id=" + data[i].id)
    //             }
    //         }
    //     })
    // }

    setTimeout(function () {
        if ($(document).scrollTop() >= 1600) {
            getNews();
            getbulletin();
            return false;
        }
        $(window).on("scroll", function (e) {
            var st = $(this).scrollTop();
            if (parseInt(st) >= 1600) {
                $(this).off("scroll");
                getNews();
                getbulletin();
            }
        })
    }, 100)

    var getNews = function () {
        var id = $(".news .news-title span").attr("data-id")
        $.ajax({
            url: "/modules/web/content/listbyprogramaid.htm",
            type: "get",
            data: { programaId: id, current: 1, pageSize: 2, remark: 10 },
            success: function (result) {
                var str = "";
                var data = result.data.list;
                for (var i = 0; i < data.length; i++) {
                    str += "<li data-id=" + data[i].id + "><img src=" + data[i].thumbnail + "><div><h1><a href='/modules/web/AboutUs/detail.htm?id=" + data[i].id + "'>" + data[i].title + "</a></h1><p>" + data[i].description + "</p><h2>" + data[i].addTime + "</h2></div></li>"
                }

                $(".news ul").html(str);
            }
        })
    }
    var getbulletin = function () {
        var id = $(".bulletin .bulletin-title").attr("data-id")
        $.ajax({
            url: "/modules/web/content/listbyprogramaid.htm",
            type: "get",
            data: { programaId: id, current: 1, pageSize: 4, remark: 10 },
            success: function (result) {
                var str = "";
                var data = result.data.list;
                for (var i = 0; i < data.length; i++) {
                    str += "<li data-id=" + data[i].id + "><img src='../../resources/img/home/newsjt.png'><div><p><a href='/modules/web/AboutUs/detail.htm?id=" + data[i].id + "'>" + data[i].title + "</a></p><h3>" + data[i].addTime + "</h3></div></li>"
                }
                $(".bulletin ul").html(str);
            }
        })
    }


    //动态加载栏目-E 

    //加载产品下载栏目
    // var proDown = function () {
    //     $.ajax({
    //         url: "/modules/web/content/listbyprogramaid.htm",
    //         type: "get",
    //         data: { programaId: id, current: 1, pageSize: 2, remark: 10 },
    //         success: function (result) {
    //             var str = "";
    //             var data = result.data.list;

    //         }
    //     })
    // }




    //动态加载测试栏目-S 
    // var data1; 
    // $.ajax({
    //     url: '/cmsArticle/find.htm?siteNid=homePageBanner',
    //     type: 'get',
    //     success: function(result) {
    //         data1 = result.data;
    //         // console.log(data1)

    //         var len = data1.length;
    //         var str = "";
    //         for (i = 0; i < len; i++) {
    //             var picPath = data1[i].picPath;
    //             var url = data1[i].url;
    //             if (url) {
    //                 url = data1[i].url;
    //             } else {
    //                 url = "javascript:;";
    //             }
    //             picPath = '/modules/cms/CmsArticleAction/read.htm?path=' + picPath;
    //             str += "<li style='background:url(" + picPath + ") repeat center 0'><a href='" + url + "' ></a></li>";
    //         }
    //         $(".banner_con").html(str);
    //         require.async('module/jquery.flexslider-min', function() {
    //             $('.flexslider').flexslider({
    //                 directionNav: true,
    //                 pauseOnAction: false,

    //             });
    //         });
    //     }
    // })

    // var data = [{ picPath: "/resources/img/home/banner.png" }, { picPath: "/resources/img/home/banner2.png" }, { picPath: "/resources/img/home/banner3.png" }, { picPath: "/resources/img/home/banner4.png" }]
    // var len = data.length;
    // var str = "";
    // for (i = 0; i < len; i++) {
    //     var picPath = data[i].picPath;
    //     /*var img=new Image();
    //     img.src=picPath;
    //     if(img.width==0){
    //         picPath="/resources/img/home/banner_2.jpg"
    //     }
    //     else{
    //         picPath=data[i].picPath
    //     }*/
    //     // var url = data[i].url;
    //     // if (url) {
    //     //     url = data[i].url;
    //     // } else {
    //     //     url = "javascript:;";
    //     // }
    //     str += "<li style='background:url(" + picPath + ") repeat center 0'></li>";
    // }
    // $(".banner_con").html(str);
    // require.async('module/jquery.flexslider-min', function () {
    //     $('.flexslider').flexslider({
    //         directionNav: true,
    //         pauseOnAction: false
    //     });
    // });
    var getBanner = function (id) {


        $.ajax({
            url: "/modules/web/content/sortbyprogramaid.htm",
            data: { remark: 10, programaId: id, current: 1, pageSize: 10 },
            type: 'get',
            success: function (result) {
                var data1 = result.data.list;
                // console.log(data1)

                var len = data1.length;
                var str = "";

                for (i = 0; i < len; i++) {
                    var picPath = ""
                    picPath = data1[i].thumbnail;

                    str += "<li style='background:url(" + picPath + ") repeat center 0'></li>";
                }
                $(".banner_con").html(str);
                setHAndW()
                require.async('module/jquery.flexslider-min', function () {
                    $('.flexslider').flexslider({
                        directionNav: true,
                        pauseOnAction: false,

                    });
                });
            }
        })
    }
    // $.ajax({
    //     url:"/modules/web/content/slideshow.htm",
    //     type: "get", 
    //     dataType: 'json',
    //     success: function (result) {
    //         var data = result.data;
    //         var len = data.length;
    //         var str = "";
    //         for (i = 0; i < len; i++) {
    //             var picPath = data[i].picPath;
    //             /*var img=new Image();
    //             img.src=picPath;
    //             if(img.width==0){
    //                 picPath="/resources/img/home/banner_2.jpg"
    //             }
    //             else{
    //                 picPath=data[i].picPath
    //             }*/
    //             var url = data[i].url;
    //             // if(url){
    //             //    url = data[i].url;
    //             // }else{
    //             //    url = "javascript:;";
    //             // }    
    //             str += "<li style='background:url(" + picPath + ") repeat center 0'></li>";
    //         }
    //         $(".banner_con").html(str);
    //         require.async('../module/jquery.flexslider-min', function () {
    //             $('.flexslider').flexslider({
    //                 directionNav: true,
    //                 pauseOnAction: false
    //             });
    //         });
    //     }
    // });
   





});