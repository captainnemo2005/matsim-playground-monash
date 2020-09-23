package components.estimators;

import model.DiscreteModeChoiceTrip;
import model.tour_based.DefaultTourCandidate;
import model.tour_based.TourCandidate;
import model.tour_based.TourEstimator;
import model.trip_based.TripEstimator;
import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.population.Person;
import replanning.time_interpreter.TimeInterpreter;

import java.util.LinkedList;
import java.util.List;

public class CumulativeTourEstimator implements TourEstimator {

    private final TimeInterpreter.Factory timeInterpreterFactory;
    private final TripEstimator delegate;

    public CumulativeTourEstimator(TripEstimator delegate,
                                   TimeInterpreter.Factory timeInterpreterFactory) {
        this.delegate = delegate;
        this.timeInterpreterFactory = timeInterpreterFactory;
    }

    @Override
    public TourCandidate estimateTour(Person person, List<String> modes, List<DiscreteModeChoiceTrip> trips,
                                      List<TourCandidate> preceedingTours) {
        List<TripCandidate> tripCandidates = new LinkedList<>();
        double utility = 0.0;

        TimeInterpreter time = timeInterpreterFactory.createTimeInterpreter();
        time.setTime(trips.get(0).getDepartureTime());

        for (int i = 0; i < modes.size(); i++) {
            String mode = modes.get(i);
            DiscreteModeChoiceTrip trip = trips.get(i);

            if (i > 0) { // We're already at the end of the first origin activity
                time.addActivity(trip.getOriginActivity());
                trip.setDepartureTime(time.getCurrentTime());
            }

            TripCandidate tripCandidate = delegate.estimateTrip(person, mode, trip, tripCandidates);
            utility += tripCandidate.getUtility();
            time.addTime(tripCandidate.getDuration());

            tripCandidates.add(tripCandidate);
        }

        return new DefaultTourCandidate(utility, tripCandidates);
    }

}
