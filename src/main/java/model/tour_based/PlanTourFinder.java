package model.tour_based;

import components.tour_finder.TourFinder;
import model.DiscreteModeChoiceTrip;

import java.util.Collections;
import java.util.List;

public class PlanTourFinder implements TourFinder {
    @Override
    public List<List<DiscreteModeChoiceTrip>> findTours(List<DiscreteModeChoiceTrip> trips) {
        return Collections.singletonList(trips);
    }
}
