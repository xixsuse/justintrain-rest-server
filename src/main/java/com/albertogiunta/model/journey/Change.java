package com.albertogiunta.model.journey;

import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TFIELD;
import com.albertogiunta.model.serializers.DateTimeSerializer;
import com.albertogiunta.model.serializers.DateTimeToDateTimeDeserializer;
import com.albertogiunta.model.serializers.TrainCategoryDeserializer;
import com.albertogiunta.model.train.Stop;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

@JsonRootName("change")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class Change {

    private String trainCategory;
    private String trainId;

    private String departureStationId;
    private String departureStationName;
    private DateTime departureTime;
    private String departurePlatform;

    private String arrivalStationName;
    private DateTime arrivalTime;

    private Integer timeDifference;
    private Integer progress;

    private String duration;

    // missing readable

    public Change() {
    }

    public Change(String departureStationName, DateTime departureTime, String departurePlatform, String arrivalStationName, DateTime arrivalTime, Integer timeDifference, String duration) {
        this.departureStationName = departureStationName;
        this.departureTime = departureTime;
        this.departurePlatform = departurePlatform;
        this.arrivalStationName = arrivalStationName;
        this.arrivalTime = arrivalTime;
        this.timeDifference = timeDifference;
        this.duration = duration;
    }

    @JsonGetter(value = JFIELD.TRAIN_CATEGORY)
    public String getTrainCategory() {
        return trainCategory;
    }

    @JsonSetter(value = TFIELD.TRAIN_CATEGORY_FOR_RIDE)
    @JsonDeserialize(using = TrainCategoryDeserializer.class)
    public void setTrainCategory(String trainCategory) {
        this.trainCategory = trainCategory;
    }

    @JsonGetter(value = JFIELD.TRAIN_ID)
    public String getTrainId() {
        return trainId;
    }

    @JsonSetter(value = TFIELD.TRAIN_ID)
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @JsonGetter(value = JFIELD.TRAIN_DEPARTURE_STATION_ID)
    public String getDepartureStationId() {
        return departureStationId;
    }

    @JsonSetter(JFIELD.TRAIN_DEPARTURE_STATION_ID)
    public void setDepartureStationId(String departureStationId) {
        this.departureStationId = departureStationId;
    }

    @JsonGetter(value = JFIELD.DEPARTURE_STATION_NAME)
    public String getDepartureStationName() {
        return departureStationName;
    }

    @JsonSetter(value = TFIELD.DEPARTURE_STATION_NAME)
    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    @JsonGetter(value = JFIELD.DEPARTURE_TIME)
    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getDepartureTime() {
        return departureTime;
    }

    @JsonSetter(value = TFIELD.DEPARTURE_TIME)
    @JsonDeserialize(using = DateTimeToDateTimeDeserializer.class)
    public void setDepartureTime(DateTime departureTime) {
        this.departureTime = departureTime;
    }

    @JsonGetter(value = JFIELD.DEPARTURE_PLATFORM)
    public String getDeparturePlatform() {
        return departurePlatform;
    }

    @JsonIgnore
    public void setDeparturePlatform(String departurePlatform) {
        this.departurePlatform = departurePlatform;
    }

    @JsonGetter(value = JFIELD.ARRIVAL_STATION_NAME)
    public String getArrivalStationName() {
        return arrivalStationName;
    }

    @JsonSetter(value = TFIELD.ARRIVAL_STATION_NAME)
    public void setArrivalStationName(String arrivalStationName) {
        this.arrivalStationName = arrivalStationName;
    }

    @JsonGetter(value = JFIELD.ARRIVAL_TIME)
    @JsonSerialize(using = DateTimeSerializer.class)
    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    @JsonSetter(value = TFIELD.ARRIVAL_TIME)
    @JsonDeserialize(using = DateTimeToDateTimeDeserializer.class)

    public void setArrivalTime(DateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        setDuration(duration());
    }

    @JsonGetter(value = JFIELD.TIME_DIFFERENCE)
    public Integer getTimeDifference() {
        return timeDifference;
    }

    @JsonSetter(value = JFIELD.TIME_DIFFERENCE)
    public void setTimeDifference(Integer timeDifference) {
        this.timeDifference = timeDifference;
    }

    @JsonGetter(value = JFIELD.PROGRESS)
    public Integer getProgress() {
        return progress;
    }

    @JsonSetter(value = JFIELD.PROGRESS)
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @JsonGetter(value = JFIELD.DURATION)
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPlatform(Stop stop) {
        if (stop != null) {
            this.departurePlatform = stop.getDeparturePlatform();
        }
    }

    private void doAsLatest() {
        setDuration(duration());
    }

    private String duration() {
        int min = Minutes.minutesBetween(getDepartureTime(), getArrivalTime()).getMinutes();
        int hour = Math.floorDiv(min, 60);
        return new DateTime().withHourOfDay(hour).withMinuteOfHour(min - hour * 60).toString(JVALUE.HHmm);
    }


    @Override
    public String toString() {
        return "Change{" +
                "trainCategory='" + trainCategory + '\'' +
                ", trainId='" + trainId + '\'' +
                ", departureStationId='" + departureStationId + '\'' +
                ", departureStationName='" + departureStationName + '\'' +
                ", departureTime=" + departureTime +
                ", departurePlatform='" + departurePlatform + '\'' +
                ", arrivalStationName='" + arrivalStationName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", timeDifference=" + timeDifference +
                ", progress=" + progress +
                ", duration='" + duration + '\'' +
                '}';
    }
}
