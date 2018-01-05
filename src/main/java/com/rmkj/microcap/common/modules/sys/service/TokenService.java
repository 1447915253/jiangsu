package com.rmkj.microcap.common.modules.sys.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.bean.AuthEntity;
import com.rmkj.microcap.common.constants.CacheKey;
import com.rmkj.microcap.common.modules.cache.Cache;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.common.constants.Constants;

/**
 * Created by zhangbowen on 2015/12/22.
 */
public class TokenService {

    /**
     * 处理token
     * 登录，并放入缓存
     *
     * @param terminal
     * @param userId
     * @return
     */
    public static String handleToken(String terminal, String userId) {
        if (terminal == null) {
            return null;
        }
        String token = Utils.md5(userId + System.currentTimeMillis());
        switch (terminal) {
            case Constants.Terminal.TERMINAL_ANDROID:
            case Constants.Terminal.TERMINAL_IOS:
            case Constants.Terminal.TERMINAL_WEB:
            case Constants.Terminal.TERMINAL_WAP:
                CacheFacade.set(cacheKey(terminal, userId), token, 0);
                break;
        }
        return token;
    }

    /**
     * 刷新token
     * @return
     */
    public static String refreshToken() {
        AuthEntity curAuth = AuthContext.getCurAuth();
        return handleToken(curAuth.getTerminal(), curAuth.getUserId());
    }

    /**
     * 删除token
     *
     * @param terminal
     * @param userId
     * @return
     */
    public static void removeToken(String terminal, String userId) {
        if (terminal == null) {
            return;
        }
        switch (terminal) {
            case Constants.Terminal.TERMINAL_ANDROID:
            case Constants.Terminal.TERMINAL_IOS:
            case Constants.Terminal.TERMINAL_WEB:
            case Constants.Terminal.TERMINAL_WAP:
                CacheFacade.delete(cacheKey(terminal, userId));
                break;
        }
    }

    /**
     * 获取token后缀
     *
     * @param terminal
     * @return
     */
    public static String getSuffix(String terminal) {
        String suffix = "";
        if (terminal != null) {
            switch (terminal) {
                case Constants.Terminal.TERMINAL_ANDROID:
                case Constants.Terminal.TERMINAL_IOS:
                case Constants.Terminal.TERMINAL_WEB:
                case Constants.Terminal.TERMINAL_WAP:
                    suffix = CacheKey.TOKEN_APP;
                    break;
            }
        }
        return suffix;
    }

    /**
     * 根据终端类型和userId获取存储token的key
     * @param terminal
     * @param userId
     * @return
     */
    private static String cacheKey(String terminal, String userId){
        return userId.concat(getSuffix(terminal));
    }

    /**
     * 验证token
     * @return
     */
    public static boolean checkToken() {
        AuthEntity curAuth = AuthContext.getCurAuth();
        //获取header中的相应验证信息
        String terminal = curAuth.getTerminal();
        if(terminal == null || "".equals(terminal)){
            System.err.println("curAuth: ".concat(JSON.toJSONString(curAuth)));
        }
        String suffix = TokenService.getSuffix(terminal);
        String userId = curAuth.getUserId();
        //获取token后缀
        String cachedToken = CacheFacade.getObject(userId + suffix);
        if (cachedToken == null || !cachedToken.equals(curAuth.getToken())) {
            //重置请求头中的token，保证真实有效
//            curAuth.setUserId(null);
            curAuth.setToken(null);
            AuthContext.setCurAuth(curAuth);
            return false;
        }
        return true;
    }

}
