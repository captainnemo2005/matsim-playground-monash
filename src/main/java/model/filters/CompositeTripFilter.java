package model.filters;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TripFilter;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;

public class CompositeTripFilter implements TripFilter {

    private final Collection<TripFilter> filters;

    public CompositeTripFilter(Collection<TripFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean filter(Person person, DiscreteModeChoiceTrip trip) {
        for (TripFilter filter : filters) {
            if (!filter.filter(person, trip)) {
                return false;
            }
        }

        return true;
    }
}
