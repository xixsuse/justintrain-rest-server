package com.albertogiunta.model.train;


import com.albertogiunta.constants.JIT.JFIELD;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("train")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class TrainHeaderOnly {

    @JsonProperty(value = JFIELD.TRAIN_CATEGORY)
    private String trainCategory;
    @JsonProperty(value = JFIELD.TRAIN_ID)
    private String trainId;

    @JsonProperty(value = JFIELD.TRAIN_DEPARTURE_STATION_ID)
    private String trainDepartureStationId;
    @JsonProperty(value = JFIELD.DEPARTURE_STATION_NAME)
    private String departureStationName;
    @JsonProperty(value = JFIELD.DEPARTURE_PLATFORM)
    private String departurePlatform;

    @JsonProperty(value = JFIELD.TRAIN_ARRIVAL_STATION_ID)
    private String arrivalStationId;
    @JsonProperty(value = JFIELD.ARRIVAL_STATION_NAME)
    private String arrivalStationName;

    @JsonProperty(value = JFIELD.TIME_DIFFERENCE)
    private int timeDifference;
    @JsonProperty(value = JFIELD.PROGRESS)
    private int progress;

    @JsonProperty(value = JFIELD.LAST_SEEN_STATION_NAME)
    private String lastSeenStationName;
    @JsonProperty(value = JFIELD.LAST_SEEN_TIME_READABLE)
    private String lastSeenTimeReadable; // in HH:mm

    @JsonProperty(value = JFIELD.CANCELLED_STOPS_INFO)
    private String cancelledStopsInfo;

    @JsonProperty(value = JFIELD.FIRST_CLASS_ORIENTATION)
    private int firstClassOrientationCode;
    @JsonProperty(value = JFIELD.TRAIN_STATUS_CODE)
    private int trainStatusCode;
    @JsonProperty(value = JFIELD.IS_DEPARTED)
    private boolean isDeparted;
    @JsonProperty(value = JFIELD.IS_ARRIVED_TO_DESTINATION)
    private boolean isArrivedToDestination;

}
