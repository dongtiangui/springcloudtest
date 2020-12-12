package com.hik.consul.hikconsul;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpConfig {



    @Bean({"x509TrustManager"})
    public X509TrustManager x509TrustManager(){
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {

            }
            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {

            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    @Bean
    public SSLSocketFactory sslSocketFactory(){
        try{
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null,new TrustManager[]{x509TrustManager()},new SecureRandom());
            return instance.getSocketFactory();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public ConnectionPool connectionPool(){
        return new ConnectionPool(40,5000, TimeUnit.SECONDS);
    }

    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory(), x509TrustManager())
            .connectionPool(connectionPool())
            .retryOnConnectionFailure(true)
            .connectTimeout(Duration.of(10000, ChronoUnit.SECONDS))
            .build();
        }

}
