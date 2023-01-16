package com.zq.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {
        return new RestTemplate(requestFactory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(10000);
        return factory;
    }

    /**
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry =
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http",
                                PlainConnectionSocketFactory.getSocketFactory())
                        .register("https",
                                SSLConnectionSocketFactory.getSocketFactory())
                        .build();
        PoolingHttpClientConnectionManager
                connectionManager = new
                PoolingHttpClientConnectionManager(registry);
        //设置整个连接池最⼤连接数
        connectionManager.setMaxTotal(500);
        //MaxPerRoute路由是对maxTotal的细分,每个主机的并
        //发，这⾥route指的是域名

        connectionManager.setDefaultMaxPerRoute(200);
        RequestConfig requestConfig =
                RequestConfig.custom()
                        //返回数据的超时时间
                        .setSocketTimeout(20000)
                        //连接上服务器的超时时间
                        .setConnectTimeout(10000)
                        //从连接池中获取连接的超时时间
                        .setConnectionRequestTimeout(1000)
                        .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
