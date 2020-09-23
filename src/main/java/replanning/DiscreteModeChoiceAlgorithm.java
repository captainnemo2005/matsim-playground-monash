package replanning;

import com.google.inject.Provider;
import model.DiscreteModeChoiceModel;
import model.DiscreteModeChoiceTrip;
import model.trip_based.candidates.RoutedTripCandidate;
import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.api.core.v01.population.PopulationFactory;
import org.matsim.core.population.algorithms.PlanAlgorithm;
import org.matsim.core.router.TripRouter;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DiscreteModeChoiceAlgorithm implements PlanAlgorithm {
    private final Random random;
    private final DiscreteModeChoiceModel modeChoiceModel;
    private final TripListConverter tripListConverter;

    private final PopulationFactory populationFactory;

    public DiscreteModeChoiceAlgorithm(Random random,
                                       DiscreteModeChoiceModel modeChoiceModel,
                                       TripListConverter tripListConverter,
                                       PopulationFactory populationFactory) {
        this.random = random;
        this.modeChoiceModel = modeChoiceModel;
        this.tripListConverter = tripListConverter;
        this.populationFactory = populationFactory;
    }

    public void run(Plan plan) {
        // I) First build a list of DiscreteModeChoiceTrips
        List<DiscreteModeChoiceTrip> trips = tripListConverter.convert(plan);

        // II) Run mode choice

        try {
            // Perform mode choice and retrieve candidates
            List<TripCandidate> chosenCandidates = modeChoiceModel.chooseModes(plan.getPerson(), trips, random);

            for (int i = 0; i < trips.size(); i++) {
                DiscreteModeChoiceTrip trip = trips.get(i);
                TripCandidate candidate = chosenCandidates.get(i);

                List<? extends PlanElement> insertElements;

                if (candidate instanceof RoutedTripCandidate) {
                    RoutedTripCandidate routedCandidate = (RoutedTripCandidate) candidate;
                    insertElements = routedCandidate.getRoutedPlanElements();
                } else {
                    Leg insertLeg = populationFactory.createLeg(candidate.getMode());
                    insertElements = Collections.singletonList(insertLeg);
                }

                TripRouter.insertTrip(plan, trip.getOriginActivity(), insertElements, trip.getDestinationActivity());
            }
        } catch (DiscreteModeChoiceModel.NoFeasibleChoiceException e) {
            throw new IllegalStateException(e);
        }
    }
}
