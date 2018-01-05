package com.rmkj.microcap.common.modules.sms.alidayu;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.sms.SmsSend;
import com.rmkj.microcap.common.modules.sms.SmsServerName;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by 123 on 2017/3/10.
 */
@SmsServerName("alidayu")
public class ALiDaYuSmsService implements SmsSend {
    private static final Logger logger = Logger.getLogger(ALiDaYuSmsService.class);

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    /*// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIDii9KSt9CaT6";
    static final String accessKeySecret = "63hdwvM7SUsLw0p2k3qfg2QwFErbeu";*/

    @Override
    public boolean send(String msg, String phone) {
        return false;
    }

    @Override
    public boolean sendMore(String msg, String... phone) {
        return false;
    }

    @Override
    public boolean send(String type, Map<String, String> params, String phone) {
        /*// TODO 短信发送 type: 类型, params：参数, phone：手机号
        TaobaoClient client = new DefaultTaobaoClient(ProjectConstants.ALIDAYU_HTTP, ProjectConstants.ALIDAYU_APP_KEY, ProjectConstants.ALIDAYU_APP_SECRE);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("123456");  //公公回传参数
        request.setSmsType("normal");   //短信类型，传入值请填写normal
        request.setSmsFreeSignName(ProjectConstants.SMS_SIGN);  //短信签名
        request.setSmsParamString("{\"code\":\""+ params.get("code") + "\",\"product\":\"" + params.get("remark") + "\"}");
        request.setRecNum(phone);
        request.setSmsTemplateCode(ProjectConstants.ALIDAYU_MESSAGE_ID); //短信模板id
        AlibabaAliqinFcSmsNumSendResponse response = null;
        try{
            response = client.execute(request);
            logger.info("阿里大于短信：" + response.getBody() +"验证码为：" + params.get("code"));
            return true;
        }catch (ApiException e){
            e.printStackTrace();
        }
        return false;*/
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-guangzhou", ProjectConstants.ALIDAYU_APP_KEY, ProjectConstants.ALIDAYU_APP_SECRE);
        try {
            DefaultProfile.addEndpoint("cn-guangzhou", "cn-guangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            String sign=params.get("sign");
            request.setSignName(sign);
            //必填:短信模板-可在短信控制台中找到
            if("mtpwd".equals(type)){
                request.setTemplateCode(ProjectConstants.ALIDAYU_MESSAGE_ID_UPDATE);
            }
            else if("reg".equals(type)){
                request.setTemplateCode(ProjectConstants.ALIDAYU_MESSAGE_ID_REG);
            }else{
                request.setTemplateCode(ProjectConstants.ALIDAYU_MESSAGE_ID_NORMAL);
            }
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
           request.setTemplateParam("{\"code\":\""+ params.get("code")  + "\"}");
           // request.setTemplateParam("{\"code\":\""+ params.get("code")}");
            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");

            //hint 此处可能会抛出异常，注意catch
            String strRequest= JSONObject.toJSONString(request);
            logger.info("短信发送请求参数："+strRequest);
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            logger.info("阿里大鱼短信接口返回的数据:" +"Code=" + sendSmsResponse.getCode() +"Message=" + sendSmsResponse.getMessage() +"RequestId=" + sendSmsResponse.getRequestId()+"验证码为："+ params.get("code"));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
