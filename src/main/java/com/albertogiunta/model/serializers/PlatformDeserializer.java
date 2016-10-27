package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PlatformDeserializer extends StdDeserializer<String> {

    public PlatformDeserializer() {
        this(null);
    }

    public PlatformDeserializer(Class<String> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return p.getText().replaceAll("\\s+", "");
    }

    @Override
    public String getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return JVALUE.NAS;
    }
}
