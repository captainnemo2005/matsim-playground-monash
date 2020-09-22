package module;

import java.util.Arrays;
import java.util.Collection;

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
}
