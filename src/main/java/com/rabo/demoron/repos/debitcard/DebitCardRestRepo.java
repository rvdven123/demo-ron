package com.rabo.demoron.repos.debitcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class DebitCardRestRepo implements DebitCardRepo {

    private final RestTemplate restTemplate;

    @Value("${url_authority:http://localhost:8080/debit-cards}")
    private String baseResourcePath;

    @Autowired
    public DebitCardRestRepo(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public DebitCard findById(String id) {

        assert id != null && id.length() != 0;//TODO use logger.

        final String uri = baseResourcePath + "/{id}";//TODO use string builder.

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        //TODO Cache exceptions and based on type of exception, retry or not.
        return restTemplate.getForObject(uri, DebitCard.class, params);
    }
}
