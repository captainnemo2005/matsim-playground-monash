package model.tour_based;

import model.trip_based.candidates.TripCandidate;

import java.util.List;

public class DefaultTourCandidate implements TourCandidate{

    final private double utility;
    final private List<TripCandidate> tripCandidates;
    public DefaultTourCandidate(double utility, List<TripCandidate> tripCandidates) {
        this.utility = utility;
        this.tripCandidates = tripCandidates;
    }

    @Override
    public List<TripCandidate> getTripCandidates() {
        return tripCandidates;
    }

    @Override
    public double getUtility() {
        return utility;
    }
}
