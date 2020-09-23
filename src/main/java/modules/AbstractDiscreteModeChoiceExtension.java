package modules;

import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.multibindings.MapBinder;
import model.trip_based.TripConstraintFactory;
import model.trip_based.TripEstimator;
import modules.model_availability.ModeAvailability;
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
    protected final LinkedBindingBuilder<TourEstimator> bindTourEstimator(String name) {
        return tourEstimatorBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<TripEstimator> bindTripEstimator(String name) {
        return tripEstimatorBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<TourFilter> bindTourFilter(String name) {
        return tourFilterBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<TripFilter> bindTripFilter(String name) {
        return tripFilterBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<TourConstraintFactory> bindTourConstraintFactory(String name) {
        return tourConstraintFactoryBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<TripConstraintFactory> bindTripConstraintFactory(String name) {
        return tripConstraintFactoryBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<UtilitySelectorFactory> bindSelectorFactory(String name) {
        return selectorFactory.addBinding(name);
    }

    protected final LinkedBindingBuilder<ModeAvailability> bindModeAvailability(String name) {
        return modeAvailabilityBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<TourFinder> bindTourFinder(String name) {
        return tourFinderBinder.addBinding(name);
    }

    protected final LinkedBindingBuilder<HomeFinder> bindHomeFinder(String name) {
        return homeFinderBinder.addBinding(name);
    }

    abstract protected void installExtension();

}
