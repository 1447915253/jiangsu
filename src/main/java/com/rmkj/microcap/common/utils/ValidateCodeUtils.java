package com.rmkj.microcap.common.utils;

import com.rmkj.microcap.common.constants.CacheKey;
import com.rmkj.microcap.common.constants.Constants;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.cache.CacheFacade;
import com.rmkj.microcap.common.modules.cache.impl.LocalCache;

/**
 * Created by 123 on 2016/10/18.
 */
public final class ValidateCodeUtils {

    /**
     * 失效时间
     */
    private static final int EXPIRE_SECONDS = 60*5;

    /**
     * 一次性的验证，验证之后失效
     * @param mobile
     * @param validCode
     * @param type
     * @return
     */
    public static boolean mobileValid(String mobile, String validCode, String type) {
        return mobileValid(mobile, validCode, type, true);
    }

    /**
     * 验证验证码
     * @param mobile
     * @param validCode
     * @param type
     * @param forceRemove
     * @return
     */
    public static boolean mobileValid(String mobile, String validCode, String type, boolean forceRemove) {
        if(mobile == null || validCode == null || type == null){
            return false;
        }
        String code = CacheFacade.getObject(mobileCodeKey(mobile, type));
        if(ProjectConstants.SMS_DEBUG){
            return true;
        }
        if (code == null || !validCode.equals(code)) {
            return false;
        }
        return true;
    }

    /**
     * 验证码
     * @param mobile
     * @param validCode
     * @param type
     */
    public static void putMobileValidateCode(String mobile, String validCode, String type){
        String key = mobileCodeKey(mobile, type);
        if(key != null){
            CacheFacade.set(key, validCode, EXPIRE_SECONDS);
        }
    }

    /**
     * 是否存在
     * @param mobile
     * @param type
     * @return
     */
    public static boolean exists(String mobile, String type){
        return CacheFacade.getObject(mobileCodeKey(mobile, type)) != null;
    }

    /**
     *
     * @param mobile
     * @param type
     * @return
     */
    private static String mobileCodeKey(String mobile, String type){
        if(mobile == null || type == null){
            return null;
        }
        return mobile.concat(CacheKey.MOBILE_VALIDATE.concat(type));
    }

    /**
     * 移除验证码
     * @param mobile
     * @param type
     */
    public static void removeMobileValidateCode(String mobile, String type) {
        String key = mobileCodeKey(mobile, type);
        if(key != null){
            CacheFacade.delete(key);
        }
    }
}
