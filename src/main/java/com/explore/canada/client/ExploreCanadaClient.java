package com.explore.canada.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.explore"})
public class ExploreCanadaClient {

    public static void main(String[] args) {
        SpringApplication.run(ExploreCanadaClient.class, args);
    }

}
