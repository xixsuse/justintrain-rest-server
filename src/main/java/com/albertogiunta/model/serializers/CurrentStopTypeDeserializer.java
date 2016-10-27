package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TVALUE;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CurrentStopTypeDeserializer extends StdDeserializer<Integer> {

    public CurrentStopTypeDeserializer() {
        this(null);
    }

    protected CurrentStopTypeDeserializer(Class<?> t) {
        super(t);
    }

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (p.getText()) {
            case TVALUE.FIRST:
                return JVALUE.FIRST;
            case TVALUE.MIDDLE:
                return JVALUE.MIDDLE;
            case TVALUE.LAST:
                return JVALUE.LAST;
            default:
                return JVALUE.NA;
        }
    }

    @Override
    public Integer getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return JVALUE.NA;
    }
}
