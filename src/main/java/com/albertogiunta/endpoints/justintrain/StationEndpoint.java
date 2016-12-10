package com.albertogiunta.endpoints.justintrain;

import com.albertogiunta.constants.JIT.JAPI;
import com.albertogiunta.model.Station;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.albertogiunta.endpoints.trenitalia.TIStationEndpoint.getStation;

@RestController
@RequestMapping(value = JAPI.VERSION)
public class StationEndpoint {

    /**
     * @param stationName partial or complete name of a station.
     * @return a list of plausible stations with their code.
     */
    @RequestMapping(value = JAPI.STATION_AUTOCOMPLETE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Station> stationAutocomplete(@RequestParam(value = JAPI.STATION_NAME) String stationName) {
        return getStation(stationName);
    }

    /**
     * @return the complete list of existing Stations in JSON format
     */
    @RequestMapping(value = JAPI.STATION_COMPLETE_LIST,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Station> stationAutocomplete() {
        return getStation();
    }

}
