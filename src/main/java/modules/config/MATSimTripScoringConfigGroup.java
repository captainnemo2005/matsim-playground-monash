package modules.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class MATSimTripScoringConfigGroup extends ComponentConfigGroup{
    private Collection<String> ptLegModes = new HashSet<>(Arrays.asList("pt"));

    public final static String PT_LEG_MODES = "ptLegModes";

    public MATSimTripScoringConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }
}
