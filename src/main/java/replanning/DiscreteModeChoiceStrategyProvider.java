package replanning;

import com.google.inject.Provider;
import model.DiscreteModeChoiceModel;
import modules.DiscreteModeChoiceModule;
import modules.config.DiscreteModeChoiceConfigGroup;
import org.matsim.api.core.v01.population.Population;
import org.matsim.api.core.v01.population.PopulationFactory;
import org.matsim.core.config.groups.GlobalConfigGroup;
import org.matsim.core.replanning.PlanStrategy;
import org.matsim.core.replanning.PlanStrategyImpl;
import org.matsim.core.replanning.selectors.RandomPlanSelector;
import org.matsim.core.router.TripRouter;
import org.matsim.facilities.ActivityFacilities;

import javax.inject.Inject;

public class DiscreteModeChoiceStrategyProvider implements Provider<PlanStrategy> {

    private final GlobalConfigGroup globalConfigGroup;
    private final Provider<TripRouter> tripRouterProvider;
    private final Provider<DiscreteModeChoiceModel> modeChoiceModelProvider;
    private final Provider<TripListConverter> tripListConverterProvider;
    private final ActivityFacilities activityFacilities;
    private final DiscreteModeChoiceConfigGroup dmcConfig;
    private final PopulationFactory populationFactory;

    @Inject
    DiscreteModeChoiceStrategyProvider(GlobalConfigGroup globalConfigGroup,
                                       ActivityFacilities activityFacilities,
                                       Provider<TripRouter> tripRouterProvider,
                                       Provider<DiscreteModeChoiceModel> modeChoiceModelProvider,
                                       DiscreteModeChoiceConfigGroup dmcConfig,
                                       Population population,
                                       Provider<TripListConverter> tripListConverterProvider) {
        this.globalConfigGroup = globalConfigGroup;
        this.activityFacilities = activityFacilities;
        this.tripRouterProvider = tripRouterProvider;
        this.modeChoiceModelProvider = modeChoiceModelProvider;
        this.tripListConverterProvider = tripListConverterProvider;
        this.dmcConfig = dmcConfig;
        this.populationFactory = population.getFactory();
    }

    @Override
    public PlanStrategy get() {
        PlanStrategyImpl.Builder builder = new PlanStrategyImpl.Builder(new RandomPlanSelector<>());
        builder.addStrategyModule(new DiscreteModeChoiceReplanningModule(globalConfigGroup,
                                                                        modeChoiceModelProvider,
                                                                        tripListConverterProvider,
                                                                        populationFactory));
        return builder.build();
    }
}
