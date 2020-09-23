package modules.config;

import components.constraints.LinkAttributeConstraint;

import java.util.Collection;
import java.util.HashSet;

public class ShapeFileConstraintConfigGroup extends ComponentConfigGroup{
    private LinkAttributeConstraint.Requirement requirement = LinkAttributeConstraint.Requirement.BOTH;
    private String path = null;
    private Collection<String> constrainedModes = new HashSet<>();

    public final static String REQUIREMENT = "requirement";
    public final static String PATH = "path";
    public final static String CONSTRAINED_MODES = "constrainedModes";

    public ShapeFileConstraintConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }

}
