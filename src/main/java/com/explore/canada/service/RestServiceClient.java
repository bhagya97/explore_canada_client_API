package com.explore.canada.service;

import com.explore.canada.bean.SearchInfo;
import com.explore.canada.bean.ShoppingCart;
import com.explore.canada.bean.TicketInfo;
import com.explore.canada.bean.UserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class RestServiceClient {
    RestService restService;
    RestTemplate restTemplate;
    UserInfo userInfo;
    UserInfo[] userInfos;
    SearchInfo searchInfo;
    SearchInfo[] searchInfos;
    TicketInfo ticketInfo;
    TicketInfo[] ticketInfos;

    public RestServiceClient(){
        restTemplate = new RestTemplate();
        restService = new RestService();
    }

    public UserInfo getUserInfo(){
        return userInfo;
    }

    public UserInfo[] getUserInfoList(){
        return userInfos;
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public SearchInfo[] getSearchInfos() {
        return searchInfos;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    public void setTicketInfo(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;
    }

    public TicketInfo[] getTicketInfos() {
        return ticketInfos;
    }

    public void setTicketInfos(TicketInfo[] ticketInfos) {
        this.ticketInfos = ticketInfos;
    }

    public void makePostRequest(String url, UserInfo user){
        try
        {
                HttpEntity<UserInfo> entity = new HttpEntity<UserInfo>(user, restService.getHeader());
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

            HttpEntity entity = new HttpEntity(restService.getHeader());

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
            HttpEntity entity = new HttpEntity(restService.getHeader());
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


    public void makePostRequest(String url, ShoppingCart shoppingCart){
        try
        {
            HttpEntity<ShoppingCart> entity = new HttpEntity<ShoppingCart>(shoppingCart, restService.getHeader());
            ResponseEntity<TicketInfo> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    TicketInfo.class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                ticketInfo = response.getBody();
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
            HttpEntity entity = new HttpEntity(restService.getHeader());
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
            HttpEntity entity = new HttpEntity(restService.getHeader());
            ResponseEntity<UserInfo> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
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

    public void makeSearchGetRequest(String url){
        try
        {
            HttpEntity entity = new HttpEntity(restService.getHeader());
            ResponseEntity<SearchInfo[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    SearchInfo[].class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                searchInfos = response.getBody();
            } else {
                System.out.println("Request Failed");
                System.out.println(response.getStatusCode());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void makeParametrizedGetRequest(String url, Map<String,String> parameters){
        try
        {
            //adding the query params to the URL
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
            for(String key : parameters.keySet()){
                String value = parameters.get(key);
                uriBuilder.queryParam(key, value);
            }

            HttpEntity entity = new HttpEntity(restService.getHeader());
            ResponseEntity<SearchInfo[]> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    SearchInfo[].class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                searchInfos = response.getBody();
            } else {
                System.out.println("Request Failed");
                System.out.println(response.getStatusCode());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void makeParametrizedBusGetRequest(String url, Map<String,String> parameters){
        try
        {
            //adding the query params to the URL
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
            for(String key : parameters.keySet()){
                String value = parameters.get(key);
                uriBuilder.queryParam(key, value);
            }

            HttpEntity entity = new HttpEntity(restService.getHeader());
            ResponseEntity<TicketInfo[]> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    TicketInfo[].class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                ticketInfos = response.getBody();
            } else {
                System.out.println("Request Failed");
                System.out.println(response.getStatusCode());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void makeParametrizedBusGetRequestById(String url, Map<String,String> parameters){
        try
        {
            //adding the query params to the URL
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
            for(String key : parameters.keySet()){
                String value = parameters.get(key);
                uriBuilder.queryParam(key, value);
            }

            HttpEntity entity = new HttpEntity(restService.getHeader());
            ResponseEntity<TicketInfo> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    TicketInfo.class
            );

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("Request Successful.");
                ticketInfo = response.getBody();
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
