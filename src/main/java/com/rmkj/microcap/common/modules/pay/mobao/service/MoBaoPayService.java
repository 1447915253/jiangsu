package com.rmkj.microcap.common.modules.pay.mobao.service;

import com.kspay.AESUtil;
import com.kspay.DateUtil;
import com.kspay.MD5Util;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.mobao.bean.*;
import com.rmkj.microcap.common.modules.pay.mobao.util.EncodeUtil;
import com.rmkj.microcap.common.utils.AuthContext;
import com.rmkj.microcap.modules.money.dao.MoneyDao;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static com.rmkj.microcap.common.modules.pay.mobao.util.DemoBase.requestBody;

/**
 * Created by M on 2017/10/23.
 */
@Service
public class MoBaoPayService {

    @Autowired
    private MoneyDao moneyDao;

    private static final Logger Log = Logger.getLogger(MoBaoPayService.class);

    public String moBaoUnionPay(MoneyRecord moneyRecord, MoBaoPrePayBean prePayBean, HttpServletRequest request) throws Exception {
        //System.out.println(DateUtil.getTimess1());

        //商户号
        String merId =ProjectConstants.MO_BAO_MERCHANT_NO;
        //String merId ="818310048160000";

        //密钥
        String merKey=ProjectConstants.MO_BAO_KEY;
        //String merKey="1FDD2547FA4FB61F";


        Map<String,String> transmap= new LinkedHashMap<String, String>();
        transmap.put("versionId","001");     //版本号
        transmap.put("businessType","1401");  //交易业务类型
//		transmap.put("insCode","");
        transmap.put("merId",merId);            //商户号
        transmap.put("orderId",moneyRecord.getSerialNo());   //商户订单号
        transmap.put("transDate",DateUtil.getTimess1());  //交易日期    yymmddhhmmss
        transmap.put("transAmount",prePayBean.getMoney());   //交易金额
        transmap.put("cardByName", MD5Util.encode(prePayBean.getCardByName().getBytes("UTF-8")));  //为中文时需进行BAS64编码转码 (UTF-8)
        transmap.put("cardByNo",prePayBean.getCardByNo());  //持卡卡号
        transmap.put("cardType","01");  //卡类型
        //transmap.put("expireDate","2006"); // 有效期
        //transmap.put("CVV","045");    //CVN
        //transmap.put("bankCode","");   //银行编码
        //transmap.put("openBankName","");//开户银行
        transmap.put("cerType","01");   //证件类型
        transmap.put("cerNumber",prePayBean.getCerNumber());//证件号码
        transmap.put("mobile",prePayBean.getMobile());  //手机号
        transmap.put("isAcceptYzm","00"); //是否下发验证码标志（是否发送验证码到手机）  00 发送(固定)
        //transmap.put("pageNotifyUrl","http://"+request.getServerName()+"/front/wap/user");//前台页面通知地址
        transmap.put("backNotifyUrl",ProjectConstants.MO_BAO_BACKNOTIFYURL);
        transmap.put("instalTransFlag","01");  // 是否分期支付
        //transmap.put("instalTransNums","12");   //分期次数
//		transmap.put("orderDesc","");
//		transmap.put("dev","");
//		transmap.put("fee","");

        String  signstr= EncodeUtil.getUrlStr(transmap);
        //System.out.println("加密的字符串:"+signstr);
        String  signtrue=MD5Util.MD5Encode(signstr+merKey);
        transmap.put("signType","MD5");
        transmap.put("signData",signtrue);

        String  transData=EncodeUtil.getUrlStr(transmap);
        //System.out.println(transData);

        String  reqMsg= AESUtil.encrypt(transData, merKey);
        String data=requestBody(merId,reqMsg);
        Log.info("摩宝预支付返回参数:"+data);

        return data;
    }


    public String moBaoUnionCodePay(MoBaoCodeReqBean moBaoCodeReqBean) throws UnsupportedEncodingException {

        String flag=null;
        //System.out.println(DateUtil.getTimess1());

        //商户号
        String merId = ProjectConstants.MO_BAO_MERCHANT_NO;
        //String merId ="818310048160000";

        //密钥
        String merKey = ProjectConstants.MO_BAO_KEY;
        //String merKey="1FDD2547FA4FB61F";

        Map<String, String> transmap = new LinkedHashMap<String, String>();
        transmap.put("versionId", "001");
        transmap.put("businessType", "1411");
        //transmap.put("insCode","");
        transmap.put("merId", merId);
        transmap.put("yzm", moBaoCodeReqBean.getYzm());         //验证码
        transmap.put("ksPayOrderId", moBaoCodeReqBean.getKsPayOrderId()); //订单号

        String signstr = EncodeUtil.getUrlStr(transmap);
        String signtrue = MD5Util.MD5Encode(signstr + merKey);
        transmap.put("signType", "MD5");
        transmap.put("signData", signtrue);

        String transData = EncodeUtil.getUrlStr(transmap);

        String reqMsg = AESUtil.encrypt(transData, merKey);

        String resp = requestBody(merId, reqMsg);

        Log.info("摩宝支付验证码接口返回参数：" + resp);
        return resp;
    }

    /**
     * moBaoPayQuery  摩宝查询个人信息
     */
    public MoBaoPerBankCardBean moBaoPayQuery(){

        return moneyDao.moBaoPayQueryBack(AuthContext.getUserId());
    }
}
