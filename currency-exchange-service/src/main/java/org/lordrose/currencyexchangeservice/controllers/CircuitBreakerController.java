package org.lordrose.currencyexchangeservice.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class CircuitBreakerController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/retry")
    @Retry(name = "test-api", fallbackMethod = "hardCodedResponse")
    public String retry() {
        log.info("Test api call received!");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/dummy", String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "test-api", fallbackMethod = "hardCodedResponse")
    public String circuitBreak() {
        log.info("Test api call received!");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8080/dummy", String.class);
        return responseEntity.getBody();
    }

    // Others useful to limit api call
    // @RateLimiter(name = "default")
    // @Bulkhead(name = "default")

    public String hardCodedResponse(Exception e) {
        return "fallback response";
    }
}
