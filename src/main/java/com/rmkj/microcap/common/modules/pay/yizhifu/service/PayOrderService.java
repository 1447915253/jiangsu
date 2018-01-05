package com.rmkj.microcap.common.modules.pay.yizhifu.service;

import com.capinfo.crypt.Md5;
import com.capinfo.crypt.RSA_MD5;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.*;
import com.rmkj.microcap.common.modules.pay.yizhifu.http.ShouXinYiApi;
import com.rmkj.microcap.common.modules.pay.yizhifu.utils.SAXParser;
import com.rmkj.microcap.common.modules.pay.yizhifu.utils.XStreamTool;
import com.rmkj.microcap.common.modules.retrofit.annotation.HttpService;
import com.rmkj.microcap.modules.money.dao.MoneyDao;
import com.rmkj.microcap.modules.money.entity.MoneyRecord;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/12/30.
 */
@Service
public class PayOrderService {

    @HttpService
    private ShouXinYiApi shouXinYiApi;

    @Autowired
    private UserService userService;

    @Autowired
    private MoneyDao moneyDao;

    @Autowired
    private MoneyService moneyService;

    private static final Logger Log = Logger.getLogger(PayOrderService.class);
    /**
     * 下订单
     */
    public PayOrderResBean createOrder(PayRequestParamBean payRequestParamBean,String v_oid,String serialNo) {
        PayOrderReqBean payOrderReqBean = new PayOrderReqBean();
        User user = (User) userService.get().getBody();
        String key = ProjectConstants.MD5KEY;//MD5Key,
        //商户号
        String v_mid = ProjectConstants.V_MID;
        String v_rcvaddr = "吉林省长春市";
        String v_rcvpost = "130400";
        String v_ordip = null;
        try {
            v_ordip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ;
        String v_merdata5 = "快赢宝";
        String v_rcvtel = user.getMobile();
        String v_rcvname = serialNo;
        String v_url = "http://qianweipan.net/front/v1/pay/yizhifu/callBack";

        java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("yyyyMMdd");//设置日期格式
        java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("HHmmss");//设置时间时分秒格式
        String dateId1 = df1.format(new Date());//获取当前日期
        //String v_oid = dateId1+"-10535-"+serialNo();//商户号
        String v_ymd = dateId1;

        payOrderReqBean.setV_mid(v_mid);
        payOrderReqBean.setV_oid(v_oid);
        payOrderReqBean.setV_rcvname(v_rcvname);
        payOrderReqBean.setV_rcvaddr(v_rcvaddr);
        payOrderReqBean.setV_rcvtel(v_rcvtel);
        payOrderReqBean.setV_rcvpost(v_rcvpost);
        String v_amount = payRequestParamBean.getMoney();
        payOrderReqBean.setV_amount(v_amount);
        payOrderReqBean.setV_ymd(v_ymd);
        payOrderReqBean.setV_orderstatus("1");
        payOrderReqBean.setV_ordername(v_mid);
        String v_moneytype = "0";
        payOrderReqBean.setV_moneytype(v_moneytype);
        payOrderReqBean.setV_url(v_url);
        String v_md5infoStr = v_moneytype + v_ymd + v_amount + v_rcvname + v_oid + v_mid + v_url;

        Md5 md5 = new Md5("");
        try {
            md5.hmac_Md5(v_md5infoStr, key); //第一个参数是加密参数，第二个参数是加密密钥，测试密钥：test，正式上线之前注意修改
            byte b[] = md5.getDigest();
            String v_md5info = md5.stringify(b);
            payOrderReqBean.setV_md5info(v_md5info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        payOrderReqBean.setV_ordip(v_ordip);
        payOrderReqBean.setV_merdata5(v_merdata5);
        String payType = payRequestParamBean.getType();
        payOrderReqBean.setV_pmode(payType);
        PayOrderResBean payOrderResBean = null;
        try {
            Response<String> execute = shouXinYiApi.createOrder(payOrderReqBean.getV_mid(),payOrderReqBean.getV_oid(),payOrderReqBean.getV_rcvname(),payOrderReqBean.getV_rcvaddr(),payOrderReqBean.getV_rcvtel(),
                    payOrderReqBean.getV_rcvpost(),payOrderReqBean.getV_amount(),payOrderReqBean.getV_ymd(),payOrderReqBean.getV_orderstatus(),payOrderReqBean.getV_ordername(),payOrderReqBean.getV_moneytype(),
                    payOrderReqBean.getV_url(),payOrderReqBean.getV_md5info(),payOrderReqBean.getV_ordip(),payOrderReqBean.getV_merdata5(),payOrderReqBean.getV_pmode()).execute();
            if(execute.isSuccessful()){
                Log.info("支付下单返回："+execute.body());
                payOrderResBean = XStreamTool.toBean(execute.body(),PayOrderResBean.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payOrderResBean;
    }
    /**
     * 获取流水号
     * @return
     */
    private final Random random = new Random();
    private String serialNo(){
        return "MO" + new Date().getTime()+""+String.format("%1$03d", random.nextInt(1000));
    }

    /**
     * 接收支付返回结果
     */
    public ResponseEntity getPayResult(PayResultBackBean payResultBackBean) {
        try {
            String v_oid = payResultBackBean.getV_oid();
            String v_pmode = payResultBackBean.getV_pmode();
            String v_pstatus = payResultBackBean.getV_pstatus();
            String v_pstring = payResultBackBean.getV_pstring();
            String v_count = payResultBackBean.getV_count();
            String v_amount = payResultBackBean.getV_amount();
            String v_moneytype = payResultBackBean.getV_moneytype();
            String v_sign = payResultBackBean.getV_sign();
            String v_md5money = payResultBackBean.getV_md5money();
            String v_mac = payResultBackBean.getV_mac();

            String[] resultOid = v_oid.split("[|][_][|]");
            String[] resultPmode = v_pmode.split("[|][_][|]");
            String[] resultPstatus = v_pstatus.split("[|][_][|]");
            String[] resultPstring = v_pstring.split("[|][_][|]");
            String[] resultAmount = v_amount.split("[|][_][|]");
            String[] resultMoneyType = v_moneytype.split("[|][_][|]");
            v_pmode = URLEncoder.encode(v_pmode,"UTF-8");
            v_pstring = URLEncoder.encode(v_pstring,"UTF-8");
            String source1=v_oid+v_pmode+v_pstatus+v_pstring+v_count;//中文加密前 encode
            String source2 = v_amount +v_moneytype; //md5加密1
            Md5 md5 = new Md5 ("");
            md5.hmac_Md5(source1 , ProjectConstants.MD5KEY);
            byte b[]= md5.getDigest();
            String digestString = md5.stringify(b) ; //md5加密2
            Md5 m = new Md5 ("") ;
            m.hmac_Md5(source2 , ProjectConstants.MD5KEY);
            byte a[]= m.getDigest();
            String digestString2 = m.stringify(a) ; //RSA验证
            String publicKey = PayOrderService.class.getClassLoader().getResource("cert/Public1024.key").getPath();
            String strSource = v_oid + v_pstatus + v_amount + v_moneytype + v_count;
            RSA_MD5 rsaMD5 = new RSA_MD5();

            int k = rsaMD5.PublicVerifyMD5(publicKey,v_sign,strSource);
             /*校验签名:digestString.equals(v_mac)*/
            if(digestString2.equals(v_md5money)&&(k==0)){
                boolean isRecharge = false;
                for(int i=0;i<resultOid.length;i++){
                    String oid = StringUtils.substringAfterLast(resultOid[i],"-");
                    String pstatus = resultPstatus[i];
                    isRecharge = moneyService.toAddMoney(oid,pstatus);
                    if(!isRecharge){
                        Log.error("recharge money fail, oid is "+resultOid[i]);
                        return new ResponseEntity("error", HttpStatus.OK);
                    }
                }
                return new ResponseEntity("sent", HttpStatus.OK);
            }else {
                return new ResponseEntity("error", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity("error",HttpStatus.OK);
    }
}
