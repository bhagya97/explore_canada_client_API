package com.explore.canada.service;

import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.bean.UserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class RestServiceClient {

    RestTemplate restTemplate;
    UserInfo userInfo;
    UserInfo[] userInfos;
    public RestServiceClient(){
        restTemplate = new RestTemplate();
    }

    public UserInfo getUserInfo(){
        return userInfo;
    }

    public UserInfo[] getUserInfoList(){
        return userInfos;
    }

    public void makePostRequest(String url, UserInfo user){
        try
        {
            HttpEntity<UserInfo> entity = new HttpEntity<UserInfo>(user, RestService.getHeader());
                ResponseEntity<UserInfo> response = restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        entity,
                        UserInfo.class
                );

                // check response
                if (response.getStatusCode() == HttpStatus.OK) {
                    System.out.println("Request Successful.");
                    userInfo = response.getBody();
                } else {
                    System.out.println("Request Failed");
                    System.out.println(response.getStatusCode());
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
    }

    public void makeParameterizedPostRequest(String url, Map<String,String> parameters){
        try
        {
            //adding the query params to the URL
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
            for(String key : parameters.keySet()){
                String value = parameters.get(key);
                uriBuilder.queryParam(key, value);
            }

            HttpEntity entity = new HttpEntity(RestService.getHeader());

            ResponseEntity<UserInfo> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.POST,
                    entity,
                    UserInfo.class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                userInfo = response.getBody();
            } else {
                System.out.println("Request Failed");
                System.out.println(response.getStatusCode());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void makePostRequest(String url){
        try
        {
            HttpEntity entity = new HttpEntity(RestService.getHeader());
            ResponseEntity<UserInfo> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    UserInfo.class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                userInfo = response.getBody();
            } else {
                System.out.println("Request Failed");
                System.out.println(response.getStatusCode());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public void makeGetRequest(String url){
        try
        {
            HttpEntity entity = new HttpEntity(RestService.getHeader());
            ResponseEntity<UserInfo[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    UserInfo[].class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                userInfos = response.getBody();
            } else {
                System.out.println("Request Failed");
                System.out.println(response.getStatusCode());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }


    public void makeParametrizedGetRequest(String url, String parameter){
        try
        {
            url = url + "/" + parameter;
            HttpEntity entity = new HttpEntity(RestService.getHeader());
            ResponseEntity<UserInfo[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    UserInfo[].class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                userInfos = response.getBody();
            } else {
                System.out.println("Request Failed");
                System.out.println(response.getStatusCode());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
