package com.rmkj.microcap.common.modules.pay.baishi.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.sf.json.JSONObject;

public class ConfigUtils {
   
    public static JSONObject getJSON(String sb){    
		JSONObject obj = new JSONObject();
		String[] strArray = sb.split("&");
		for (int i = 0; i < strArray.length; i++) {
			String strTemp = strArray[i];
			int splitIndex = strTemp.indexOf("=");
			if (splitIndex > 0 && strTemp.length() > (splitIndex+1)) {
				obj.put(strTemp.substring(0,splitIndex), strTemp.substring(splitIndex+1));
			}
		}
        return obj;
    } 
}
