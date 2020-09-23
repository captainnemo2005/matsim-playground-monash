package modules.config;

import java.util.Arrays;
import java.util.Collection;

public class ActivityHomeFinderConfigGroup extends ComponentConfigGroup{
    private Collection<String> activityTypes = Arrays.asList("home");

    public static final String ACTIVITY_TYPES = "activityTypes";

    public ActivityHomeFinderConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }

}
