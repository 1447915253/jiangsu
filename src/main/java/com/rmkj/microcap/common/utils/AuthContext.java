package com.rmkj.microcap.common.utils;

import com.rmkj.microcap.common.bean.AuthEntity;

/**
 * Created by zhangbowen on 2015/12/25.
 * 用户权限 全局使用工具
 */
public class AuthContext {
    private static ThreadLocal<AuthEntity> authEntityThreadLocal = new ThreadLocal<AuthEntity>();

    //获得当前请求者的权限标识
    public static AuthEntity getCurAuth() {
        AuthEntity authEntity = authEntityThreadLocal.get();
        return authEntity == null ? new AuthEntity() : authEntity;
    }

    public static String getUserId(){
        AuthEntity curAuth = getCurAuth();
        return curAuth == null ? null : curAuth.getUserId() ;
    }

    //设置当前请求者的权限标识
    public static void setCurAuth(AuthEntity authEntity) {
        authEntityThreadLocal.set(authEntity);
    }

    //删除当前请求者的权限标识
    public static void removeCurAuth() {
        authEntityThreadLocal.remove();
    }

}
