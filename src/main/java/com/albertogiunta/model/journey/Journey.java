package com.albertogiunta.model.journey;

import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.constants.TI.TFIELD;
import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonRootName("journey")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class Journey {

    private String journeyDepartureStationId;
    private String journeyArrivalStationId;
    private List<Solution> solutions;

    @JsonGetter(value = JFIELD.JOURNEY_DEPARTURE_STATION_ID)
    public String getJourneyDepartureStationId() {
        return journeyDepartureStationId;
    }

    public void setJourneyDepartureStationId(String journeyDepartureStationId) {
        this.journeyDepartureStationId = journeyDepartureStationId;
    }

    @JsonGetter(value = JFIELD.JOURNEY_ARRIVAL_STATION_ID)
    public String getJourneyArrivalStationId() {
        return journeyArrivalStationId;
    }

    public void setJourneyArrivalStationId(String journeyArrivalStationId) {
        this.journeyArrivalStationId = journeyArrivalStationId;
    }

    @JsonGetter(value = JFIELD.SOLUTIONS)
    public List<Solution> getSolutions() {
        return solutions;
    }

    @JsonProperty(value = TFIELD.SOLUTIONS)
    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "journeyDepartureStationId='" + journeyDepartureStationId + '\'' +
                ", journeyArrivalStationId='" + journeyArrivalStationId + '\'' +
                ", solutions=" + solutions +
                '}';
    }
}

/*    "soluzioni": [
    {
        "durata": "01:03",
            "vehicles": [
        {
            "origine": "Cesena",
                "destinazione": "Pesaro",
                "orarioPartenza": "2016-03-14T16:42:00",
                "orarioArrivo": "2016-03-14T17:45:00",
                "categoria": "235",
                "categoriaDescrizione": "RV",
                "numeroTreno": "2921"
        }
        ]
    },
*/

/*

{
    "journeyDepartureStationId": "7104",
    "journeyArrivalStationId": "5066",
    "solutions": [
    {
      "trainCategory": "",
      "trainId": "",
      "trainDepartureStationId": "S11145",
      "departureStationName": "Pesaro",
      "departureTime": 1477310100,
      "departureTimeReadable": "11:55",
      "departurePlatform": "2",
      "arrivalStationName": "Cesena",
      "arrivalTime": 1477313640,
      "arrivalTimeReadable": "12:54",
      "timeDifference": -1,
      "progress": 0,
      "duration": "00:59",
      "hasChanges": true,
      "changes": {
        "changesNumber": 1,
        "quickTrainDetails": [
        {
          "trainCategory": "FB",
          "trainId": "9814",
          "trainDepartureStationId": "S11145"
        },
        {
          "trainCategory": "REG",
          "trainId": "11534",
          "trainDepartureStationId": "S05071"
        }
      ],
      "changesList": [
        {
          "trainCategory": "FB",
          "trainId": "9814",
          "trainDepartureStationId": "S11145",
          "departureStationName": "Pesaro",
          "departureTime": 1477310100,
          "departureTimeReadable": "11:55",
          "departurePlatform": "2",
          "arrivalStationName": "Rimini",
          "arrivalTime": 1477311300,
          "arrivalTimeReadable": "12:15",
          "timeDifference": -1,
          "progress": 0,
          "duration": "00:20"
        },
        {
          "trainCategory": "REG",
          "trainId": "11534",
          "trainDepartureStationId": "S05071",
          "departureStationName": "Rimini",
          "departureTime": 1477312200,
          "departureTimeReadable": "12:30",
          "departurePlatform": "1",
          "arrivalStationName": "Cesena",
          "arrivalTime": 1477313640,
          "arrivalTimeReadable": "12:54",
          "timeDifference": 0,
          "progress": 0,
          "duration": "00:24"
        }
      ]
    }
  }]
}

 */