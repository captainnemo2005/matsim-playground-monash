package components.constraints;

import components.utils.HomeFinder;
import components.utils.LocationUtils;
import model.DiscreteModeChoiceTrip;
import model.tour_based.TourCandidate;
import model.tour_based.TourConstraint;
import model.tour_based.TourConstraintFactory;
import org.matsim.api.core.v01.BasicLocation;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public class VehicleTourConstraint implements TourConstraint {
    private final Collection<String> restrictedModes;
    private final Id<? extends BasicLocation> homeLocationId;

    public VehicleTourConstraint(Collection<String> restrictedModes, Id<? extends BasicLocation> homeLocationId) {
        this.restrictedModes = restrictedModes;
        this.homeLocationId = homeLocationId;
    }

    private int getFirstIndex(String mode, List<String> modes) {
        for (int i = 0; i < modes.size(); i++) {
            if (modes.get(i).equals(mode)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastIndex(String mode, List<String> modes) {
        for (int i = modes.size() - 1; i >= 0; i--) {
            if (modes.get(i).equals(mode)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean validateBeforeEstimation(List<DiscreteModeChoiceTrip> tour,
                                            List<String> modes,
                                            List<List<String>> previousModes) {
        for (String restrictedMode : restrictedModes) {
            if (modes.contains(restrictedMode)) {
                int firstIndex = getFirstIndex(restrictedMode, modes);
                int lastIndex = getLastIndex(restrictedMode, modes);

                if (homeLocationId != null) {
                    Id<? extends BasicLocation> startLocationId = LocationUtils
                            .getLocationId(tour.get(firstIndex).getOriginActivity());
                    Id<? extends BasicLocation> endLocationId = LocationUtils
                            .getLocationId(tour.get(lastIndex).getDestinationActivity());

                    if (!startLocationId.equals(homeLocationId)) {
                        return false;
                    }

                    if (!endLocationId.equals(homeLocationId)) {
                        return false;
                    }
                } else {
                    if (firstIndex > 0 || lastIndex < modes.size() - 1) {
                        return false;
                    }
                }

                Id<? extends BasicLocation> currentLocationId = LocationUtils
                        .getLocationId(tour.get(firstIndex).getDestinationActivity());

                for (int index = firstIndex + 1; index <= lastIndex; index++) {
                    if (modes.get(index).equals(restrictedMode)) {
                        DiscreteModeChoiceTrip trip = tour.get(index);

                        if (!currentLocationId.equals(LocationUtils.getLocationId(trip.getOriginActivity()))) {
                            return false;
                        }

                        currentLocationId = LocationUtils.getLocationId(trip.getDestinationActivity());
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean validateAfterEstimation(List<DiscreteModeChoiceTrip> tour,
                                           TourCandidate candidate,
                                           List<TourCandidate> previousCandidates) {
        return true;
    }

    public static class Factory implements TourConstraintFactory {
        private final Collection<String> restrictedModes;
        private final HomeFinder homeFinder;

        public Factory(Collection<String> restrictedModes, HomeFinder homeFinder) {
            this.restrictedModes = restrictedModes;
            this.homeFinder = homeFinder;
        }

        @Override
        public TourConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> planTrips,
                                               Collection<String> availableModes) {
            return new VehicleTourConstraint(restrictedModes, homeFinder.getHomeLocationId(planTrips));
        }
    }
}
