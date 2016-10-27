package com.albertogiunta.endpoints.trenitalia;

import com.albertogiunta.constants.TI.TAPI;
import com.albertogiunta.endpoints.exceptions.ClientErrorHandler;
import com.albertogiunta.endpoints.exceptions.ResourceNotFoundException;
import com.albertogiunta.model.journey.Change;
import com.albertogiunta.model.journey.Journey;
import com.albertogiunta.model.journey.Solution;
import com.albertogiunta.model.train.Train;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static com.albertogiunta.constants.JIT.JVALUE.*;

public class JourneyEndpoint {

    private static final Logger log = LoggerFactory.getLogger(JourneyEndpoint.class);
    private static final RestTemplate REST_TEMPLATE;

    static {
        REST_TEMPLATE = new RestTemplate();
        REST_TEMPLATE.setErrorHandler(new ClientErrorHandler());
    }

    public static Journey getJourney(String departureStationId,
                                     String arrivalStationId,
                                     DateTime startTime,
                                     DateTime endTime,
                                     boolean includeDelays,
                                     boolean searchPreemptive) throws ResourceNotFoundException {

        log.warn("SEARCHING JOURNEYS WITH PARAMS: {} {} {} {} {} {}", departureStationId, arrivalStationId, startTime, endTime, includeDelays, searchPreemptive);

        DateTime time = startTime;
        if (startTime == null && endTime == null) time = DateTime.now();
        if (searchPreemptive) time = time.minusHours(PREEMPTIVE_HOURS);
        if (endTime != null) time = endTime.minusHours(HOUR_PER_SLOT);

        Journey journey = getJourney(departureStationId, arrivalStationId, time.toString(yyyyMMddTHHmm00));

        if (journey == null || journey.getSolutions() == null || journey.getSolutions().size() == 0) {
            throw new ResourceNotFoundException();
        }

        log.warn(time.toString(yyyyMMddTHHmm00));
        log.warn(journey.toString());

        if (startTime != null || (startTime == null && endTime == null)) {
            cutToMaxNumberAllowed(journey, RIDES_PER_REQUEST);
        }
        if (searchPreemptive) {
            cutStartOfList(journey, startTime);
        }
        if (endTime != null) {
            cutEndOfList(journey, endTime);
        }

        includeTrainDataAndFlatten(journey, includeDelays);

        return journey;
    }

    private static Journey getJourney(String departureStationId, String arrivalStationId, String dateTime) throws ResourceNotFoundException {
        Journey journey = REST_TEMPLATE.getForObject(TAPI.DNS + TAPI.JOURNEY + departureStationId + "/" + arrivalStationId + "/" + dateTime, Journey.class);

        journey.setJourneyDepartureStationId(departureStationId);
        journey.setJourneyArrivalStationId(arrivalStationId);
        return journey;
    }

    private static void cutToMaxNumberAllowed(Journey journey, int maxElems) {
        if (maxElems <= journey.getSolutions().size()) {
            journey.setSolutions(journey.getSolutions().subList(0, maxElems));
        }
    }

    private static void cutStartOfList(Journey journey, DateTime startTime) {
        // cut list at the element at the last element where departuretime is before endtime
        journey.setSolutions(journey.getSolutions().subList(getIndexOfFirstAfterTime(journey.getSolutions(), startTime), journey.getSolutions().size() - 1));
    }

    private static void cutEndOfList(Journey journey, DateTime endTime) {
        // cut list at the element at the last element where departuretime is before endtime
        journey.setSolutions(journey.getSolutions().subList(0, getIndexOfFirstAfterTime(journey.getSolutions(), endTime)));
    }

    private static int getIndexOfFirstAfterTime(List<Solution> solutions, DateTime time) {
        // cut list at the element before the first element with departureTime after startTime
        //   ^     ^
        // 0 1 2 3 4 5 6
        // n n s s s n n
        int i = 0;
        for (Solution solution : solutions) {
            if (solution.getChanges().get(0).getDepartureTime().isAfter(time)) {
                // this is the first element after a certain time
                if (i > 0) i--;
                break;
            }
            i++;
        }
        return i >= solutions.size() ? i - solutions.size() : i;
    }

    private static void includeTrainDataAndFlatten(Journey journey, boolean includeDelays) {
        ObjectMapper mapper = new ObjectMapper();

        DateTime minTime = null;
        int minIndex = 0;
        int detailedTrainsNumber = 0;
        int maxDetailedTrains = RIDES_WITH_DELAY;
        for (Solution solution : journey.getSolutions()) {
            if (includeDelays) {
                if (detailedTrainsNumber < maxDetailedTrains) {
                    for (Change change : solution.getChanges()) {
                        try {
                            Train train = TrainEndpoint.getTrain(change.getTrainId());
                            mapper.readerForUpdating(change).readValue(new Gson().toJson(train));
                            change.setPlatform(train.getStopData(solution.getChanges().get(0).getDepartureStationName()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            solution.doAsLatest();

//            if (detailedTrainsNumber == 0) {
//                journey.getSolutions().get(0).getSolution().setDepartureTime(
//                        journey.getSolutions().get(0).getSolution().getDepartureTime().plusMinutes(10)
//                );
//            }
//
//            if (detailedTrainsNumber == 1) {
//                journey.getSolutions().get(1).getSolution().setArrivalTime(
//                        journey.getSolutions().get(1).getSolution().getArrivalTime().minusMinutes(20)
//                );
//            }

            if (solution.leavesAfterNow() && solution.isFirstArriving(minTime)) {
                minIndex = detailedTrainsNumber;
                minTime = solution.getSolution().getArrivalTime();
                if (solution.getSolution().getTimeDifference() != null) {
                    minTime = minTime.plusMinutes(solution.getSolution().getTimeDifference());
                }
            }
            detailedTrainsNumber++;
        }
        try {
            if (minTime != null) {
                journey.getSolutions().get(minIndex).setArrivesFirst(true);
            } else {
                journey.getSolutions().get(minIndex).setArrivesFirst(false);
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
