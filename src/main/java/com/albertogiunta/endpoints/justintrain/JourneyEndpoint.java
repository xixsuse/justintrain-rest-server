package com.albertogiunta.endpoints.justintrain;

import com.albertogiunta.constants.JIT.JAPI;
import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.model.journey.Journey;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.albertogiunta.endpoints.trenitalia.JourneyEndpoint.getJourney;

@RestController
@RequestMapping(value = JAPI.VERSION)
public class JourneyEndpoint {

    private static final Logger log = LoggerFactory.getLogger(JourneyEndpoint.class);

    /**
     * Given departure and arrival data, returns a list of possible rides one could take to go from station A to station B.
     * The method should be used when a request regarding a favourite journey is made.
     * It uses current time because it's supposed to answer the question:
     * what are the next plausible rides I could take to go from point A to point B?
     * It also returns the ride right before the current time (in case it's late and therefore it could be good to catch)
     *
     * @param departureId  Must be the id of the station one wants to start the journey from. (i.e. 5066)
     * @param arrivalId    Must be the id of the station one wants to end the journey in. ( i.e. 5066)
     * @param isPreemptive Must be true of false, depending on if we wanna have also a train before or not.
     * @return a list of Solutions.
     */
    @RequestMapping(value = JAPI.INSTANT_JOURNEY,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Journey instantJourney(@PathVariable(value = JAPI.DEP_STAT_ID) String departureId,
                                  @PathVariable(value = JAPI.ARR_STAT_ID) String arrivalId,
                                  @RequestParam(value = JAPI.IS_PREEMPTIVE, required = false, defaultValue = "false") Boolean isPreemptive) {
        return getJourney(departureId, arrivalId, null, null, true, isPreemptive, true);
    }

    /**
     * Given departure and arrival data, returns a list of possible rides one could take to go from station A to station B.
     * It will search train that pass by the defined departure station after the given time, and it could be preemptive or not
     * (means that it will also give the train before the "good ones" or not) depending on the value of isPreemptive.
     *
     * @param departureId  Must be the id of the station one wants to start the journey from.
     * @param arrivalId    Must be the id of the station one wants to end the journey in.
     * @param startAt      Must be the time we want to start the search from. Format Unixtime (seconds)
     * @param isPreemptive Must be true of false, depending on if we wanna have also a train before or not.
     * @return a list of Solutions.
     */
    @RequestMapping(value = JAPI.JOURNEY_AFTER_TIME,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Journey journeyAfterTime(@PathVariable(value = JAPI.DEP_STAT_ID) String departureId,
                                    @PathVariable(value = JAPI.ARR_STAT_ID) String arrivalId,
                                    @RequestParam(value = JAPI.STARTING_FROM) @DateTimeFormat(pattern = JVALUE.yyyyMMddTHHmmZ) Date startAt,
                                    @RequestParam(value = JAPI.IS_PREEMPTIVE, required = false, defaultValue = "false") Boolean isPreemptive,
                                    @RequestParam(value = JAPI.INCLUDE_DELAYS, required = false, defaultValue = "false") Boolean includeDelay,
                                    @RequestParam(value = JAPI.INCLUDE_TTBT, required = false, defaultValue = "false") Boolean includeTrainToBeTaken) {
        DateTime d = new DateTime(startAt);
        if (d.isAfter(DateTime.now().withHourOfDay(23).withMinuteOfHour(59)) ||
                d.isBefore(DateTime.now().withHourOfDay(0).withMinuteOfHour(0))) {
            includeDelay = false;
            log.warn("Searching for a day that is not today. I'll give you delays, but not today.");
        }
        return getJourney(departureId, arrivalId, new DateTime(startAt), null, includeDelay, isPreemptive, includeTrainToBeTaken);
    }

    /**
     * Given departure and arrival data, returns a list of possible rides one could take to go from station A to station B.
     * It will search trains that pass by the defined departure station before the given time.
     *
     * @param departureId Must be the id of the station one wants to start the journey from.
     * @param arrivalId   Must be the id of the station one wants to end the journey in.
     * @param endAt       It will return a list of rides starting 5 hours before this time til this time.
     * @return a list of Solutions.
     */
    @RequestMapping(value = JAPI.JOURNEY_BEFORE_TIME,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Journey journeyBeforeTime(@PathVariable(value = JAPI.DEP_STAT_ID) String departureId,
                                     @PathVariable(value = JAPI.ARR_STAT_ID) String arrivalId,
                                     @RequestParam(value = JAPI.END_AT) @DateTimeFormat(pattern = JVALUE.yyyyMMddTHHmmZ) Date endAt,
                                     @RequestParam(value = JAPI.INCLUDE_DELAYS, required = false, defaultValue = "false") Boolean includeDelay) {
        return getJourney(departureId, arrivalId, null, new DateTime(endAt), includeDelay, false, false);
    }

}
