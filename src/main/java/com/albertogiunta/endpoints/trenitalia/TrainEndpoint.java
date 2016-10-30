package com.albertogiunta.endpoints.trenitalia;

import com.albertogiunta.constants.TI.TAPI;
import com.albertogiunta.endpoints.exceptions.ResourceNotFoundException;
import com.albertogiunta.model.Station;
import com.albertogiunta.model.train.Train;
import com.albertogiunta.model.train.TrainHeaderOnly;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class TrainEndpoint {

    private static final RestTemplate REST_TEMPLATE;
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        REST_TEMPLATE = new RestTemplate();
//        REST_TEMPLATE.setErrorHandler(new ClientErrorHandler());
        OBJECT_MAPPER = new ObjectMapper();
    }

    public static Train getTrain(String departureStationId, String trainId) throws ResourceNotFoundException {
        try {
            return REST_TEMPLATE.getForObject(TAPI.DNS + TAPI.TRAIN + departureStationId + "/" + trainId, Train.class);
        } catch (RestClientException e) {
            return null;
        }
    }

    public static Train getTrain(String trainId) throws ResourceNotFoundException {
        return getTrain(getStationData(trainId).getStationLongCode(), trainId);
    }

    public static TrainHeaderOnly getTrainHeaderOnly(String departureStationId, String trainId) throws ResourceNotFoundException {
        TrainHeaderOnly train = new TrainHeaderOnly();
        try {
            OBJECT_MAPPER.readerForUpdating(train).readValue(new Gson().toJson(getTrain(departureStationId, trainId)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return train;
    }

    public static TrainHeaderOnly getTrainHeaderOnly(String trainId) throws ResourceNotFoundException {
        return getTrainHeaderOnly(getStationData(trainId).getStationLongCode(), trainId);
    }

    public static Station getStationData(String trainId) throws ResourceNotFoundException {
        return new Station(trainId, REST_TEMPLATE.getForObject(TAPI.DNS + TAPI.TRAIN_AUTOCOMPLETE + trainId, String.class));
    }
}
