package com.adapt.rak.main.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Sam Raju on 02/11/2021
 */
@Component
@ConfigurationProperties("qmatic")
public class AppProperties {
    private String baseUrl;
    private String username;
    private String password;


    public AppProperties() {
    }

    public AppProperties(String baseUrl, String username, String password) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public String getBranchUrl(){
        return getBaseUrl()+"/rest/servicepoint/branches";
    }

   public String getVariables() {
	   return getBaseUrl()+"/rest/entrypoint/variables";
   }
}
