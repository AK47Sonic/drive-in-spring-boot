package com.sonic.client;

import com.sonic.bean.User;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestClient
 *
 * @author Sonic
 * @since 2019/8/3
 */
public class RestClient {

    public static void main(String[] args) {

        // 使用HttpClient
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);

        // 使用RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//        String content = restTemplate.getForObject("http://localhost:8080/user", String.class);
        User content = restTemplate.getForObject("http://localhost:8080/user", User.class); //自动通过定义类型序列化
        System.out.println(content);



    }
}
