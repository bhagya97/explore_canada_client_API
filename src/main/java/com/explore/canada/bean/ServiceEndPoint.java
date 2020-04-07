package com.explore.canada.bean;
import com.explore.canada.configuration.Config;

public class ServiceEndPoint {
    public static final String API_END_POINT = Config.getInstance().getRestService().getEndPoint();
    public static final String GET_ALL_USER_SERVICE_URL = API_END_POINT + "/users";
    public static final String LOGIN_USER_SERVICE_URL = API_END_POINT + "/login";
    public static final String REGISTER_USER_SERVICE_URL = API_END_POINT + "/register";
    public static final String DELETE_USER_SERVICE_URL = API_END_POINT + "/delete";
    public static final String UPDATE_USER_SERVICE_URL = API_END_POINT + "/update";
    public static final String GET_USER_SERVICE_URL = GET_ALL_USER_SERVICE_URL + "/user";
    public static final String GET_LOCATION_CATEGORY_URL = API_END_POINT + "/locations/places";
    public static final String GET_LOCATION_URL = API_END_POINT + "/locations";
    public static final String GET_OTP_URL = API_END_POINT + "/validateotp";
    public static final String GET_FIND_BUS = API_END_POINT + "/findbus";
    public static final String GET_PAYMENT_BUS = API_END_POINT + "/payment";
    public static final String GET_BUS_BY_ID = API_END_POINT + "/findbusbyid";
    public static final String PAYMENT_USER_SERVICE_URL = API_END_POINT + "/payment";
}
