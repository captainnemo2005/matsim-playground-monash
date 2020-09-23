package model.trip_based;

import model.DiscreteModeChoiceTrip;
import model.trip_based.candidates.TripCandidate;

import java.util.List;

public interface TripConstraint {
    boolean validateBeforeEstimation(DiscreteModeChoiceTrip trip,
                                     String mode,
                                     List<String> previousModes);

    boolean validateAfterEstimation(DiscreteModeChoiceTrip trip,
                                    TripCandidate candidate,
                                    List<TripCandidate> previousCandidates);
}
