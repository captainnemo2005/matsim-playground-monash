package module.config;

import java.util.Collection;
import java.util.HashSet;

public class LinkAttributeConstraintConfigGroup extends ComponentConfigGroup{
    private Requirement requirement = Requirement.BOTH;
    private String attributeName = null;
    private String attributeValue = null;
    private Collection<String> constrainedModes = new HashSet<>();

    public static final String REQUIREMENT = "requirement";
    public static final String ATTRIBUTE_NAME = "attributeName";
    public static final String ATTRIBUTE_VALUE = "attributeValue";
    public static final String CONSTRAINED_MODES = "constrainedModes";

    public LinkAttributeConstraintConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }

}
