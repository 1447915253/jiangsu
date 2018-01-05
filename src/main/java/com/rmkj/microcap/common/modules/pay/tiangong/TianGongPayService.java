package com.rmkj.microcap.common.modules.pay.tiangong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.modules.pay.tiangong.bean.PayResultReqBean;
import com.rmkj.microcap.common.modules.pay.tiangong.bean.PrePayReqBean;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * Created by 123 on 2017/1/7.
 */
@Service
public class TianGongPayService {

    private final Logger Log = Logger.getLogger(TianGongPayService.class);

    @HttpService
    private TianGongPayApi tianGongPayApi;

    @Value("${tian_gong_client_id}")
    private String client_id;

    @Value("${tian_gong_client_secret}")
    private String client_secret;

    @Value("${tian_gong_notify_url}")
    private String notify_url;

    @Value("${tian_gong_return_url}")
    private String return_url;

    @Autowired
    private MoneyService moneyService;

    public String chargeWeiXin(String orderNo, String money){
        Call<String> wxpay_jsapi = tianGongPayApi.charge(client_id, notify_url, return_url, client_secret, orderNo,
                "wxpay_jsapi", money, null, null, null);
        try {
            Response<String> execute = wxpay_jsapi.execute();
            if(execute.isSuccessful()){
                JSONObject jsonObject = JSON.parseObject(execute.body());
                return jsonObject.containsKey("error") ? "error" : jsonObject.getJSONObject("result").getJSONObject("action").getString("params");
            }else{
                Log.error(execute.errorBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public ResponseEntity notify(PayResultReqBean payResultReqBean) {
        moneyService.payResultOfTianGong(payResultReqBean);
        return new ResponseEntity(HttpStatus.OK);
    }

    public PrePayReqBean chargeYinLian(String serialNo, String money) {
        PrePayReqBean prePayReqBean = new PrePayReqBean();
        prePayReqBean.setClient_id(client_id);
        prePayReqBean.setNotify_url(notify_url);
        prePayReqBean.setReturn_url(return_url);
        prePayReqBean.setOrder_no(serialNo);
        prePayReqBean.setChannel("chinapay");
        prePayReqBean.setAmount(money);
        prePayReqBean.setSubject("微商品");
        prePayReqBean.setMetadata(null);
        prePayReqBean.setClient_ip(null);
        prePayReqBean.setSign(Utils.md5(client_secret.concat(Utils.param2(prePayReqBean)).concat(client_secret)).toUpperCase());
        return prePayReqBean;
    }
}
