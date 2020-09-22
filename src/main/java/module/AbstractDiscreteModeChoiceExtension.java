package module;

import com.google.inject.multibindings.MapBinder;
import org.matsim.core.controler.AbstractModule;

public abstract class AbstractDiscreteModeChoiceExtension extends AbstractModule {

    protected MapBinder<String, TourEstimator> tourEstimatorBinder;
    protected MapBinder<String, TripEstimator> tripEstimatorBinder;

    protected MapBinder<String, TourConstraintFactory> tourConstraintFactoryBinder;
    protected MapBinder<String, TripConstraintFactory> tripConstraintFactoryBinder;

    protected MapBinder<String, UtilitySelectorFactory> selectorFactory;

    protected MapBinder<String, ModeAvailability> modeAvailabilityBinder;
    protected MapBinder<String, TourFinder> tourFinderBinder;
    protected MapBinder<String, HomeFinder> homeFinderBinder;

    protected MapBinder<String, TourFilter> tourFilterBinder;
    protected MapBinder<String, TripFilter> tripFilterBinder;

    @Override
    public void install() {
        tourEstimatorBinder = MapBinder.newMapBinder(binder(), String.class, TourEstimator.class);
        tripEstimatorBinder = MapBinder.newMapBinder(binder(), String.class, TripEstimator.class);

        tourFilterBinder = MapBinder.newMapBinder(binder(), String.class, TourFilter.class);
        tripFilterBinder = MapBinder.newMapBinder(binder(), String.class, TripFilter.class);

        tourConstraintFactoryBinder = MapBinder.newMapBinder(binder(), String.class, TourConstraintFactory.class);
        tripConstraintFactoryBinder = MapBinder.newMapBinder(binder(), String.class, TripConstraintFactory.class);

        selectorFactory = MapBinder.newMapBinder(binder(), String.class, UtilitySelectorFactory.class);

        modeAvailabilityBinder = MapBinder.newMapBinder(binder(), String.class, ModeAvailability.class);
        tourFinderBinder = MapBinder.newMapBinder(binder(), String.class, TourFinder.class);
        homeFinderBinder = MapBinder.newMapBinder(binder(), String.class, HomeFinder.class);

        installExtension();
    }

    abstract protected void installExtension();

}
