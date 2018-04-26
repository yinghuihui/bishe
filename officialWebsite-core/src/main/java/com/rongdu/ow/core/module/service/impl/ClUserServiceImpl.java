package com.rongdu.ow.core.module.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.module.domain.ClUser;
import com.rongdu.ow.core.module.mapper.ClUserMapper;
import com.rongdu.ow.core.module.service.ClUserService;


/**
 * 用户表ServiceImpl
 * 
 * @author yinghh
 * @version 1.0.0
 * @date 2018-04-25 17:41:23
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("clUserService")
public class ClUserServiceImpl extends BaseServiceImpl<ClUser, Long> implements ClUserService {
	
    private static final Logger logger = LoggerFactory.getLogger(ClUserServiceImpl.class);
   
    @Resource
    private ClUserMapper clUserMapper;

	@Override
	public BaseMapper<ClUser, Long> getMapper() {
		return clUserMapper;
	}
//	@Transactional
//    public Map pcRegisterUser(HttpServletRequest request, String phone, String pwd, String vcode, String invitationCode,
//                            String registerCoordinate,String registerAddr,String address, String regClient, String signMsg, String channelCode) {
//        try {
//            if (StringUtil.isEmpty(phone) || !StringUtil.isPhone(phone) || StringUtil.isEmpty(pwd) || StringUtil.isEmpty(vcode) || pwd.length() < 32) {
//            	Map ret = new LinkedHashMap();
//                ret.put("success", false);
//                ret.put("msg", "参数有误");
//                return ret;
//            }
//            
//            CloanUserService cloanUserService = (CloanUserService) BeanUtil.getBean("cloanUserService");
//            long todayCount = cloanUserService.todayCount();
//            String dayRegisterMax_ = Global.getValue("day_register_max");
//            if(StringUtil.isNotBlank(dayRegisterMax_)){
//            	int dayRegisterMax = Integer.parseInt(dayRegisterMax_);
//            	if(dayRegisterMax > 0 && todayCount >= dayRegisterMax){
//            		 Map ret = new LinkedHashMap();
//                     ret.put("success", false);
//                     ret.put("msg", "今日注册用户数已达上限，请明日再来");
//                     return ret;
//            	}
//            }
//            
//            ClSmsService clSmsService = (ClSmsService)BeanUtil.getBean("clSmsService");
//            int results = clSmsService.verifySms(phone, SmsModel.SMS_TYPE_REGISTER, vcode);
//            String vmsg;
//            if (results == 1) {
//            	vmsg = null;
//    		}else if(results == -1){
//    			vmsg="验证码已过期";
//    		}else {
//    			vmsg="手机号码或验证码错误";
//    		}
//            if (vmsg != null) {
//                Map ret = new LinkedHashMap();
//                ret.put("success", false);
//                ret.put("msg", vmsg);
//                return ret;
//            }
//
//            Map old = mybatisService.queryRec("usr.queryUserByLoginName", phone);
//            if (old != null) {
//                Map ret = new LinkedHashMap();
//                ret.put("success", false);
//                ret.put("msg", "该手机号码已被注册");
//                return ret;
//            }
//            
//            // 渠道
//            long channelId = 0;
//            if(StringUtil.isNotBlank(channelCode)){
//            	ChannelService channelService = (ChannelService) BeanUtil.getBean("channelService");
//            	Channel channel = channelService.findByCode(channelCode);
//            	 if (channel != null) {
//            		 channelId=channel.getId();
//                 }
//            }
//            
//            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//            long userId = dbService.insert(SqlUtil.buildInsertSqlMap("cl_user", new Object[][]{
//                {"login_name", phone},
//                {"login_pwd", pwd},
//                {"invitation_code", randomInvitationCode(6)},
//                {"regist_time", new Date()},
//                {"uuid", uuid},
//                {"level", 3},
//                {"register_client", regClient},
//                {"channel_id", channelId}
//            }));
//
//            dbService.insert(SqlUtil.buildInsertSqlMap("cl_user_base_info", new Object[][]{
//                {"user_id", userId},
//                {"phone", phone},
//                {"register_coordinate", registerCoordinate},
//                {"register_addr", registerAddr},
//                {"address", address}
//            }));
//
//            dbService.insert(SqlUtil.buildInsertSqlMap("arc_credit", new Object[][]{
//                {"consumer_no", userId},
//                {"total", Global.getValue("init_credit")},
//                {"unuse", Global.getValue("init_credit")},
//                {"state", 10}
//            }));
//            dbService.insert(SqlUtil.buildInsertSqlMap("cl_profit_amount", new Object[][]{
//                {"user_id", userId},
//                {"state", "10"}
//            }));
//
//            dbService.insert(SqlUtil.buildInsertSqlMap("cl_user_auth", new Object[][]{
//                {"user_id", userId},
//                {"id_state", 10},
//                {"zhima_state", 10},
//                {"phone_state", 10},
//                {"contact_state", 10},
//                {"bank_card_state", 10},
//                {"work_info_state", 10},
//                {"other_info_state", 10},
//            }));
//            
//            //2017.5.6 仅用于demo演示环境
//            demoUser(userId);
//            
//            Map result = new LinkedHashMap();
//            result.put("success", true);
//            result.put("msg", "注册成功");
//            return result;
//        } catch (Exception e) {
//            logger.error("注册失败", e);
//            Map ret = new LinkedHashMap();
//            ret.put("success", false);
//            ret.put("msg", "注册失败");
//            return ret;
//        }
//    }
	
}