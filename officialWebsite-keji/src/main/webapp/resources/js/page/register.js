/**
 * Created by Administrator on 2015/8/25.
 */
define(function (require, exports, modlue) {
    require('jQuery');
    //解决IE下不支持placeholder
    require.async('./placeholder', function () {
        if ($.browser.msie) {
            $(".zhuce_1 :input[placeholder]").each(function () {
                $(this).placeholder({
                    posL: 10,
                    activeBorder: "#ffa185"
                });
            });
        }
    })
    $.ajax({
        url: '/modules/web/programa/listbyparentid.htm',
        type: 'get',
        data: {
            id: 0,
            remark:20
        },
        success: function (result) {
            var data = result.data
            var str = ""
            var AbNav = ""
            for (var i = 0; i < data.length; i++) {
                str += "<span><a data-id="+data[i].id+" href=" + data[i].href + "?id="+data[i].id+">" + data[i].programaName + "</a></span>"
                if (data[i].programaName == "首页") {
                    $(".logo a").attr("href",data[i].href)
                    break;
                }
            }
           getLogo();
        }
    })
    
    //获取LOGO
    var getLogo = function(){
        $.ajax({
            url:"/modules/web/content/getlogo.htm",
            type:"get",
            data:{remark:20},
            success:function(result){
                var data= result.data;
                $(".logo a").html("<img src="+data.thumbnail+">")
            }
        })
    }
    //验证码倒计时
    var wait = 60;
    var canClick = true;
    get_code_time = function (o) {
        if (wait == 0) {
            o.removeAttribute("disabled");
            o.style.cssText = "background:#DB2B1D;color:#fff";
            canClick = true;
            o.text = "重新发送";
            wait = 60;
        } else {
            o.setAttribute("disabled", true);
            o.style.cssText = "background:#999;color:#fff";
            o.text = "请稍后" + wait + "s";
            wait--;
            canClick = false;
            setTimeout(function () {
                get_code_time(o);
            }, 1000)
        }
    }

    //表单验证
    require.async('../module/jquery-validation-1.13.0/jquery.validate.min', function () {
        require.async(['../module/jquery-validation-1.13.0/additional-methods', '/resources/js/module/layer/skin/default/layer.css', '/resources/js/module/layer/layer.min'], function () {
            $("#zhuce_1").validate({
                rules: {
                    userName: {
                        required: true,
                        regexUserName: true,
                        remote: {
                            type: "POST",
                            url: "/checkUserName.htm",
                            dataType: "json",
                            data: {
                                userName: function () {
                                    return $("#username").val();
                                }
                            }
                        }
                    },
                    mobile: {
                        required: true,
                        isMobile: true

                    },
                    password: {
                        required: true,
                        regexPasswords: true
                    },
                    confirmPassword: {
                        required: true,
                        equalTo: "#password"
                    },
                    validateCode: {
                        required: true,
                        minlength: 4,


                    },
                    code: {
                        required: true,
                        maxlength: 6
                    },
                    invitationCode: {
                        required: true
                    },
                    agree: {
                        required: true
                    }
                },
                messages: {
                    userName: {
                        required: "用户名由英文字母、数字组成，且不能为纯数字",
                        regexUserName: "请输入4到16位由字母、数字组成，且不能为纯数字的用户名",
                        remote: "该用户名已经存在"
                    },
                    mobile: {
                        required: "请输入手机号码",
                        isMobile: "手机号码格式错误",
                        remote: "该手机号码已存在"
                    },
                    password: {
                        required: "登录密码为6到16位字符，其中包括至少一个字母和一个数字",
                        regexPassword: "密码格式错误"
                    },
                    confirmPassword: {
                        required: "请输入确认密码",
                        equalTo: "两次输入密码不一致"
                    },
                    validateCode: {
                        required: "请输入验证码",
                        minlength: " ",
                        remote: "验证码不正确"
                    },
                    code: {
                        required: "请输入验证码",
                        maxlength: "验证码格式错误"
                    },
                    invitationCode: {
                        required: "请输入邀请码"
                    },
                    agree: {
                        required: "请勾选服务协议条款"
                    }
                },
                errorPlacement: function (error, element) {
                    element.parents("dd").find(".msg_tip").html(error);
                },
                success: function (element) {
                    if (element.parents("dd").find("input").has("validateCode")) {
                        //element.parents("dd").find(".msg_tip").html('');
                        element.parents("dd").find(".msg_tip").html('<i class="iconfont gre">&#xe605;</i>');
                    } else {
                        element.parents("dd").find(".msg_tip").html('');
                    }
                },
                submitHandler: function (form, event, validator) {
                    require.async(['../module/jquery.form.js',"../jquery.md5.js"], function () {
                        var data={};
                        data.mobile=$("#mobilePhone").val();
                        data.password=$.md5($("#password").val());
                        data.code=$("#code").val();
                        var url = $("#submit").val();
                        $.ajax({
                            url:url,
                            type: "post",
                            data:data,
                            dataType: 'json',
                            xhrFields:{withCredentials:true},
                            success: function (data) {
                                if (data.code == 200) {
                                    //$("#regPhone").text(data.mobilePhone);
                                    //$("#regPhone_2").text(data.mobilePhone);
                                    // alert(data.realName)

                                    // $("#regPhone_4").text(data.confirmPassword);
                                    $(".register_main").hide();
                                    $(".register_success").show();
                                    var time = parseInt($(".remain_time").text())
                                    if (time > 0) {
                                        var t = setInterval(function () {
                                            time--;
                                            $(".remain_time").text(time);
                                            if (time <= 1) {
                                                window.location.href = $(".reg_link").attr("href");
                                            }
                                        }, 1000);
                                    }
                                    //var i = $(".registeredBox_nav li").index();
                                    //$(".registeredBox_nav li").find(".nav_nub").eq(1).addClass("active");
                                    //$(".registeredBox_nav li").find(".long").eq(0).addClass("active_long");
                                } else {
                                    // 清空并刷新验证码
                                    $("#validateCode").val("")
                                    $("#yanzheng").click();
                                    $("validateCode .msg_tip").html("")
                                    //提示层
                                    layer.msg(data.msg);
                                }
                                /*else {
                                    $("input[name='validateCode']").val('');
                                    $("input[name='validateCode']").parents("dd").find(".msg_tip").html('');
                                    // if (data.msg == "") {
                                    // 	$(".zhuce_1 li:eq(0)").find(".msg_tip").html('<i class="iconfont gre ">&#xe60b;可以注册</i>');
                                    // };
                                    //if (data.msg == "手机号码已存在") {
                                    // $(".zhuce_1 dd:eq(2)").find(".msg_tip").html('<label class="error">手机号码已存在</label>');
                                    // $(".zhuce_1 dd:eq(2)").find("input").addClass("error");
                                    //} else if (data.msg == "验证码不正确") {
                                    // $(".zhuce_1 dd:eq(3)").find(".msg_tip").html('<label class="error">验证码不正确</label>');
                                    //$(".zhuce_1 dd:eq(3)").find("input").addClass("error");
                                    //}else if (data.msg == "验证码不对") {
                                    //$(".zhuce_1 dd:eq(6)").find(".msg_tip").html('<label class="error">手机验证码错误</label>');
                                    // $(".zhuce_1 dd:eq(6)").find("input").addClass("error");
                                    //} else if (data.msg == "两次密码不一致") {
                                    // $(".zhuce_1 dd:eq(5)").find(".msg_tip").html('<label class="error">两次输入密码不一致</label>');
                                    //$(".zhuce_1 dd:eq(5)").find("input").addClass("error");
                                    //}
                                    if (data.msg == "邀请码对应的融资企业不存在，不能注册!") {
                                        $(".zhuce_1 dd:eq(6)").find(".msg_tip").html('<label class="error">邀请码对应的融资企业不存在，不能注册!</label>');
                                        $(".zhuce_1 dd:eq(6)").find("input").addClass("error");
                                    }
                                    $(".valicode_img").each(function () {
                                        $(this).attr("src", '/validimg.htm?t=' + Math.random());
                                    })
                                }*/
                            }
                        });
                    })
                }
            });

        })
    })


    //点击验证码切换
    //$(".change").click(function() {
    //  $(".valicode_img").attr("src", '/modules/system/controller/captchaAction/captchaImage.htm?t=' + Math.random());
    //})

    //协议弹出
    // $("#service_contract").click(function() {
    //     require.async(['../module/layer-v1.8.4/skin/layer.css', '../module/layer-v1.8.4/layer.min'], function() {
    //         var i = $.layer({
    //             type: 1,
    //             title: "车快融注册服务协议",
    //             closeBtn: [0, true],
    //             border: [10, 0.1, '#000', true],
    //             area: ['1034px', '750px'],
    //             page: {
    //                 dom: '#modal_dialog'
    //             }
    //         });
    //     })
    // });


    // 获取手机验证码
    $("#getPhoneVcode").click(function () {
        var o = this;
        var mobile = $("#mobilePhone").val();
        var urlTop = $("#subflag").val() + '/api/user/pcSendSms.htm';
        console.log(urlTop)
        var code = $("#validateCode").val();
        if (!/0?(13|14|15|18|17)[0-9]{9}/.test(mobile)) {
            layer.msg("手机号码格式错误", {
                "time": 800
            });
            return;
        } else if (code.trim() == "" || code.trim() == null) {
            layer.msg("请填写图形验证码", {
                "time": 800
            });
            return;
        }
        else {
            if (canClick) {
                $.ajax({
                    url: urlTop,
                    type: 'post',
                    dataType:'JSON',
                    xhrFields:{withCredentials:true},
                    data: {
                        code: code,
                        phone: mobile,
                        type: "register"
                    },
                    success: function (result) {
                        if (result.code == 200) {
                            get_code_time(o);
                        } else {
                            layer.msg(result.msg, {
                                "time": 1000
                            });
                        }
                        
                    },
                    
                });

            }
        }

    })

    //协议
    $("a[name='agreement']").click(function() {
        require.async('/resources/js/module/handlebars-v1.3.0/handlebars-v1.3.0', function() {
            require.async('/resources/js/module/handlebars-v1.3.0/transFormatJson', function() {
                var tpl = require('./registerTpl.tpl'); //载入tpl模板
                var template = Handlebars.compile(tpl);
                // 弹窗询问是否需要购买
                var text = template("");

                layer.open({
                    type: 1,
                    title: '协议内容',
                    skin: 'layui-layer-rim',
                    area: ['835px', '605px'],
                    content: text
                });
            })
        })
    })

})