/**
 * [千分位方法]
 * @num 数字
 * @step 小数点位数【默认两位】
 */
function commafy(num, step) {
	var microValue;
	var floatSection; // 小数部分
	num = num && num.toString().replace(/,/g, ''); //避免带逗号的数据传入
	var value = String(parseFloat(num)); // 吧传入数据转换为float后再转换为字符串

	var arr = value.split('.');
	var isFloat = arr.length == 2; //判断是否是浮点数
	if (isFloat) {
		floatSection = arr[1].substr(0, step); // 小数部分
		floatSection = floatSection.length == 2 ? floatSection : floatSection + "0"
		value = arr[0]; //整数部分
	}

	// 小数点前面部分设置千分位
	var microValue = value.replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function(s) {
		// console.log(s)
		return s + ',';
	});

	if (isFloat) {
		microValue = microValue + '.' + floatSection
	} else {
		microValue = microValue + '.' + "00"
	}

	if (typeof microValue == 'undefined' || microValue == 'NaN') {
		microValue = ""
	}
	return microValue;
}


//编号递增
Handlebars.registerHelper("increasingIndex", function(value) {
	return value + 1;
})

function formatFloat(f, digits) {
	digits = !!digits ? digits : 10; // 默认精度为10
	return parseFloat(f.toFixed(digits))
}
// 两个参数相乘
Handlebars.registerHelper("multiplicationFn", function(v1, v2, options) {
	// return value + 1;
	var v1 = v1 || 0,
		v2 = v2 || 0;
	// formatFloat = function(f, digits) {
	// 	digits = !!digits ? digits : 10; // 默认精度为10
	// 	return parseFloat(f.toFixed(digits))
	// }
	var result = formatFloat(v1 * v2);
	// console.log(formatFloat(result, 2))
	return commafy(formatFloat(result, 2));
})

// 两个参数相减
Handlebars.registerHelper("subtractionFn", function(v1, v2, options) {
	// return value + 1;
	var v1 = v1 || 0,
		v2 = v2 || 0;
	var result = formatFloat(v1 - v2);
	// console.log(formatFloat(result, 2))
	return commafy(formatFloat(result, 2));
})

// 将json对象转换成json对符串 
Handlebars.registerHelper("toJsonString", function(jsonDate) {
	return JSON.stringify(jsonDate);
})


//时间转换  格式:2014-08-15 00:00:00
Handlebars.registerHelper("transFormatDate", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		if (minute < 10) {
			minute = '0' + minute;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
	if (value == null || value == '') {
		return '-';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});
//时间转换  格式:2014-08-15 00:00
Handlebars.registerHelper("transFormatShortDate", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		if (minute < 10) {
			minute = '0' + minute;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});
// 格式:2014/08/15
Handlebars.registerHelper("noticeDateFormat", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		return year + "/" + month + "/" + date;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});

// 格式:2014-08-15
Handlebars.registerHelper("dateFormat", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		return year + "-" + month + "-" + date;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});

// 格式:2014/08/15 12:00
Handlebars.registerHelper("noticeDateFormatNew", function(value) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (month < 10) {
			month = '0' + month;
		}
		if (date < 10) {
			date = '0' + date;
		}
		if (hour < 10) {
			hour = '0' + hour;
		}
		if (second < 10) {
			second = '0' + second;
		}
		return year + "/" + month + "/" + date + " " + hour + ":" + second;
	}
	if (value == null || value == '') {
		return '';
	}
	var d = new Date(value.replace(new RegExp(/-/gm), "/"));
	return formatDate(d);
});



// 保留两位小数
Handlebars.registerHelper("tofixed", function(value) {
	return value.toFixed(2);
});


//时间格式转换 2014-8-18 16:09:27
Handlebars.registerHelper("timeFormat", function(time) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
	if (time == null || time == '') {
		return '';
	}
	var d = new Date(time);
	return formatDate(d);
})

//时间格式转换 2014-9-4
Handlebars.registerHelper("timeMonthFormat", function(time) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (date < 10) {
			date = "0" + date;
		}
		return year + "-" + month + "-" + date;
	}
	if (time == null || time == '') {
		return '';
	}
	var d = new Date(time);
	return formatDate(d);
})

