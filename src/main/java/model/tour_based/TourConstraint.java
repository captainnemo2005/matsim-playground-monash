package model.tour_based;

import model.DiscreteModeChoiceTrip;

import java.util.List;

public interface TourConstraint {
    boolean validateBeforeEstimation(List<DiscreteModeChoiceTrip> tour, List<String> modes,
                                     List<List<String>> previousModes);

    boolean validateAfterEstimation(List<DiscreteModeChoiceTrip> tour, TourCandidate candidate,
                                    List<TourCandidate> previousCandidates);
}
