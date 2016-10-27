package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class TrainOrientationDeserializer extends StdDeserializer<Integer> {

    public TrainOrientationDeserializer() {
        this(null);
    }

    public TrainOrientationDeserializer(Class<String> t) {
        super(t);
    }

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        int orientationCode;

        if (p.getText() == null || p.getText().equals("")) {
            orientationCode = JVALUE.NA;
        } else {
            switch (p.getText()) {
                case "A":
                    orientationCode = JVALUE.TAIL;
                    break;
                case "B":
                    orientationCode = JVALUE.HEAD;
                    break;
                default:
                    orientationCode = JVALUE.NA;
            }
        }

        return orientationCode;
    }

    @Override
    public Integer getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return JVALUE.NA;
    }
}
