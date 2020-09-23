package modules.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class ModeAvailabilityConfigGroup extends ComponentConfigGroup{
    private Collection<String> availableModes = new HashSet<>(Arrays.asList("car", "bike", "pt", "walk"));

    public static final String AVAILABLE_MODES = "availableModes";

    public ModeAvailabilityConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }
}
