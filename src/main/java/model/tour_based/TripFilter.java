package model.tour_based;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

public interface TripFilter {
    boolean filter(Person person, DiscreteModeChoiceTrip trip);
}