//时间格式转换 2014年7月10日 12:32:00
Handlebars.registerHelper("timeMsgFormat", function(time) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();

		if (hour < 10) {
			hour = '0' + hour;
		}

		if (minute < 10) {
			minute = '0' + minute;
		}

		if (second < 10) {
			second = '0' + second;
		}
		return year + '年' + month + '月' + date + '日' + '' + hour + ':' + minute + ':' + second;
	}
	if (time == null || time == '') {
		return '';
	}
	return formatDate(new Date(time));
})

//时间格式转换 2014年7月10日
Handlebars.registerHelper("dateFormatChinese", function(time) {
	function formatDate(now) {
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();

		if (hour < 10) {
			hour = '0' + hour;
		}

		if (minute < 10) {
			minute = '0' + minute;
		}

		if (second < 10) {
			second = '0' + second;
		}
		return year + '年' + month + '月' + date + '日';
	}
	if (time == null || time == '') {
		return '';
	}
	return formatDate(new Date(time));
})

/**
 * [金额格式化]
 * 格式: 1,000.00
 */
Handlebars.registerHelper("toThousands", function(num) {
	// var step = step || 2;

	if (typeof num == 'undefined' || num == 'NaN') {
		return "-";
	} else {
		var microValue = commafy(num, 2)
		return microValue;
	}
})

//--S--个人中心-我的资料
//婚姻状况
Handlebars.registerHelper("transMaritalStatus", function(value) {
	switch (value) {
		case 1:
			return "未婚";
			break;
		case 2:
			return "已婚";
			break;
		case 3:
			return "离异";
			break;
		case 4:
			return "丧偶";
			break;
		default:
			return "";
			break;
	}
});


//年龄
Handlebars.registerHelper("transBirthday", function(value) {
	return (new Date()).getFullYear() - (new Date(value)).getFullYear() + "岁";
});

//车产情况
Handlebars.registerHelper("transCar", function(value) {
	switch (value) {
		case 1:
			return "无";
			break;
		case 2:
			return "有";
			break;
		default:
			return "";
			break;
	}
});

//车产情况
Handlebars.registerHelper("transRepaymentType", function(value) {
	return {
		1: '先息后本',
		2: '等额本息'
	}[value];
});

//房产状况
Handlebars.registerHelper("transLive", function(value) {
	switch (value) {
		case 0:
			return "自有住房";
			break;
		case 1:
			return "租赁";
			break;
		case 2:
			return "与亲属同住";
			break;
		case 3:
			return "公司宿舍";
			break;
		case 4:
			return "其它";
			break;
		default:
			return "";
			break;
	}
});

//教育情况
Handlebars.registerHelper("transEducation", function(value) {
	switch (value) {
		case 1:
			return "博士";
			break;
		case 2:
			return "硕士";
			break;
		case 3:
			return "本科";
			break;
		case 4:
			return "专科";
			break;
		case 5:
			return "高中及以下";
			break;
		default:
			return "";
			break;
	}
});


//个人中心-我的贷款-贷款类型
Handlebars.registerHelper("transproductType", function(value) {
	switch (value) {
		case 1:
			return "短期";
			break;
		case 2:
			return "长期";
		case 3:
			return "融资租赁";
			break;
		default:
			return "";
			break;
	}
});

//个人中心-我的贷款-还款状态
Handlebars.registerHelper("transRepaymentStaus", function(value) {
	switch (value) {
		case 1:
			return "正常还款";
			break;
		case 2:
			return "逾期未还款";
			break;
		case 3:
			return "逾期还款";
			break;
		case 4:
			return "提前还款";
			break;
		case 0:
			return "还款中";
			break;
		default:
			return "";
			break;
	}
});


//个人中心-我的贷款-还款情况表-还款状态
Handlebars.registerHelper("tranrepaymentType", function(value) {
	switch (value) {
		case 0:
			return "还款中";
			break;
		case 1:
			return "结清";
			break;
		case 2:
			return "逾期";
			break;
		default:
			return "";
			break;
	}
});


