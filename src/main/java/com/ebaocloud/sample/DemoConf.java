package com.ebaocloud.sample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ggg on 5/3/17.
 */
@Component
@ConfigurationProperties(prefix = "ebaocloud")
public class DemoConf {
    private String loginUrl;
    private String compareTravelQuotesUrl;
    private String compareMotorQuotesUrl;
    private String listMakesUrl;
    private String listModelsUrl;

    private String username;
    private String password;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getCompareTravelQuotesUrl() {
        return compareTravelQuotesUrl;
    }

    public void setCompareTravelQuotesUrl(String compareTravelQuotesUrl) {
        this.compareTravelQuotesUrl = compareTravelQuotesUrl;
    }

    public String getCompareMotorQuotesUrl() {
        return compareMotorQuotesUrl;
    }

    public void setCompareMotorQuotesUrl(String compareMotorQuotesUrl) {
        this.compareMotorQuotesUrl = compareMotorQuotesUrl;
    }

    public String getListMakesUrl() {
        return listMakesUrl;
    }

    public void setListMakesUrl(String listMakesUrl) {
        this.listMakesUrl = listMakesUrl;
    }

    public String getListModelsUrl() {
        return listModelsUrl;
    }

    public void setListModelsUrl(String listModelsUrl) {
        this.listModelsUrl = listModelsUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
