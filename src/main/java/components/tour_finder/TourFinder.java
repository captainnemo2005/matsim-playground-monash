package components.tour_finder;

import model.DiscreteModeChoiceTrip;

import java.util.List;

public interface TourFinder {
    List<List<DiscreteModeChoiceTrip>> findTours(List<DiscreteModeChoiceTrip> trips);
}
