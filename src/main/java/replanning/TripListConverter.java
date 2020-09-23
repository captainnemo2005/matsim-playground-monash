package replanning;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.core.router.TripStructureUtils;

import java.util.ArrayList;
import java.util.List;

public final class TripListConverter {

    public List<DiscreteModeChoiceTrip> convert(Plan plan) {
        List<TripStructureUtils.Trip> initialTrips = TripStructureUtils.getTrips(plan);
        List<DiscreteModeChoiceTrip> trips = new ArrayList<>(initialTrips.size());

        int index = 0;

        for (TripStructureUtils.Trip initialTrip : initialTrips) {
            Activity originActivity = initialTrip.getOriginActivity();
            Activity destinationActivity = initialTrip.getDestinationActivity();

            Leg firstLeg = (Leg) initialTrip.getTripElements().get(0);
            String routingMode = TripStructureUtils.getRoutingMode(firstLeg);

            trips.add(new DiscreteModeChoiceTrip(originActivity, destinationActivity, routingMode,
                    initialTrip.getTripElements(), plan.getPerson().hashCode(), index, index));

            index++;
        }

        return trips;
    }
}
