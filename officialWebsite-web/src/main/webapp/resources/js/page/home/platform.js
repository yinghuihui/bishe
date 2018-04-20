define(function (require, exports, module) {
    require('jquery');

    var secId = ""
    //获取一级导航
    $.ajax({
        url: '/modules/web/programa/listbyparentid.htm',
        type: 'get',
        data: {
            id: 0,
            remark: 10
        },
        success: function (result) {
            var data = result.data;
            var str = "";
            var AbNav = "";
            var ids = "";
            var footNav = ""
            for (var i = 0; i < data.length; i++) {
                if (data[i].programaName == "关于我们") {
                    ids = data[i].id;
                    str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a></span>"
                    AbNav += "<a data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a>"
                    footNav += "|<a class='jump' data-id=" + data[i].id + " href=" + data[i].href + ">" + data[i].programaName + "</a>"
                    continue;
                }

                if (data[i].programaName == "APP下载") {

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
                footNav += "|<a class='jump' data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a>"
                str += "<span><a data-id=" + data[i].id + " href=" + data[i].href + "?id=" + data[i].id + ">" + data[i].programaName + "</a></span>"



            }
            $("#nav").html(str)
            $(".tabs").html(AbNav)
            $(".foot-left-up").html(footNav)
            getSec(ids)
            $("#nav span a[data-id=" + ids + "]").addClass("hover")
            $(".tabs a[data-id=" + ids + "]").addClass("hover")
            

        }
    })

    var getid = function () {
        var url = window.location.href;
        var index = url.indexOf("?id=")
        if (index == -1) {
            return null
        }
        var id = url.substring(index + 4)
        return id
    }


    //获取二级菜单
    var getSec = function (id) {
        $.ajax({
            url: '/modules/web/programa/listbyparentid.htm',
            type: 'get',
            data: {
                id: id,
                remark: 10
            },
            success: function (result) {
                var data = result.data
                var str = ""
                for (var i = 0; i < data.length; i++) {
                    if (i != data.length - 1) {
                        str += "<li data-id='" + data[i].id + "'>" + data[i].programaName + "</li>"
                    } else {
                        str += "<li data-id='" + data[i].id + "'>" + data[i].programaName + "</li>"
                    }

                }
                $(".tabmainAbUs-ul").html(str)
                addClick();
                $(".tabmainAbUs-ul li[data-id=" + getid() + "]").addClass("ab-hover")
                if (getid() == null) {
                    getContent(data[data.length - 1].id)
                    $(".tabmainAbUs-ul li:last").addClass("ab-hover")
                } else {
                    getContent(getid())
                }

            }
        })
    }


    var getContent = function (id) {
        $.ajax({
            url: "/modules/web/content/listbyprogramaid.htm",
            type: "get",
            data: { programaId: id, current: 1, pageSize: 1, remark: 10 },
            success: function (result) {
                $(".content").html(result.data.list[0].content)
            }
        })
    }

    //给二级菜单添加点击事件
    var addClick = function () {
        $(".tabmainAbUs-ul li").on("click", function () {
            //   var index = $(this).index(".tabmainAbUs-ul li");
            $(this).addClass("ab-hover")
            $(this).siblings("li").removeClass("ab-hover")
            $(".content").html(" ")
            var id = $(this).attr("data-id")
            getContent(id)
        })
    }


});