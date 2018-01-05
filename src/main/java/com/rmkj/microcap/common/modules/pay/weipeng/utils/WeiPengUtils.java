package com.rmkj.microcap.common.modules.pay.weipeng.utils;/**
 * Created by Administrator on 2017/3/6.
 */

import com.rmkj.microcap.common.constants.ProjectConstants;
import com.rmkj.microcap.common.modules.pay.weipeng.bean.WeiPengDoBean;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO 威鹏支付工具类
 * @author k
 * @create -03-06-10:48
 **/

public class WeiPengUtils {

    /**s
     * TODO 返回链接参数
     * @param weiPengDoBean
     * @return
     */
    public static Map<String, String> getWeiPengScanQrcodeInfo(WeiPengDoBean weiPengDoBean, String serialNo){
        String merchant_no = ProjectConstants.WEIPENG_PAY_MERCHANT_NO;// 商户号，威鹏分配
        Integer total_fee = fromYuan(new BigDecimal(weiPengDoBean.getMoney()));// 单位为分，整型
        String pay_num = serialNo;// 商户自己系统订单号
        String key = ProjectConstants.WEIPENG_PAY_SECRET;// 威鹏分配，用户提交
        //生成参数签名
        StringBuffer sign = new StringBuffer();
        sign.append(merchant_no)
                .append(total_fee)
                .append(utilSimpleDateFormat("yyyyMMdd"))
                .append(key);
        Map<String, String> result = new HashMap();
        result.put("merchant_no", merchant_no);
        result.put("total_fee", total_fee.toString());
        result.put("pay_num", pay_num);
        result.put("sign", getMD5(sign.toString()));
        result.put("notifyurl", ProjectConstants.WEIPENG_QRCODE_PAY_ASYNCRESULT_URL);
        result.put("productname", "快盈国际扫码下单");
        return result;
    }


    public static String getMD5(String str) {
        String reStr = null;

        try {
            // 创建具有指定算法名称的信息
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());// 使用指定的字节更新摘要。
            byte ss[] = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算
            reStr = bytes2String(ss);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return reStr;
    }

    // 将字节数组转换为字符串
    private static String bytes2String(byte[] aa) {
        String hash = "";
        for (int i = 0; i < aa.length; i++) {// 循环数组
            int temp;
            if (aa[i] < 0) // 如果小于零，将其变为正数
                temp = 256 + aa[i];
            else
                temp = aa[i];
            if (temp < 16)
                hash += "0";
            hash += Integer.toString(temp, 16);// 转换为16进制
        }
        hash = hash.toUpperCase();// 全部转换为大写
        return hash;
    }

    /**
     * TODO 格式化时间样式
     * @param formatStyle yyyy-MM-dd
     * @return
     */
    public static String utilSimpleDateFormat(String formatStyle){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStyle);
        return simpleDateFormat.format(new Date());
    }

    /**
     * TODO元转换为分.
     *
     * @param yuan
     *            元
     * @return 分
     */
    public static String fromYuanToFen(final String yuan) {
        String fen = "";
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");
        Matcher matcher = pattern.matcher(yuan);
        if (matcher.matches()) {
            try {
                NumberFormat format = NumberFormat.getInstance();
                Number number = format.parse(yuan);
                double temp = number.doubleValue() * 100.0;
                // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012
                format.setGroupingUsed(false);
                // 设置返回数的小数部分所允许的最大位数
                format.setMaximumFractionDigits(0);
                fen = format.format(temp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("参数格式不正确!");
        }
        return fen;
    }

    /**
     * TODO BigDecimal类型转换int
     * @param money
     * @return  分
     */
    public static int fromYuan(final BigDecimal money){
        return money.multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 威鹏H5支付参数
     * @param weiPengDoBean
     * @return
     */
    public static Map<String, String> getWeiPengH5Info(WeiPengDoBean weiPengDoBean, String serialNo){
        String merchant_no = ProjectConstants.WEIPENG_PAY_MERCHANT_NO_H5;// 商户号，威鹏分配
        Integer total_fee = fromYuan(new BigDecimal(weiPengDoBean.getMoney()));// 单位为分，整型
        String pay_num = serialNo;// 商户自己系统订单号
        String key = ProjectConstants.WEIPENG_PAY_SECRET_H5;// 威鹏分配，用户提交
        //生成参数签名 sign = MD5(merchant_no+pay_num+total_fee+today+key)；
        StringBuffer sign = new StringBuffer();
        sign.append(merchant_no)
                .append(pay_num)
                .append(total_fee)
                .append(utilSimpleDateFormat("yyyyMMdd"))
                .append(key);
        Map<String, String> result = new HashMap();
        result.put("merchant_no", merchant_no);
        result.put("total_fee", total_fee.toString());
        result.put("pay_num", pay_num);
        result.put("sign", getMD5(sign.toString()));
        result.put("notifyurl", ProjectConstants.WEIPENG_WECHAT_ASYNCRESULT_URL_H5);
        result.put("productname", "人人宝扫码下单");
        return result;
    }
}
