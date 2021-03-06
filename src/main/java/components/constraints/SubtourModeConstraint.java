package components.constraints;

import components.utils.IndexUtils;
import components.utils.LocationUtils;
import model.DiscreteModeChoiceTrip;
import model.tour_based.TourConstraint;
import model.tour_based.TourConstraintFactory;
import org.matsim.api.core.v01.BasicLocation;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.population.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubtourModeConstraint extends AbstractTourConstraint {
        private final Collection<String> constrainedModes;
        private final List<Id<? extends BasicLocation>> originLocations;
        private final List<Id<? extends BasicLocation>> destinationLocations;

        public SubtourModeConstraint(Collection<String> constrainedModes, List<Id<? extends BasicLocation>> originLocations,
                                     List<Id<? extends BasicLocation>> destinationLocations) {
            this.constrainedModes = constrainedModes;
            this.originLocations = originLocations;
            this.destinationLocations = destinationLocations;
        }

        @Override
        public boolean validateBeforeEstimation(List<DiscreteModeChoiceTrip> tour, List<String> modes,
                                                List<List<String>> previousModes) {
            int tourLocationOffset = IndexUtils.getFirstTripIndex(previousModes);

            for (int index = 0; index < modes.size(); index++) {
                // We loop over all trips
                Id<? extends BasicLocation> startLocationId = originLocations.get(index + tourLocationOffset);

                for (int offset = 0; offset + index < modes.size(); offset++) {
                    // We loop over all following destinations

                    if (destinationLocations.get(offset + index + tourLocationOffset).equals(startLocationId)) {
                        // We found a destination that has the origin location. Now we need to check
                        // that all modes in between are of the same type.
                        String mode = modes.get(index + tourLocationOffset);

                        for (int testIndex = index + 1; testIndex <= index + offset; testIndex++) {
                            String testMode = modes.get(testIndex);

                            if (!mode.equals(testMode)
                                    && (constrainedModes.contains(testMode) || constrainedModes.contains(mode))) {
                                return false;
                            }
                        }

                        index += offset;
                        break;
                    }
                }
            }

            return true;
        }

        static public class Factory implements TourConstraintFactory {
            private final Collection<String> constrainedModes;

            public Factory(Collection<String> constrainedModes) {
                this.constrainedModes = constrainedModes;
            }

            @Override
            public TourConstraint createConstraint(Person person, List<DiscreteModeChoiceTrip> trips,
                                                   Collection<String> availableModes) {
                List<Id<? extends BasicLocation>> originLocations = new ArrayList<>(trips.size());
                List<Id<? extends BasicLocation>> destinationLocations = new ArrayList<>(trips.size());

                for (int index = 0; index < trips.size(); index++) {
                    originLocations.add(LocationUtils.getLocationId(trips.get(index).getOriginActivity()));
                    destinationLocations.add(LocationUtils.getLocationId(trips.get(index).getDestinationActivity()));
                }

                return new SubtourModeConstraint(constrainedModes, originLocations, destinationLocations);
            }
        }
}
