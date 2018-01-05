package com.rmkj.microcap.common.modules.pay.yizhifu.utils;

import com.thoughtworks.xstream.XStream;

/**
 * Created by 123 on 2016/10/28.
 * 微信
 */
public class XStreamTool {

    private final static XStream xStream = new XStream();

    static {
        init(xStream);
    }

    public static void init(XStream x){
        // 转bean时，忽略未知field
        x.ignoreUnknownElements();
    }

    public static <T> T toBean(String xml, Class clazz){
        XStream xStream = getInstance();
        xStream.alias("ordermessage", clazz);
        return (T) xStream.fromXML(xml);
    }

    public static <T> T toObject(String xml, Class clazz,Class clazz2,Class clazz3){
        XStream xStream = getInstance();
        xStream.alias("messagehead", clazz);
        xStream.alias("order",clazz2);
        xStream.alias("ordermessage",clazz3);
        return (T) xStream.fromXML(xml);
    }

    public static String toXml(Object bean, Class clazz){
        XStream xStream = getInstance();
        xStream.alias("xml", clazz);
        return xStream.toXML(bean).replaceAll("__", "_");
    }

    private static XStream getInstance(){
        XStream xStream = new XStream();
        init(xStream);
        return xStream;
    }

    public static XStream getXStream(){
        return xStream;
    }

}
