package com.albertogiunta.model;

import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.constants.TI.TFIELD;
import com.fasterxml.jackson.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@JsonRootName("station")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class Station {

    @JsonProperty(JFIELD.TRAIN_ID)
    String trainId;
    String stationName;
    @JsonProperty(JFIELD.STATION_LONG_ID)
    String stationLongCode;
    @JsonProperty(JFIELD.STATION_SHORT_ID)
    String stationShortCode;

    public Station() {
    }


    public Station(String trainId, String string) {
        this.trainId = trainId;
        trainAutocomplete(string);
        extractShortCode();
    }


    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @JsonGetter(value = JFIELD.NAME)
    public String getStationName() {
        return stationName;
    }

    @JsonSetter(value = TFIELD.NAME)
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    @JsonGetter(value = JFIELD.STATION_LONG_ID)
    public String getStationLongCode() {
        return stationLongCode;
    }

    @JsonSetter(value = TFIELD.STATION_LONG_ID)
    public void setStationLongCode(String stationLongCode) {
        this.stationLongCode = stationLongCode;
        extractShortCode();
    }

    public String getStationShortCode() {
        return stationShortCode;
    }

    public void setStationShortCode(String stationShortCode) {
        this.stationShortCode = stationShortCode;
    }

    private void extractShortCode() {
        this.stationShortCode = stationLongCode.replaceAll("(S|N)0+|(S|N)", "");
    }

    private void trainAutocomplete(String s) {
        if (s != null) {
            final Matcher m = Pattern
                    .compile("(?<=\\d{0,10} \\- )(.*?)(?=\\|)|(?<=\\|\\d{0,10}\\-)(.*?)\\w+")
                    .matcher(s);
            setStationName(m.find() ? m.group() : "");
            setStationLongCode(m.find() ? m.group() : "");
            return;
        }

        setStationName("");
        setStationLongCode("");

    }
}
