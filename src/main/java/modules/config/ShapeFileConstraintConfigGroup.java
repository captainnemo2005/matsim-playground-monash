package modules.config;

import components.constraints.LinkAttributeConstraint;
import components.constraints.ShapeFileConstraint;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ShapeFileConstraintConfigGroup extends ComponentConfigGroup{
    private ShapeFileConstraint.Requirement requirement = ShapeFileConstraint.Requirement.BOTH;
    private String path = null;
    private Collection<String> constrainedModes = new HashSet<>();

    public final static String REQUIREMENT = "requirement";
    public final static String PATH = "path";
    public final static String CONSTRAINED_MODES = "constrainedModes";

    public ShapeFileConstraintConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }

    @StringSetter(REQUIREMENT)
    public void setRequirement(ShapeFileConstraint.Requirement requirement) {
        this.requirement = requirement;
    }

    @StringGetter(REQUIREMENT)
    public ShapeFileConstraint.Requirement getRequirement() {
        return requirement;
    }

    @StringSetter(PATH)
    public void setPath(String path) {
        this.path = path;
    }

    @StringGetter(PATH)
    public String getPath() {
        return path;
    }

    public void setConstrainedModes(Collection<String> constrainedModes) {
        this.constrainedModes = new HashSet<>(constrainedModes);
    }

    public Collection<String> getConstrainedModes() {
        return constrainedModes;
    }

    @StringSetter(CONSTRAINED_MODES)
    public void setConstrainedModesAsString(String constrainedModes) {
        this.constrainedModes = Arrays.asList(constrainedModes.split(",")).stream().map(String::trim)
                .collect(Collectors.toSet());
    }

    @StringGetter(CONSTRAINED_MODES)
    public String getConstrainedModesAsString() {
        return String.join(", ", constrainedModes);
    }
}
