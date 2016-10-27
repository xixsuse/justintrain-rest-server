package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class TrainStatusCodeDeserializer extends StdDeserializer<Integer> {

    public TrainStatusCodeDeserializer() {
        this(null);
    }

    public TrainStatusCodeDeserializer(Class<Integer> t) {
        super(t);
    }

    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        int statusCode;

        if (p.getText() == null) {
            statusCode = JVALUE.NA;
        } else {
            switch (p.getText()) {
                case "PG":
                    statusCode = JVALUE.REGULAR;
                    break;
                case "ST":
                    statusCode = JVALUE.CANCELLED;
                    break;
                case "PP":
                case "SI":
                case "SF":
                    statusCode = JVALUE.PARTIALLY_CANCELLED;
                    break;
                case "DV":
                    statusCode = JVALUE.DEVIATED;
                    break;
                default:
                    statusCode = JVALUE.NA;
            }
        }

        return statusCode;
    }
}
