package org.lordrose.currencyexchangeservice.controllers;

import org.lordrose.currencyexchangeservice.entities.CurrencyExchange;
import org.lordrose.currencyexchangeservice.models.CurrencyExchangeRateResponse;
import org.lordrose.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CurrencyExchangeRepository exchangeRepository;
    private final Environment environment;

    public CurrencyExchangeController(CurrencyExchangeRepository exchangeRepository, Environment environment) {
        this.exchangeRepository = exchangeRepository;
        this.environment = environment;
    }

    @GetMapping("/{from}/to/{to}")
    public CurrencyExchangeRateResponse retrieveExchangeRate(@PathVariable String from,
                                                             @PathVariable String to) {
        log.info("retrieveExchangeRate called with value from {} to {}", from, to);

        String currentLocalPort = environment.getProperty("local.server.port");

        String message = "Unable to find exchange rate value from %s to %s";
        CurrencyExchange exchange = exchangeRepository.findByFromAndTo(from.toUpperCase(), to.toUpperCase())
                .orElseThrow(() -> new RuntimeException(String.format(message, from, to)));

        return new CurrencyExchangeRateResponse(
                exchange.getId(),
                exchange.getFrom(),
                exchange.getTo(),
                exchange.getConversionRate(),
                currentLocalPort);
    }
}
