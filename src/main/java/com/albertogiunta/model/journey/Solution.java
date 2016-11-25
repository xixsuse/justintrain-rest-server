package com.albertogiunta.model.journey;

import com.albertogiunta.constants.JIT.JFIELD;
import com.albertogiunta.constants.JIT.JVALUE;
import com.albertogiunta.constants.TI.TFIELD;
import com.fasterxml.jackson.annotation.*;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.List;

@JsonRootName("solution")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings("unused")
public class Solution {

    @JsonUnwrapped
    private Change solution;
    private boolean arrivesFirst;
    private boolean hasChanges;
    private List<Change> changes;

    @JsonGetter(value = JFIELD.SOLUTION)
    public Change getSolution() {
        return solution;
    }

    public void setSolution(Change solution) {
        this.solution = solution;
    }

    @JsonGetter(value = JFIELD.ARRIVES_FIRST)
    public boolean isArrivesFirst() {
        return arrivesFirst;
    }

    public void setArrivesFirst(boolean arrivesFirst) {
        this.arrivesFirst = arrivesFirst;
    }

    @JsonGetter(value = JFIELD.HAS_CHANGES)
    public boolean isHasChanges() {
        return hasChanges;
    }

    public void setHasChanges(boolean hasChanges) {
        this.hasChanges = hasChanges;
    }

    @JsonGetter(value = JFIELD.CHANGES_LIST)
    public List<Change> getChanges() {
        return changes;
    }

    @JsonProperty(value = TFIELD.VEHICLES)
    public void setChanges(List<Change> changes) {
        this.changes = changes;
    }

    public boolean isFirstArriving(DateTime currentFirst) {
        DateTime solutionArrivalTime = solution.getArrivalTime();
        if (solution.getTimeDifference() != null) {
            solutionArrivalTime = solutionArrivalTime.plusMinutes(solution.getTimeDifference());
        }
        return currentFirst == null || solutionArrivalTime.isBefore(currentFirst);
    }

    public boolean leavesAfterNow() {
        DateTime solutionDepartureTime = solution.getDepartureTime();
        if (solution.getTimeDifference() != null) {
            solutionDepartureTime = solutionDepartureTime.plusMinutes(solution.getTimeDifference());
        }
        return solutionDepartureTime.isAfter(DateTime.now());
    }

    public void doAsLatest() {
        int changesNumber = changes.size();
        int lastIndex = changes.size() - 1;
        if (changesNumber == 1) {
            setHasChanges(false);
            solution = changes.get(0);
            changes = null;
            if (solution.getTrainStatusCode() != null && solution.getTrainStatusCode() == 2) {
                solution.setTimeDifference(null);
                solution.setProgress(null);
            }
        } else if (changesNumber > 1) {
            setHasChanges(true);
            solution = new Change(
                    changes.get(0).getDepartureStationName(),
                    changes.get(0).getDepartureTime(),
                    changes.get(0).getDeparturePlatform(),
                    changes.get(lastIndex).getArrivalStationName(),
                    changes.get(lastIndex).getArrivalTime(),
                    timeDifference(),
                    duration(changes.get(0).getDepartureTime(), changes.get(lastIndex).getArrivalTime()),
                    null);
            // TODO che status code dare alle soluzioni con cambio?
        }
    }

    private Integer timeDifference() {
        Integer timeDifference = null;
        for (Change change : changes) {
            if (change.getTimeDifference() != null) {
                timeDifference = timeDifference == null ? 0 : timeDifference;
                timeDifference += change.getTimeDifference();
            }
        }
        return timeDifference;
    }

    private String duration(DateTime start, DateTime end) {
        int min = Minutes.minutesBetween(start, end).getMinutes();
        int hour = Math.floorDiv(min, 60);
        return new DateTime().withHourOfDay(hour).withMinuteOfHour(min - hour * 60).toString(JVALUE.HHmm);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solution=" + solution +
                ", arrivesFirst=" + arrivesFirst +
                ", hasChanges=" + hasChanges +
                ", changes=" + changes +
                '}';
    }
}
