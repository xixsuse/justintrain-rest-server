package com.albertogiunta.constants.JIT;

public class JAPI {

    // API ENDPOINTS
    public static final String DNS = "";
    public static final String TIME = "time";

    // TRAIN
    public static final String TRAIN_AUTOCOMPLETE = "/train/{trainId}/departure-station";
    public static final String TRAIN = "/station/{departureStationId}/train/{trainId}";
    public static final String TRAIN_NO_STAT = "/train/{trainId}";
    public static final String TRAIN_DELAY = "/station/{departureStationId}/train/{trainId}/delay";
    public static final String TRAIN_DELAY_NO_STAT = "/train/{trainId}/delay";
    public static final String TRAIN_HEADER = "/station/{departureStationId}/train/{trainId}/header";
    public static final String TRAIN_HEADER_NO_STAT = "/train/{trainId}/header";
    public static final String TRAIN_NEWS = "/train/{trainId}/departure-time/{time}/news";
    public static final String NEWS = "/news";
    public static final String STATION_AUTOCOMPLETE = "/station";
    public static final String STATION_COMPLETE_LIST = "/station/all";
    public static final String UPDATE_DB = "/update/all";

    // JOURNEY
    public static final String INSTANT_JOURNEY = "/departure/{departureStationId}/arrival/{arrivalStationId}/instant";
    public static final String JOURNEY_AFTER_TIME = "/departure/{departureStationId}/arrival/{arrivalStationId}/look-ahead";
    public static final String JOURNEY_BEFORE_TIME = "/departure/{departureStationId}/arrival/{arrivalStationId}/look-behind";
    public static final String JOURNEY_DELAY = "/departure/{departureStationId}/arrival/{arrivalStationId}/train/{trainId}/station/{trainDepartureStationId}";
    public static final String JOURNEY_DELAY_WITHOUT_STATION = "/departure/{departureStationId}/arrival/{arrivalStationId}/train/{trainId}";
    
    public static final String MESSAGES = "/messages";
    
    // PARAMS - TRAIN CONTROLLER
    public static final String TRAIN_ID = "trainId";
    public static final String TRAIN_DEP_STAT_ID = "trainDepartureStationId";
    public static final String DEP_STAT_ID = "departureStationId";
    public static final String ARR_STAT_ID = "arrivalStationId";

    // PARAMS - JOURNEY CONTROLLER
    public static final String STARTING_FROM = "start-from";
    public static final String END_AT = "end-at";
    public static final String IS_PREEMPTIVE = "preemptive";
    public static final String INCLUDE_DELAYS = "include-delays";

    // PARAMS - NEWS CONTROLLER
    public static final String STATION_NAME = "stationName";
}
