package modules.config;

import java.util.Collection;
import java.util.HashSet;

public class SubtourModeConstraintConfigGroup extends ComponentConfigGroup {
    private Collection<String> constrainedModes = new HashSet<>();

    public final static String CONSTRAINED_MODES = "constrainedModes";

    public SubtourModeConstraintConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }


}
