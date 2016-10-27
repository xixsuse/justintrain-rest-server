package com.albertogiunta.endpoints.trenitalia;

import com.albertogiunta.constants.TI.TAPI;
import com.albertogiunta.endpoints.exceptions.ClientErrorHandler;
import com.albertogiunta.endpoints.exceptions.ResourceNotFoundException;
import com.albertogiunta.model.Station;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class StationEndpoint {

    private static final RestTemplate REST_TEMPLATE;

    static {
        REST_TEMPLATE = new RestTemplate();
        REST_TEMPLATE.setErrorHandler(new ClientErrorHandler());
    }

    public static List<Station> getStation(String query) throws ResourceNotFoundException {
        Station[] stationArray = REST_TEMPLATE.getForObject(TAPI.DNS + TAPI.STATION_AUTOCOMPETE_JSON + query, Station[].class);
        return stationArray.length > 0 ? Arrays.asList(stationArray) : new LinkedList<>();
    }

    public static List<Station> getStation() {
        char[] alphabet = "abcdefghilmnopqrstuvz".toCharArray();
        List<Station> completeStationList = new LinkedList<>();
        for (char letter : alphabet) {
            completeStationList.addAll(getStation(String.valueOf(letter)));
        }
        return completeStationList;
    }

}
