package com.explore.canada.service;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.function.Consumer;


@Service
@PropertySource("classpath:application.properties")
public class RestService
{
    private static HttpHeaders headers;
    private static HttpEntity entity;

    private static final String HOST_NAME = "127.0.0.1";
    private static final String PORT = "8083";
    private static final String TOKEN = "AUTH_API_KEY";
    private static final String TOKEN_SECRET = "abcd123456";

    public static HttpHeaders getHeader(){
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(TOKEN,TOKEN_SECRET);
        //return entity = new HttpEntity<>("Authorization", headers);
        return headers;
    }

    public static String getEndPoint(){
        return "http://" + HOST_NAME + ":" + PORT + "/api";
    }
    public static String getHostName() {
        return HOST_NAME;
    }

    public static String getPORT() {
        return PORT;
    }
}
