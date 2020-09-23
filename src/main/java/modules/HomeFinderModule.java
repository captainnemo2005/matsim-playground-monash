package modules;

import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.utils.ActivityTypeHomeFinder;
import components.utils.FirstActivityHomeFinder;
import components.utils.HomeFinder;
import modules.config.ActivityHomeFinderConfigGroup;
import modules.config.DiscreteModeChoiceConfigGroup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class HomeFinderModule extends AbstractDiscreteModeChoiceExtension {
    public static final String FIRST_ACTIVITY = "FirstActivity";
    public static final String ACTIVITY_BASED = "ActivityBased";

    public static final Collection<String> COMPONENTS = Arrays.asList(FIRST_ACTIVITY, ACTIVITY_BASED);

    @Override
    public void installExtension() {
        bindHomeFinder(FIRST_ACTIVITY).to(FirstActivityHomeFinder.class);
        bindHomeFinder(ACTIVITY_BASED).to(ActivityTypeHomeFinder.class);
    }

    @Provides
    @Singleton
    public FirstActivityHomeFinder provideFirstActivityHomeFinder() {
        return new FirstActivityHomeFinder();
    }

    @Provides
    @Singleton
    public ActivityTypeHomeFinder provideActivityTypeHomeFinder(DiscreteModeChoiceConfigGroup dmcConfig) {
        ActivityHomeFinderConfigGroup config = dmcConfig.getActivityHomeFinderConfigGroup();
        return new ActivityTypeHomeFinder(config.getActivityTypes());
    }

    @Provides
    @Singleton
    public HomeFinder provideHomeFinder(DiscreteModeChoiceConfigGroup dmcConfig,
                                        Map<String, Provider<HomeFinder>> components) {
        Provider<HomeFinder> provider = components.get(dmcConfig.getHomeFinder());

        if (provider != null) {
            return provider.get();
        } else {
            throw new IllegalStateException(
                    String.format("There is no HomeFinder component called '%s',", dmcConfig.getHomeFinder()));
        }
    }
}
