package com.blinklink.servicelearning;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.money.MonetaryAmount;
import java.io.IOException;

public class MonetaryAmountSerializer extends JsonSerializer<MonetaryAmount> {

    @Override
    public void serialize(MonetaryAmount monetaryAmount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("price", monetaryAmount.getNumber().toString());
        jsonGenerator.writeStringField("currencyCode", monetaryAmount.getCurrency().getCurrencyCode());
        jsonGenerator.writeEndObject();
    }
}
