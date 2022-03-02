package org.lordrose.currencyconversionservice.controllers;

import org.lordrose.currencyconversionservice.models.CurrencyConversionResponse;
import org.lordrose.currencyconversionservice.proxies.CurrencyExchangeProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {

    private final CurrencyExchangeProxy exchangeProxy;

    public CurrencyConversionController(CurrencyExchangeProxy exchangeProxy) {
        this.exchangeProxy = exchangeProxy;
    }

    @GetMapping("/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse calculateCurrencyConversion(@PathVariable String from,
                                                                  @PathVariable String to,
                                                                  @PathVariable BigDecimal quantity) {
        String url = "http://localhost:8000/currency-exchange/{from}/to/{to}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionResponse> responseEntity = new RestTemplate()
                .getForEntity(url, CurrencyConversionResponse.class, uriVariables);

        CurrencyConversionResponse response = responseEntity.getBody();

        if (response == null) {
            throw new RuntimeException("Invalid response call from currency exchange service");
        }

        return new CurrencyConversionResponse(response.id(), from, to, quantity,
                response.conversionRate(), quantity.multiply(response.conversionRate()),
                response.environment() + " rest template");
    }

    @GetMapping("/feign/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse calculateCurrencyConversionFeign(@PathVariable String from,
                                                                       @PathVariable String to,
                                                                       @PathVariable BigDecimal quantity) {
        CurrencyConversionResponse response = exchangeProxy.retrieveExchangeRate(from, to);

        return new CurrencyConversionResponse(response.id(), from, to, quantity,
                response.conversionRate(), quantity.multiply(response.conversionRate()),
                response.environment() + " feign");
    }
}
