package modules;

import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.tour_finder.TourFinder;
import model.DiscreteModeChoiceModel;
import model.filters.CompositeTourFilter;
import model.filters.CompositeTripFilter;
import model.mode_chain.DefaultModeChainGenerator;
import model.mode_chain.ModeChainGeneratorFactory;
import model.tour_based.*;
import model.trip_based.TripBasedModel;
import model.trip_based.TripConstraintFactory;
import model.trip_based.TripEstimator;
import model.utilities.UtilitySelectorFactory;
import modules.config.DiscreteModeChoiceConfigGroup;
import modules.model_availability.ModeAvailability;
import org.matsim.core.controler.AbstractModule;
import replanning.TripListConverter;
import replanning.time_interpreter.TimeInterpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ModelModule extends AbstractModule {

    @Override
    public void install() {
        install(new ModeAvailabilityModule());
        install(new EstimatorModule());
        install(new TourFinderModule());
        install(new SelectorModule());
        install(new ConstraintModule());
        install(new FilterModule());
        install(new HomeFinderModule());

        bind(ModeChainGeneratorFactory.class).to(DefaultModeChainGenerator.Factory.class);

    }

    public enum ModelType {
        Trip, Tour
    }

    @Provides
    public DiscreteModeChoiceModel provideDiscreteModeChoiceModel(DiscreteModeChoiceConfigGroup dmcConfig,
                                                                  Provider<TourBasedModel> tourBasedProvider,
                                                                  Provider<TripBasedModel> tripBasedProvider) {
        switch (dmcConfig.getModelType()) {
            case Tour:
                return tourBasedProvider.get();
            case Trip:
                return tripBasedProvider.get();
            default:
                throw new IllegalStateException();
        }
    }

    @Provides
    public TourBasedModel provideTourBasedModel(ModeAvailability modeAvailability,
                                                TourFilter tourFilter,
                                                TourEstimator tourEstimator,
                                                TourConstraintFactory tourConstraintFactory,
                                                TourFinder tourFinder,
                                                UtilitySelectorFactory selectorFactory,
                                                ModeChainGeneratorFactory modeChainGeneratorFactory,
                                                DiscreteModeChoiceConfigGroup dmcConfig,
                                                TimeInterpreter.Factory timeInterpreterFactory) {
        return new TourBasedModel(tourEstimator, modeAvailability, tourConstraintFactory, tourFinder, tourFilter,
                selectorFactory, modeChainGeneratorFactory, dmcConfig.getFallbackBehaviour(), timeInterpreterFactory);
    }

    @Provides
    public TripBasedModel provideTripBasedModel(TripEstimator estimator,
                                                TripFilter tripFilter,
                                                ModeAvailability modeAvailability,
                                                TripConstraintFactory constraintFactory,
                                                UtilitySelectorFactory selectorFactory,
                                                DiscreteModeChoiceConfigGroup dmcConfig,
                                                TimeInterpreter.Factory timeInterpreterFactory)
    {
        return new TripBasedModel(estimator, tripFilter, modeAvailability, constraintFactory, selectorFactory,
                dmcConfig.getFallbackBehaviour(), timeInterpreterFactory);
    }

    @Provides
    @Singleton
    public DefaultModeChainGenerator.Factory provideDefaultModeChainGeneratorFactory() {
        return new DefaultModeChainGenerator.Factory();
    }

    @Provides
    public TripFilter provideTripFilter(DiscreteModeChoiceConfigGroup dmcConfig,
                                        Map<String, Provider<TripFilter>> providers) {
        Collection<String> names = dmcConfig.getTripFilters();
        Collection<TripFilter> filters = new ArrayList<>(names.size());

        for (String name : names) {
            if (!providers.containsKey(name)) {
                throw new IllegalStateException(String.format("TripFilter '%s' does not exist.", name));
            } else {
                filters.add(providers.get(name).get());
            }
        }

        return new CompositeTripFilter(filters);
    }

    @Provides
    public TourFilter provideTourFilter(DiscreteModeChoiceConfigGroup dmcConfig,
                                        Map<String, Provider<TourFilter>> providers) {
        Collection<String> names = dmcConfig.getTourFilters();
        Collection<TourFilter> filters = new ArrayList<>(names.size());

        for (String name : names) {
            if (!providers.containsKey(name)) {
                throw new IllegalStateException(String.format("TourFilter '%s' does not exist.", name));
            } else {
                filters.add(providers.get(name).get());
            }
        }

        return new CompositeTourFilter(filters);
    }

    @Provides
    public TripListConverter provideTripListConverter() {
        return new TripListConverter();
    }
}
