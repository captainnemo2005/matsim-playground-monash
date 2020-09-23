package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.trip_based.TripConstraint;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public interface TripConstraintFactory {
    TripConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> planTrips,
                                    Collection<String> availableModes);
}

