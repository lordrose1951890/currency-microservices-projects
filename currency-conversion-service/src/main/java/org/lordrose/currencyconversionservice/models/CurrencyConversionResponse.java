package org.lordrose.currencyconversionservice.models;

import java.math.BigDecimal;

public record CurrencyConversionResponse(Long id,
                                         String from, String to,
                                         BigDecimal quantity,
                                         BigDecimal conversionRate,
                                         BigDecimal calculatedAmount,
                                         String environment) {
}
