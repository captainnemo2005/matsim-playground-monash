package model.trip_based;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public interface TripConstraintFactory {
    TripConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> planTrips,
                                    Collection<String> availableModes);
}
