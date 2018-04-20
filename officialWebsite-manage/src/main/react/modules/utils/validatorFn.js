   //验证各类字段方法
   var validatorFn = {
       officeCodeCheck(rule, value, callback) {
           if (!value) {
               return callback();
           } else {
               if (/^\d{8}-\d$/.test(value)) {
                   callback();
               } else {
                   callback([new Error('请输入正确的企业代码')]);
               }
           }
       },
       required: function(rule, value, callback) {
           if (value) {
               callback();
           } else {
               callback([new Error('必填')]);
           }
       },
       provincialCity: function(rule, value, callback) { //省市区验证请选择
           if (!value) {
               return callback();
           }
           if (value == '100101') {
               callback(' ');
           } else {
               callback();
           }
       },
       // 验证中文
       isChinese: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (!/^[\u2E80-\u9FFF]+$/.test(value)) {
               callback('请输入中文名称');
           } else {
               callback();
           }
       },
       // 验证英文
       isEnglish: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (!/^[a-zA-Z_]+$/.test(value)) {
               callback('请输入英文和下划线');
           } else {
               callback();
           }
       },

       bankNumber: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (value.match(/^[0-9\.]{1,35}$/) || value == 0) {
               callback();
           } else {
               callback('请输入正确的银行卡号')
           }
       },

       bankLength: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (value.match(/^\d{16,19}$/) || value == 0) {
               callback();
           } else {
               callback('请输入正确的银行卡号')
           }
       },
       //验证是否是整数
       isNumber: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (!!value && String(value).match(/^-?\d+$/g) || value == 0) {
               callback();
           } else {
               callback('请输入整数');
           }
       },
       isTrueNumber: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (!!value && String(value).match(/^\+?[1-9][0-9]{0,3}$/g)) {
               callback();
           } else {
               callback('请输入不超过9999的整数');
           }
       },
       isAdTrueNumber: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (!!value && String(value).match(/^\+?[1-9][0-9]{0,19}$/g)) {
               callback();
           } else {
               callback('请输入不超过9999的整数');
           }
       },
       number: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (!!value && value.match(/^\+?[1-9][0-9]*$/g) || value == 0) {
               callback();
           } else {
               callback('请输入大于等于0的整数');
           }
       },
       registeredCapital: function(rule, value, callback) {
           if (!value) {
               return callback();
           }
           if (!!value && value > 0 || value == 0) {
               callback();
           } else {
               callback('需大于等于0');
           }
       },
       //输入位数
       isTrueIntNumber: function(rule, value, callback) {
           var intValue = parseInt(value)
           if (!intValue) {
               return callback();
           }
           if (!!intValue && String(intValue).match(/^\+?[1-9][0-9]{0,3}$/g)) {
               callback();
           } else {
               callback('放款金额不能超过10000元');
           }
       },
       // 验证正浮点数
       isFloat: function(rule, value, callback) {

           if (!value) {
               return callback();
           }
           if(typeof value === 'number'){
               value = value.toString();
           }
           if (!!value && value.match(/^([1-9]\d*|0)(\.\d{1,2})?$/g) || value == 0) {
               callback();
           } else {
               callback('请输入数字,仅限两位小数');
           }
       },
       //手机验证
       checkMobile(rule, value, callback) {
           var re = /^(13[0-9]|147|15[012356789]|17[678]|18[0-9])[0-9]{8}$/;
           if (!value || re.test(value)) {
               callback();
           } else {
               callback([new Error('手机格式错误')]);
           }
       },
       //手机段号验证
       checkNoMobile(rule, value, callback) {
           var re = /^(13[0-9]|147|15[012356789]|17[678]|18[0-9])[0-9]{4}$/;
           if (!value || re.test(value)) {
               callback();
           } else {
               callback([new Error('手机段号格式错误')]);
           }
       },
       //邮箱验证
       checkEmail(rule, value, callback) {
           var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
           if (!value || re.test(value)) {
               callback();
           } else {
               callback([new Error('邮箱格式错误')]);
           }
       },
       //空格验证
       checkBlackCode(rule, value, callback) {
           var re = /^[1-9][0-9]{5}$/;
           if (!value || value.trim()) {
               callback();
           } else {
               callback([new Error('邮编格式错误')]);
           }
       },
       //输入长度验证
       checkLength(length, rule, value, callback) {
           if (!value || value.length < length) {
               callback();
           } else {
               callback([new Error(`需小于${length}个字符`)]);
           }
       },
       //输入反馈长度验证
       checkFeedbackLength(rule, value, callback) {
           if (!value || value.length < 401) {
               callback();
           } else {
               callback([new Error('需小于401个字符')]);
           }
       },
       //输入渠道长度验证
       checkChannelLength(rule, value, callback) {
           if (!value || value.length < 16) {
               callback();
           } else {
               callback([new Error('需小于16个字符')]);
           }
       },
       //输入手机名称验证
       checkMobileNameLength(rule, value, callback) {
           if (!value || value.length < 15) {
               callback();
           } else {
               callback([new Error('需小于15个字符')]);
           }
       },
       //输入手机型号验证
       checkMobileModelLength(rule, value, callback) {
           if (!value || value.length < 30) {
               callback();
           } else {
               callback([new Error('需小于30个字符')]);
           }
       },
       //处理备注长度验证
       checkRemarkLength(rule, value, callback) {
           if (!value || value.length < 50) {
               callback();
           } else {
               callback([new Error('需小于50个字符')]);
           }
       },
       //处理语音长度验证
       checkVioceLength(rule, value, callback) {
           if (!value || value.length < 32) {
               callback();
           } else {
               callback([new Error('需小于32个字符')]);
           }
       },
       //处理长度验证
       checkSixFourLength(rule, value, callback) {
           if (!value || value.length < 64) {
               callback();
           } else {
               callback([new Error('需小于64个字符')]);
           }
       },
       //处理手机段号长度验证
       checkMobileLength(rule, value, callback) {
           if (!value || value.length < 8) {
               callback();
           } else {
               callback([new Error('需小于8个字符')]);
           }
       },
       //处理手机段号长度验证
       checkBusinessLength(rule, value, callback) {
           if (!value || value.length < 9) {
               callback();
           } else {
               callback([new Error('需小于9个字符')]);
           }
       },
       //处理意见长度验证
       checkRemark(rule, value, callback) {
           if (!value || value.length < 513) {
               callback();
           } else {
               callback([new Error('需小于512个字符')]);
           }
       },
       //社会信用代码长度验证
       checkSocialCreditCode(rule, value, callback) {
           if (!value || value.length < 256) {
               callback();
           } else {
               callback([new Error('需小于256个字符')]);
           }
       },
       //中证码长度验证
       checkIntermediateCode(rule, value, callback) {
           if (!value || value.length < 31) {
               callback();
           } else {
               callback([new Error('需小于31个字符')]);
           }
       },
       //证件号码长度验证
       checkIdentificationCode(rule, value, callback) {
           if (!value || value.length < 64) {
               callback();
           } else {
               callback([new Error('需小于64个字符')]);
           }
       },
       //企业名称长度验证
       checkEcustName(rule, value, callback) {
           if (!value || value.length < 128) {
               callback();
           } else {
               callback([new Error('需小于128个字符')]);
           }
       },
       //评分卡名称长度验证
       checkItemName(rule, value, callback) {
           if (!value || value.length < 32) {
               callback();
           } else {
               callback([new Error('需小于32个字符')]);
           }
       },
       //广告-标题长度验证
       adLengthName(rule, value, callback) {
           if (!value || value.length < 50) {
               callback();
           } else {
               callback([new Error('需小于50个字符')]);
           }
       },
       //广告-地址长度验证
       adAddName(rule, value, callback) {
           if (!value || value.length < 100) {
               callback();
           } else {
               callback([new Error('需小于100个字符')]);
           }
       },
       //广告-排序号长度验证
       adNoName(rule, value, callback) {
           if (!value || value.length < 20) {
               callback();
           } else {
               callback([new Error('需小于20个字符')]);
           }
       },
       //规则-排序号长度验证
       adRulesLength(rule, value, callback) {
           if (!value || value.length < 3) {
               callback();
           } else {
               callback([new Error('需小于3个字符')]);
           }
       },
       //严格身份证验证
       checkIdCode(rule, value, callback) {
           if (!value || validatorFn.IdCardValidate(value)) {
               callback();
           } else {
               callback([new Error('身份证格式错误')]);
           }
       },
       Wi: [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1], // 加权因子
       ValideCode: [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2], // 身份证验证位值.10代表X
       IdCardValidate(idCard) {
           idCard = validatorFn.trim(idCard.replace(/ /g, "")); //去掉字符串头尾空格
           if (idCard.length == 15) {
               return validatorFn.isValidityBrithBy15IdCard(idCard); //进行15位身份证的验证
           } else if (idCard.length == 18) {
               var a_idCard = idCard.split(""); // 得到身份证数组
               if (validatorFn.isValidityBrithBy18IdCard(idCard) && validatorFn.isTrueValidateCodeBy18IdCard(a_idCard)) { //进行18位身份证的基本验证和第18位的验证
                   return true;
               } else {
                   return false;
               }
           } else {
               return false;
           }
       },
       /**
        * 判断身份证号码为18位时最后的验证位是否正确
        * @param a_idCard 身份证号码数组
        * @return
        */
       isTrueValidateCodeBy18IdCard(a_idCard) {
           var sum = 0; // 声明加权求和变量
           if (a_idCard[17].toLowerCase() == 'x') {
               a_idCard[17] = 10; // 将最后位为x的验证码替换为10方便后续操作
           }
           for (var i = 0; i < 17; i++) {
               sum += validatorFn.Wi[i] * a_idCard[i]; // 加权求和
           }
           var valCodePosition = sum % 11; // 得到验证码所位置
           if (a_idCard[17] == validatorFn.ValideCode[valCodePosition]) {
               return true;
           } else {
               return false;
           }
       },
       /**
        * 验证18位数身份证号码中的生日是否是有效生日
        * @param idCard 18位书身份证字符串
        * @return
        */
       isValidityBrithBy18IdCard(idCard18) {
           var year = idCard18.substring(6, 10);
           var month = idCard18.substring(10, 12);
           var day = idCard18.substring(12, 14);
           var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
           // 这里用getFullYear()获取年份，避免千年虫问题
           if (temp_date.getFullYear() != parseFloat(year) ||
               temp_date.getMonth() != parseFloat(month) - 1 ||
               temp_date.getDate() != parseFloat(day)) {
               return false;
           } else {
               return true;
           }
       },
       /**
        * 验证15位数身份证号码中的生日是否是有效生日
        * @param idCard15 15位书身份证字符串
        * @return
        */
       isValidityBrithBy15IdCard(idCard15) {
           var year = idCard15.substring(6, 8);
           var month = idCard15.substring(8, 10);
           var day = idCard15.substring(10, 12);
           var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
           // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
           if (temp_date.getYear() != parseFloat(year) ||
               temp_date.getMonth() != parseFloat(month) - 1 ||
               temp_date.getDate() != parseFloat(day)) {
               return false;
           } else {
               return true;
           }
       },
       //去掉字符串头尾空格
       trim(str) {
           return str.replace(/(^\s*)|(\s*$)/g, "");
       }
   };
   module.exports = validatorFn;