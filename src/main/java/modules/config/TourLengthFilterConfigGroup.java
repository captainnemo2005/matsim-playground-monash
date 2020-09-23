package modules.config;

public class TourLengthFilterConfigGroup  extends ComponentConfigGroup{
    private int maximumLength = 10;

    public static final String MAXIMUM_LENGTH = "maximumLength";

    public TourLengthFilterConfigGroup(String componentType, String componentName) {
        super(componentType, componentName);
    }
}
