package model.trip_based;

import model.DiscreteModeChoiceTrip;
import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.population.Person;

import java.util.List;

public interface TripEstimator {
    TripCandidate estimateTrip(Person person, String mode, DiscreteModeChoiceTrip trip, List<TripCandidate> previousTrips);
}
