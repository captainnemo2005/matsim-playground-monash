package modules.config;

import components.constraints.LinkAttributeConstraint;

import java.util.*;
import java.util.stream.Collectors;

public class LinkAttributeConstraintConfigGroup extends ComponentConfigGroup{
    private LinkAttributeConstraint.Requirement requirement = LinkAttributeConstraint.Requirement.BOTH;
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

    @Override
    public Map<String, String> getComments() {
        Map<String, String> comments = new HashMap<>();

        String options = Arrays.asList(LinkAttributeConstraint.Requirement.values()).stream().map(String::valueOf)
                .collect(Collectors.joining(", "));
        comments.put(REQUIREMENT,
                "Defines the criterion on when a trip with the constrained mode will be allowed: " + options);
        comments.put(ATTRIBUTE_NAME, "Link attribute that will be considered for feasibility of the trip.");
        comments.put(ATTRIBUTE_VALUE, "Value that the link attributes should equal.");
        comments.put(CONSTRAINED_MODES, "Modes for which the constraint will be considered.");

        return comments;
    }

    @StringSetter(REQUIREMENT)
    public void setRequirement(LinkAttributeConstraint.Requirement requirement) {
        this.requirement = requirement;
    }

    @StringGetter(REQUIREMENT)
    public LinkAttributeConstraint.Requirement getRequirement() {
        return requirement;
    }

    @StringSetter(ATTRIBUTE_NAME)
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @StringGetter(ATTRIBUTE_NAME)
    public String getAttributeName() {
        return attributeName;
    }

    @StringSetter(ATTRIBUTE_VALUE)
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    @StringGetter(ATTRIBUTE_VALUE)
    public String getAttributeValue() {
        return attributeValue;
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
