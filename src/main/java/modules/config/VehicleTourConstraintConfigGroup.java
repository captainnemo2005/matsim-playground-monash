package modules.config;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleTourConstraintConfigGroup extends ComponentConfigGroup{
    private Collection<String> restrictedModes = new HashSet<>(Arrays.asList("car", "bike"));

    private static final String RESTRICTED_MODES = "restrictedModes";

    public VehicleTourConstraintConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }

    @Override
    public Map<String, String> getComments() {
        Map<String, String> comments = new HashMap<>();

        comments.put(RESTRICTED_MODES,
                "Defines which modes must fulfill continuity constraints (can only be used where they have been brough to before)");

        return comments;
    }

    public void setRestrictedModes(Collection<String> restrictedModes) {
        this.restrictedModes = new HashSet<>(restrictedModes);
    }

    @StringSetter(RESTRICTED_MODES)
    public void setRestrictedModesAsString(String restrictedModes) {
        this.restrictedModes = new HashSet<>(
                Arrays.asList(restrictedModes.split(",")).stream().map(String::trim).collect(Collectors.toSet()));
    }

    public Collection<String> getRestrictedModes() {
        return restrictedModes;
    }

    @StringGetter(RESTRICTED_MODES)
    public String getRestrictedModesAsString() {
        return String.join(", ", restrictedModes);
    }
}
