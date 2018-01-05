package com.rmkj.microcap.common.modules.pay.xionyunPay.service;


import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.mobao.bean.MoBaoPerBankCardBean;
import com.rmkj.microcap.common.modules.pay.xionyunPay.util.MD5Util;
import com.rmkj.microcap.common.modules.pay.xionyunPay.util.PayUtil;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.money.bean.XionyunPayBean;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.parameterset.service.ParameterSetService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by M on 2017/10/23.
 */
@Service
public class XionyunPayService {

    private static final Logger Log = Logger.getLogger(XionyunPayService.class);

    @Autowired
    private ParameterSetService parameterSetService;

    public Map<String, Object> xionyunScanPay(MoneyRecord moneyRecord,XionyunPayBean xionyunPayBean) {

        int shopId =15;  //商户uid
        String key ="B6567F9CF90879219A3251CCAD2FB13A";
        /*int shopId = 2;
        String key ="66987BFECB7B42FD3E98FB1CE91D2299";*/

        String money=xionyunPayBean.getMoney();//价格
        int payWay=xionyunPayBean.getTradeType();  //支付渠道
        String notify_url="http://www.cdwanjia.cn/front/v1/xionyunScanPay/notify";
        String return_url="http://www.ytsma.top/front/wap/user";
        String orderId=moneyRecord.getSerialNo();//商户自定义订单号
        String orderUid=moneyRecord.getChnName();//最好传用户的用户名,好查账
        String goodsname="充值";
        String to =goodsname + payWay + notify_url + orderId + orderUid + money + return_url + key + shopId;
        String token= MD5Util.getPwd(to);
        token = token.toLowerCase();
        String url="http://pay.crossex.cn/bear-pay/pay?goodsname="+goodsname+"&payWay="+payWay+"&notify_url="
                +notify_url+"&orderId="+orderId+"&orderUid="+orderUid+"&money="+money+"&return_url="
                +return_url+"&token="+token+"&shopId="+shopId+"";

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("shopId",15);
        resultMap.put("money",xionyunPayBean.getMoney());
        resultMap.put("payWay",xionyunPayBean.getTradeType());
        resultMap.put("notify_url","http://www.cdwanjia.cn/front/v1/xionyunScanPay/notify");
        resultMap.put("return_url","http://www.ytsma.top/front/wap/user");
        resultMap.put("orderId",moneyRecord.getSerialNo());
        resultMap.put("orderUid",moneyRecord.getUserId());
        resultMap.put("goodsname", "充值");
        resultMap.put("token",token);
        resultMap.put("url",url);
        Log.info("url"+url);
        return resultMap;
    }
}
