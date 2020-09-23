package modules.config;

public class MultinomialLogitSelectorConfigGroup extends ComponentConfigGroup{
    private double minimumUtility = -700.0;
    private double maximumUtility = 700.0;
    private boolean considerMinimumUtility = false;

    public static final String MINIMUM_UTILITY = "minimumUtility";
    public static final String MAXIMUM_UTILITY = "maximumUtility";
    public static final String CONSIDER_MINIMUM_UTILITY = "considerMinimumUtility";

    public MultinomialLogitSelectorConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }
}
