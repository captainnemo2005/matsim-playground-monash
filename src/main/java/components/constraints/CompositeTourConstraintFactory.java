package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.tour_based.TourConstraint;
import model.tour_based.TourConstraintFactory;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeTourConstraintFactory implements TourConstraintFactory {
    private final List<TourConstraintFactory> factories = new LinkedList<>();

    public CompositeTourConstraintFactory() {
    }

    public CompositeTourConstraintFactory(List<TourConstraintFactory> factories) {
        this.factories.addAll(factories);
    }

    public void addFactory(TourConstraintFactory factory) {
        this.factories.add(factory);
    }

    @Override
    public TourConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> planTrips,
                                           Collection<String> availableModes) {
        return new CompositeTourConstraint(factories.stream()
                .map(f -> f.createConstraint(person, planTrips, availableModes)).collect(Collectors.toList()));
    }
}
