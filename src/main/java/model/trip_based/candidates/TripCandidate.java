package model.trip_based.candidates;

import model.utilities.UtilityCandidate;

public interface TripCandidate  extends UtilityCandidate {
    String getMode();

    double getDuration();
}
