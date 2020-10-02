package com.rabo.demoron.repos.poa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class PowerOfAttorneyRestRepo implements PowerOfAttorneyRepo {

    private final RestTemplate restTemplate;

    @Value("${url_authority:http://localhost:8080/power-of-attorneys}") //TODO make url authority configurable in application properties.
    private String baseResourcePath;

    public PowerOfAttorneyRestRepo(RestTemplate restTemplate){
        this.restTemplate =  restTemplate;
    }

    public List<PowerOfAttorney> findAll() {

        final String uri = "http://localhost:8080/power-of-attorneys";

        ResponseEntity<PowerOfAttorney[]> response =
                restTemplate.getForEntity(
                        baseResourcePath,
                        PowerOfAttorney[].class);
        PowerOfAttorney[] powerOfAttorneyIds = response.getBody();

        List<PowerOfAttorney> returnList = new ArrayList();
        Arrays.stream(powerOfAttorneyIds).forEach(powerOfAttorney -> {
            returnList.add(findById(powerOfAttorney.getId()));
        });

        return returnList;
    }

    public PowerOfAttorney findById(String id) {

        //TODO check id not empty.

        final String uri = baseResourcePath + "/{id}";//TODO use string builder.
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);

        //TODO Cache exceptions and based on type of exception, retry or not.
        return restTemplate.getForObject(uri, PowerOfAttorney.class, params);

    }

}
