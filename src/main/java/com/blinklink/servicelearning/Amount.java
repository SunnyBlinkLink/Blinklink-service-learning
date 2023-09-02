package com.blinklink.servicelearning;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Embeddable
@EqualsAndHashCode
public class Amount implements Serializable {
    private BigDecimal amount;

    private String currencyCode;

    public Amount() {
        this.amount = null;
        this.currencyCode = null;
    }

    @JsonCreator
    public Amount(BigDecimal amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public static Amount of(BigDecimal amount, String currencyCode) {
        return new Amount(amount, currencyCode);
    }
}

