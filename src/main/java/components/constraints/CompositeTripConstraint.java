package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.trip_based.TripConstraint;
import model.trip_based.candidates.TripCandidate;

import java.util.List;

public class CompositeTripConstraint implements TripConstraint {
    final private List<TripConstraint> constraints;

    CompositeTripConstraint(List<TripConstraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean validateBeforeEstimation(DiscreteModeChoiceTrip trip, String mode, List<String> previousModes) {
        for (TripConstraint constraint : constraints) {
            if (!constraint.validateBeforeEstimation(trip, mode, previousModes)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean validateAfterEstimation(DiscreteModeChoiceTrip trip, TripCandidate candidate,
                                           List<TripCandidate> previousCandidates) {
        for (TripConstraint constraint : constraints) {
            if (!constraint.validateAfterEstimation(trip, candidate, previousCandidates)) {
                return false;
            }
        }

        return true;
    }
}