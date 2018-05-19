  
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
            addClick();
            //uploadImg();
            getauthdata();
        }
    })
    var getauthdata = function(){
        $.ajax({
            url:"/modules/user/credit/creditdata.htm",
            type: "get",
            data:"",
            cache: false,
            dataType: "json",
            success: function (result) {
              if(result.code==200){
                  result = result.data
                 //$(".authstate1").append("<li id='1' class='ab-hover'>个人信息&nbsp;&nbsp;<span class ='state'>"+result.authMap.idState+"</span></li><li id='2' >绑银行卡&nbsp;&nbsp;<span class ='state'>"+result.authMap.idState+"</span></li><li id='3' >工作信息&nbsp;&nbsp;<span class ='state'>"+result.authMap.idState+"</span></li>");
                 //个人信息
                 $("#state1").html("("+result.authMap.idState+")");
                 $("#state2").html("("+result.authMap.bankCardState+")");
                 $("#state3").html("("+result.authMap.workInfoState+")");
                 $("#front_pic").attr('src',result.personInfoMap.frontImg); 
                 $("#obverse_pic").attr('src',result.personInfoMap.backImg);
                 $("#realname").val(result.personInfoMap.realName);
                 $("#idno").val(result.personInfoMap.idNo);
                 $(".degree").val(result.personInfoMap.education);
                 $("#addres").val(result.personInfoMap.liveAddr);
                 //银行卡信息
                 $("#cname").val(result.bankCardMap.brealName);
                 $("#cardPhone").val(result.bankCardMap.phone);
                 $(".bank_name").val(result.bankCardMap.bank);
                 $("#carno").val(result.bankCardMap.cardNo);
                 //工作信息
                 $("#companyname").val(result.workInfoMap.companyName);
                 $("#companyphone").val(result.workInfoMap.companyPhone);
                 $("#companyplace").val(result.workInfoMap.companyAddr);
              }
            }
    
    
        })
    }
    var getid = function () {
        var url = window.location.href;
        var index = url.indexOf("?id=")
        if(index ==-1){
            return null
        }
        var id = url.substring(index + 4)
        return id
    }

