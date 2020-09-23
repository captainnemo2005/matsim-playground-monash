package model.filters;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TourFilter;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public class CompositeTourFilter implements TourFilter {
    private final Collection<TourFilter> filters;

    public CompositeTourFilter(Collection<TourFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean filter(Person person, List<DiscreteModeChoiceTrip> tour) {
        for (TourFilter filter : filters) {
            if (!filter.filter(person, tour)) {
                return false;
            }
        }

        return true;
    }

}
