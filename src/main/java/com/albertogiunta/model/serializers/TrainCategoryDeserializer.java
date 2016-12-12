package com.albertogiunta.model.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class TrainCategoryDeserializer extends StdDeserializer<String> {

    public TrainCategoryDeserializer() {
        this(null);
    }

    public TrainCategoryDeserializer(Class<String> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        String category = p.getText();

        switch (category) {
            case "Frecciarossa":
                category = "FR";
                break;
            case "ES*":
            case "Frecciabianca":
                category = "FB";
                break;
            case "Frecciaargento":
                category = "FA";
                break;
            case "Autobus":
                category = "BUS";
                break;
            case "Urb":
                category = "BUS";
                break;
        }

        return category;
    }
}

