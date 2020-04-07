package com.explore.canada.bean;
import com.explore.canada.configuration.Config;
import com.explore.canada.service.LoginService;
import com.explore.canada.service.RegistrationService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfo {
    // This regex comes from here:
    // https://howtodoinjava.com/regex/java-regex-validate-email-address/
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    String userId;
    String userEmail;
    String userFirstName;
    String userLastName;
    String userDateOfBirth;
    String userPassword;

    public UserInfo() {

    }

    public UserInfo(String userId, String userEmail, String userFirstName, String userLastName, String userDateOfBirth, String userPassword) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDateOfBirth = userDateOfBirth;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserDateOfBirth() {
        return userDateOfBirth;
    }

    public void setUserDateOfBirth(String userDateOfBirth) {
        this.userDateOfBirth = userDateOfBirth;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean registerUser(UserInfo userInfo)
    {
        boolean success = false;
        RegistrationService registrationService = Config.getInstance().getRegistrationService();
        try {
            UserInfo u = registrationService.registerUser(userInfo);
            if(u != null) {
                success = true;
            }
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return success;
        }
        return success;
    }

    public UserInfo loadUserByEmailId(String emailId, UserInfo userInfo)
    {
        LoginService loginService = Config.getInstance().getLoginService();
        try {
                userInfo = loginService.loadUserByEmailId(emailId);
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return null;
        }
        return userInfo;
    }

    public UserInfo loadUserById(String userId, UserInfo userInfo)
    {
        try {
            //userInfo = userDb.loadUserById(userId);
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return null;
        }
        return userInfo;
    }

    public List<UserInfo> loadAllUsers()
    {
        List<UserInfo> userInfoList = null;
        try {
            //userInfoList = userDb.loadAllUsers();
        }
        catch (Exception genericException){
            //logger.error(genericException);
            return null;
        }
        return userInfoList;
    }

    private static boolean isStringNullOrEmpty(String s)
    {
        if (null == s)
        {
            return true;
        }
        return s.isEmpty();
    }

    public static boolean isUserIdValid(String userId)
    {
        return !isStringNullOrEmpty(userId);
    }

    public static boolean isFirstNameValid(String name)
    {
        return !isStringNullOrEmpty(name);
    }

    public static boolean isLastNameValid(String name)
    {
        return !isStringNullOrEmpty(name);
    }

    public static boolean isEmailValid(String email)
    {
        if (isStringNullOrEmpty(email))
        {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
