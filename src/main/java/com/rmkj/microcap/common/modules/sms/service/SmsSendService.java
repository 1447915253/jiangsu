package com.rmkj.microcap.common.modules.sms.service;

import com.rmkj.microcap.common.handler.SpringContextHolder;
import com.rmkj.microcap.common.modules.sms.SmsSend;
import com.rmkj.microcap.common.modules.sms.SmsServerName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 123 on 2016/10/13.
 * 管理短信服务
 */
@Service
public class SmsSendService{

    @Value("${sms_server}")
    private String sms_server;

    private static Map<String, SmsSend> map = new HashMap<>();

    @PostConstruct
    private void init(){
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] beanNamesForType = SpringContextHolder.getApplicationContext().getBeanNamesForType(SmsSend.class);
            for (String beanName: beanNamesForType){
                putBean((SmsSend) SpringContextHolder.getApplicationContext().getBean(beanName), beanName);
            }
        }).start();
    }

    /**
     *
     * @param bean
     * @param beanName
     * @return
     */
    private Object putBean(SmsSend bean, String beanName){
        Type[] genericInterfaces = bean.getClass().getGenericInterfaces();
        for (Type type : genericInterfaces){
            if(SmsSend.class.equals(type)){
                SmsServerName annotation = bean.getClass().getAnnotation(SmsServerName.class);
                String key = beanName;
                if(annotation == null || annotation.value() == null){
                    throw new ExceptionInInitializerError("短信服务实现类必须添加注解"+SmsServerName.class.getName());
                }
                key = annotation.value();
                map.put(key, (SmsSend) bean);
            }
        }
        return bean;
    }

    /**
     * 发送短信
     * @param msg
     * @param phone
     * @return
     */
     public boolean send(String msg, String phone) {
         SmsSend smsSend = map.get(sms_server);
         return smsSend.send(msg, phone);
     }

     public boolean send(String type, Map<String, String> params, String phone){
         SmsSend smsSend = map.get(sms_server);
         return smsSend.send(type, params, phone);
     }

    /**
     * 群发短信
     * @param msg
     * @param phone
     * @return
     */
    public boolean sendMore(String msg, String ... phone){
        SmsSend smsSend = map.get(sms_server);
        return smsSend.sendMore(msg, phone);
    }

}
