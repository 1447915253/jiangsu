package com.rmkj.microcap.common.modules.pay.yizhifu.service;

import com.capinfo.crypt.Md5;
import com.capinfo.crypt.RSA_MD5;
import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.PayResultBackBean;
import com.rmkj.microcap.common.modules.pay.yizhifu.bean.UnionPayOrderReq;
import com.rmkj.microcap.modules.money.service.MoneyService;
import com.rmkj.microcap.modules.user.entity.User;
import com.rmkj.microcap.modules.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017/1/6.
 */
@Service
public class UnionPayOrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private MoneyService moneyService;

    private static final Logger Log = Logger.getLogger(UnionPayOrderService.class);
    /**
     * 下订单
     */
    public UnionPayOrderReq createUnionPayOrder() {
        UnionPayOrderReq unionPayOrderReq = new UnionPayOrderReq();
        User user = (User) userService.get().getBody();
        String key = ProjectConstants.MD5KEY;//MD5Key,
        //商户号
        String v_mid = ProjectConstants.V_MID;
        String v_rcvaddr = "吉林省长春市";
        String v_rcvpost = "130400";
        ;
        String v_rcvtel = user.getMobile();
        String v_rcvname = v_mid;
        String v_url = "trade.dzwjyzx.com/front/wap/yizhifu/pay/yiZhiFuReturn";

        SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("HHmmss");//设置时间时分秒格式

        unionPayOrderReq.setV_mid(v_mid);
        unionPayOrderReq.setV_rcvname(v_rcvname);
        unionPayOrderReq.setV_rcvaddr(v_rcvaddr);
        unionPayOrderReq.setV_rcvtel(v_rcvtel);
        unionPayOrderReq.setV_rcvpost(v_rcvpost);
        unionPayOrderReq.setV_orderstatus("1");
        unionPayOrderReq.setV_ordername(v_mid);
        String v_moneytype = "0";
        unionPayOrderReq.setV_moneytype(v_moneytype);
        unionPayOrderReq.setV_url(v_url);

        return unionPayOrderReq;
    }

    private final Random random = new Random();
    /**
     * 获取流水号
     * @return
     */
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
        return new ResponseEntity("error", HttpStatus.OK);
    }
}
