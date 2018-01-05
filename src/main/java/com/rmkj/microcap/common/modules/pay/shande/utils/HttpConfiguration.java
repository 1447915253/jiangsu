package com.rmkj.microcap.common.modules.pay.shande.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 项目：fs-fubei-shop
 * 包名：com.fshows.fubei.shop.config.http
 * 功能：
 * 时间：2016-11-04
 * 作者：呱牛
 */
@Configuration
public class HttpConfiguration {

    @Bean
    public LayeredConnectionSocketFactory sslSF() {
        LayeredConnectionSocketFactory sslSF = null;
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            //信任任何链接
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();

            return new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return sslSF;
    }

    @Bean
    public Registry<ConnectionSocketFactory> registryBuilder() {
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslSF())
                .register("http", new PlainConnectionSocketFactory())
                .build();
    }

    @Bean
    public PoolingHttpClientConnectionManager connectionManager() {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registryBuilder());
        connectionManager.setMaxTotal(500);
        connectionManager.setDefaultMaxPerRoute(500);

        return connectionManager;
    }

    @Bean
    public CloseableHttpClient httpClient() {
        return HttpClients.custom()
                .setConnectionManager(connectionManager())
                .build();
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10000)
                .build();
    }

    @Bean(destroyMethod = "shutdown")
    public IdleConnectionEvictor idleConnectionEvictor() {
        return new IdleConnectionEvictor(connectionManager());
    }
}
