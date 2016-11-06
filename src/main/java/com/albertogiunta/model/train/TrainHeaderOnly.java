package com.albertogiunta.model.train;


import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.model.serializers.DateTimeSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

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
    @JsonProperty(value = JFIELD.TRAIN_DEPARTURE_PLATFORM)
    private String trainDeparturePlatform;
    @JsonProperty(value = JFIELD.TRAIN_DEPARTURE_STATION_NAME)
    private String trainDepartureStationName;

    @JsonProperty(value = JFIELD.TRAIN_ARRIVAL_STATION_ID)
    private String arrivalStationId;
    @JsonProperty(value = JFIELD.TRAIN_ARRIVAL_STATION_NAME)
    private String trainArrivalStationName;

    @JsonProperty(value = JFIELD.TIME_DIFFERENCE)
    private Integer timeDifference;
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
    
    @JsonProperty(value = JFIELD.JOURNEY_DEPARTURE_STATION_ID)
    private String journeyDepartureStationId;
    @JsonProperty(value = JFIELD.JOURNEY_DEPARTURE_STATION_NAME)
    private String journeyDepartureStationName;
    @JsonProperty(value = JFIELD.JOURNEY_DEPARTURE_TIME)
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime journeyDepartureTime;
    @JsonProperty(value = JFIELD.JOURNEY_ARRIVAL_STATION_ID)
    private String journeyArrivalStationId;
    @JsonProperty(value = JFIELD.JOURNEY_ARRIVAL_STATION_NAME)
    private String journeyArrivalStationName;
    @JsonProperty(value = JFIELD.JOURNEY_ARRIVAL_TIME)
    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime journeyArrivalTime;
    @JsonProperty(value = JFIELD.DEPARTURE_PLATFORM)
    private String departurePlatform;
    @JsonProperty(value = JFIELD.IS_JOURNEY_DEPARTURE_STATION_VISITED)
    private Boolean isJourneyDepartureStationVisited;
    @JsonProperty(value = JFIELD.IS_JOURNEY_ARRIVAL_STATION_VISITED)
    private Boolean isJourneyArrivalStationVisited;
    @JsonProperty(value = JFIELD.ETA_TO_NEXT_JOURNEY_STATION)
    private int ETAToNextJourneyStation;
    
    public Integer getTimeDifference() {
        return timeDifference;
    }
    
    @JsonIgnore
    public void setETAToNextJourneyStation(int ETAToNextJourneyStation) {
        this.ETAToNextJourneyStation = ETAToNextJourneyStation;
    }
    
    @JsonIgnore
    public void setJourneyDepartureStationId(String journeyDepartureStationId) {
        this.journeyDepartureStationId = journeyDepartureStationId;
    }
    
    @JsonIgnore
    public void setJourneyDepartureStationName(String journeyDepartureStationName) {
        this.journeyDepartureStationName = journeyDepartureStationName;
    }
    
    @JsonIgnore
    public void setJourneyDepartureTime(DateTime journeyDepartureTime) {
        this.journeyDepartureTime = journeyDepartureTime;
    }
    
    @JsonIgnore
    public void setJourneyArrivalStationId(String journeyArrivalStationId) {
        this.journeyArrivalStationId = journeyArrivalStationId;
    }
    
    @JsonIgnore
    public void setJourneyArrivalStationName(String journeyArrivalStationName) {
        this.journeyArrivalStationName = journeyArrivalStationName;
    }
    
    @JsonIgnore
    public void setJourneyArrivalTime(DateTime journeyArrivalTime) {
        this.journeyArrivalTime = journeyArrivalTime;
    }
    
    @JsonIgnore
    public void setDeparturePlatform(String departurePlatform) {
        this.departurePlatform = departurePlatform;
    }
    
    @JsonIgnore
    public void setJourneyDepartureStationVisited(Boolean journeyDepartureStationVisited) {
        isJourneyDepartureStationVisited = journeyDepartureStationVisited;
    }
    
    @JsonIgnore
    public void setJourneyArrivalStationVisited(Boolean journeyArrivalStationVisited) {
        isJourneyArrivalStationVisited = journeyArrivalStationVisited;
    }
}