// 个人中心-我的贷款-审批情况
Handlebars.registerHelper("transtaskDef", function(value) {
	switch (value) {
		case 0:
			return new Handlebars.SafeString('<span class="green">贷款受理</span>');
			break;
		case 1:
			return new Handlebars.SafeString('<span class="green">贷款受理</span>');
			break;
		case 2:
			return new Handlebars.SafeString('<span class="red">审批失败</span>');
			break;
		case 3:
			return new Handlebars.SafeString('<span class="green">已结清</span>');
			break;
		default:
			return "";
			break;
	}
});


Handlebars.registerHelper("getPlatformFee", function(record) {
	var fee = record.account - record.capital - record.interest - record.parkingFee;
	return fee.toFixed(2);
});

Handlebars.registerHelper("getAllAccount", function(record) {
	var allAccount = record.account + record.penalty + record.defaultInterest;
	return allAccount.toFixed(2);
});

//判断为空或者null或者undefined
Handlebars.registerHelper("isBlank", function(v1, options) {
	if (v1 == "" || v1 == null || v1 == undefined) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

//判断不为空或者null或者undefined
Handlebars.registerHelper("isNotBlank", function(v1, options) {
	if (v1 != null && v1 != undefined && v1.toString() != "") {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

Handlebars.registerHelper('operation', function(first, operation, second) {
	if (first !== undefined && operation && second) {
		if (isNaN(first) || isNaN(second)) {
			return 'error';
		}

		var opt = {
			'+': function(l, r) {
				return l + r;
			},
			'-': function(l, r) {
				return l - r;
			},
			'*': function(l, r) {
				return l * r;
			},
			'/': function(l, r) {
				return l / r;
			}
		}
		var scale = arguments[3];

		if (scale && typeof scale == 'number') {
			return opt[operation](first, second).toFixed(scale);
		} else {
			return opt[operation](first, second);
		}
	} else {
		// return multiplicand;
		console.log('first:' + first + ' operation:' + operation + ' second:' + second);
		return 'error';
	}
});

// 判断相等
Handlebars.registerHelper("equal", function(v1, v2, options) {
	if (v1 == v2) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

// 不相等
Handlebars.registerHelper("notEqual", function(v1, v2, options) {
	if (v1 != v2) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});

// 判断是否是正数
Handlebars.registerHelper("isPositive", function(value, options) {
	var isPositive = /^[1-9]\d*$/.test(value);
	if (isPositive) {
		//满足添加继续执行
		return options.fn(this);
	} else {
		//不满足条件执行{{else}}部分
		return options.inverse(this);
	}
});


//剩余天数
Handlebars.registerHelper("stayDateFormat", function(dueDate, serverTime) {
	if (new Date(dueDate) > 0) {
		var nowDate = new Date(serverTime);
		var dueTime = new Date(dueDate.toString().substring(0, 10));
		var s1 = dueTime.getTime(),
			s2 = nowDate.getTime();
		var total = (s1 - s2) / 1000;
		var day = parseInt(total / (24 * 60 * 60)); //计算整数天数
		day = day > 0 ? day : 0;
		return day;
	} else {
		return "-";
	}

});
Handlebars.registerHelper("Exif", function(v1, operator, v2, options) {
	switch (operator) {
		case "==":
			return (v1 == v2) ? options.fn(this) : options.inverse(this);

		case "!=":
			return (v1 != v2) ? options.fn(this) : options.inverse(this);

		case "===":
			return (v1 === v2) ? options.fn(this) : options.inverse(this);

		case "!==":
			return (v1 !== v2) ? options.fn(this) : options.inverse(this);

		case "&&":
			return (v1 && v2) ? options.fn(this) : options.inverse(this);

		case "||":
			return (v1 || v2) ? options.fn(this) : options.inverse(this);

		case "<":
			return (v1 < v2) ? options.fn(this) : options.inverse(this);

		case "<=":
			return (v1 <= v2) ? options.fn(this) : options.inverse(this);

		case ">":
			return (v1 > v2) ? options.fn(this) : options.inverse(this);

		case ">=":
			return (v1 >= v2) ? options.fn(this) : options.inverse(this);

		default:
			return eval("" + v1 + operator + v2) ? options.fn(this) : options.inverse(this);
	}
});

//剩余时间 格式 4:00
Handlebars.registerHelper("getLastTime", function(countDown) {
	if (countDown > 0) {
		var min = parseInt(countDown / 60); //分钟
		var second = countDown % 60; //秒
		return min + ":" + second;
	} else {
		return "0:00"
	}
})

//倒计时功能
Handlebars.registerHelper("showListCountDownTimeFun", function(idName, idIndex, putStartTime, nowTime) {
	// console.log(idName)
	// var sys_second1 = (putStartTime - nowTime) / 1000;
	var putStartTime = putStartTime || 0;
	var sys_second1 = putStartTime; //剩余时间

	var timer1 = setInterval(function() {
		if (sys_second1 >= 1) {
			sys_second1 -= 1;
			var day1 = Math.floor((sys_second1 / 3600) / 24);
			var hour1 = Math.floor((sys_second1 / 3600) % 24);
			var minute1 = Math.floor((sys_second1 / 60) % 60);
			var second1 = Math.floor(sys_second1 % 60);
			var showDay1 = day1;
			var showHour1 = hour1 < 10 ? "0" + hour1 : hour1; //计算小时
			var showMinute1 = minute1 < 10 ? "0" + minute1 : minute1; //计算分钟
			var showSecond1 = second1 < 10 ? "0" + second1 : second1; //计算秒杀
			// $("#showTime" + id).html('<i>' + showDay1 + '</i>天<i>' + showHour1 + '</i>时<i>' + showMinute1 + '</i>分<i>' + showSecond1 + '</i>秒');
			$("#" + idName + idIndex).html(showMinute1 + ":" + showSecond1);
		} else {
			clearInterval(timer1);
			// window.location.reload();
		}
	}, 1000);
});

Handlebars.registerHelper("ruleListExpires", function(expires) {
	switch (expires) {
		case "0":
			return "不限";
			break;
		case "1":
			return "足月:180天";
			break;
		case "2":
			return "足月:360天";
			break;
		case "3":
			return "不足月:1-180天";
			break;
		case "4":
			return "不足月:181-360天";
			break;
		default:
			return "不限";
			break;
	}
})


/**
 * [状态列表-和数据字典对应]
 * @type {Object}
 */
var statusList = {
	"01": "等待买方",
	"02": "待买方确认",
	"06": "待卖家签收电子担保函",
	"09": "待卖家签署合同",
	"10": "待买方付款",
	"12": "待卖家背书",
	"15": "待买家签收",
	"17": "待平台转账",
	"18": "待卖家确认收款",
	"21": "交割完成",
	"24": "交割失败"
}

/**
 * [orderDetail.tpl 详情展示函数是否展开]
 * 参数：
 * 操作列表 pjsOrderProcesseList
 * 数据状态 orderStatus
 * 当前数据状态值 indexStatus
 * 剩余时间 countDown
 * 
 * 规则：
 * // 数据状态 == 当前数据状态值 时 展开
 * // 列表中数据状态 和 当前匹配 表示已操作
 */
Handlebars.registerHelper("orderDetailActive", function(pjsOrderProcesseList, orderStatus, indexStatus, countDown) {
	// console.log(orderStatus == indexStatus)

	var className = ""; //样式名称
	var listLength = pjsOrderProcesseList.length || 0;

	for (i = 0; i < listLength; i++) {
		var thisOrder = pjsOrderProcesseList[i];

		// 列表中数据状态 和 当前匹配 表示已操作
		if (thisOrder.status == indexStatus) {
			// 表示该操作已执行
			className = "isOver ";
			return className;
		} else if (orderStatus == indexStatus) {
			// 表示在当前状态
			className = "isOver active";
			return className;

			// if (pjsOrderProcesseList[listLength - 1].status == indexStatus) {
			// 	// 表示该操作已执行 并且当前状态
			// 	className = "isOver active";
			// 	return className;
			// } else {
			// 	// 在当前状态【未执行】
			// 	className = "isOver active";
			// 	return className;
			// }
		}
	}


})

/**
 * [展开状态函数]
 * 参数：
 * 操作列表 pjsOrderProcesseList
 * 数据状态 orderStatus
 * 当前数据状态值 indexStatus
 * 剩余时间 countDown
 */
Handlebars.registerHelper("orderDetailOpen", function(pjsOrderProcesseList, orderStatus, indexStatus, countDown) {

	var listLength = pjsOrderProcesseList.length || 0;
	var htmlTest;
	for (i = 0; i < listLength; i++) {
		var thisOrder = pjsOrderProcesseList[i];

		// 列表中数据状态 和 当前匹配 有操作时间
		if (thisOrder.status == indexStatus) {
			htmlTest = thisOrder.createTime + "&nbsp;&nbsp;&nbsp;&nbsp;已操作";
			return new Handlebars.SafeString(htmlTest);
		} else {
			//最后一条数据才能返回[最后一条数据判断是否需要倒计时]
			if (i == listLength - 1) {
				htmlTest = "未操作"
					// 倒计时大于0 && 数据状态和列表中数据状态一致时 才有倒计时
					// 资金转账、交易完成、交易关闭 不需要倒计时
				if (countDown > 0 && orderStatus == indexStatus && orderStatus != "17" && orderStatus != "21" && orderStatus != "24") {
					htmlTest = htmlTest + '<div class="time"><span class="alarm"></span><span id="detailTimeFun">0:00</span></div>'
				}

				return new Handlebars.SafeString(htmlTest);
			}
		}
	}
	// timer1();

})

/**
 * [每个状态返回的文案]
 * orderStatus 当前状态
 */
Handlebars.registerHelper("orderStatusText", function(orderStatus) {
	return statusList[orderStatus]
		// $.ajax({
		// 		url: "/modules/common/action/ComboAction/queryDic.htm?typeCode=ORDER_STATUS",
		// 		type: "get",
		// 		dataType: "json",
		// 		success: function(result) {
		// 			var resultDate = result.data
		// 			var dataLength = resultDate.length;
		// 			var newDate = {};
		// 			for (var i = 0; i < dataLength; i++) {
		// 				var valId = resultDate[i].value;
		// 				newDate[valId] = resultDate[i].text;
		// 			}
		// 			console.log(newDate)
		// 		}
		// 	})
})

/**
 * [返回卖家操作状态]
 */
Handlebars.registerHelper("sentSellerOperation", function(orderStatus, id, receiptId, buyerId) {
	var commonHtml = '<a class="orderInfor"  href="javascript:;">查看</a>' + '<a class="cancel"  href="javascript:;" data-id=' + id + ' data-status=' + orderStatus + ' data-receiptId=' + receiptId + '>取消</a>';

	var reciteYes = '<a class="reciteYes"  href="javascript:;" data-id=' + id + ' data-status=' + orderStatus + '>已背书</a>' //背书操作

	var signGuarantee = '<a class="signGuarantee"  href="javascript:;" data-id=' + id + ' data-status=' + orderStatus + '>签担保函</a>' //签收担保函

	var signContract = '<a class="signContract"  href="javascript:;" data-id=' + id + ' data-status=' + orderStatus + ' data-buyerId=' + buyerId + ' >签合同</a>' //签署合同

	var getMoney = '<a class="getMoney" href="javascript:;" data-id=' + id + ' data-status=' + orderStatus + ' data-receiptId="' + receiptId + '">确认收款</a>' + '<a class="orderInfor"  href="javascript:;">查看</a>' //确认收款


	if (orderStatus == "06") {
		//签担保函
		return new Handlebars.SafeString(signGuarantee + commonHtml)
	} else if (orderStatus == "09") {
		// 签署合同
		return new Handlebars.SafeString(signContract + commonHtml)
	} else if (orderStatus == "12") {
		// 背书操作
		return new Handlebars.SafeString(reciteYes + commonHtml)
	} else if (orderStatus == "18") {
		//确认收款
		return new Handlebars.SafeString(getMoney)
	} else if (orderStatus == "15" || orderStatus == "17") {
		// 待买家签收, 平台转账  时候不能取消
		return new Handlebars.SafeString('<a class="orderInfor"  href="javascript:;">查看</a>')
	} else {
		// 返回 查看+取消 功能
		return new Handlebars.SafeString(commonHtml)
	}
})

/**
 * [返回买家操作状态]
 */
Handlebars.registerHelper("sentBuyerOperation", function(orderStatus, id, receiptId, buyerId, creator) {
	var commonHtml = '<a class="orderInfor"  href="javascript:;">查看</a>' + '<a class="cancel"  href="javascript:;" data-id=' + id + ' data-status=' + orderStatus + ' data-receiptId=' + receiptId + '>取消</a>';

	var confirm = '<a class="confirm" href="javascript:;" data-id=' + id + '>确认</a>'; //确认
	var payNow = '<a class="payNow" href="javascript:;" data-id=' + id + ' data-buyerId=' + buyerId + ' data-receiptId=' + receiptId + ' data-orderStatus=' + orderStatus + ' data-creator=' + creator + '>支付</a>'; //支付
	var signFor = '<a class="signFor" href="javascript:;" data-id=' + id + ' data-buyerId=' + buyerId + ' data-receiptId=' + receiptId + ' data-orderStatus=' + orderStatus + '>确认签收</a>' + '<a class="orderInfor"  href="javascript:;">查看</a>'; //确认签收


	if (orderStatus == "02") {
		//买家确认
		return new Handlebars.SafeString(confirm + commonHtml)
	} else if (orderStatus == "10") {
		// 支付
		return new Handlebars.SafeString(payNow + commonHtml)
	} else if (orderStatus == "15") {
		// 确认签收
		return new Handlebars.SafeString(signFor)
	} else if (orderStatus == "17" || orderStatus == "18") {
		// 平台转账 和 卖方确认收款 时候不能取消
		return new Handlebars.SafeString('<a class="orderInfor"  href="javascript:;">查看</a>')
	} else {
		// 返回 查看+取消 功能
		return new Handlebars.SafeString(commonHtml)
	}

})

/**
 * [卖家-我的票源状态]
 * orderStatus==null or  orderStatus== "24"可以发起交易
 */
Handlebars.registerHelper("myTikiteSellerStatus", function(orderStatus, receiptNo, receiptStatus) {
	if (orderStatus == null || orderStatus == "24") {
		return new Handlebars.SafeString('<a class="startTrade" data-tikiteNo="' + receiptNo + '" data-receiptStatus="' + receiptStatus + '">发起交割</a>')
	} else {
		return new Handlebars.SafeString('<a class="checkStartTrade" data-tikiteNo="' + receiptNo + '">查看</a>')
	}

})

/**
 * 根据areaCode 返回省市
 */
Handlebars.registerHelper("showBankArea", function(areaCode) {
	// console.log(areaCode)
	var selectRegion = function(regionCode) {
		var regionCode = regionCode || "110101"; //如果不传regionCode,默认显示北京
		var _data = CHINA_REGION;

		var selectProvinceCode = regionCode.substr(0, 2) + "0000"; //选择的省ID
		var selectCitysCode = regionCode.substr(0, 4) + "00"; //选择的市ID
		var selectAreasCode = regionCode; //选择的地区ID

		var province = _filter("0"); //省列表
		var citys = _filter(selectProvinceCode); //城市列表
		var areas = _filter(selectCitysCode); //地区列表

		var renturnProvinceName = selectProvince(selectProvinceCode); //返回的省的名字
		var renturnCityeName = selectCitys(selectCitysCode); //返回的省的名字

		/**
		 * 根据pid查询子节点
		 */
		function _filter(pid) {
			var result = [];

			for (var code in _data) {
				if (_data.hasOwnProperty(code) && _data[code][1] === pid) {
					result.push([code, _data[code][0]]);
				}
			}
			return result;
		}

		// 选择省
		function selectProvince(selectProvinceCode) {
			var provinceStr = ""
			for (var i = 0; i < province.length; i++) {
				var provinceName = province[i][1];
				var provinceCode = province[i][0];
				if (provinceCode == selectProvinceCode) {
					provinceStr = provinceName;
					break;
					// provinceStr += '<option value="' + provinceCode + '" selected = "selected">' + provinceName + '</option>'
				}

			}
			return provinceStr;
			// $("#province").html(provinceStr)
		}

		// 选着市
		function selectCitys(provinceCode) {
			var citysStr = ""
				// citys = _filter(provinceCode);
			for (var i = 0; i < citys.length; i++) {
				var citysName = citys[i][1];
				var citysCode = citys[i][0];

				if (citysCode == provinceCode) {
					citysStr = citysName;
					break;
					// citysStr += '<option value="' + citysCode + '" selected = "selected">' + citysName + '</option>'
				}
			}
			// console.log(citysStr)
			return citysStr;
		}

		return renturnProvinceName + "-" + renturnCityeName
	}

	return selectRegion(areaCode)

})