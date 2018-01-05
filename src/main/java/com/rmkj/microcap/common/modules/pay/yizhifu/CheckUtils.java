package com.rmkj.microcap.common.modules.pay.yizhifu;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/1/5.
 * 易支付 代付 账号信息检验类
 */
public class CheckUtils {

    private final static Map<String, String> lianHangHaoMap = new HashMap<>();

    private static final List<String> provinceList = new ArrayList<>();

    static {
        lianHangHaoMap.put("南京银行","313301008887");
        lianHangHaoMap.put("包商银行股份有限公司","313192000013");
        lianHangHaoMap.put("中国农业银行","103100000026");
        lianHangHaoMap.put("中国建设银行","105100000017");
        lianHangHaoMap.put("中国光大银行","303100000006");
        lianHangHaoMap.put("中国民生银行","305100000013");
        lianHangHaoMap.put("兴业银行总行","309391000011");
        lianHangHaoMap.put("北京农村商业银行4","402100000018");
        lianHangHaoMap.put("韩亚银行","597100000014");
        lianHangHaoMap.put("渤海银行","318110000014");
        lianHangHaoMap.put("天津银行","313110000017");
        lianHangHaoMap.put("天津农商银行","317110010019");
        lianHangHaoMap.put("外换银行(中国)有限公司","591110000016");
        lianHangHaoMap.put("深圳农商行","402584009991");
        lianHangHaoMap.put("平安银行","313584099990");
        lianHangHaoMap.put("东莞农村商业银行","402602000018");
        lianHangHaoMap.put("广州银行","313581003284");
        lianHangHaoMap.put("广州农村商业银行","314581000011");
        lianHangHaoMap.put("中国工商银行","102100099996");
        lianHangHaoMap.put("交通银行","301290000007");
        lianHangHaoMap.put("中信银行","302100011000");
        lianHangHaoMap.put("华夏银行","304100040000");
        lianHangHaoMap.put("招商银行","308584000013");
        lianHangHaoMap.put("广发银行股份有限公司","306581000003");
        lianHangHaoMap.put("中国邮政储蓄银行","403100000004");
        lianHangHaoMap.put("北京银行","313100000013");
        lianHangHaoMap.put("新韩银行中国","595100000007");
        lianHangHaoMap.put("东莞银行","313602088017");
        lianHangHaoMap.put("顺德农村商业银行","314588000016");
        lianHangHaoMap.put("上海浦东发展银行","310290000013");
        lianHangHaoMap.put("安徽省农村信用联合社","402361018886");
        lianHangHaoMap.put("厦门银行","313393080005");
        lianHangHaoMap.put("广西北部湾银行","313611001018");
        lianHangHaoMap.put("长沙银行","313551088886");
        lianHangHaoMap.put("昆山农村商业银行","314305206650");
        lianHangHaoMap.put("苏州银行","14305006665");
        lianHangHaoMap.put("张家港农村商业银行","314305670002");
        lianHangHaoMap.put("南昌银行","313421087506");
        lianHangHaoMap.put("上饶银行","313433076801");
        lianHangHaoMap.put("东营市商业银行","313455000018");
        lianHangHaoMap.put("济宁银行5","313461000012");
        lianHangHaoMap.put("莱商银行","313463400019");
        lianHangHaoMap.put("临商银行","313473070018");
        lianHangHaoMap.put("齐商银行","313453001017");
        lianHangHaoMap.put("青岛银行","313452060150");
        lianHangHaoMap.put("日照银行","313473200011");
        lianHangHaoMap.put("泰安市商业银行","313463000993");
        lianHangHaoMap.put("威海市商业银行","313465000010");
        lianHangHaoMap.put("烟台银行","313456000108");
        lianHangHaoMap.put("大连银行","313222080002");
        lianHangHaoMap.put("锦州银行","313227000012");
        lianHangHaoMap.put("鞍山市商业银行","313223007007");
        lianHangHaoMap.put("葫芦岛银行","313227600018");
        lianHangHaoMap.put("上海农村商业银行","402290000011");
        lianHangHaoMap.put("上海银行","313290000017");
        lianHangHaoMap.put("浙江稠州商业银行","313338707013");
        lianHangHaoMap.put("杭州银行","313331000014");
        lianHangHaoMap.put("湖州银行","313336071575");
        lianHangHaoMap.put("宁波银行","313332082914");
        lianHangHaoMap.put("浙江泰隆商业银行","313345010019");
        lianHangHaoMap.put("温州银行","313333007331");
        lianHangHaoMap.put("鄞州银行","402332010004");
        lianHangHaoMap.put("浙商银行","316331000018");
        lianHangHaoMap.put("黄河农村商业银行","402871099996");
        lianHangHaoMap.put("上海农村商业银行","322290000011");
        lianHangHaoMap.put("中国银行","104100000004");

        String provinces = "重庆市、福建省、甘肃省、广西自治区、贵州省、海南省、河北省、河南省、黑龙江省、" +
                "湖北省、湖南省、江苏省、江西省、吉林省、辽宁省、内蒙古自治区、宁夏自治区、" +
                "青海省、山东省、山西省、陕西省、四川省、天津市、新疆自治区、西藏自治区、" +
                    "云南省、浙江省";
        String[] arr = provinces.split("、");
        for (int i = 0; i < arr.length; i++){
            provinceList.add(arr[i]);
        }
    }

    public static String getLianHangHao(String bankName){
        return lianHangHaoMap.get(bankName);
    }

    public static boolean checkProvince(String provinceName){
        return provinceList.contains(provinceName);
    }

    public static boolean checkCity(String cityName){
        return StringUtils.isNotBlank(cityName) && (cityName.endsWith("市") || cityName.endsWith("县"));
    }

}
