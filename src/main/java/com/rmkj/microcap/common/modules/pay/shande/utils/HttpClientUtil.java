package com.rmkj.microcap.common.modules.pay.shande.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目：fs-fubei-shop
 * 包名：com.fshows.fubei.shop.common.http
 * 功能：
 * 时间：2016-11-03
 * 作者：呱牛
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private static RequestConfig requestConfig = RequestConfig.custom().build();

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String get(String url, Map<String, String> queries) {
        String responseBody = "";
        //支持https
        StringBuilder sb = new StringBuilder(url);

        if (queries != null && !queries.isEmpty()) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        HttpGet httpGet = new HttpGet(sb.toString());
        //设置超时
        httpGet.setConfig(requestConfig);
        //HttpEntity
        HttpEntity entity = null;
        //请求数据
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                entity = response.getEntity();
                responseBody = EntityUtils.toString(entity);
//                EntityUtils.consume(response.getEntity()); //会自动释放连接
            }
        } catch (Exception ex) {
            logger.error("http error >> ex = {}", ex.getStackTrace());
        } finally {
            // 关闭连接,释放资源
            if (entity != null) {
                EntityUtils.consumeQuietly(entity); //会自动释放连接
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("http close error >> ex = {}", e.getStackTrace());
                }
            }
        }
        return responseBody;
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static File getFile(String url, Map<String, String> queries, File file) {
        //支持https
        StringBuilder sb = new StringBuilder(url);

        if (queries != null && !queries.isEmpty()) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        HttpGet httpGet = new HttpGet(sb.toString());
        //设置超时
        httpGet.setConfig(requestConfig);
        //HttpEntity
        HttpEntity entity = null;
        //请求数据
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                entity = response.getEntity();

                BufferedInputStream bis = new BufferedInputStream(entity.getContent());
                if (bis.available() < 1024) {
                    bis.close();

                    return null;
                }

                FileOutputStream fos = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int size = 0;
                while ((size = bis.read(buf)) != -1) {
                    fos.write(buf, 0, size);
                }
                fos.close();
                bis.close();
            }
        } catch (Exception ex) {
            logger.error("http error >> ex = {}", ex.getStackTrace());

            file.delete();

            return null;
        } finally {
            // 关闭连接,释放资源
            if (entity != null) {
                EntityUtils.consumeQuietly(entity); //会自动释放连接
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("http close error >> ex = {}", e.getStackTrace());
                }
            }
        }
        return file;
    }

    /**
     * post
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @param params  post form 提交的参数
     * @return
     */
    public static String post(String url, Map<String, String> queries, Map<String, String> params) {
        String responseBody = "";
        //支持https
        StringBuilder sb = new StringBuilder(url);

        if (queries != null && !queries.isEmpty()) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                if (firstFlag) {
                    sb.append("?").append(entry.getKey()).append("=").append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }

        //指定url,和http方式
        HttpPost httpPost = new HttpPost(sb.toString());
        httpPost.setConfig(requestConfig);
        //添加参数
        List<NameValuePair> nvps = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        //HttpEntity
        HttpEntity entity = null;
        //请求数据
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                entity = response.getEntity();
                responseBody = EntityUtils.toString(entity);
//                EntityUtils.consume(response.getEntity()); //会自动释放连接
            }
        } catch (IOException ex) {
            logger.error("http error >> ex = {}", ex.getStackTrace());
        } finally {
            // 关闭连接,释放资源
            if (entity != null) {
                EntityUtils.consumeQuietly(entity); //会自动释放连接
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("http close error >> ex = {}", e.getStackTrace());
                }
            }
        }
        return responseBody;
    }


    /**
     * post
     *
     * @param url  请求的url
     * @param data post json 提交的参数
     * @return
     */
    public static String post(String url, String data) {
        String responseBody = "";

        //指定url,和http方式
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        //设置类型
        StringEntity se = new StringEntity(data, "utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(se);
        //HttpEntity
        HttpEntity entity = null;
        //请求数据
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                entity = response.getEntity();
                responseBody = EntityUtils.toString(entity);
            }
        } catch (IOException ex) {
            logger.error("http post error >> ex = {}", ex.getStackTrace());
        } finally {
            // 关闭连接,释放资源
            if (entity != null) {
                EntityUtils.consumeQuietly(entity); //会自动释放连接
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("http post close error >> ex = {}", e.getStackTrace());
                }
            }
        }
        return responseBody;
    }
}
