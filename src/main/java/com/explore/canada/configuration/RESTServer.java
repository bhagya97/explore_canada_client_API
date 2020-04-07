package com.explore.canada.configuration;

/**
 * This bean is used to hold the user name, password and host which we need to make the
 * call to the REST service.
 */

public class RESTServer
{
    private String token = "AUTH_API_KEY";
    private String secret = "abcd123456";
    private String host = "explore-canada.us-east-1.elasticbeanstalk.com";
    //private String host = "localhost";
    private String port = "5000";

    public String getToken()
    {
        return token;
    }
    public String getSecret()
    {
        return secret;
    }
    public String getHost()
    {
        return host;
    }
    public String getPort()
    {
        return port;
    }
}
