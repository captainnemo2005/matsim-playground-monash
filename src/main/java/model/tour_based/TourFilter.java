package model.tour_based;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

import java.util.List;

public interface TourFilter {
    boolean filter(Person person, List<DiscreteModeChoiceTrip> tour);
}
