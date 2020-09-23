package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TourConstraint;
import model.tour_based.TourConstraintFactory;
import model.trip_based.TripConstraint;
import model.trip_based.TripConstraintFactory;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public class TourFromTripConstraintFactory implements TourConstraintFactory {
    private final TripConstraintFactory factory;

    public TourFromTripConstraintFactory(TripConstraintFactory factory) {
        this.factory = factory;
    }

    @Override
    public TourConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> planTrips,
                                           Collection<String> availableModes) {
        TripConstraint constraint = factory.createConstraint(person, planTrips, availableModes);
        return new TourFromTripConstraint(constraint);
    }
}