var addClick = function () {
        $(".credit_main ul li").on("click", function () {
            $(this).addClass("ab-hover")
            $(this).siblings("li").removeClass("ab-hover")
            if($(this).attr("id")==1){
               $(".person_inform").css("display","block");
               $(".person_inform").siblings("div").css("display","none");
            }
             if($(this).attr("id")==2){
               $(".bank_card").css("display","block");
               $(".bank_card").siblings("div").css("display","none");
            }
            if($(this).attr("id")==3){
               $(".work_info").css("display","block");
               $(".work_info").siblings("div").css("display","none");
            }
        })
    }

 function getObjectURLFront(file) {
            var url = null
            if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file)
            } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file)
            } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file)
                }
               return url
        }


     $("#front_pic").click(function () {
                $("#front_upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
                $("#front_upload").on("change",function(){
                var objUrl = getObjectURLFront(this.files[0])//获取图片的路径，该路径不是图片在本地的路径
                if (objUrl) {
                $("#front_pic").attr("src", objUrl)//将图片路径存入src中，显示出图片
                }
                });
            });  
     $("#obverse_pic").click(function () {
                $("#obverse_upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
                $("#obverse_upload").on("change",function(){
                var objUrl = getObjectURLFront(this.files[0])//获取图片的路径，该路径不是图片在本地的路径
                console.info(objUrl)
                if (objUrl) {
                $("#obverse_pic").attr("src", objUrl) //将图片路径存入src中，显示出图片
                }
                });
                });


 //表单验证个人信息
    require.async('../module/jquery-validation-1.13.0/jquery.validate.min', function () {
        require.async(['../module/jquery-validation-1.13.0/additional-methods', '/resources/js/module/layer/skin/default/layer.css', '/resources/js/module/layer/layer.min'], function () {
            $("#person_1").validate({
                rules: {
                    frontFile: {
                        required: true
                    },
                    obverseFile: {
                        required: true
                    },
                     name: {
                        required: true
                    },
                     idNo: {
                        required: true,
                        isIdCardNo: true
                    },
                    degree: {
                        required: true
                    },
                    address: {
                        required: true
                    }
                },
                messages: {
                    frontFile: {
                        required: "请上传身份证正面"
                    },
                    obverseFile: {
                        required: "请上传身份证反面"
                    },
                       name: {
                        required: "请输入真实姓名"
                    },
                     idNo: {
                        required: "请输入身份证",
                        isIdCardNo: "身份证格式错误"

                    },
                    degree: {
                        required: "请选择学历"
                    },
                    address: {
                        required: "请输入居住地址"
                    }
                },
                errorPlacement: function (error, element) {
                    element.parents("dd").find(".msg_tip").html(error);
                },
                success: function (element) {
                    if (1==1) {
                        //element.parents("dd").find(".msg_tip").html('');
                        element.parents("dd").find(".msg_tip").html('<i class="iconfont gre">&#xe605;</i>');
                    } else {
                        element.parents("dd").find(".msg_tip").html('');
                    }
                },
                submitHandler: function (form, event, validator) {
                    require.async(['../module/jquery.form.js',"../jquery.md5.js"], function () { 
                        var isborrow = $("#isborrow").val();
                        var data = {};
                        //data.frontPic=$("#front_upload").files[0]
                       // data.obversePic=$("#obverse_upload").files[0]
                        var formData =new FormData();
                        formData.append("frontPic",$("#front_upload")[0].files[0])
                        formData.append("obversePic",$("#obverse_upload")[0].files[0])
                        formData.append("name",$("#realname").val())
                        formData.append("idNo",$("#idno").val())
                        formData.append("degree",$(".degree option:selected").val())
                        formData.append("address",$("#addres").val())
                        console.info(formData);
                        if(isborrow==0||isborrow=="0"){
                        $.ajax({
                            url:"/modules/user/userbaseinfo/save.htm",
                            type: "post",
                            contentType: "multipart/form-data",
                            data:formData,
                            cache: false,
                            contentType: false,
                            processData: false,
                            dataType: "json",
                            success: function (result) {
                              if(result.code==200){
                                  alert(result.msg)
                              }
                            }
                        })
                    } else {
                        alert("存在未完成借款不可修改")
                    }
                    })
                }
            });

        })
    })

//表单验证 银行卡
    require.async('../module/jquery-validation-1.13.0/jquery.validate.min', function () {
        require.async(['../module/jquery-validation-1.13.0/additional-methods', '/resources/js/module/layer/skin/default/layer.css', '/resources/js/module/layer/layer.min'], function () {
            $("#bank_1").validate({
                rules: {
                     cname: {
                        required: true
                    },
                    cardPhone: {
                        required: true,
                        isMobile: true
                    },
                      bankname: {
                        required: true
                    },
                     carNo: {
                        required: true
                        //bankaccountNL: true
                    }
                },
                messages: {
                    cname: {
                        required: "请输入真实姓名"
                    },
                    cardPhone: {
                        required: "请填写绑定手机号",
                        isMobile: "手机号码格式错误"
                    },
                    bankname: {
                        required: "请选择银行"
                    },
                     carNo: {
                        required: "请输入银行卡号"
                        //bankaccountNL: "银行卡格式错误"
                    }
                },
                errorPlacement: function (error, element) {
                    element.parents("dd").find(".msg_tip").html(error);
                },
                success: function (element) {
                    if (1==1) {
                        //element.parents("dd").find(".msg_tip").html('');
                        element.parents("dd").find(".msg_tip").html('<i class="iconfont gre">&#xe605;</i>');
                    } else {
                        element.parents("dd").find(".msg_tip").html('');
                    }
                },
                submitHandler: function (form, event, validator) {
                    require.async(['../module/jquery.form.js',"../jquery.md5.js"], function () { 
                        var cname = $("#cname").val()
                        var cardPhone = $("#cardPhone").val()
                        var bankname = $(".bank_name option:selected").val()
                        var carNo = $("#carno").val()
                        var data = {"cname":cname,"cardPhone":cardPhone,"bankname":bankname,"carNo":carNo};
                        //console.info(data)
                        var isborrow = $("#isborrow").val();
                        if(isborrow==0||isborrow=="0"){
                            $.ajax({
                                url:"/modules/user/bankcard/save.htm",
                                type: "post",
                                data:data,
                                cache: false,
                                dataType: "json",
                                success: function (result) {
                                  if(result.code==200){
                                      alert(result.msg)
                                  }
                                }
                            })
                        }else{
                            alert("存在未完成借款不可修改")
                        }
                       
                    })
                }
            });

        })
    })
    


    //表单验证 工作信息
    require.async('../module/jquery-validation-1.13.0/jquery.validate.min', function () {
        require.async(['../module/jquery-validation-1.13.0/additional-methods', '/resources/js/module/layer/skin/default/layer.css', '/resources/js/module/layer/layer.min'], function () {
            $("#work_1").validate({
                rules: {
                    companyName: {
                        required: true
                    },
                     companyPhone: {
                        required: true
                    },
                      companyPlace: {
                        required: true
                    },
                },
                messages: {
                    companyName: {
                        required: "请输入公司名称"
                    },
                    companyPhone: {
                        required: "请输入公司电话"
                    },
                     companyPlace: {
                        required: "请输入公司地点"
                    }
                },
                errorPlacement: function (error, element) {
                    element.parents("dd").find(".msg_tip").html(error);
                },
                success: function (element) {
                    if (1==1) {
                        //element.parents("dd").find(".msg_tip").html('');
                        element.parents("dd").find(".msg_tip").html('<i class="iconfont gre">&#xe605;</i>');
                    } else {
                        element.parents("dd").find(".msg_tip").html('');
                    }
                },
                submitHandler: function (form, event, validator) {
                    require.async(['../module/jquery.form.js',"../jquery.md5.js"], function () {    
                        var companyName = $("#companyname").val()
                        var companyPhone = $("#companyphone").val()
                        var companyPlace = $("#companyplace").val()
                        var data = {"companyName":companyName,"companyPhone":companyPhone,"companyPlace":companyPlace};
                        var isborrow = $("#isborrow").val();
                        if(isborrow==0||isborrow=="0"){
                            $.ajax({
                                url:"/modules/user/workinfo/save.htm",
                                type: "post",
                                data:data,
                                cache: false,
                                dataType: "json",
                                success: function (result) {
                                  if(result.code==200){
                                      alert(result.msg)
                                  }
                                }
                            })
                        }else{
                            alert("存在未完成借款不可修改")
                        }
                     
                    })
                }
                
            });

        })
    })
//个人信息的提交按钮
// $("#person_submit").click(function () {
//     var isborrow = $("#isborrow").val();
//     var data = {};
//     //data.frontPic=$("#front_upload").files[0]
//    // data.obversePic=$("#obverse_upload").files[0]
//     var formData =new FormData();
//     formData.append("frontPic",$("#front_upload")[0].files[0])
//     formData.append("obversePic",$("#obverse_upload")[0].files[0])
//     formData.append("name",$("#realname").val())
//     formData.append("idNo",$("#idno").val())
//     formData.append("degree",$(".degree option:selected").val())
//     formData.append("address",$("#addres").val())
//     console.info(formData);
//     if(isborrow==0||isborrow=="0"){
//     $.ajax({
//         url:"/modules/user/userbaseinfo/save.htm",
//         type: "post",
//         contentType: "multipart/form-data",
//         data:formData,
//         cache: false,
//         contentType: false,
//         processData: false,
//         dataType: "json",
//         success: function (result) {
//           if(result.code==200){
//               alert(result.msg)
//           }
//         }
//     })
// } else {
//     alert("存在未完成借款不可修改")
// }

// })

//银行卡的提交按钮
// $("#bank_submit").click(function () {
//     var cname = $("#cname").val()
//     var cardPhone = $("#cardPhone").val()
//     var bankname = $(".bank_name option:selected").val()
//     var carNo = $("#carno").val()
//     var data = {"cname":cname,"cardPhone":cardPhone,"bankname":bankname,"carNo":carNo};
//     //console.info(data)
//     $.ajax({
//         url:"/modules/user/bankcard/save.htm",
//         type: "post",
//         data:data,
//         cache: false,
//         dataType: "json",
//         success: function (result) {
//           if(result.code==200){
//               alert(result.msg)
//           }
//         }


//     })


// })


//公司信息的提交按钮
// $("#work_submit").click(function () {
//     var companyName = $("#companyname").val()
//     var companyPhone = $("#companyphone").val()
//     var companyPlace = $("#companyplace").val()
//     var data = {"companyName":companyName,"companyPhone":companyPhone,"companyPlace":companyPlace};
//     console.info(data)
//     $.ajax({
//         url:"/modules/user/workinfo/save.htm",
//         type: "post",
//         data:data,
//         cache: false,
//         dataType: "json",
//         success: function (result) {
//           if(result.code==200){
//               alert(result.msg)
//           }
//         }


//     })


// })
   













});