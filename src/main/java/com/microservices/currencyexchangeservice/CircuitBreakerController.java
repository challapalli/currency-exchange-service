package com.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardCodeResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardCodeResponse")
    //@RateLimiter(name = "default")
    @Bulkhead(name = "sample-api")
    public String getSampleReq() {
        log.info("Sample API received...");
        ResponseEntity<String> forEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/dummy-api", String.class);
        return forEntity.getBody();
    }

    public String hardCodeResponse(Exception ex) {
        return "fallback-response";
    }
}
