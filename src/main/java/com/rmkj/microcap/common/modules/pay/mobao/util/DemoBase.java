package com.rmkj.microcap.common.modules.pay.mobao.util;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;


public class DemoBase {

	private static final Logger Log = Logger.getLogger(DemoBase.class);

	static HttpClient client = new HttpClient();

	static  String   testUrl="http://newpay.kspay.net:8080/ks_onlpay/gateways/trans";

	public static String  requestBody(String merId,String transData){
	PostMethod method= new PostMethod(testUrl);
	method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");    
	method.setParameter("merId",merId);
	method.setParameter("transData",transData);
	client.setConnectionTimeout(8000);
	try {
		int statusCode = client.executeMethod(method);
		if (statusCode != 200) {
			Log.info("statusCode=" + statusCode);
			return null;
	    }else{
	    	String resp = method.getResponseBodyAsString();
	    	return resp;
	    }
	} catch (HttpException e){
		e.printStackTrace();
		return ""+e.getMessage();
	} catch (IOException e) {
		e.printStackTrace();
		return ""+e.getMessage();
	}
	}
	
	
	/***
	 * 
	 * @param url
	 * @param map
	 * @param charSet
	 * @return
	 */
	public static  String POSTReturnString(String url, Map<String, String> map,String charSet) {
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=" + charSet);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			method.setParameter(entry.getKey(), entry.getValue());
		}
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != 200) {
				System.out.println("statusCode=" + statusCode);
				return null;
			} else {
				String resp = method.getResponseBodyAsString();
				System.out.println("resp=" + resp);
				return resp;
			}
		} catch (HttpException e) {
			e.printStackTrace();
			return "" + e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return "" + e.getMessage();
		}
	}
}
