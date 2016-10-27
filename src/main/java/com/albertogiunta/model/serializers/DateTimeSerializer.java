package com.albertogiunta.model.serializers;

import com.albertogiunta.constants.JIT.JVALUE;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.DateTime;

import java.io.IOException;

public class DateTimeSerializer extends StdSerializer<DateTime> {

    public DateTimeSerializer() {
        this(null);
    }

    public DateTimeSerializer(Class<DateTime> t) {
        super(t);
    }

    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeString(value.toString(JVALUE.yyyyMMddTHHmmssZ));
        }
    }
}
