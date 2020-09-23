package model.tour_based;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public interface TourConstraintFactory {
    TourConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> planTrips,
                                    Collection<String> availableModes);
}
