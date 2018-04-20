	define(function(require, exports, module) {
	require('/resources/js/jquery');
	var getid = function () {
        var url = window.location.href;
        var index = url.indexOf("?id=")
        var id = url.substring(index + 4)
        return id
    }
        
    //获取当前类别
	// var arcType = $(".arcList").attr("data-val");
	//文章列表
	$.ajax({
		url:"/modules/web/content/listbyprogramaid.htm?programaId=" + getid() + "&current=1&pageSize=5&remark=20",
		type:"get",
		dataType:"json",
		success:function(result){
			require.async('../../module/handlebars-v1.3.0/handlebars-v1.3.0',function(){
				require.async('../../module/handlebars-v1.3.0/transFormatJson',function(){
					var tpl = require('./arcList.tpl');//载入tpl模板
					var template = Handlebars.compile(tpl);
					var data=result.data;
					var html = template(data);
					$(".arcContent").html(html);
				})
			});
			//分页插件
			if(result.page.pages > 0)
			{
				require.async(['../../module/pager/pager.css','../../module/pager/pager'],function(){
					kkpager.generPageHtml({
							pno : result.page.pageNum,//当前页码
							total : result.page.pages,//总页码
							totalRecords : result.page.total,//总数据条数
							isShowFirstPageBtn	: false, 
							isShowLastPageBtn	: false, 
							isShowTotalPage 	: false, 
							isShowTotalRecords 	: false, 
							isGoPage 			: false,
							lang:{
								prePageText				: '<',
								nextPageText			: '>'
							},
							mode:'click',//click模式匹配getHref 和 click
							click:function(n,total,totalRecords){
					        	$.ajax({
					        		type:"get",
					        		url:"/modules/web/content/listbyprogramaid.htm?programaId=" + getid() +"&current="+n+"&pageSize="+result.page.pageSize+"&remark=20",
					        		dataType:"json",//这个必不可少，如果缺少，导致传回来的不是json格式
					        		success:function(data){
					        			require.async(['../../module/handlebars-v1.3.0/handlebars-v1.3.0.js','../../module/handlebars-v1.3.0/transFormatJson'],function(){
											var tpl = require('./arcList.tpl');
											var template = Handlebars.compile(tpl);
											var html= template(data.data);
											$(".arcContent").html(html);
										});
					        		}
					        	});
								this.selectPage(n); //处理完后可以手动条用selectPage进行页码选中切换
							}
					});
				});
			}else{
				$("#kkpager").html('<p style="text-align:center;">暂无数据</p>');
			}
		}
		
	})
    })