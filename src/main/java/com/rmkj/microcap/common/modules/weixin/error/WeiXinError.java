package com.rmkj.microcap.common.modules.weixin.error;

/**
 * Created by zhangbowen on 2016/6/7.
 */
public class WeiXinError extends RuntimeException {
    public WeiXinError(String message) {
        super(message);
    }
}
