package com.rmkj.microcap.common.modules.pay.xinwenxin.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 * 功能：MD5签名处理核心文件，不需要修改 版本：3.3 修改日期：2016-10-23 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个
 */

public class MD5Utils {

	/**
	 * 签名字符串
	 * 
	 * 需要签名的字符串
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	// public static String sign(String text, String key, String input_charset)
	// {
	// text = text + key;
	// return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	// }
	//
	private final static Logger Log = Logger.getLogger(MD5Utils.class);

	public static String sign(Map<String, String> params, String key, String input_charset) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = paraFilter(params);
		// 获取待签名字符串
		String preSignStr = createLinkString(sParaNew);
		// 获得签名验证结果
		String text = preSignStr + "&key=" + key;
		Log.info("待签名串："+text);
		String sign = DigestUtils.md5Hex(getContentBytes(text, input_charset)).toUpperCase();
		// 签名结果与签名方式加入请求提交参数组中
		//preSignStr += "&sign=" + sign + "&sign_type=" + sign_type;
		return sign;

	}
	
	//生成签名并对字符进行编码
		public static String signAndEncode(Map<String, String> params, String sign_type, String key, String input_charset) throws UnsupportedEncodingException {
			// 过滤空值、sign与sign_type参数
			Map<String, String> sParaNew = paraFilter(params);
			// 获取待签名字符串
			String preSignStr = createLinkString(sParaNew);
			// 获得签名验证结果
			String text = preSignStr + key;
			String sign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
			// 签名结果与签名方式加入请求提交参数组中
			Map<String,String> result = new HashMap<String,String>();
			for (String ikey : sParaNew.keySet()) {
				String value = sParaNew.get(ikey);
				result.put(ikey, URLEncoder.encode(value, input_charset));
			}
			preSignStr = createLinkString(result);
			preSignStr += "&sign=" + sign + "&sign_type=" + sign_type;
			System.out.println(preSignStr);
			return preSignStr;
		}

	public static String signStr(Map<String, String> params, String sign_type, String key, String input_charset) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = paraFilter(params);
		// 获取待签名字符串
		String preSignStr = createLinkString(sParaNew);
		// 获得签名验证结果
		String text = preSignStr + key;
		String sign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		return sign;

	}

	public static Map<String, String> signMap(Map<String, String> params, String sign_type, String key,
			String input_charset) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = paraFilter(params);
		// 获取待签名字符串
		String preSignStr = createLinkString(sParaNew);
		// 获得签名验证结果
		String text = preSignStr + key;
		String sign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		// 签名结果与签名方式加入请求提交参数组中
		sParaNew.put("sign", sign);
		sParaNew.put("sign_type", sign_type);
		preSignStr +="&sign="+sign+"&sign_type=MD5";
		System.out.println("请求字符串:"+preSignStr);
		
		return sParaNew;

	}

//	public static String sign(String queryStr, String sign_type, String key, String input_charset) {
//		Map<String, String> params = new HashMap<String, String>();
//		if (!Strings.isNullOrEmpty(queryStr)) {
//			String[] arrays = queryStr.split("&");
//			for (int i = 0; i < arrays.length; i++) {
//				String tempKey = arrays[i].split("=")[0];
//				String tmeValue = arrays[i].split("=")[1];
//				params.put(tempKey, tmeValue);
//			}
//		}
//		// 过滤空值、sign与sign_type参数
//		Map<String, String> sParaNew = paraFilter(params);
//		// 获取待签名字符串
//		String preSignStr = createLinkString(sParaNew);
//		// 获得签名验证结果
//		String text = preSignStr + key;
//		String sign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
//		// 签名结果与签名方式加入请求提交参数组中
//		preSignStr += "&sign=" + sign + "&sign_type=" + sign_type;
//		return preSignStr;
//
//	}

	/**
	 * 签名字符串
	 * 
	 * 需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @param key
	 *            密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static boolean verify(Map<String, String> params, String sign, String key, String input_charset) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = paraFilter(params);
		// 获取待签名字符串
		String preSignStr = createLinkString(sParaNew);

		String text = preSignStr + key;
		String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	/**
	 * 对所有参数值进行一次编码
	 * 
	 *            签名参数组
	 * @return 对所有参数值进行一次解码的新签名参数组
	 */
	public static Map<String, String> encodeFilter(Map<String, String> params, String input_charset) {

		Map<String, String> result = new HashMap<String, String>();

		if (params == null || params.size() <= 0) {
			return result;
		}

		for (String key : params.keySet()) {
			String value = params.get(key);
			try {
				result.put(key, URLEncoder.encode(value, input_charset));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + input_charset);
			}
		}

		return result;
	}
	/**
	 * 对所有参数值进行一次解码
	 * 
	 *            签名参数组
	 * @return 对所有参数值进行一次解码的新签名参数组
	 */
	public static Map<String, String> decodeFilter(Map<String, String> params, String input_charset) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		if (params == null || params.size() <= 0) {
			return result;
		}
		
		for (String key : params.keySet()) {
			String value = params.get(key);
			try {
				result.put(key, URLDecoder.decode(value, input_charset));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + input_charset);
			}
		}
		
		return result;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {

		Map<String, String> result = new HashMap<String, String>();

		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}

		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

	/**
	 * 生成随即密码
	 * 
	 * @param pwd_len
	 *            生成的密码的总长度
	 * @return 密码的字符串
	 */
	public static String genRandomNum(int pwd_len) {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		//amount=0.1&currency_type=CNY&merchant_no=2016169558218894&product_name=TEST&return_url=http://www.baidu.com&sett _currency_type=CNY&user_order_no=20161024090807&sign=1816ca1a4b3db56581f1dbd89d50fc9e&sign_type=MD5
		//http://paytest.yunpuvip.com/ant/api/pay/wechat?amount=0.1&currency_type=CNY&merchant_no=2016169558218894&product_name=TEST&return_url=http://www.baidu.com&sett _currency_type=CNY&user_order_no=20161024090807&sign=1816ca1a4b3db56581f1dbd89d50fc9e&sign_type=MD5

		Map<String,String> map = new HashMap<String,String>();
   		map.put("merchant_no", "2016169558218894");
   		map.put("user_order_no", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
   		map.put("amount", "0.1");
   		map.put("currency_type", "CNY");
   		map.put("sett_currency_type", "CNY");
   		map.put("product_name", "商品购买");
   		map.put("product_desc", "图书购买");
   		map.put("product_type", "图书");
   		map.put("return_url", "http://www.baidu.com");
   		map.put("notify_url", "http://paytest.yunpuvip.com/notify.shtml");
		map.put("user_bank_card_no", "6217003320004294073");
		map.put("user_name", "杨学勇");
		map.put("user_mobile", "18200982382");
		map.put("user_cert_no", "522631198702280017");
		map.put("user_cert_type", "01");
		
		String returnDate = MD5Utils.signAndEncode(map, "MD5", "OtLgMjWQuRdoYhuOIP6JjPRjzBf47B9x", "UTF-8");
		String queryString = "http://paytest.yunpuvip.com/ant/api/pay/quick.shtml?"+returnDate;		System.out.println(returnDate);
		System.out.println(queryString);
//		
//		String   mytext   =   java.net.URLEncoder.encode("",   "utf-8");     
//		System.out.println(mytext);
//		String   mytext2   =   java.net.URLDecoder.decode(mytext,   "utf-8");  
//		System.out.println(mytext2);
		
		
		
	}

}