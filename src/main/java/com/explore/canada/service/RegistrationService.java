package com.explore.canada.service;

import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.configuration.Config;

public class RegistrationService {

    public UserInfo registerUser(UserInfo userInfo){
        RestServiceClient serviceClient = Config.getInstance().getRestServiceClient();
        serviceClient.makePostRequest(ServiceEndPoint.REGISTER_USER_SERVICE_URL,userInfo);
        return serviceClient.getUserInfo();
    }
}
