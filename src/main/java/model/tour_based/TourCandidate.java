package model.tour_based;

import model.trip_based.candidates.TripCandidate;
import model.utilities.UtilityCandidate;

import java.util.List;

public interface TourCandidate extends UtilityCandidate {
    List<TripCandidate> getTripCandidates();
}
