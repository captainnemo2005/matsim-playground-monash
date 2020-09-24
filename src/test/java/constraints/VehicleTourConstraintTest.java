package constraints;

import components.constraints.VehicleTourConstraint;
import components.utils.HomeFinder;
import model.DiscreteModeChoiceTrip;
import model.tour_based.TourConstraintFactory;
import org.junit.Test;
import org.matsim.api.core.v01.Id;
import org.matsim.facilities.ActivityFacility;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class VehicleTourConstraintTest {

    @Test
    public void testWithHome(){

        HomeFinder  homeFinder = (List<DiscreteModeChoiceTrip> trips)-> Id.create("A", ActivityFacility.class);

        Collection<String> availableModes = Arrays.asList("car", "walk");
        Collection<String> restrictedModes = Arrays.asList("car");

        TourConstraintFactory constraintFactory = new VehicleTourConstraint.Factory(restrictedModes,homeFinder);


    }
}
