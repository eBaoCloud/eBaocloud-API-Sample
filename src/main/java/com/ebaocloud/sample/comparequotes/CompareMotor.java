package com.ebaocloud.sample.comparequotes;

import com.ebaocloud.sample.DemoConf;
import com.ebaocloud.sample.pub.AppContext;
import com.ebaocloud.sample.pub.Authentication;
import com.ebaocloud.sample.pub.Resp;
import groovy.json.JsonOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Guo Rui on 5/3/17.
 */
@Component
public class CompareMotor {
    @Autowired
    private Authentication authentication;
    @Autowired
    private DemoConf demoConf;

    private static final Logger logger = LoggerFactory.getLogger(CompareTravel.class);

    public void compare() throws Exception {

        Map<String, Object> result = new HashMap<>();

        //do login, if has logged in before, just reuse the token returned from server last time, this is to improve performance
        authentication.loginIfNecessary();

        long start = System.currentTimeMillis();
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap();
        params.put("policySource", 2);
        params.put("vehicleKey", "TOYO15BI");
        params.put("preferredPlanType", Collections.EMPTY_LIST);
        params.put("deductible", 0);
        params.put("regYear", 2015);
        params.put("usage", "COMMERCIAL");
        String requestJson = JsonOutput.toJson(params);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(MediaType.parseMediaTypes("application/json, text/javascript, */*; q=0.01"));
        headers.setConnection("keep-alive");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + AppContext.getInstance().getToken());

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        CompareMotorResp resp = restTemplate.postForObject(demoConf.getCompareMotorQuotesUrl(), entity, CompareMotorResp.class);

        result.put("success", resp.isSuccess());
        result.put("message", resp.getMessage());
        result.put("costInMillis", (System.currentTimeMillis() - start));
        if (resp.isSuccess()) {
            //if the response from server is successful, consumer systems can proceed to do necessary logic
            //here just print out the plan with premium on screen
            List<AggregatorPlan> plans = resp.getData();
            if (plans != null && plans.size() > 0) {
                //for each plan, if it's indicated underwriting needed "uwNeeded=true", that means the premium is subject to UW and this premium could be changed by UWer
                //consumer system can show premium with warning to customer or just don't show the plan on screen
                List<String> sPlans = new ArrayList<>();
                for (int i = 0; i < plans.size(); i++) {
                    AggregatorPlan plan = plans.get(i);
                    String s = String.format("insurer=%s / plan=%s / product=%s / premium=%s / uwNeeded=%s",
                            plan.getInsurerTenantcode(), plan.getPlanCode(), plan.getProductCode(),
                            plan.getPremium().getApp(),
                            plan.getPremium().isUwNeeded());
                    sPlans.add(s);
                }
                result.put("eligiblePlans", sPlans);
            } else {
                result.put("eligiblePlans", "no eligible plan found");
            }
        }
        logger.info("Compare Motor: completed");
        logger.info(JsonOutput.prettyPrint(JsonOutput.toJson(result)));
    }

    static class CompareMotorResp extends Resp<List<AggregatorPlan>> {
    }

}
