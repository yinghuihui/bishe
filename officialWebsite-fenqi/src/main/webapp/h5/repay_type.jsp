<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <title>还款方式</title>
    <meta name="keywords" content="贷款,小额借钱,借贷,贷款app,急用钱,短期快速放贷,极速借款借钱,小额贷款">
    <meta name="description" content="优品钱包专注于为个人提供正规小额贷款、无抵押贷款、个人贷款、闪电借钱等服务">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <script src="/static/js/flexable.js"></script>
    <link href="/static/css/style1.css" rel="stylesheet"/>
	    <script src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/spin.js"></script>
    <script type="text/javascript" src="/static/js/common.js"></script>
    <script>
      var _hmt = _hmt || [];
      (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?985acfc678db5c774efb3ed1a2235b53";
        var s = document.getElementsByTagName("script")[0]; 
        s.parentNode.insertBefore(hm, s);
      })();
  </script>
  </head>
  <body>
    <div class="repayment-description">
    <ul>
      <li>如何赎回售出的手机？
        <p class="broTitle">在优品钱包平台开展手机售后回租业务的用户，7天租赁期满后，可申请赎回。赎回的方式有：
			<span>1、	登录优品钱包APP，通过在线支付方式，手动支付手机回购价格，即可赎回手机</span>
			<span>2、	平台会在还款日当天进行自动扣款，自动完成赎回操作，如果扣款不成功，可能会产生逾期记录，产生滞纳金（请优先使用在线支付方式）。</span>
		</p>
      </li>
      <li>在线支付方式有哪些？
        <p class="broTitle">登录优品钱包APP，在线还款方便快捷，支持多种还款方式，可手动提前还款：
        	<span>（1）	微信支付：手机需安装微信APP，按照操作步骤指示即可完成</span>
			<span>（2）	支付宝支付：手机需安装支付宝APP，按照操作步骤指示即可完成；</span>
			<span>（3）	银行卡支付：需在优品钱包绑定银行卡，还款时，选择“银行卡”支付，按照操作步骤指示，即可完成。</span>
        </p>
      </li>
      <li>平台自动扣款赎回的时间
        <p>平台会在租赁期满的到期日当天，进行自动扣款赎回，扣款时间为当日的22:00</p>
      </li>
      <li>手动支付赎回后，平台是否再次扣款?
        <p>手动发起在线支付赎回手机后，平台不会重复进行扣款</p>
      </li>
      <li>银行卡账户余额不足会影响自动扣款吗?
        <p>银行卡账户余额不足，会导致扣款失败，可能会造成逾期.</p>
      </li>
      <li>所有支付方式都无法还款，如何处理?
        <p>请联系优品钱包官方客服：<span class='phone'></span></p>
      </li>
    </ul>
  </div>
  <script type="text/javascript" src="/static/js/config.js" ></script>
  <script>
  $(function() {
    $("li").each(function(k,v){
      $(v).attr('id',k); 
    })
    $("li").click(function(){
      $(this).toggleClass("active");
      var id = $(this).attr('id');
      var lis = $('ul li').filter(function(i,e){
         return $(e).attr('id') != id;
      })
      lis.removeClass();
      
   });
 })
 $('.bank').text(getBank2());
 $('.airPay').text(getAirpay());
 $('.phone').text(getPhone());
 </script>  </body>
</html>
