package components.estimators;

import model.DiscreteModeChoiceTrip;
import model.trip_based.TripEstimator;
import model.trip_based.candidates.DefaultTripCandidate;
import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.router.TripRouter;
import org.matsim.facilities.ActivityFacilities;
import replanning.time_interpreter.TimeInterpreter;

import java.util.Collection;
import java.util.List;

public class UniformTripEstimator implements TripEstimator {

    private final TimeInterpreter.Factory timeInterpreterFactory;

    public UniformTripEstimator(TimeInterpreter.Factory timeInterpreterFactory) {
        this.timeInterpreterFactory = timeInterpreterFactory;
    }

    @Override
    public TripCandidate estimateTrip(Person person,
                                      String mode,
                                      DiscreteModeChoiceTrip trip,
                                      List<TripCandidate> previousTrips) {
        TimeInterpreter time = timeInterpreterFactory.createTimeInterpreter();

        time.setTime(trip.getDepartureTime());
        time.addPlanElements(trip.getInitialElements());

        double duration = time.getCurrentTime() - trip.getDepartureTime();

        return new DefaultTripCandidate(1.0, mode, duration);
    }
}
