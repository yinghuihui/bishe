<meta name="renderer" content="webkit" />
<link type="image/x-icon" rel="shortcut icon" href="/resources/img/favicon.ico" />
<link rel="stylesheet" href="/resources/css/web/top.css">
<link rel="stylesheet" href="/resources/css/web/footer.css">
<link rel="stylesheet" type="text/css" href="/resources/css/foundation/base.css">
<link rel="stylesheet" type="text/css" href="/resources/css/foundation/Zbase.css">
<link rel="stylesheet" type="text/css" href="/resources/css/web/seller.css">

<script type="text/javascript">
var glovalIsbuyerManage=false;
<#if pjsRole.nid == "buyerManage">
	// 是否是监管账户
	// console.log("监管账户")
	glovalIsbuyerManage = true;
</#if>
</script>

<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript" src="/resources/js/module/seajs-2.3.0/dist/sea.js"></script>
<script type="text/javascript" src="/resources/js/seajs-text.js"></script>
<script type="text/javascript" src="/resources/js/config.js"></script>
<script type="text/javascript" src="/resources/js/module/seajs-css/dist/seajs-css.js"></script>
<script type="text/javascript" src="/resources/js/module/seajs-circular/src/seajs-circular.js"></script>
<script type="text/javascript" src="/resources/js/module/JSON-js/json2.js"></script>
<script src="/resources/js/module/sockjs.min.js"></script>
<script type="text/javascript">
<#if sysUser.userName != ""> 
	seajs.use("/resources/js/alertInfo.js")
</#if>
</script>
