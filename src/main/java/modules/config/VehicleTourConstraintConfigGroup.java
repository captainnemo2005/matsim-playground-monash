package modules.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class VehicleTourConstraintConfigGroup extends ComponentConfigGroup{
    private Collection<String> restrictedModes = new HashSet<>(Arrays.asList("car", "bike"));

    private static final String RESTRICTED_MODES = "restrictedModes";

    public VehicleTourConstraintConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }
}
