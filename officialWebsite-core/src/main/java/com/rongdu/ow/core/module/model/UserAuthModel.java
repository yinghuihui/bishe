package com.rongdu.ow.core.module.model;

import com.rongdu.ow.core.module.domain.ClUserAuth;

public class UserAuthModel extends ClUserAuth{
   public static final String NOT_AUTH_ = "10";
   public static final String IS_AUTHING = "20";
   public static final String IS_AUTHED = "30";
   public static String authStateStr(String state){
	   if(state.equals(NOT_AUTH_)){
		   return "未认证";
	   }else if(state.equals(IS_AUTHING)){
		   return "认证中";
	   }else{
		   return "已认证";
	   }
		   
	   
   }
}
