package components.utils;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.BasicLocation;
import org.matsim.api.core.v01.Id;

import java.util.List;

public class FirstActivityHomeFinder implements HomeFinder {
    @Override
    public Id<? extends BasicLocation> getHomeLocationId(List<DiscreteModeChoiceTrip> trips) {
        if (trips.size() > 0) {
            return LocationUtils.getLocationId(trips.get(0).getOriginActivity());
        } else {
            return null;
        }
    }
}