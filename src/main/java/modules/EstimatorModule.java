package modules;

import components.estimators.MATSimTripScoringEstimator;
import org.matsim.pt.config.TransitConfigGroup;

import java.util.Arrays;
import java.util.Collection;

public class EstimatorModule extends AbstractDiscreteModeChoiceExtension{

    public static final String MATSIM_TRIP_SCORING = "MATSimTripScoring";
    public static final String MATSIM_DAY_SCORING = "MATSimDayScoring";
    public static final String CUMULATIVE = "Cumulative";
    public static final String UNIFORM = "Uniform";

    public static final Collection<String> TRIP_COMPONENTS = Arrays.asList(MATSIM_TRIP_SCORING, UNIFORM);
    public static final Collection<String> TOUR_COMPONENTS = Arrays.asList(MATSIM_DAY_SCORING, CUMULATIVE, UNIFORM);

    @Override
    public void installExtension() {
        bindTripEstimator(MATSIM_TRIP_SCORING).to(MATSimTripScoringEstimator.class);
        bindTripEstimator(UNIFORM).to(UniformTripEstimator.class);

        bindTourEstimator(MATSIM_DAY_SCORING).to(MATSimDayScoringEstimator.class);
        bindTourEstimator(CUMULATIVE).to(CumulativeTourEstimator.class);
        bindTourEstimator(UNIFORM).to(UniformTourEstimator.class);

        TransitConfigGroup transitConfigGroup = getConfig().transit();

        if (transitConfigGroup.isUseTransit()) {
            install(new ScheduleWaitingTimeEstimatorModule());
        } else {
            bind(PTWaitingTimeEstimator.class).to(NullWaitingTimeEstimator.class);
        }
    }
}
