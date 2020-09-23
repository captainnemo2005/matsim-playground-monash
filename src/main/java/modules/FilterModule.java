package modules;

import java.util.Arrays;
import java.util.Collection;

public class FilterModule extends AbstractDiscreteModeChoiceExtension{
    public final static String TOUR_LENGTH = "TourLength";

    public final static Collection<String> TRIP_COMPONENTS = Arrays.asList();

    public final static Collection<String> TOUR_COMPONENTS = Arrays.asList(TOUR_LENGTH);

    @Override
    public void installExtension() {
        bindTourFilter(TOUR_LENGTH).to(TourLengthFilter.class);
    }
}
