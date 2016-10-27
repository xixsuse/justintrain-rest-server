package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TVALUE;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CurrentAndNextStopStatusCodeDeserializer extends StdDeserializer<Integer> {

    public CurrentAndNextStopStatusCodeDeserializer() {
        this(null);
    }

    public CurrentAndNextStopStatusCodeDeserializer(Class<Integer> t) {
        super(t);
    }

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (p.getIntValue()) {
            case TVALUE.CVNV:
                return JVALUE.CVNV;
            case TVALUE.CVNU:
                return JVALUE.CVNU;
            case TVALUE.CUNU:
                return JVALUE.CUNU;
            case TVALUE.CVND:
                return JVALUE.CVND;
            case TVALUE.CDND:
                return JVALUE.CDND;
            case TVALUE.CDNE:
                return JVALUE.CDNE;
            default:
                return JVALUE.NA;
        }
    }

}
