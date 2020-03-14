package com.explore.canada.bean;

public class ErrorHolder
{

    public ErrorHolder(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString()
    {
        return "ErrorHolder{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String errorMessage;
}
