package com.albertogiunta.model;

import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.constants.TI.TFIELD;
import com.fasterxml.jackson.annotation.*;

@JsonRootName("news")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class News {

    private String message;

    @JsonGetter(JFIELD.MESSAGE)
    public String getMessage() {
        return message;
    }

    @JsonSetter(TFIELD.MESSAGE)
    public void setMessage(String message) {
        this.message = message.replaceAll("(\\r|\\n)", "");
    }
}