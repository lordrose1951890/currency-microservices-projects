package org.lordrose.currencyexchangeservice.models;

import java.math.BigDecimal;

public record CurrencyExchangeRateResponse(Long id,
                                           String from, String to,
                                           BigDecimal conversionRate,
                                           String environment) {
}
