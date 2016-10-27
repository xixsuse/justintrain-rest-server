package com.albertogiunta.endpoints.exceptions;

import com.albertogiunta.endpoints.exceptions.ResourceNotFoundException;
import com.albertogiunta.endpoints.exceptions.UnexpectedHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ClientErrorHandler implements ResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ResourceNotFoundException();
        }

        // handle other possibilities, then use the catch all...

        throw new UnexpectedHttpException(response.getStatusCode());
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
                || (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }
}