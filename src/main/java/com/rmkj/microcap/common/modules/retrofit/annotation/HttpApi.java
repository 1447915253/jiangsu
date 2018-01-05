package com.rmkj.microcap.common.modules.retrofit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangbowen on 2016/5/12.
 * 用于标识服务接口类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HttpApi {
    String value() default "";//通过key获得配置文件中的值
    Class[] interceptor() default {};
}
