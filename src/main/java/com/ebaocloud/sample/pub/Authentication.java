package com.ebaocloud.sample.pub;

import com.ebaocloud.sample.DemoConf;
import com.ebaocloud.sample.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * Created by Guo Rui on 5/3/17.
 */
@Component
public class Authentication {

    private static final Logger logger = LoggerFactory.getLogger(Authentication.class);


    @Autowired
    private DemoConf demoConf;

    public void loginIfNecessary() throws Exception {
        String token = AppContext.getInstance().getToken();
        if (token != null && !token.trim().equals("")) {
            return;
        }

        RestTemplate restTemplate = new RestTemplate();

        String requestJson = "{}";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(MediaType.parseMediaTypes("application/json, text/javascript, */*; q=0.01"));
        headers.setConnection("keep-alive");
        headers.setContentType(MediaType.APPLICATION_JSON);
        String base64 = Base64.getEncoder()
                .encodeToString((demoConf.getUsername() + ":" + demoConf.getPassword())
                        .getBytes("utf-8"));
        headers.set("Authorization", "Basic " + base64);
        headers.set("language", "en_US");

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        LoginResp resp = restTemplate.postForObject(demoConf.getLoginUrl(), entity, LoginResp.class);
        if (!resp.isSuccess()) {
            throw new RuntimeException("Login failed with error: " + resp.getErrorCode() + "-" + resp.getMessage());
        }
        token = Base64.getEncoder()
                .encodeToString((demoConf.getUsername() + ":" + resp.data.user.password)
                        .getBytes("utf-8"));
        AppContext.getInstance().setToken(token);

        logger.info("logged in");
    }

    static class LoginResp extends Resp<LoginRespBean> {
    }

    static class LoginRespBean {
        private LoginRespUserBean user;

        public LoginRespUserBean getUser() {
            return user;
        }

        public void setUser(LoginRespUserBean user) {
            this.user = user;
        }
    }

    static class LoginRespUserBean {
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
