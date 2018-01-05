package com.rmkj.microcap.common.modules.pay.baishi.util;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.rmkj.microcap.common.constants.ProjectConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.util.encoders.Hex;

public class SignUtils {
    private static final Logger Log = Logger.getLogger(SignUtils.class);

    public static String signData(List<BasicNameValuePair> nvps) throws Exception {
        TreeMap<String, String> tempMap = new TreeMap<String, String>();
        for (BasicNameValuePair pair : nvps) {
            if (StringUtils.isNotBlank(pair.getValue())) {
                tempMap.put(pair.getName(), pair.getValue());
            }
        }
        StringBuffer buf = new StringBuffer();
        for (String key : tempMap.keySet()) {
            buf.append(key).append("=").append((String) tempMap.get(key)).append("&");
        }
        buf.append("Key").append("=").append(ProjectConstants.BAI_SHI_KEY);
        String signatureStr = buf.toString();
        String signData = MD5Utils.makeMD5String(signatureStr).toUpperCase();
        return signData;
    }

    public static boolean verferSignData(String str) {
        String data[] = str.split("&");
        StringBuffer buf = new StringBuffer();
        String signature = "";
        for (int i = 0; i < data.length; i++) {
            String tmp[] = data[i].split("=", 2);
            if ("Sign".equals(tmp[0])) {
                signature = tmp[1];
            } else {
                buf.append(tmp[0]).append("=").append(tmp[1]).append("&");
            }
        }
        buf.append("Key").append("=").append(ProjectConstants.BAI_SHI_KEY);
        String signatureStr = buf.toString();
        String signData = MD5Utils.makeMD5String(signatureStr).toUpperCase();
        return signature.equals(signData);
    }
    
}
