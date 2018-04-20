define(function (require, exports, module) {
    require('jquery');
    // var comfn = require('/resources/js/commonFn.js');






    //获取一级
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
            var AbNav = ""
            var footNav = ""
            for (var i = 0; i < data.length; i++) {
                if (data[i].programaName == "关于我们") {
                    ids = data[i].id;
                    str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a></span>"
                    AbNav += "<a data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a>"
                    footNav += "<a class='jump' data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a>"
                    continue
                }
                if (data[i].programaName == "APP下载") {
                    str += "<span><a href=" + data[i].href + "?id=" + data[i].id + " data-id=" + data[i].id + ">" + data[i].programaName + "</a></span>"
                    footNav += "|<a class='jump' data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a>"
                    continue
                }

                if (data[i].programaName == "首页") {
                    $(".logo a").attr("href", data[i].href)
                    footNav += "<a class='ec-jump' data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a>"
                    str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a></span>"
                    continue
                } else {
                    if (data[i].programaName != "我要借款") {
                        AbNav += "<a data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a>"
                    }


                }

                if (data[i].programaName == "网站公告") {
                    continue
                }
                if (data[i].programaName == "联系我们") {
                    $("#nav span a[data-id=" + data[i].id + "]").addClass("hover")
                    $(".tabs a[data-id=" + data[i].id + "]").addClass("hover")
                }
                footNav += "|<a class='jump' data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a>"
                str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a></span>"

            }
            $("#nav").html(str)
            $(".tabs").html(AbNav)
            $(".foot-left-up").html(footNav)


        }
    })
    var getid = function () {
        var url = window.location.href;
        var index = url.indexOf("?id=")
        var id = url.substring(index + 4)
        return id
    }


    $.ajax({
        url: '/modules/web/content/listbyprogramaid.htm',
        type: 'get',
        data: {
            programaId: getid(),
            current: 1,
            pageSize: 1,
            remark: 10
        },
        success: function (result) {
            var data = result.data.list[0]
            $(".c-content").html(data.content)
            $(".c-title").html(data.title)
            $("#nav span a[data-id=" + data.programaId + "]").addClass("hover")
            $(".tabs a[data-id=" + data.programaId + "]").addClass("hover")
        }
    })



});