package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TVALUE;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CurrentStopStatusCodeDeserializer extends StdDeserializer<Integer> {

    public CurrentStopStatusCodeDeserializer() {
        this(null);
    }

    public CurrentStopStatusCodeDeserializer(Class<Integer> t) {
        super(t);
    }

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (p.getIntValue()) {
            case TVALUE.NOT_VISITED:
                return JVALUE.NOT_VISITED;
            case TVALUE.VISITED:
                return JVALUE.VISITED;
            case TVALUE.EXTRAORDINARY:
                return JVALUE.EXTRAORDINARY;
            case TVALUE.DELETED:
                return JVALUE.DELETED;
            default:
                return JVALUE.NA;
        }
    }

}
