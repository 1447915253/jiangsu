package com.rmkj.microcap.common.modules.weixin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangbowen on 2016/6/8.
 * 定义微信回调的消息类型
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MsgType {
    String value() default "";//消息类型
}
