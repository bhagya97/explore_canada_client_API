package com.explore.canada.configuration;

/**
 * This bean is used to hold the user name, password and host which we need to make the
 * call to the REST service.
 */
public class RESTServer
{
    private String user;
    private String password;
    private String host;
    private String port;


    public RESTServer(String user, String password, String host, String port)
    {
        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
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
