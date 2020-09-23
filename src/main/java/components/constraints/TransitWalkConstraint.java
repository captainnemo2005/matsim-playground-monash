package components.constraints;

import model.DiscreteModeChoiceTrip;
import model.trip_based.TripConstraint;
import model.trip_based.TripConstraintFactory;
import model.trip_based.candidates.RoutedTripCandidate;
import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;

import java.util.Collection;
import java.util.List;

public class TransitWalkConstraint extends AbstractTripConstraint {
    @Override
    public boolean validateAfterEstimation(DiscreteModeChoiceTrip trip, TripCandidate candidate,
                                           List<TripCandidate> previousCandidates) {
        if (candidate.getMode().equals(TransportMode.pt)) {
            if (candidate instanceof RoutedTripCandidate) {
                // Go through all plan elments
                for (PlanElement element : ((RoutedTripCandidate) candidate).getRoutedPlanElements()) {
                    if (element instanceof Leg) {
                        if (((Leg) element).getMode().equals(TransportMode.pt)) {
                            // If we find at least one pt leg, we're good
                            return true;
                        }
                    }
                }

                // If there was no pt leg, we do not accept this candidate
                return false;
            } else {
                throw new IllegalStateException("Need a route to evaluate constraint");
            }
        }

        return true;
    }

    static public class Factory implements TripConstraintFactory {
        @Override
        public TripConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> trips,
                                               Collection<String> availableModes) {
            return new TransitWalkConstraint();
        }
    }
}
