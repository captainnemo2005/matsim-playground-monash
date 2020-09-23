package components.estimators;

import model.DiscreteModeChoiceTrip;
import model.trip_based.TripEstimator;
import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.core.router.TripRouter;
import org.matsim.facilities.ActivityFacilities;
import org.matsim.facilities.FacilitiesUtils;
import org.matsim.facilities.Facility;
import replanning.time_interpreter.TimeInterpreter;

import java.util.Collection;
import java.util.List;

public abstract class AbstractTripRouterEstimator implements TripEstimator {
    private final TripRouter tripRouter;
    private final ActivityFacilities facilities;
    private final TimeInterpreter.Factory timeInterpreterFactory;
    private final Collection<String> preroutedModes;

    public AbstractTripRouterEstimator(TripRouter tripRouter,
                                       ActivityFacilities facilities,
                                       TimeInterpreter.Factory timeInterpreterFactory,
                                       Collection<String> preroutedModes) {
        this.tripRouter = tripRouter;
        this.facilities = facilities;
        this.timeInterpreterFactory = timeInterpreterFactory;
        this.preroutedModes = preroutedModes;
    }

    private boolean isPrerouted(String mode, DiscreteModeChoiceTrip trip) {
        if (preroutedModes.contains(mode)) {
            if (mode.equals(trip.getInitialMode())) {
                Leg initialLeg = (Leg) trip.getInitialElements().get(0);
                double initialDepartureTime = initialLeg.getDepartureTime().seconds();
                if (initialDepartureTime == trip.getDepartureTime()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public final TripCandidate estimateTrip(Person person, String mode, DiscreteModeChoiceTrip trip,
                                            List<TripCandidate> previousTrips) {
        // I) Find the correct origin and destination facilities

        Facility originFacility = FacilitiesUtils.toFacility(trip.getOriginActivity(), facilities);
        Facility destinationFacility = FacilitiesUtils.toFacility(trip.getDestinationActivity(), facilities);

        if (!isPrerouted(mode, trip)) {
            // II) Perform the routing
            List<? extends PlanElement> elements = tripRouter.calcRoute(mode, originFacility, destinationFacility,
                    trip.getDepartureTime(), person);

            // III) Perform utility estimation
            return estimateTripCandidate(person, mode, trip, previousTrips, elements);
        } else {
            // If we already have the route of interest, just pass it on
            return estimateTripCandidate(person, mode, trip, previousTrips, trip.getInitialElements());
        }
    }

    /**
     * Implement this if you just want to calculate a utility, but don't want to
     * return a custom TripCandidate object.
     */
    protected double estimateTrip(Person person, String mode, DiscreteModeChoiceTrip trip,
                                  List<TripCandidate> previousTrips, List<? extends PlanElement> routedTrip) {
        return 0.0;
    }

    /**
     * Implement this if you want to return a custom TripCandidate object rather
     * than just a utility.
     */
    protected TripCandidate estimateTripCandidate(Person person, String mode, DiscreteModeChoiceTrip trip,
                                                  List<TripCandidate> previousTrips, List<? extends PlanElement> routedTrip) {

        TimeInterpreter time = timeInterpreterFactory.createTimeInterpreter();
        time.setTime(trip.getDepartureTime());
        time.addPlanElements(routedTrip);

        double utility = estimateTrip(person, mode, trip, previousTrips, routedTrip);

        double duration = time.getCurrentTime() - trip.getDepartureTime();
        return new DefaultRoutedTripCandidate(utility, mode, routedTrip, duration);
    }
}
