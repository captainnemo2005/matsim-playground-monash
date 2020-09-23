package model.estimation;

import model.DiscreteModeChoiceTrip;
import model.trip_based.TripEstimator;
import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachedTripEstimator implements TripEstimator {
    final private Map<String, Map<DiscreteModeChoiceTrip, TripCandidate>> cache = new HashMap<>();
    final private TripEstimator delegate;

    public CachedTripEstimator(TripEstimator delegate,
                               Collection<String> cachedModes) {
        this.delegate = delegate;

        for (String mode : cachedModes) {
            cache.put(mode, new HashMap<>());
        }
    }

    @Override
    public TripCandidate estimateTrip(Person person,
                                      String mode,
                                      DiscreteModeChoiceTrip trip,
                                      List<TripCandidate> preceedingTrips) {
        Map<DiscreteModeChoiceTrip, TripCandidate> modeCache = cache.get(mode);

        if (modeCache != null) {
            TripCandidate candidate = modeCache.get(trip);

            if (candidate == null) {
                candidate = delegate.estimateTrip(person, mode, trip, preceedingTrips);
                modeCache.put(trip, candidate);
            }

            return candidate;
        } else {
            return delegate.estimateTrip(person, mode, trip, preceedingTrips);
        }
    }
}
