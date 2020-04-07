package com.explore.canada.service;

import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.configuration.Config;
import com.explore.canada.security.CustomAuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginService {

    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String SECRET = "secret";

    public UserInfo authenticateUser(String userId, String password){
        String url = ServiceEndPoint.LOGIN_USER_SERVICE_URL;
        RestServiceClient serviceClient = Config.getInstance().getRestServiceClient();
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put(USER_EMAIL,userId);
        parameters.put(USER_PASSWORD, password);
        serviceClient.makeParameterizedPostRequest(url,parameters);
        return serviceClient.getUserInfo();
    }

    public UserInfo loadUserByEmailId(String emailId){
        String url = ServiceEndPoint.GET_USER_SERVICE_URL;
        RestServiceClient serviceClient = Config.getInstance().getRestServiceClient();
        serviceClient.makeParametrizedGetRequest(url,emailId);
        return serviceClient.getUserInfo();
    }

    public UserInfo validateOneTimePassword(String emailId,String password){
        String url = ServiceEndPoint.GET_OTP_URL;
        RestServiceClient serviceClient = Config.getInstance().getRestServiceClient();
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put(USER_EMAIL,emailId);
        parameters.put(SECRET, password);
        serviceClient.makeParameterizedPostRequest(url,parameters);
        return serviceClient.getUserInfo();
    }

    public Authentication setUserAuthentication(String userId, String password){
        // Grant USER rights system-wide, this is used to protect controller mappings.
        List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
        rights.add(new SimpleGrantedAuthority("USER"));
        // Return valid authentication token.
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(
                userId,
                password,
                rights);
        AuthenticationManager authenticationManager = new CustomAuthenticationManager();
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        return auth;
    }
}
