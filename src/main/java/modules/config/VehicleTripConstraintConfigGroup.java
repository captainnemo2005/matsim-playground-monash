package modules.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class VehicleTripConstraintConfigGroup extends ComponentConfigGroup {
    private Collection<String> restrictedModes = new HashSet<>(Arrays.asList("car", "bike"));
    private boolean isAdvanced = true;

    private static final String RESTRICTED_MODES = "restrictedModes";
    private static final String IS_ADVANCED = "isAdvanced";

    public VehicleTripConstraintConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }

}
