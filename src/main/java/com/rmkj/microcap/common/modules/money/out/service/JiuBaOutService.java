package com.rmkj.microcap.common.modules.money.out.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rmkj.microcap.common.modules.money.out.http.JiuBaOutApi;
import com.rmkj.microcap.common.modules.money.out.pojo.CheckBankNoReq;
import com.rmkj.microcap.common.modules.money.out.pojo.CheckBankNoResp;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.DateUtils;
import com.rmkj.microcap.common.utils.Utils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/10.
 */
@Service
public class JiuBaOutService {

    private Logger Log = Logger.getLogger(JiuBaOutService.class);

    @HttpService
    JiuBaOutApi jiuBaOutApi;

    @Value("${jiu_ba_merchant_no}")
    String JIU_BA_MERCHANT_NO;

    @Value("${jiu_ba_secret}")
    String JIU_BA_SECRET;

    public CheckBankNoResp checkBankNo(String cardNo){
        CheckBankNoReq checkBankNoReq = new CheckBankNoReq();
        checkBankNoReq.setService("v2_liquidation_bankcard_query");
        checkBankNoReq.setVersion("2.0");
        checkBankNoReq.setCharset("UTF-8");
        checkBankNoReq.setReq_time(DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
        checkBankNoReq.setNonce_str(Utils.uuid());

        checkBankNoReq.setMerchant_no(JIU_BA_MERCHANT_NO);
        checkBankNoReq.setCard_no(cardNo);

        checkBankNoReq.setSign(DigestUtils.md5Hex(Utils.param(checkBankNoReq).concat(JIU_BA_SECRET)).toLowerCase());
        checkBankNoReq.setSign_type("MD5");

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(checkBankNoReq));
        HashMap<String, String> map = new HashMap<>();
        for(String key : jsonObject.keySet()){
            map.put(key, jsonObject.getString(key));
        }
        try {
            Response<String> execute = jiuBaOutApi.checkBankNo(map).execute();
            if(execute.isSuccessful()){
                String body = execute.body();
                Log.info(body);
                CheckBankNoResp checkBankNoResp = JSON.parseObject(body, CheckBankNoResp.class);
                if("000000".equals(checkBankNoResp.getResp_code())){
                    return checkBankNoResp;
                }
            }else{
                String errorStr = JSON.toJSONString(execute.errorBody()).toString();
                Log.error(errorStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
