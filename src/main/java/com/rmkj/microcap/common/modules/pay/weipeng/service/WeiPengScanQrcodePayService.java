package com.rmkj.microcap.common.modules.pay.weipeng.service;/**
 * Created by Administrator on 2017/3/6.
 */

import com.alibaba.fastjson.JSON;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengD0ResultBean;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengDoBean;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengQrCodeAsyncResultBean;
import com.rmkj.microcap.common.modules.pay.weipeng.http.WeiPengApi;
import com.rmkj.microcap.common.modules.pay.weipeng.utils.WeiPengUtils;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.common.utils.Utils;
import com.rmkj.microcap.modules.money.service.MoneyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

/**
 * @author k
 * @create -03-06-14:32
 **/
@Service
public class WeiPengScanQrcodePayService {

    @HttpService
    private WeiPengApi weiPengApi;

    @Autowired
    private MoneyService moneyService;

    private static final Logger Log = Logger.getLogger(WeiPengScanQrcodePayService.class);

    public String getGeiPengQrCodeAsyncResult(WeiPengQrCodeAsyncResultBean asyncResultBean){
        try {
            if(null != asyncResultBean){
                boolean flag = moneyService.getWeiPengQrCodeAsyncResult(asyncResultBean);
                if(flag = true){
                    return "success";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 威鹏微信、支付宝，支付成功异步通知
     * @param asyncResultBean
     * @return
     */
    public String getGeiPengQrCodeAsyncResult(WeiPengQrCodeAsyncResultBean asyncResultBean, String falg){
        try {
            if(null != asyncResultBean){
                //验签 sign= MD5(merchant_no+out_trade_no+ pay_num + total_fee +key)
                StringBuffer buffer = new StringBuffer();
                if(falg.equals("H5")){
                    buffer.append(ProjectConstants.WEIPENG_PAY_MERCHANT_NO_H5).append(asyncResultBean.getOut_trade_no()).append(asyncResultBean.getPay_num())
                            .append(asyncResultBean.getTotal_fee()).append(ProjectConstants.WEIPENG_PAY_SECRET_H5);
                }else{
                    buffer.append(ProjectConstants.WEIPENG_PAY_MERCHANT_NO).append(asyncResultBean.getOut_trade_no()).append(asyncResultBean.getPay_num())
                            .append(asyncResultBean.getTotal_fee()).append(ProjectConstants.WEIPENG_PAY_SECRET);
                }

                String sign = Utils.md5(buffer.toString()).toUpperCase();
                if(!sign.equals(asyncResultBean.getSign())){
                    Log.info("验签错误:" + sign.concat("------").concat(asyncResultBean.getSign()));
                    return "fail";
                }

                boolean flag = moneyService.getWeiPengQrCodeAsyncResult(asyncResultBean);
                if(flag = true){
                    return "success";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 威鹏扫码支付下单
     * @param weiPengDoBean
     */
    public WeiPengD0ResultBean weiPengScanQrcodePayService(WeiPengDoBean weiPengDoBean, String serialNo){
        WeiPengD0ResultBean weiPengD0ResultBean = null;
//        String merchant_no = ProjectConstants.WEIPENG_PAY_MERCHANT_NO;// 商户号，威鹏分配
//        Integer total_fee = weiPengDoBean.getMoney();// 单位为分，整型
//        String pay_num = Utils.generateTraceno();// 商户自己系统订单号
//        String key = ProjectConstants.WEIPENG_PAY_SECRET;// 威鹏分配，用户提交
//        //生成参数签名
//        StringBuffer sign = new StringBuffer();
//        sign.append(merchant_no)
//                .append(total_fee)
//                .append(WeiPengUtils.utilSimpleDateFormat("yyyyMMdd"))
//                .append(key);
        try {
            Response<String> execute = weiPengApi.getCreateYyWxOrderD0InfoMap(WeiPengUtils.getWeiPengScanQrcodeInfo(weiPengDoBean, serialNo)).execute();
            String xmlResult = execute.body();
            if(execute.isSuccessful()){
                Log.info("支付下单返回："+execute.body());
                weiPengD0ResultBean = JSON.parseObject(xmlResult,WeiPengD0ResultBean.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return weiPengD0ResultBean;
    }

    /**
     * 威鹏微信H5支付下单
     * @param weiPengDoBean
     */
    public WeiPengD0ResultBean weiPengH5PayService(WeiPengDoBean weiPengDoBean, String serialNo){
        WeiPengD0ResultBean weiPengD0ResultBean = null;

        try {
            Response<String> execute = weiPengApi.getCreateWxH5InfoMap(WeiPengUtils.getWeiPengH5Info(weiPengDoBean, serialNo)).execute();
            String xmlResult = execute.body();
            if(execute.isSuccessful()){
                Log.info("威鹏微信H5支付下单返回："+execute.body());
                weiPengD0ResultBean = JSON.parseObject(xmlResult,WeiPengD0ResultBean.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return weiPengD0ResultBean;
    }
}
