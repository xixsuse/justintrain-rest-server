package com.albertogiunta.model.train;

import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TFIELD;
import com.albertogiunta.model.serializers.LastSeenSerializer;
import com.albertogiunta.model.serializers.TrainCategoryDeserializer;
import com.albertogiunta.model.serializers.TrainOrientationDeserializer;
import com.albertogiunta.model.serializers.TrainStatusCodeDeserializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@JsonRootName("train")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class Train {

    private String trainCategory;
    private String trainId;

    private String trainDepartureStationId;
    private String departureStationName;
//    private String departurePlatform;

    private String arrivalStationId;
    private String arrivalStationName;

    private int timeDifference; //TODO vedi se calcolarlo anche a mano
    private int progress;

    private String lastSeenStationName;
    private String lastSeenTimeReadable; // in HH:mm

    private List<Stop> stops = new ArrayList<>();
    private String cancelledStopsInfo;

    private int firstClassOrientationCode;
    private int trainStatusCode;
    private boolean isDeparted;
    private boolean isArrivedToDestination;


    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* TRAIN CATEGORY */
    @JsonGetter(JFIELD.TRAIN_CATEGORY)
    public String getTrainCategory() {
        return trainCategory;
    }

    @JsonSetter(TFIELD.TRAIN_CATEGORY_FOR_TRAIN)
    @JsonDeserialize(using = TrainCategoryDeserializer.class)
    public void setTrainCategory(String trainCategory) {
        this.trainCategory = trainCategory;
    }

    /* TRAIN NUMBER */
    @JsonGetter(JFIELD.TRAIN_ID)
    public String getTrainId() {
        return trainId;
    }

    @JsonProperty(TFIELD.TRAIN_ID)
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* DEPARTURE STATION ID */
    @JsonGetter(JFIELD.TRAIN_DEPARTURE_STATION_ID)
    public String getTrainDepartureStationId() {
        return trainDepartureStationId;
    }

    @JsonSetter(TFIELD.DEPARTURE_STATION_ID)
    public void setTrainDepartureStationId(String trainDepartureStationId) {
        this.trainDepartureStationId = trainDepartureStationId;
    }

    /* DEPARTURE STATION NAME */
    @JsonGetter(JFIELD.DEPARTURE_STATION_NAME)
    public String getDepartureStationName() {
        return departureStationName;
    }

    @JsonSetter(TFIELD.DEPARTURE_STATION_NAME)
    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    /* DEPARTURE STATION PLATFORM */
//    @JsonGetter(JFIELD.DEPARTURE_PLATFORM)
//    public String getPlannedDeparturePlatform() {
//        return departurePlatform;
//    }
//
//    @JsonIgnore
//    public void setPlannedDeparturePlatform() {
//        this.departurePlatform = setPlannedDeparturePlatform(getStops(), getTrainDepartureStationId());
//    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* ARRIVAL STATION ID */
    @JsonGetter(JFIELD.TRAIN_ARRIVAL_STATION_ID)
    public String getArrivalStationId() {
        return arrivalStationId;
    }

    @JsonSetter(TFIELD.ARRIVAL_STATION_ID)
    public void setArrivalStationId(String arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    /* ARRIVAL STATION NAME*/
    @JsonGetter(JFIELD.ARRIVAL_STATION_NAME)
    public String getArrivalStationName() {
        return arrivalStationName;
    }

    @JsonSetter(TFIELD.ARRIVAL_STATION_NAME)
    public void setArrivalStationName(String arrivalStationName) {
        this.arrivalStationName = arrivalStationName;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* TIME DIFFERENCE */
    @JsonGetter(JFIELD.TIME_DIFFERENCE)
    public int getTimeDifference() {
        return timeDifference;
    }

    @JsonSetter(TFIELD.TIME_DIFFERENCE_FOR_TRAIN)
    public void setTimeDifference(int timeDifference) {
        this.timeDifference = timeDifference;
        doAsLatest();
    }

    /* PROGRESS */
    @JsonGetter(JFIELD.PROGRESS)
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* LAST SEEN STATION */
    @JsonGetter(JFIELD.LAST_SEEN_STATION_NAME)
    public String getLastSeenStationName() {
        return lastSeenStationName;
    }

    @JsonSetter(TFIELD.LAST_SEEN_STATION_NAME)
    @JsonDeserialize(using = LastSeenSerializer.class)
    public void setLastSeenStationName(String lastSeenStationName) {
        this.lastSeenStationName = lastSeenStationName;
    }

    /* LAST SEEN TIME */
    @JsonGetter(JFIELD.LAST_SEEN_TIME_READABLE)
    public String getLastSeenTimeReadable() {
        return lastSeenTimeReadable;
    }

    @JsonSetter(TFIELD.LAST_SEEN_TIME_READABLE)
    @JsonDeserialize(using = LastSeenSerializer.class)
    public void setLastSeenTimeReadable(String lastSeenTime) {
        this.lastSeenTimeReadable = lastSeenTime;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* STATION ARRAY */
    @JsonGetter(JFIELD.STOPS)
    public List<Stop> getStops() {
        return stops;
    }

    @JsonSetter(TFIELD.STOPS)
    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    /* CANCELLED STOPS INFO */
    @JsonGetter(JFIELD.CANCELLED_STOPS_INFO)
    public String getCancelledStopsInfo() {
        return cancelledStopsInfo;
    }

    @JsonSetter(TFIELD.CANCELLED_STOPS_INFO)
    public void setCancelledStopsInfo(String cancelledStopsInfo) {
        this.cancelledStopsInfo = cancelledStopsInfo;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* FIRST CLASS ORIENTATION */
    @JsonGetter(JFIELD.FIRST_CLASS_ORIENTATION)
    public int getFirstClassOrientationCode() {
        return firstClassOrientationCode;
    }

    @JsonSetter(TFIELD.FIRST_CLASS_ORIENTATION)
    @JsonDeserialize(using = TrainOrientationDeserializer.class)
    public void setFirstClassOrientationCode(int firstClassOrientationCode) {
        this.firstClassOrientationCode = firstClassOrientationCode;
    }

    /* TRAIN STATUS COE */
    @JsonGetter(JFIELD.TRAIN_STATUS_CODE)
    public int getTrainStatusCode() {
        return this.trainStatusCode;
    }

    @JsonSetter(TFIELD.TRAIN_STATUS_CODE)
    @JsonDeserialize(using = TrainStatusCodeDeserializer.class)
    public void setTrainStatusCode(int trainStatusMessage) {
        this.trainStatusCode = trainStatusMessage;
    }

    /* IS DEPARTED*/
    @JsonGetter(value = JFIELD.IS_DEPARTED)
    public boolean isDeparted() {
        return isDeparted;
    }

    public void setDeparted(boolean departed) {
        isDeparted = departed;
    }

    /* ARRIVED TO DESTINATION */
    @JsonGetter(value = JFIELD.IS_ARRIVED_TO_DESTINATION)
    public boolean isArrivedToDestination() {
        return isArrivedToDestination;
    }

    public void setArrivedToDestination(boolean arrivedToDestination) {
        isArrivedToDestination = arrivedToDestination;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    private void doAsLatest() {
//        setPlannedDeparturePlatform();
        if (stops.size() > 0) {
            setDeparted(stops.get(0).getActualDepartureTime() != null);
            setArrivedToDestination(stops.get(stops.size() - 1).getActualDepartureTime() != null);
            setProgress(setProgress(stops));
        }
    }

    private int setProgress(List<Stop> oldArray) {
        List<Stop> visited = new LinkedList<>();
        long delta = 0L;
        long intermediateDelta;
        for (Stop s : oldArray) {
            if (s.getCurrentStopStatusCode() == JVALUE.VISITED) {
                visited.add(s);
            }
        }

        if (visited.size() <= 5) {
            for (int i = visited.size() - 2; i >= 0; i--) {
                intermediateDelta = visited.get(i + 1).getTimeDifference() - visited.get(i).getTimeDifference();
                delta += intermediateDelta;
            }
        } else {
            for (int i = visited.size() - 2; i >= visited.size() - 5 - 1; i--) {
                intermediateDelta = visited.get(i + 1).getTimeDifference() - visited.get(i).getTimeDifference();
                delta += intermediateDelta;
            }
        }
        if (delta > 2) {
            return JVALUE.FASTER;
        } else if (delta < -2) {
            return JVALUE.SLOWER;
        } else {
            return JVALUE.CONSTANT;
        }
    }

    public Stop getStopData(String stationName) {
        for (Stop stop : stops) {
            if (stop.getStationName().equalsIgnoreCase(stationName)) {
                return stop;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Train{" +
                "isArrivedToDestination=" + isArrivedToDestination +
                ", isDeparted=" + isDeparted +
                ", trainStatusCode=" + trainStatusCode +
                ", cancelledStopsInfo='" + cancelledStopsInfo + '\'' +
                ", stops=" + stops +
                ", lastSeenTimeReadable='" + lastSeenTimeReadable + '\'' +
                ", lastSeenStationName='" + lastSeenStationName + '\'' +
                ", progress=" + progress +
                ", timeDifference=" + timeDifference +
                ", arrivalStationName='" + arrivalStationName + '\'' +
                ", arrivalStationId='" + arrivalStationId + '\'' +
                ", departureStationName='" + departureStationName + '\'' +
                ", trainDepartureStationId='" + trainDepartureStationId + '\'' +
                ", trainId='" + trainId + '\'' +
                ", trainCategory='" + trainCategory + '\'' +
                '}';
    }
}