package components.utils;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.BasicLocation;
import org.matsim.api.core.v01.Id;

import java.util.List;

public interface HomeFinder {
    Id<? extends BasicLocation> getHomeLocationId(List<DiscreteModeChoiceTrip> trips);
}
