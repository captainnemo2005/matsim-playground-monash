package model.trip_based;

import java.util.List;

public interface TripConstraint {
    boolean validateBeforeEstimation(DiscreteModeChoiceTrip trip,
                                     String mode,
                                     List<String> previousModes);

    boolean validateAfterEstimation(DiscreteModeChoiceTrip trip,
                                    TripCandidate candidate,
                                    List<TripCandidate> previousCandidates);
}
