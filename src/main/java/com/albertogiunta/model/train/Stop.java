package com.albertogiunta.model.train;

import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TFIELD;
import com.albertogiunta.model.serializers.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

@JsonRootName("stop")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class Stop {

    private String stationId;
    private String stationName;

    private Integer currentStopStatusCode;
    private Integer currentStopTypeCode;
    private Integer currentAndNextStopStatusCode;

    private DateTime plannedArrivalTime;
    private DateTime actualArrivalTime;

    private DateTime plannedDepartureTime;
    private String plannedDeparturePlatform;

    private DateTime actualDepartureTime;
    private String actualDeparturePlatform;

    private String departurePlatform;

    private Integer timeDifference;

    private Boolean isVisited;


    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* STATION ID */
    @JsonGetter(JFIELD.STATION_ID)
    public String getStationId() {
        return stationId;
    }

    @JsonSetter(TFIELD.STATION_ID)
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    /* STATION NAME_LONG */
    @JsonGetter(JFIELD.STATION_NAME)
    public String getStationName() {
        return stationName;
    }

    @JsonSetter(TFIELD.STATION_NAME)
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    @JsonGetter(JFIELD.IS_VISITED)
    public Boolean isVisited() {
        return isVisited;
    }

    @JsonIgnore
    public void setVisited(Boolean visited) {
        isVisited = visited;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* CURRENT STOP CATEGORY */
    @JsonGetter(JFIELD.CURRENT_STOP_TYPE_CODE)
    public Integer getCurrentStopTypeCode() {
        return currentStopTypeCode;
    }

    @JsonSetter(TFIELD.CURRENT_STOP_TYPE_CODE)
    @JsonDeserialize(using = CurrentStopTypeDeserializer.class)
    public void setCurrentStopTypeCode(Integer currentStopTypeCode) {
        this.currentStopTypeCode = currentStopTypeCode;
        if (this.currentStopTypeCode == JVALUE.LAST) {
            this.plannedDepartureTime = getPlannedArrivalTime();
            this.actualDepartureTime = getActualArrivalTime();
        }
        setVisited(setVisited(getActualArrivalTime(), getActualDepartureTime()));
    }

    /* CURRENT STOP TYPE */
    @JsonGetter(JFIELD.CURRENT_STOP_STATUS_CODE)
    public Integer getCurrentStopStatusCode() {
        return currentStopStatusCode;
    }

    @JsonSetter(TFIELD.CURRENT_STOP_STATUS_CODE)
    @JsonDeserialize(using = CurrentStopStatusCodeDeserializer.class)
    public void setCurrentStopStatusCode(Integer currentStopStatusCode) {
        this.currentStopStatusCode = currentStopStatusCode;
    }

    /* NEXT STOP TYPE*/
    @JsonGetter(JFIELD.CURRENT_AND_NEXT_STOP_STATUS_CODE)
    public Integer getCurrentAndNextStopStatusCode() {
        return currentAndNextStopStatusCode;
    }

    @JsonSetter(TFIELD.CURRENT_AND_NEXT_STOP_STATUS_CODE)
    @JsonDeserialize(using = CurrentAndNextStopStatusCodeDeserializer.class)
    public void setCurrentAndNextStopStatusCode(Integer currentAndNextStopStatusCode) {
        this.currentAndNextStopStatusCode = currentAndNextStopStatusCode;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* PLANNED ARRIVAL TIME */
    @JsonIgnore
    public DateTime getPlannedArrivalTime() {
        return plannedArrivalTime;
    }

    @JsonSetter(TFIELD.PLANNED_ARRIVAL_TIME)
    @JsonDeserialize(using = MillisecondsDesererializer.class)
    public void setPlannedArrivalTime(DateTime plannedArrivalTime) {
        this.plannedArrivalTime = plannedArrivalTime;
        if (plannedDepartureTime == null) {
            this.plannedDepartureTime = this.plannedArrivalTime;
        }
    }

    /* ACTUAL ARRIVAL TIME */
    @JsonIgnore
    public DateTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    @JsonSetter(TFIELD.ACTUAL_ARRIVAL_TIME)
    @JsonDeserialize(using = MillisecondsDesererializer.class)
    public void setActualArrivalTime(DateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* PLANNED DEPARTURE TIME */
    @JsonGetter(JFIELD.PLANNED_DEPARTURE_TIME)
    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getPlannedDepartureTime() {
        return plannedDepartureTime;
    }

    @JsonSetter(TFIELD.PLANNED_DEPARTURE_TIME)
    @JsonDeserialize(using = MillisecondsDesererializer.class)
    public void setPlannedDepartureTime(DateTime plannedDepartureTime) {
        this.plannedDepartureTime = plannedDepartureTime;
    }

    /* PLANNED DEPARTURE PLATFORM */
    @JsonIgnore
    public String getPlannedDeparturePlatform() {
        return plannedDeparturePlatform;
    }

    @JsonSetter(TFIELD.PLANNED_DEPARTURE_PLATFORM)
    @JsonDeserialize(using = PlatformDeserializer.class)
    public void setPlannedDeparturePlatform(String plannedDeparturePlatform) {
        this.plannedDeparturePlatform = plannedDeparturePlatform;
        if ((this.departurePlatform == null || this.departurePlatform.length() == 0)) {
            this.departurePlatform = this.plannedDeparturePlatform;
        } else {
        }
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    /* ACTUAL DEPARTURE TIME */
    @JsonGetter(JFIELD.ACTUAL_DEPARTURE_TIME)
    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    @JsonSetter(TFIELD.ACTUAL_DEPARTURE_TIME)
    @JsonDeserialize(using = MillisecondsDesererializer.class)
    public void setActualDepartureTime(DateTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    /* ACTUAL DEPARTURE PLATEFORM */
    @JsonIgnore
    public String getActualDeparturePlatform() {
        return actualDeparturePlatform;
    }

    @JsonSetter(TFIELD.ACTUAL_DEPARTURE_PLATFORM)
    @JsonDeserialize(using = PlatformDeserializer.class)
    public void setActualDeparturePlatform(String actualDeparturePlatform) {
        this.actualDeparturePlatform = actualDeparturePlatform;
        this.departurePlatform = this.actualDeparturePlatform;
    }

    /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    @JsonGetter(value = JFIELD.DEPARTURE_PLATFORM)
    public String getDeparturePlatform() {
        return departurePlatform;
    }

    @JsonIgnore
    public void setDeparturePlatform(String departurePlatform) {
        this.departurePlatform = departurePlatform;
    }

    /* TIME DIFFERENCE */
    @JsonGetter(JFIELD.TIME_DIFFERENCE)
    public Integer getTimeDifference() {
        return timeDifference;
    }

    @JsonSetter(TFIELD.TIME_DIFFERENCE_FOR_STOPS)
    public void setTimeDifference(Integer timeDifference) {
        this.timeDifference = timeDifference;
    }

   /*--------------------------------------------------------------------------------------------------------
    --------------------------------------------------------------------------------------------------------*/

    private Boolean setVisited(DateTime actualDepartureTime, DateTime actualArrivalTime) {
        return actualDepartureTime != null || actualArrivalTime != null;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "stationId='" + stationId + '\'' +
                ", stationName='" + stationName + '\'' +
                ", isVisited=" + isVisited +
                ", currentStopTypeCode=" + currentStopTypeCode +
                ", currentStopStatusCode=" + currentStopStatusCode +
                ", currentAndNextStopStatusCode=" + currentAndNextStopStatusCode +
                ", plannedArrivalTime=" + plannedArrivalTime +
                ", actualArrivalTime=" + actualArrivalTime +
                ", plannedDepartureTime=" + plannedDepartureTime +
                ", plannedDeparturePlatform='" + plannedDeparturePlatform + '\'' +
                ", actualDepartureTime=" + actualDepartureTime +
                ", actualDeparturePlatform='" + actualDeparturePlatform + '\'' +
                ", timeDifference=" + timeDifference +
                '}';
    }
}