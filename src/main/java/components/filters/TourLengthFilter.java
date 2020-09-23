package components.filters;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TourFilter;
import org.matsim.api.core.v01.population.Person;

import java.util.List;

public class TourLengthFilter implements TourFilter {
    private final int maximumLength;

    public TourLengthFilter(int maximumLength) {
        this.maximumLength = maximumLength;
    }

    @Override
    public boolean filter(Person person, List<DiscreteModeChoiceTrip> tour) {
        return tour.size() <= maximumLength;
    }
}

