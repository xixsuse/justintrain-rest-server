package com.albertogiunta.endpoints.justintrain;

import com.albertogiunta.constants.JIT.JAPI;
import com.albertogiunta.endpoints.exceptions.ResourceNotFoundException;
import com.albertogiunta.model.Station;
import com.albertogiunta.model.train.Train;
import com.albertogiunta.model.train.TrainHeaderOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.albertogiunta.endpoints.trenitalia.TrainEndpoint.getStationData;
import static com.albertogiunta.endpoints.trenitalia.TrainEndpoint.getTrain;
import static com.albertogiunta.endpoints.trenitalia.TrainEndpoint.getTrainHeaderOnly;

@RestController
public class TrainEndpoint {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * It will infer the departureStationId of the given train in order to actually make the request to get info about the train.
     * NB a specific train number (i.e. 22) can be associated to more than a departureStationId. Means that there are two trains out there,
     * called the same but leaving from different stations.
     * Plot twist: their ride, as far as I noticed, is actually the freaking same, making this whole thing just a hole in this worlds logic.
     * But who are we to decide who's smart and who's not? Maybe those guys at Trenitalia had their good reasons to do this insanity. Maybe.
     *
     * @param trainId Must be the id of the train we want to know more info about
     * @return a list of Pair with the following structure: [stationName, stationCode]
     */
    @RequestMapping(value = JAPI.TRAIN_AUTOCOMPLETE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Station trainAutocomplete(@PathVariable(value = JAPI.TRAIN_ID) String trainId) {
        log.info("Asked for full train details with ID {}", trainId);
        try {
            return getStationData(trainId);
        } catch (ResourceNotFoundException e) {
            log.error("Departure station not found. ID {}", trainId);
            return null;
        }
    }

    /**
     * Method called by every other method that need information regarding a train (in this specific class).
     *
     * @param trainId            Must be the id of the train we want to know more info about
     * @param departureStationId Could be the id of the station one wants to start the journey from
     *                           It will be inferred here if not present (better if present tho)
     * @return a train object with all the information that make actually sense (not like the original API)
     */
    @RequestMapping(value = JAPI.TRAIN,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Train getFullTrainDetails(@PathVariable(value = JAPI.TRAIN_ID) String trainId,
                                     @PathVariable(value = JAPI.DEP_STAT_ID) String departureStationId) {
        log.info("Asked for full train details with STATION {} and ID {}", departureStationId, trainId);
        try {
            return getTrain(departureStationId, trainId);
        } catch (ResourceNotFoundException e) {
            log.error("Train not found. STATION {} ID {}", departureStationId, trainId);
            return null;
        }
    }

    /**
     * Method called by every other method that need information regarding a train (in this specific class) when departureStationId is not given.
     *
     * @param trainId Must be the id of the train we want to know more info about
     * @return a train object with all the information that make actually sense (not like the original API)
     */
    @RequestMapping(value = JAPI.TRAIN_NO_STAT,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Train getFullTrainDetailsWithoutStation(@PathVariable(value = JAPI.TRAIN_ID) String trainId) {
        log.info("Asked for full train details with ID {}", trainId);
        try {
            return getTrain(trainAutocomplete(trainId).getStationLongCode(), trainId);
        } catch (ResourceNotFoundException e) {
            log.error("Train not found. ID {}", trainId);
            return null;
        }
    }

    /**
     * Simple method that could be useful for a feature that only requests for the delay of a specific train
     * (which is already present in the list of rides i.e. but doesn't have a delay set yet)
     *
     * @param trainId            Must be the id of the train we want to know more info about
     * @param departureStationId Could be the id of the station one wants to start the journey from
     *                           It will be inferred in the getFullTrainDetails() method if not present (better if present tho)
     * @return a simple getTrainDelay object that contains the timeDifference of the requested train
     */
//    @RequestMapping(value = JAPI.TRAIN_DELAY,
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getTrainDelay(@PathVariable(value = JAPI.TRAIN_ID) String trainId,
//                                @PathVariable(value = JAPI.DEP_STAT_ID) String departureStationId) {
//        return null;
//    }

    /**
     * Simple method that could be useful for a feature that only requests for the delay of a specific train
     * (which is already present in the list of rides i.e. but doesn't have a delay set yet) when departureStationId is not given.
     *
     * @param trainId Must be the id of the train we want to know more info about
     * @return a simple getTrainDelay object that contains the timeDifference of the requested train
     */
//    @RequestMapping(value = JAPI.TRAIN_DELAY_NO_STAT,
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public String getTrainDelayWithoutStation(@PathVariable(value = JAPI.TRAIN_ID) String trainId) {
//        return null;
//    }

    /**
     * Returns only the principal information regarding a specific train. Useful for the notification feature or a "following train" feature.
     *
     * @param trainId            Must be the id of the train we want to know more info about
     * @param departureStationId Could be the id of the station one wants to start the journey from
     *                           It will be inferred in the getFullTrainDetails() method if not present (better if present tho)
     * @return a trainHeader object with the most meaningful information regarding a specific train
     */
    @RequestMapping(value = JAPI.TRAIN_HEADER,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TrainHeaderOnly getTrainHeader(@PathVariable(value = JAPI.TRAIN_ID) String trainId,
                                          @PathVariable(value = JAPI.DEP_STAT_ID) String departureStationId) {
        log.info("Asked for train header only with STATION {} and ID {}", departureStationId, trainId);
        try {
            return getTrainHeaderOnly(departureStationId, trainId);
        } catch (ResourceNotFoundException e) {
            log.error("Train not found. STATION {} ID {}", departureStationId, trainId);
            return null;
        }
    }


    /**
     * Returns only the principal information regarding a specific train. Useful for the notification feature or a "following train" feature.
     * (when departureStationId is not given)
     *
     * @param trainId Must be the id of the train we want to know more info about
     * @return a trainHeader object with the most meaningful information regarding a specific train
     */
    @RequestMapping(value = JAPI.TRAIN_HEADER_NO_STAT,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public TrainHeaderOnly getTrainHeaderWithoutStation(@PathVariable(value = JAPI.TRAIN_ID) String trainId) {
        log.info("Asked for train header only with ID {}", trainId);
        try {
            return getTrainHeaderOnly(trainId);
        } catch (ResourceNotFoundException e) {
            log.error("Train not found. ID {}", trainId);
            return null;
        }
    }

}
