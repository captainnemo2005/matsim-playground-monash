package modules;

import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.estimators.*;
import components.utils.NullWaitingTimeEstimator;
import components.utils.PTWaitingTimeEstimator;
import components.utils.ScheduleWaitingTimeEstimatorModule;
import model.estimation.CachedTripEstimator;
import model.tour_based.TourEstimator;
import model.trip_based.TripEstimator;
import modules.config.DiscreteModeChoiceConfigGroup;
import modules.config.MATSimTripScoringConfigGroup;
import org.matsim.core.router.TripRouter;
import org.matsim.core.scoring.functions.ScoringParametersForPerson;
import org.matsim.facilities.ActivityFacilities;
import org.matsim.pt.config.TransitConfigGroup;
import replanning.time_interpreter.TimeInterpreter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

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


    @Provides
    public TourEstimator provideTourEstimator(DiscreteModeChoiceConfigGroup dmcConfig,
                                              Map<String, Provider<TourEstimator>> components) {
        Provider<TourEstimator> provider = components.get(dmcConfig.getTourEstimator());

        if (provider != null) {
            return provider.get();
        } else {
            throw new IllegalStateException(
                    String.format("There is no TourEstimator component called '%s',", dmcConfig.getTourEstimator()));
        }
    }

    @Provides
    public TripEstimator provideTripEstimator(DiscreteModeChoiceConfigGroup dmcConfig,
                                              Map<String, Provider<TripEstimator>> components) {
        Provider<TripEstimator> provider = components.get(dmcConfig.getTripEstimator());

        if (provider != null) {
            return new CachedTripEstimator(provider.get(), dmcConfig.getCachedModes());
        } else {
            throw new IllegalStateException(
                    String.format("There is no TripEstimator component called '%s',", dmcConfig.getTripEstimator()));
        }
    }

    @Provides
    @Singleton
    public UniformTripEstimator provideNullTripEstimator(TimeInterpreter.Factory timeInterpreterFactory) {
        return new UniformTripEstimator(timeInterpreterFactory);
    }

    @Provides
    @Singleton
    public UniformTourEstimator proideNullTourEstimator(TimeInterpreter.Factory timeInterpreterFactory) {
        return new UniformTourEstimator(timeInterpreterFactory);
    }

    @Provides
    @Singleton
    public NullWaitingTimeEstimator provideNullWaitingTimeEstimator() {
        return new NullWaitingTimeEstimator();
    }

    @Provides
    public MATSimTripScoringEstimator provideMATSimTripScoringEstimator(ActivityFacilities facilities,
                                                                        TripRouter tripRouter, PTWaitingTimeEstimator waitingTimeEstimator,
                                                                        ScoringParametersForPerson scoringParametersForPerson,
                                                                        DiscreteModeChoiceConfigGroup dmcConfig,
                                                                        TimeInterpreter.Factory timeInterpreterFactory) {
        MATSimTripScoringConfigGroup scoringConfig = dmcConfig.getMATSimTripScoringConfigGroup();
        return new MATSimTripScoringEstimator(facilities, tripRouter, waitingTimeEstimator, scoringParametersForPerson,
                timeInterpreterFactory, scoringConfig.getPtLegModes());
    }

    @Provides
    public MATSimDayScoringEstimator provideMATSimDayScoringEstimator(MATSimTripScoringEstimator tripEstimator,
                                                                      ScoringParametersForPerson scoringParametersForPerson, DiscreteModeChoiceConfigGroup dmcConfig,
                                                                      TimeInterpreter.Factory timeInterpreterFactory) {
        return new MATSimDayScoringEstimator(new CachedTripEstimator(tripEstimator, dmcConfig.getCachedModes()),
                scoringParametersForPerson, timeInterpreterFactory);
    }

    @Provides
    public CumulativeTourEstimator provideCumulativeTourEstimator(TripEstimator tripEstimator,
                                                                  TimeInterpreter.Factory timeInterpreterFactory) {
        return new CumulativeTourEstimator(tripEstimator, timeInterpreterFactory);
    }
}
