package com.explore.canada.configuration;

import com.explore.canada.bean.ShoppingCart;
import com.explore.canada.bean.TicketInfo;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.service.*;

public class Config {

    private static Config config = null;
    private ShoppingCart shoppingCart = null;
    private TicketInfo ticketInfo = null;
    private UserInfo userAuth = null;
    private RESTServer serverConfig;
    private LoginService loginService;
    private RegistrationService registrationService;
    private BookingService bookingService;
    private PaymentService paymentService;
    private SearchService searchService;
    private RestService restService;
    private RestServiceClient restServiceClient;

    private Config(){
        serverConfig = new RESTServer();
        loginService = new LoginService();
        registrationService = new RegistrationService();
        bookingService = new BookingService();
        paymentService = new PaymentService();
        searchService = new SearchService();
        restService = new RestService();
        restServiceClient = new RestServiceClient();
        userAuth = new UserInfo();
        shoppingCart = new ShoppingCart();
        ticketInfo = new TicketInfo();
    }

    public static Config getInstance(){
        if(null == config){
            config = new Config();
        }
        return config;
    }

    public TicketInfo getTicketInfo() {
        return ticketInfo;
    }

    public void setTicketInfo(TicketInfo ticketInfo) {
        this.ticketInfo = ticketInfo;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public UserInfo getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserInfo userAuth) {
        this.userAuth = userAuth;
    }

    public RESTServer getServerConfig() {
        return serverConfig;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public RestService getRestService() {
        return restService;
    }

    public RestServiceClient getRestServiceClient() {
        return restServiceClient;
    }

    public SearchService getSearchService() {
        return searchService;
    }
}
