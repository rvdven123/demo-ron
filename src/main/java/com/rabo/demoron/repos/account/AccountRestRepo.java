package com.rabo.demoron.repos.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class AccountRestRepo implements AccountRepo {

    private final RestTemplate restTemplate;

    @Value("${url_authority:http://localhost:8080/accounts}")
    private String baseResourcePath;

    @Autowired
    public AccountRestRepo(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Account findById(String id) {

        //TODO check id not empty
        final String uri = baseResourcePath + "/{id}";//TODO use string builder.

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        //TODO Cache exceptions and based on type of exception, retry or not.
        return restTemplate.getForObject(uri, Account.class, params);

    }
}
