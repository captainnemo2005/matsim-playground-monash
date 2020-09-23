package modules.utils;

import com.google.inject.Inject;
import modules.DiscreteModeChoiceConfigurator;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.core.config.groups.StrategyConfigGroup;
import org.matsim.core.controler.events.StartupEvent;
import org.matsim.core.controler.listener.StartupListener;
import org.matsim.core.replanning.selectors.PlanSelector;
import replanning.NonSelectedPlanSelector;

public class ModeChoiceInTheLoopChecker implements StartupListener {
    private final StrategyConfigGroup strategyConfig;
    private final PlanSelector<Plan, Person> removalSelector;


    @Inject
    public ModeChoiceInTheLoopChecker(StrategyConfigGroup strategyConfig,
                                      PlanSelector<Plan, Person> removalSelector) {
        this.strategyConfig = strategyConfig;
        this.removalSelector = removalSelector;
    }

    @Override
    public void notifyStartup(StartupEvent startupEvent) {
        DiscreteModeChoiceConfigurator.checkModeChoiceInTheLoop(strategyConfig);

        if (!(removalSelector instanceof NonSelectedPlanSelector)) {
            throw new IllegalStateException(
                    "Removal strategy should be NonSelectedPlanSelector if mode-choice-in-the-loop is enforced.");
        }
    }
}
