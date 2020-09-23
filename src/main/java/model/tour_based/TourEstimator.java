package model.tour_based;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

import java.util.List;

public interface TourEstimator {
    TourCandidate estimateTour(Person person, List<String> modes, List<DiscreteModeChoiceTrip> trips,
                               List<TourCandidate> previousTours);
}

