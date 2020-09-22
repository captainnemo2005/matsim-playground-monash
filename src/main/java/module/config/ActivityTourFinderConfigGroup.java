package module.config;

import java.util.Arrays;
import java.util.Collection;

public class ActivityTourFinderConfigGroup extends ComponentConfigGroup{

    private Collection<String> activityTypes = Arrays.asList("home");

    public static final String ACTIVITY_TYPES = "activityTypes";

    public ActivityTourFinderConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }

}
