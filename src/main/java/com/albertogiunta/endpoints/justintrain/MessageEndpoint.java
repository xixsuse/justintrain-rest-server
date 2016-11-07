package com.albertogiunta.endpoints.justintrain;

import com.albertogiunta.constants.JIT.JAPI;
import com.albertogiunta.endpoints.exceptions.ClientErrorHandler;
import com.albertogiunta.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

@RestController
public class MessageEndpoint {
    
    private static final RestTemplate REST_TEMPLATE;
    
    static {
        REST_TEMPLATE = new RestTemplate();
        REST_TEMPLATE.setErrorHandler(new ClientErrorHandler());
    }
    
    @RequestMapping(value = JAPI.MESSAGES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message[] getAllMessages() throws URISyntaxException, IOException {
        Message[] messages = new Message[0];
        try {
            messages = REST_TEMPLATE.getForObject("http://localhost:8083/messages.json", Message[].class);
        } catch (RestClientException e) {
            ObjectMapper mapper = new ObjectMapper();
            messages = mapper.readValue(new File(this.getClass().getResource("/messages.json").toURI()), Message[].class);
        } finally {
            System.out.println("getAllMessages: " + Arrays.toString(messages));
            //messages = mapper.readValue(new File(this.getClass().getResource("/messages.json").toURI()), new TypeReference<List<Message>>() {});
            return messages;
        }
    }
}
