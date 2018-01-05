package com.rmkj.microcap.common.modules.pay.fiveeight.service;/**
 * Created by Administrator on 2017/3/20.
 */

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.*;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayByServiceBean.FiveEightOpenPayReqByServiceBean;
import com.rmkj.microcap.common.modules.pay.fiveeight.bean.FiveEightOpenPayByServiceBean.FiveEightOpenPayResByServiceBeanData2;
import com.rmkj.microcap.common.modules.pay.fiveeight.http.FiveEightWeiXinApi;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengD0ResultBean;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.bean.FiveEightPrePayBean;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.parameterset.entity.ParameterSetBean;
import com.rmkj.microcap.modules.parameterset.service.ParameterSetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author k
 * @create -03-20-15:39
 **/
@Service
public class FiveEightOpenPayService {
    private static final Logger Log = Logger.getLogger(FiveEightOpenPayService.class);

    @Autowired
    private ParameterSetService parameterSetService;

    @Autowired
    private MoneyService moneyService;

    @HttpService
    private FiveEightWeiXinApi fiveEightWeiXinApi;

    /**
     * 58支付 微信扫码支付
     * @param moneyRecord
     */
    public String fiveEightOpenPayOrder(MoneyRecord moneyRecord, FiveEightOpenPayReqBean fiveEightOpenPayReqBean){

        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());

