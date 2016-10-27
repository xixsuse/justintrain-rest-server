package com.albertogiunta.model.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.time.DateTime;

import java.io.IOException;

public class MillisecondsDesererializer extends StdDeserializer<DateTime> {

    public MillisecondsDesererializer() {
        this(null);
    }

    public MillisecondsDesererializer(Class<DateTime> t) {
        super(t);
    }

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return new DateTime(p.getLongValue());
    }

    @Override
    public DateTime getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return null;
    }
}
