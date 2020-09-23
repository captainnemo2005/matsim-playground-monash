package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TourCandidate;
import model.tour_based.TourConstraint;
import model.trip_based.TripConstraint;
import model.trip_based.candidates.TripCandidate;

import java.util.LinkedList;
import java.util.List;

public class TourFromTripConstraint implements TourConstraint {
    private final TripConstraint constraint;

    TourFromTripConstraint(TripConstraint constraint) {
        this.constraint = constraint;
    }

    @Override
    public boolean validateBeforeEstimation(List<DiscreteModeChoiceTrip> currentTourTrips,
                                            List<String> currentTourModes,
                                            List<List<String>> previousTourModes) {
        List<String> previousTripModes = new LinkedList<>();
        previousTourModes.forEach(previousTripModes::addAll);

        for (int i = 0; i < currentTourModes.size(); i++) {
            if (!constraint.validateBeforeEstimation(currentTourTrips.get(i), currentTourModes.get(i),
                    previousTripModes)) {
                return false;
            }

            previousTripModes.add(currentTourModes.get(i));
        }

        return true;
    }

    @Override
    public boolean validateAfterEstimation(List<DiscreteModeChoiceTrip> currentTourTrips,
                                           TourCandidate currentTourCandidate,
                                           List<TourCandidate> previousTourCandidates) {
        List<TripCandidate> previousTripCandidates = new LinkedList<>();
        previousTourCandidates.stream().map(TourCandidate::getTripCandidates).forEach(previousTripCandidates::addAll);

        for (int i = 0; i < currentTourCandidate.getTripCandidates().size(); i++) {
            TripCandidate currentTripCandidate = currentTourCandidate.getTripCandidates().get(i);

            if (!constraint.validateAfterEstimation(currentTourTrips.get(i), currentTripCandidate,
                    previousTripCandidates)) {
                return false;
            }

            previousTripCandidates.add(currentTripCandidate);
        }

        return true;
    }
}

