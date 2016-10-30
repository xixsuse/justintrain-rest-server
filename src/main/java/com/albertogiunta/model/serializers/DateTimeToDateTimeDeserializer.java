package com.albertogiunta.model.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.joda.time.DateTime;

import java.io.IOException;

public class DateTimeToDateTimeDeserializer extends StdDeserializer<DateTime> {

    public DateTimeToDateTimeDeserializer() {
        this(null);
    }

    public DateTimeToDateTimeDeserializer(Class<DateTime> t) {
        super(t);
    }

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return DateTime.parse(p.getText());
    }

    @Override
    public DateTime getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return null;
    }

}
