package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.trip_based.TripConstraint;
import model.trip_based.candidates.TripCandidate;

import java.util.List;

public abstract class AbstractTripConstraint implements TripConstraint {
    public boolean validateBeforeEstimation(DiscreteModeChoiceTrip trip, String mode, List<String> previousModes) {
        return true;
    }

    public boolean validateAfterEstimation(DiscreteModeChoiceTrip trip, TripCandidate candidate,
                                           List<TripCandidate> previousCandidates) {
        return true;
    }
}
