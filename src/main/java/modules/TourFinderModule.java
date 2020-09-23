package modules;

import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.tour_finder.ActivityTourFinder;
import components.tour_finder.HomeTourFinder;
import components.tour_finder.TourFinder;
import components.utils.HomeFinder;
import model.tour_based.PlanTourFinder;
import modules.config.ActivityTourFinderConfigGroup;
import modules.config.DiscreteModeChoiceConfigGroup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class TourFinderModule extends AbstractDiscreteModeChoiceExtension {
    public static final String PLAN_BASED = "PlanBased";
    public static final String ACTIVITY_BASED = "ActivityBased";
    public static final String HOME_BASED = "HomeBased";

    public static final Collection<String> COMPONENTS = Arrays.asList(PLAN_BASED, ACTIVITY_BASED, HOME_BASED);

    @Override
    protected void installExtension() {
        bindTourFinder(PLAN_BASED).to(PlanTourFinder.class);
        bindTourFinder(ACTIVITY_BASED).to(ActivityTourFinder.class);
        bindTourFinder(HOME_BASED).to(HomeTourFinder.class);
    }

    @Provides
    @Singleton
    public PlanTourFinder providePlanTourFinder() {
        return new PlanTourFinder();
    }

    @Provides
    @Singleton
    public ActivityTourFinder provideActivityBasedTourFinder(DiscreteModeChoiceConfigGroup dmcConfig) {
        ActivityTourFinderConfigGroup config = dmcConfig.getActivityTourFinderConfigGroup();
        return new ActivityTourFinder(config.getActivityTypes());
    }

    @Provides
    @Singleton
    public HomeTourFinder provideHomeTourFinder(HomeFinder homeFinder) {
        return new HomeTourFinder(homeFinder);
    }

    @Provides
    @Singleton
    public TourFinder provideTourFinder(DiscreteModeChoiceConfigGroup dmcConfig,
                                        Map<String, Provider<TourFinder>> components) {
        Provider<TourFinder> provider = components.get(dmcConfig.getTourFinder());

        if (provider != null) {
            return provider.get();
        } else {
            throw new IllegalStateException(
                    String.format("There is no TourFinder component called '%s',", dmcConfig.getTourFinder()));
        }
    }
}
