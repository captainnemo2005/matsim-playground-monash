package components.utils;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.BasicLocation;
import org.matsim.api.core.v01.Id;

import java.util.Collection;
import java.util.List;

public class ActivityTypeHomeFinder implements HomeFinder {
    private final Collection<String> activityTypes;
    private final String singleActivityType;

    public ActivityTypeHomeFinder(Collection<String> activityTypes) {
        this.activityTypes = activityTypes;
        this.singleActivityType = activityTypes.size() == 1 ? activityTypes.iterator().next() : null;
    }

    @Override
    public Id<? extends BasicLocation> getHomeLocationId(List<DiscreteModeChoiceTrip> trips) {
        for (DiscreteModeChoiceTrip trip : trips) {
            if (singleActivityType == null) {
                if (activityTypes.contains(trip.getOriginActivity().getType())) {
                    return LocationUtils.getLocationId(trip.getOriginActivity());
                }

                if (activityTypes.contains(trip.getDestinationActivity().getType())) {
                    return LocationUtils.getLocationId(trip.getDestinationActivity());
                }
            } else {
                if (trip.getOriginActivity().getType().equals(singleActivityType)) {
                    return LocationUtils.getLocationId(trip.getOriginActivity());
                }

                if (trip.getDestinationActivity().getType().equals(singleActivityType)) {
                    return LocationUtils.getLocationId(trip.getDestinationActivity());
                }
            }
        }

        return null;
    }
}
