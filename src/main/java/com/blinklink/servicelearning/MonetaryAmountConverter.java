package com.blinklink.servicelearning;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, Amount> {

    @Override
    public Amount convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
        return Amount.of(monetaryAmount.getNumber().numberValue(BigDecimal.class).setScale(2, RoundingMode.CEILING),
                monetaryAmount.getCurrency().getCurrencyCode());
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(Amount amount) {
        return Money.of(amount.getAmount(), amount.getCurrencyCode());
    }

}

