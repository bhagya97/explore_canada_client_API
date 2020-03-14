package com.explore.canada.bean;

import com.explore.canada.service.RestService;

public class ServiceEndPoint {
    public static final String GET_ALL_USER_SERVICE_URL = RestService.getEndPoint() + "/users";
    public static final String LOGIN_USER_SERVICE_URL = RestService.getEndPoint() + "/login";
    public static final String REGISTER_USER_SERVICE_URL = RestService.getEndPoint() + "/register";
    public static final String DELETE_USER_SERVICE_URL = RestService.getEndPoint() + "/delete";
    public static final String UPDATE_USER_SERVICE_URL = RestService.getEndPoint() + "/update";
    public static final String GET_USER_SERVICE_URL = GET_ALL_USER_SERVICE_URL + "/user";
}
