package com.rmkj.microcap.common.modules.weixin.service;

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.modules.weixin.XStreamTool;
import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyRespBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.NotifyReqBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.UnifiedOrderReqBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.UnifiedOrderRespBean;
import com.rmkj.microcap.common.modules.weixin.bean.pay.WCPayReqBean;
import com.rmkj.microcap.common.modules.weixin.face.NotifyForPayService;
import com.rmkj.microcap.common.modules.weixin.http.WeiXinPayApi;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.common.utils.ValidatorUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import retrofit2.Response;

import java.io.IOException;
import java.util.Date;

/**
 * Created by 123 on 2016/10/28.
 */
@Service
public class WeiXinPayService {

    private static final Logger Log = Logger.getLogger(WeiXinPayService.class);

    @HttpService
    private WeiXinPayApi weiXinPayApi;

    @Autowired
    private Validator validator;

    /**
     * 具体业务实现类
     */
    @Autowired
    private NotifyForPayService notifyForPayService;

    public UnifiedOrderRespBean unifiedOrder(String device_info, String body, String out_trade_no, int total_fee,
                                             String spbill_create_ip, String trade_type, String openid) {
        UnifiedOrderReqBean unifiedOrderReqBean = new UnifiedOrderReqBean();
        unifiedOrderReqBean.setAppid(ProjectConstants.WEI_XIN_APP_ID);
        unifiedOrderReqBean.setMch_id(ProjectConstants.WEI_XIN_MCH_ID);
        unifiedOrderReqBean.setDevice_info(device_info);
        unifiedOrderReqBean.setNonce_str(Utils.uuid().toUpperCase());
        unifiedOrderReqBean.setBody(body);
        unifiedOrderReqBean.setOut_trade_no(out_trade_no);
        unifiedOrderReqBean.setSpbill_create_ip(spbill_create_ip);
        unifiedOrderReqBean.setTrade_type(trade_type);
        unifiedOrderReqBean.setNotify_url(ProjectConstants.WEI_XIN_PAY_CALLBACK_URL);
        unifiedOrderReqBean.setTotal_fee(total_fee);
        unifiedOrderReqBean.setOpenid(openid);

        // 签名
        unifiedOrderReqBean.setSign(Utils.md5(Utils.param(unifiedOrderReqBean).concat("&key=").concat(ProjectConstants.WEI_XIN_KEY)).toUpperCase());

        UnifiedOrderRespBean respBean = new UnifiedOrderRespBean();
        if(ValidatorUtils.valid(unifiedOrderReqBean)){
            try {
                String xml = XStreamTool.toXml(unifiedOrderReqBean, UnifiedOrderReqBean.class);
                Log.info("预支付接口 xml=".concat(xml));
                Response<String> execute = weiXinPayApi.unifiedOrder(xml).execute();
                if(execute.isSuccessful()){
                    Log.info("预支付接口返回 ".concat(new String(execute.body())));
                    String _body = execute.body();
                    respBean = XStreamTool.toBean(_body, UnifiedOrderRespBean.class);
                }else{
                    Log.error("预支付失败 ".concat(new String(execute.errorBody().bytes())));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return respBean;
    }

    /**
     *
     * @param notifyReqBean
     * @return
     */
    public NotifyRespBean notify(NotifyReqBean notifyReqBean) {
        NotifyRespBean notifyRespBean = new NotifyRespBean();
        notifyRespBean.setReturn_code("SUCCESS");

        if (!ValidatorUtils.valid(notifyReqBean)) {
            notifyRespBean.setReturn_code("FAIL");
            notifyRespBean.setReturn_msg("签名失败,参数格式校验错误");
            return notifyRespBean;
        }

        if(!notifyReqBean.success()){
            Log.error("微信支付结果回调：".concat(JSON.toJSONString(notifyReqBean)));
        }else {
            // 校验签名是否正确
            String reqSign = notifyReqBean.getSign();
            notifyReqBean.setSign(null);
            String sign = Utils.md5(Utils.param(notifyReqBean).concat("&key=").concat(ProjectConstants.WEI_XIN_KEY)).toUpperCase();

            if(!reqSign.equals(sign)){
                notifyRespBean.setReturn_code("FAIL");
                notifyRespBean.setReturn_msg("签名失败,参数格式校验错误");
                }else if(!notifyForPayService.payResult(notifyReqBean)) {// 处理业务
                notifyRespBean.setReturn_code("FAIL");
                notifyRespBean.setReturn_msg("系统业务处理失败");
            }
        }
        Log.info(JSON.toJSONString(notifyRespBean));
        return notifyRespBean;
    }

    /**
     * 奈何！
     * @param unifiedOrderRespBean
     * @return
     */
    public WCPayReqBean toWCPayReqBean(UnifiedOrderRespBean unifiedOrderRespBean){
        WCPayReqBean respBean = new WCPayReqBean();
        respBean.setAppId(unifiedOrderRespBean.getAppid());
        respBean.setTimeStamp(new Date().getTime()/1000 + "");
        respBean.setNonceStr(Utils.uuid());
        respBean.setPk("prepay_id=".concat(unifiedOrderRespBean.getPrepay_id()));
        respBean.setSignType("MD5");
        respBean.setPaySign(Utils.md5("appId".concat("=").concat(respBean.getAppId())
                .concat("&nonceStr").concat("=").concat(respBean.getNonceStr())
                .concat("&package").concat("=").concat(respBean.getPk())
                .concat("&signType").concat("=").concat(respBean.getSignType())
                .concat("&timeStamp").concat("=").concat(respBean.getTimeStamp())
                .concat("&key=").concat(ProjectConstants.WEI_XIN_KEY)).toUpperCase());
        return respBean;
    }
}
