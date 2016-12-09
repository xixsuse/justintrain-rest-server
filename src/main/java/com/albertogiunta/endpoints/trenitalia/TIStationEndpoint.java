package com.albertogiunta.endpoints.trenitalia;

import com.albertogiunta.constants.TI.TAPI;
import com.albertogiunta.endpoints.exceptions.ClientErrorHandler;
import com.albertogiunta.endpoints.exceptions.ResourceNotFoundException;
import com.albertogiunta.model.Station;
import com.albertogiunta.model.StationRepository;
import com.albertogiunta.model.Stations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class TIStationEndpoint {

    private static final RestTemplate REST_TEMPLATE;
    private static StationRepository repository;

    static {
        REST_TEMPLATE = new RestTemplate();
        REST_TEMPLATE.setErrorHandler(new ClientErrorHandler());
    }

    @Autowired
    public TIStationEndpoint(StationRepository rep) {
        TIStationEndpoint.repository = rep;
    }

    public static List<Station> getStation(String query) throws ResourceNotFoundException {
        Station[] stationArray = REST_TEMPLATE.getForObject(TAPI.DNS + TAPI.STATION_AUTOCOMPETE_JSON + query.replaceAll(" ", "%20"), Station[].class);
        return stationArray.length > 0 ? Arrays.asList(stationArray) : new LinkedList<>();
    }

    public static Stations getStationOffline(String query) throws ResourceNotFoundException {
        System.out.println(query);
        Stations s = repository.findByNameShort(query);
        System.out.println(" " + s == null + " " + s.toString());
        return s;
    }

    public static List<Station> getStation() {
        char[] alphabet = "abcdefghijklmnopqrstuvz".toCharArray();
        List<Station> completeStationList = new LinkedList<>();
        for (char letter : alphabet) {
            completeStationList.addAll(getStation(String.valueOf(letter)));
        }
        return completeStationList;
    }

}
