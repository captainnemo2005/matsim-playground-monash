package constraints;

import components.constraints.VehicleTourConstraint;
import components.utils.HomeFinder;
import model.DiscreteModeChoiceTrip;
import model.tour_based.TourConstraint;
import model.tour_based.TourConstraintFactory;
import org.junit.Test;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;
import org.matsim.facilities.ActivityFacility;
import test_utils.PlanBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VehicleTourConstraintTest {

    @Test
    public void testWithHome(){

        HomeFinder  homeFinder = (List<DiscreteModeChoiceTrip> trips)-> Id.create("A", ActivityFacility.class);

        Collection<String> availableModes = Arrays.asList("car", "walk");
        Collection<String> restrictedModes = Arrays.asList("car");

        TourConstraintFactory constraintFactory = new VehicleTourConstraint.Factory(restrictedModes,homeFinder);

        PlanBuilder planBuilder = new PlanBuilder().addActivityWithFacilityId("home", "A") //
                .addLeg() //
                .addActivityWithFacilityId("other", "B") //
                .addLeg() //
                .addActivityWithFacilityId("other", "C") //
                .addLeg() //
                .addActivityWithFacilityId("other", "A") //
                .addLeg() //
                .addActivityWithFacilityId("other", "C") //
                .addLeg() //
                .addActivityWithFacilityId("home", "A");

        List<DiscreteModeChoiceTrip> trips = planBuilder.buildDiscreteModeChoiceTrips();
        Person person = planBuilder.buildPlan().getPerson();

        TourConstraint constraint = constraintFactory.createConstraint(person, trips, availableModes);
        List<String> modes;


        // test for continuaty
        modes = Arrays.asList("car", "car", "car", "car", "car");
        assertTrue(constraint.validateBeforeEstimation(trips, modes, Arrays.asList()));

        modes = Arrays.asList("car", "walk", "car", "walk", "walk");
        assertFalse(constraint.validateBeforeEstimation(trips, modes, Arrays.asList()));
    }
}
