package modules;

import java.util.Arrays;
import java.util.Collection;

public class ModeAvailabilityModule extends AbstractDiscreteModeChoiceExtension {
    public static final String DEFAULT = "Default";
    public static final String CAR = "Car";

    public static final Collection<String> COMPONENTS = Arrays.asList(DEFAULT, CAR);

    @Override
    protected void installExtension() {
        bindModeAvailability(DEFAULT).to(DefaultModeAvailability.class);
        bindModeAvailability(CAR).to(CarModeAvailability.class);
    }
}
