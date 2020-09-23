package components.tour_finder;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Activity;

import java.util.*;

public abstract class AbstractTourFinder implements TourFinder {
    abstract protected Set<Activity> findActivities(List<DiscreteModeChoiceTrip> trips);

    @Override
    public List<List<DiscreteModeChoiceTrip>> findTours(List<DiscreteModeChoiceTrip> trips) {
        Set<Activity> relevantActivities = findActivities(trips);

        List<List<DiscreteModeChoiceTrip>> tours = new LinkedList<>();
        List<DiscreteModeChoiceTrip> currentTour = new LinkedList<>();

        for (DiscreteModeChoiceTrip trip : trips) {
            currentTour.add(trip);

            if (relevantActivities.contains(trip.getDestinationActivity())) {
                tours.add(new ArrayList<>(currentTour));
                currentTour.clear();
            }
        }

        if (currentTour.size() > 0) {
            tours.add(currentTour);
        }

        int count = tours.stream().mapToInt(Collection::size).sum();
        if (count != trips.size()) {
            throw new IllegalStateException("Error while finding tours. This should never happen.");
        }

        return tours;
    }
}
