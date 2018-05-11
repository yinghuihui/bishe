package com.rongdu.ow.core.module.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rongdu.ow.core.common.context.Global;
import com.rongdu.ow.core.common.mapper.BaseMapper;
import com.rongdu.ow.core.common.service.impl.BaseServiceImpl;
import com.rongdu.ow.core.common.util.StringUtil;
import com.rongdu.ow.core.module.domain.ClBorrow;
import com.rongdu.ow.core.module.domain.ClCredit;
import com.rongdu.ow.core.module.domain.ClUser;
import com.rongdu.ow.core.module.domain.ClUserAuth;
import com.rongdu.ow.core.module.domain.ClUserBaseInfo;
import com.rongdu.ow.core.module.mapper.ClBorrowMapper;
import com.rongdu.ow.core.module.mapper.ClCreditMapper;
import com.rongdu.ow.core.module.mapper.ClUserAuthMapper;
import com.rongdu.ow.core.module.mapper.ClUserBaseInfoMapper;
import com.rongdu.ow.core.module.mapper.ClUserMapper;
import com.rongdu.ow.core.module.model.BorrowProgressModel;
import com.rongdu.ow.core.module.model.ClBorrowModel;
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
    @Resource
    private ClUserBaseInfoMapper clUserBaseInfoMapper;
    @Resource
    private ClCreditMapper clCreditMapper;
    @Resource
    private ClUserAuthMapper clUserAuthMapper;
    @Resource
    private ClBorrowMapper clBorrowMapper;
	@Override
	public BaseMapper<ClUser, Long> getMapper() {
		return clUserMapper;
	}
	@Override
    public Map pcRegisterUser(HttpServletRequest request, String phone, String pwd, String vcode) {
        try {
            if (StringUtil.isEmpty(phone) || !StringUtil.isPhone(phone) || StringUtil.isEmpty(pwd) || StringUtil.isEmpty(vcode) || pwd.length() < 32) {
            	Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "参数有误");
                return ret;
            }
            
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
            Map<String,Object> userMap = new HashMap<String, Object>();
            userMap.put("loginName", phone);
            ClUser user = clUserMapper.findSelective(userMap);
            if (user != null) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "该手机号码已被注册");
                return ret;
            }
            
            // 渠道
            ClUser newUser = new ClUser();
            newUser.setLoginName(phone);
            newUser.setLoginPwd(pwd);
            newUser.setRegistTime(new Date());
            long userIdjia = clUserMapper.save(newUser);
            long userId = newUser.getId();
            logger.info("userId"+userId);
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
            ClUserBaseInfo userBaseInfo = new ClUserBaseInfo();
            userBaseInfo.setUserId(userId);
            userBaseInfo.setPhone(phone);
            userBaseInfo.setCreateTime(new Date());
            clUserBaseInfoMapper.save(userBaseInfo);
//            dbService.insert(SqlUtil.buildInsertSqlMap("cl_user_base_info", new Object[][]{
//                {"user_id", userId},
//                {"phone", phone},
//                {"register_coordinate", registerCoordinate},
//                {"register_addr", registerAddr},
//                {"address", address}
//            }));
            ClCredit clcredit = new ClCredit();
            clcredit.setUserId(userId);
            clcredit.setState("10");
            clcredit.setUsed(Double.valueOf(0));
            clcredit.setUnuse(Double.parseDouble((Global.getValue("init_credit"))));
            clcredit.setTotal(Double.parseDouble((Global.getValue("init_credit"))));
            clcredit.setUpdateTime(new Date());
            clCreditMapper.save(clcredit);
//            dbService.insert(SqlUtil.buildInsertSqlMap("arc_credit", new Object[][]{
//                {"consumer_no", userId},
//                {"total", Global.getValue("init_credit")},
//                {"unuse", Global.getValue("init_credit")},
//                {"state", 10}
//            }));
            ClUserAuth clUserAuth = new ClUserAuth();
            clUserAuth.setUserId(userId);
            clUserAuth.setIdState("10");
            clUserAuth.setWorkInfoState("10");
            clUserAuth.setPhoneState("10");
            clUserAuth.setBankCardState("10");
            clUserAuthMapper.save(clUserAuth);
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
            
            //仅用于demo演示环境
            //demoUser(userId);
            
            Map result = new LinkedHashMap();
            result.put("success", true);
            result.put("msg", "注册成功");
            return result;
        } catch (Exception e) {
            logger.error("注册失败", e);
            Map ret = new LinkedHashMap();
            ret.put("success", false);
            ret.put("msg", "注册失败");
            return ret;
        }
    }
	@Override
	public Map pcUserLogin(HttpServletRequest request, String loginName, String pwd){
		Map<String,Object> ret = new HashMap<>();
		Map<String,Object> searchMap = new HashMap<>();
		searchMap.put("loginName", loginName);
		ClUser  clUser = clUserMapper.findSelective(searchMap);
		if(clUser == null){
			ret.put("msg", "用户不存在");
		} else {
		  if(clUser.getLoginPwd().equals(pwd)){
			  Map<String,Object> paramMap = new HashMap<>();
			  ClUserBaseInfo clUserBaseInfo = clUserBaseInfoMapper.findByUserId(clUser.getId());
			  paramMap.put("userId", clUserBaseInfo.getUserId());
			  paramMap.put("total", 4);
			  Map<String,Object> authMap = clUserAuthMapper.getRequiredAuthState(paramMap);
			  request.getSession().setAttribute("user", clUserBaseInfo.getRealName());
			  Map<String,Object> params = new HashMap<>();
			  params.put("userId", clUserBaseInfo.getUserId());
			  ClBorrow clBorrow = clBorrowMapper.findRepayBorrow(params);
			  if(clBorrow!= null && clBorrow.getState().equals(ClBorrowModel.STATE_FINISH)){
				  request.getSession().setAttribute("isborrow", 1);
			  }else {
				  request.getSession().setAttribute("isborrow", 0);
			  }
			  //List<BorrowProgressModel> list = clBorrowService.borrowProgress(borrow, "detail");
			  logger.info("认证状态"+authMap.get("qualified"));
			  request.getSession().setAttribute("isAuth", authMap.get("qualified"));
			  request.getSession().setAttribute("loginName", loginName);
			  request.getSession().setAttribute("userId",clUser.getId());
			  ret.put("msg", "登陆成功");
		  } else {
			  ret.put("msg", "密码错误");
		  }
		}
		
		return ret;
	}
}