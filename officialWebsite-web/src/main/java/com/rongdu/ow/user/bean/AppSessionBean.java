package com.rongdu.ow.user.bean;

import java.util.Map;

/**
 * Created by lsk on 2016/9/21.
 */
@SuppressWarnings({ "rawtypes" })
public class AppSessionBean {
//    private String token;
//    private String refreshToken;
//    private String userId;
    private Map session;


    public AppSessionBean(Map session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
	public Map<String,Object> getFront(){
        return  (Map<String, Object>) session.get("front");
    }


//    public String getToken() {
//        return token;
//    }
//
//    public String getRefreshToken() {
//        return refreshToken;
//    }

    public String getUserId() {
        return getFront().get("userId").toString();
    }

    public Map getSession() {
        return session;
    }

    public Map getUserData(){
        return (Map) session.get("userData");
    }
}
