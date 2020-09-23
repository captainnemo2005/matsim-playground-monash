package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TourCandidate;
import model.tour_based.TourConstraint;

import java.util.List;

public abstract class AbstractTourConstraint implements TourConstraint {
    @Override
    public boolean validateBeforeEstimation(List<DiscreteModeChoiceTrip> tour, List<String> modes,
                                            List<List<String>> previousModes) {
        return true;
    }

    @Override
    public boolean validateAfterEstimation(List<DiscreteModeChoiceTrip> tour, TourCandidate candidate,
                                           List<TourCandidate> previousCandidates) {
        return true;
    }
}
