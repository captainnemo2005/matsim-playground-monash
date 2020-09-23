package modules;

import java.util.Arrays;
import java.util.Collection;

public class HomeFinderModule extends AbstractDiscreteModeChoiceExtension {
    public static final String FIRST_ACTIVITY = "FirstActivity";
    public static final String ACTIVITY_BASED = "ActivityBased";

    public static final Collection<String> COMPONENTS = Arrays.asList(FIRST_ACTIVITY, ACTIVITY_BASED);

    @Override
    public void installExtension() {
        bindHomeFinder(FIRST_ACTIVITY).to(FirstActivityHomeFinder.class);
        bindHomeFinder(ACTIVITY_BASED).to(ActivityTypeHomeFinder.class);
    }
}
