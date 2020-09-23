package components.tour_finder;

import components.utils.HomeFinder;
import components.utils.LocationUtils;
import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.BasicLocation;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Activity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeTourFinder extends AbstractTourFinder {
    private final HomeFinder homeFinder;

    public HomeTourFinder(HomeFinder homeFinder) {
        this.homeFinder = homeFinder;
    }

    @Override
    protected Set<Activity> findActivities(List<DiscreteModeChoiceTrip> trips) {
        Set<Activity> relevantActivities = new HashSet<>();

        Id<? extends BasicLocation> homeLocationId = homeFinder.getHomeLocationId(trips);

        for (DiscreteModeChoiceTrip trip : trips) {
            if (LocationUtils.getLocationId(trip.getDestinationActivity()).equals(homeLocationId)) {
                relevantActivities.add(trip.getDestinationActivity());
            }

            if (LocationUtils.getLocationId(trip.getDestinationActivity()).equals(homeLocationId)) {
                relevantActivities.add(trip.getDestinationActivity());
            }
        }

        return relevantActivities;
    }
}
