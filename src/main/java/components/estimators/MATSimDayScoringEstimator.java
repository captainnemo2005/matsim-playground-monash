package components.estimators;

import model.DiscreteModeChoiceTrip;
import model.tour_based.DefaultTourCandidate;
import model.tour_based.TourCandidate;
import model.tour_based.TourEstimator;
import model.trip_based.TripEstimator;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.scoring.functions.ModeUtilityParameters;
import org.matsim.core.scoring.functions.ScoringParameters;
import org.matsim.core.scoring.functions.ScoringParametersForPerson;
import replanning.time_interpreter.TimeInterpreter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MATSimDayScoringEstimator implements TourEstimator {
    private final TourEstimator delegate;
    private final ScoringParametersForPerson scoringParametersForPerson;

    public MATSimDayScoringEstimator(TripEstimator tripEstimator, ScoringParametersForPerson scoringParametersForPerson,
                                     TimeInterpreter.Factory timeInterpreterFactory) {
        this.delegate = new CumulativeTourEstimator(tripEstimator, timeInterpreterFactory);
        this.scoringParametersForPerson = scoringParametersForPerson;
    }

    @Override
    public TourCandidate estimateTour(Person person, List<String> modes, List<DiscreteModeChoiceTrip> trips,
                                      List<TourCandidate> previousTours) {
        ScoringParameters parameters = scoringParametersForPerson.getScoringParameters(person);

        // First, calculate utility from trips. They're simply summed up.
        TourCandidate candidate = delegate.estimateTour(person, modes, trips, previousTours);
        double utility = candidate.getUtility();

        // Add daily constants for trips
        Set<String> uniqueModes = new HashSet<>(modes);

        for (String uniqueMode : uniqueModes) {
            ModeUtilityParameters modeParams = parameters.modeParams.get(uniqueMode);
            utility += modeParams.dailyUtilityConstant;
            utility += parameters.marginalUtilityOfMoney * modeParams.dailyMoneyConstant;
        }

        return new DefaultTourCandidate(utility, candidate.getTripCandidates());
    }
}