        ParameterSetBean parameterSetBean= null;
        if( list!=null&&list.size()>0){
            parameterSetBean=list.get(0);
        }
        fiveEightOpenPayReqBean.setService("Pay.jsPay");
        fiveEightOpenPayReqBean.setNonce(Utils.uuid().toUpperCase());
        fiveEightOpenPayReqBean.setMerchantId(ProjectConstants.FIVE_EIGHT_PAY_MCH_ID);
        fiveEightOpenPayReqBean.setOutOrderId(moneyRecord.getSerialNo());
        fiveEightOpenPayReqBean.setTotalAmount(moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue()+"");
        fiveEightOpenPayReqBean.setSubJect(null == parameterSetBean.getProductName() ? "众宝乐商":parameterSetBean.getProductName());
        fiveEightOpenPayReqBean.setDesc(null == parameterSetBean.getProductName() ? "众宝乐商":parameterSetBean.getProductName());
        fiveEightOpenPayReqBean.setPaymentType("weixin");
        fiveEightOpenPayReqBean.setCallBack(ProjectConstants.FIVE_EIGHT_PAY_NOTIFY_URL);
        fiveEightOpenPayReqBean.setSign(Utils.md5(Utils.param(fiveEightOpenPayReqBean).concat("&appSecret=").concat(ProjectConstants.FIVE_EIGHT_PAY_KEY)).toUpperCase());
        Log.info(JSON.toJSONString(fiveEightOpenPayReqBean));
        try {
            Response<String> execute = fiveEightWeiXinApi.createOrder(fiveEightOpenPayReqBean).execute();
            if(execute.isSuccessful()){
                Log.info("预支付接口返回 ".concat(new String(execute.body())));
            }else{
                Log.error("预支付失败 ".concat(new String(execute.errorBody().bytes())));
            }
            return execute.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 58支付 微信H5支付 接口提交订单
     * @param moneyRecord
     */
    public FiveEightOpenPayResByServiceBeanData2 fiveEightOpenPayOrderByService(MoneyRecord moneyRecord,HttpServletRequest request){

        List<ParameterSetBean> list= parameterSetService.queryList(new ParameterSetBean());

        ParameterSetBean parameterSetBean= null;
        if( list!=null&&list.size()>0){
            parameterSetBean=list.get(0);
        }
        FiveEightOpenPayReqByServiceBean fiveEightOpenPayReqBean = new FiveEightOpenPayReqByServiceBean();
        fiveEightOpenPayReqBean.setService("Pay.gPay");
        fiveEightOpenPayReqBean.setNonce(Utils.uuid().toUpperCase());
        fiveEightOpenPayReqBean.setMerchantId(ProjectConstants.FIVE_EIGHT_PAY_MCH_ID);
        fiveEightOpenPayReqBean.setOutOrderId(moneyRecord.getSerialNo());
        fiveEightOpenPayReqBean.setTotalAmount(moneyRecord.getMoney().multiply(Utils.toBigDecimal("100")).intValue()+"");
        fiveEightOpenPayReqBean.setSubJect(Utils.uuid());
        fiveEightOpenPayReqBean.setCallBack(ProjectConstants.FIVE_EIGHT_PAY_NOTIFY_URL);
        fiveEightOpenPayReqBean.setCallUrl("http://"+request.getServerName()+"/front/wap/user");
        fiveEightOpenPayReqBean.setCallBack(ProjectConstants.FIVE_EIGHT_PAY_NOTIFY_URL);
        fiveEightOpenPayReqBean.setAttach(moneyRecord.getSerialNo());
        fiveEightOpenPayReqBean.setSign(Utils.md5(Utils.param(fiveEightOpenPayReqBean).concat("&appSecret=").concat(ProjectConstants.FIVE_EIGHT_PAY_KEY)).toUpperCase());
        Log.info(JSON.toJSONString(fiveEightOpenPayReqBean));
        try {
            Response<String> execute = fiveEightWeiXinApi.createOrderByService(fiveEightOpenPayReqBean).execute();
            String result = execute.body();
            if(execute.isSuccessful()){
                Log.info("预支付接口返回 ".concat(new String(result)));
            }else{
                Log.error("预支付失败 ".concat(new String(execute.errorBody().bytes())));
            }
            FiveEightOpenPayResBean fiveEightOpenPayResBean = JSON.parseObject(result,FiveEightOpenPayResBean.class);
            String data = fiveEightOpenPayResBean.getData();
            FiveEightOpenPayResBeanData1 fiveEightOpenPayResBeanData1 = JSON.parseObject(data,FiveEightOpenPayResBeanData1.class);
            String data1 = fiveEightOpenPayResBeanData1.getData();
            FiveEightOpenPayResByServiceBeanData2 fiveEightOpenPayResByServiceBeanData2 = JSON.parseObject(data1,FiveEightOpenPayResByServiceBeanData2.class);
            return fiveEightOpenPayResByServiceBeanData2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 58支付 微信扫码回调
     */
    public synchronized ResponseEntity getOpenPayResult(FiveEightOpenPayResultResBeanData fiveEightOpenPayResultResBeanData) {
        Log.info("58微信扫码异步通知：".concat(JSON.toJSONString(fiveEightOpenPayResultResBeanData)));
        FiveEightOpenPayResultResBean fiveEightOpenPayResultResBean = JSON.parseObject(fiveEightOpenPayResultResBeanData.getData(),FiveEightOpenPayResultResBean.class);
        String reqSign = fiveEightOpenPayResultResBean.getMerchantSign();
        fiveEightOpenPayResultResBean.setChannelSign(null);
        fiveEightOpenPayResultResBean.setMerchantSign(null);
        String signPre = Utils.param(fiveEightOpenPayResultResBean).concat("&appSecret=").concat(ProjectConstants.FIVE_EIGHT_PAY_KEY);
        String sign = Utils.md5(signPre).toUpperCase();
        Log.info("本地签名：" + sign + "  58微信支付签名：" + reqSign);
        //验签成功就算支付成功
        if(!reqSign.equals(sign)){
            Log.error("58微信扫码支付验签失败：".concat(sign));
            return new ResponseEntity("fail", HttpStatus.OK);
        }else if(!moneyService.payResultOfFiveEight(fiveEightOpenPayResultResBean)) {// 处理业务
            return new ResponseEntity("fail", HttpStatus.OK);
        }

        return new ResponseEntity("success", HttpStatus.OK);
    }
}