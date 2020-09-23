package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TourCandidate;
import model.tour_based.TourConstraint;

import java.util.List;

public class CompositeTourConstraint implements TourConstraint {
    final private List<TourConstraint> constraints;

    CompositeTourConstraint(List<TourConstraint> constraints) {
        this.constraints = constraints;
    }

    @Override
    public boolean validateBeforeEstimation(List<DiscreteModeChoiceTrip> tour, List<String> modes,
                                            List<List<String>> previousModes) {
        for (TourConstraint constraint : constraints) {
            if (!constraint.validateBeforeEstimation(tour, modes, previousModes)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean validateAfterEstimation(List<DiscreteModeChoiceTrip> tour, TourCandidate candidate,
                                           List<TourCandidate> previousCandidates) {
        for (TourConstraint constraint : constraints) {
            if (!constraint.validateAfterEstimation(tour, candidate, previousCandidates)) {
                return false;
            }
        }

        return true;
    }
}

