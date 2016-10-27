package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class LastSeenSerializer extends StdDeserializer<String> {

    public LastSeenSerializer() {
        this(null);
    }

    public LastSeenSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (p.getText().equals("--")) {
            return JVALUE.NAS;
        }
        return p.getText();
    }
}
