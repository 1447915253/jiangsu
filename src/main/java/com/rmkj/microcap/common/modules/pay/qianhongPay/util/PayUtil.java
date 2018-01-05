package com.rmkj.microcap.common.modules.pay.qianhongPay.util;


import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

public class PayUtil {

	private final static Logger logger = Logger.getLogger(PayUtil.class);

	public static String generateOrderId(){
		String keyup_prefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String keyup_append = String.valueOf(new Random().nextInt(899999) + 100000);
		String pay_orderid = keyup_prefix + keyup_append;//订单号
		return pay_orderid;
	}

	public static String generateTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
