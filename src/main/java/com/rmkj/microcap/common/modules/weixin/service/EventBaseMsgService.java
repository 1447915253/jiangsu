package com.rmkj.microcap.common.modules.weixin.service;

import com.rmkj.microcap.common.modules.weixin.XStreamTool;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.common.utils.JaxbUtil;

/**
 * Created by zhangbowen on 2016/6/8.
 */
public abstract class EventBaseMsgService<T> {
    private Class msgBeanClass;

    public EventBaseMsgService() {
        this.msgBeanClass = Utils.getSuperClassGenericType(getClass(), 0);
    }

    /**
     * 处理请求消息，返回结果
     *
     * @return
     */
    public String execute(String bodyXml) {
//        //转换成当前Service所需要的实体
//        T msgBean = (T) JaxbUtil.convertToJavaBean(bodyXml, msgBeanClass);
//        Object resultObj = handleMsg(msgBean);
//        //转换成返回的字符串
//        return JaxbUtil.convertToXml(resultObj);
        T msgBean = XStreamTool.toBean(bodyXml, msgBeanClass);
        Object resultObj = handleMsg(msgBean);
        if(resultObj == null){
            return null;
        }
        return XStreamTool.toXml(resultObj, Object.class);
    }

    public abstract Object handleMsg(T obj);
}
