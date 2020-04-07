package com.explore.canada.service;
import com.explore.canada.configuration.Config;
import com.explore.canada.configuration.RESTServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class RestService
{
    private RESTServer restServer;
    private HttpHeaders headers;
    private HttpEntity entity;
    private String HOST_NAME;
    private String PORT;
    private String TOKEN;
    private String TOKEN_SECRET;

    public RestService(){
        restServer = new RESTServer();
        this.HOST_NAME = restServer.getHost();
        this.PORT = restServer.getPort();
        this.TOKEN = restServer.getToken();
        this.TOKEN_SECRET = restServer.getSecret();
    }

    public HttpHeaders getHeader(){
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(TOKEN,TOKEN_SECRET);
        return headers;
    }


    public String getEndPoint(){
        return "http://" + HOST_NAME + "/api";
    }

    /*
    public String getEndPoint(){
        return "http://" + HOST_NAME + ":" + PORT + "/api";
    }
     */

    public String getHostName() {
        return HOST_NAME;
    }
    public String getPORT() {
        return PORT;
    }
}
