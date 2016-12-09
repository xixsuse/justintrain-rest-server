package com.albertogiunta.model.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CancelledStopsInfoDeserializer extends StdDeserializer<String> {

    public CancelledStopsInfoDeserializer() {
        this(null);
    }

    public CancelledStopsInfoDeserializer(Class<String> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (p.getText().equals("")) {
            return null;
        }
        return p.getText();
    }
}
