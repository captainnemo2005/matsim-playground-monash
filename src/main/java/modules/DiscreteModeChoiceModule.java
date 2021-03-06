package modules;

import com.google.inject.Inject;
import modules.config.DiscreteModeChoiceConfigGroup;
import modules.utils.ModeChoiceInTheLoopChecker;
import org.matsim.core.controler.AbstractModule;
import replanning.DiscreteModeChoiceStrategyProvider;
import replanning.NonSelectedPlanSelector;
import replanning.time_interpreter.TimeInterpreterModule;

public class DiscreteModeChoiceModule extends AbstractModule {

    public static final String STRATEGY_NAME = "DiscreteModeChoice";

    @Inject
    private DiscreteModeChoiceConfigGroup dmcConfig;

    @Override
    public void install(){
        addPlanStrategyBinding(STRATEGY_NAME).toProvider(DiscreteModeChoiceStrategyProvider.class);

        if (getConfig().strategy().getPlanSelectorForRemoval().equals(NonSelectedPlanSelector.NAME)) {
            bindPlanSelectorForRemoval().to(NonSelectedPlanSelector.class);
        }

        if (dmcConfig.getEnforceSinglePlan()) {
            addControlerListenerBinding().to(ModeChoiceInTheLoopChecker.class);
        }

        install(new ModelModule());
        install(new TimeInterpreterModule());
    }
}
