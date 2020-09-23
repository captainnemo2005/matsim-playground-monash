package modules;

import java.util.Arrays;
import java.util.Collection;

public class ConstraintModule extends AbstractDiscreteModeChoiceExtension {
    public final static String FROM_TRIP_BASED = "FromTripBased";
    public final static String VEHICLE_CONTINUITY = "VehicleContinuity";
    public final static String SUBTOUR_MODE = "SubtourMode";

    public final static String SHAPE_FILE = "ShapeFile";
    public final static String LINK_ATTRIBUTE = "LinkAttribute";
    public final static String TRANSIT_WALK = "TransitWalk";

    public final static Collection<String> TRIP_COMPONENTS = Arrays.asList(VEHICLE_CONTINUITY, SHAPE_FILE,
            LINK_ATTRIBUTE, TRANSIT_WALK);

    public final static Collection<String> TOUR_COMPONENTS = Arrays.asList(FROM_TRIP_BASED, VEHICLE_CONTINUITY,
            SUBTOUR_MODE);

    @Override
    public void installExtension() {
        bindTourConstraintFactory(FROM_TRIP_BASED).to(TourFromTripConstraintFactory.class);
        bindTourConstraintFactory(VEHICLE_CONTINUITY).to(VehicleTourConstraint.Factory.class);
        bindTourConstraintFactory(SUBTOUR_MODE).to(SubtourModeConstraint.Factory.class);

        bindTripConstraintFactory(SHAPE_FILE).to(ShapeFileConstraint.Factory.class);
        bindTripConstraintFactory(LINK_ATTRIBUTE).to(LinkAttributeConstraint.Factory.class);
        bindTripConstraintFactory(TRANSIT_WALK).to(TransitWalkConstraint.Factory.class);
        bindTripConstraintFactory(VEHICLE_CONTINUITY).to(VehicleTripConstraint.Factory.class);
    }
}
