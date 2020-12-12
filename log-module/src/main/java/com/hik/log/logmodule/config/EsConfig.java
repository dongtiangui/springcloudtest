package com.hik.log.logmodule.config;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * es配置
 */
@Configuration
public class EsConfig {
    @Value("${elasticsearch.uris}")
    private String esUrl;
    @Bean
    public RestClientBuilder restClientBuilder() {
        return RestClient.builder(makeHttpHost(esUrl));
    }
    @Bean(name = "highLevelClient")
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }
    private HttpHost makeHttpHost(String s) {
        String[] address = s.split(":");
        String ip = address[0];
        int port = Integer.parseInt(address[1]);
        return new HttpHost(ip, port, "http");
    }
}
