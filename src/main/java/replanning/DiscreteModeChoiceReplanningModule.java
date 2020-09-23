package replanning;

import com.google.inject.Provider;
import model.DiscreteModeChoiceModel;
import org.matsim.api.core.v01.population.PopulationFactory;
import org.matsim.core.config.groups.GlobalConfigGroup;
import org.matsim.core.gbl.MatsimRandom;
import org.matsim.core.population.algorithms.PlanAlgorithm;
import org.matsim.core.replanning.modules.AbstractMultithreadedModule;
import org.matsim.core.router.TripRouter;

public class DiscreteModeChoiceReplanningModule extends AbstractMultithreadedModule {

    public static final String NAME = "DiscreteModeChoice";
    final private Provider<DiscreteModeChoiceModel> modelProvider;
    final private Provider<TripListConverter> converterProvider;

    final private PopulationFactory populationFactory;

    public DiscreteModeChoiceReplanningModule(GlobalConfigGroup globalConfigGroup,
                                              Provider<DiscreteModeChoiceModel> modeChoiceModelProvider,
                                              Provider<TripListConverter> converterProvider,
                                              PopulationFactory populationFactory) {
        super(globalConfigGroup);

        this.modelProvider = modeChoiceModelProvider;
        this.converterProvider = converterProvider;
        this.populationFactory = populationFactory;
    }

    @Override
    public PlanAlgorithm getPlanAlgoInstance() {
        DiscreteModeChoiceModel choiceModel = modelProvider.get();
        TripListConverter converter = converterProvider.get();

        return new DiscreteModeChoiceAlgorithm(MatsimRandom.getLocalInstance(),
                                                choiceModel,
                                                converter,
                                                populationFactory);
    }
}
