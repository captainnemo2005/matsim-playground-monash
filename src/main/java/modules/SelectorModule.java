package modules;

import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import model.utilities.MaximumSelector;
import model.utilities.MultinomialLogitSelector;
import model.utilities.RandomSelector;
import model.utilities.UtilitySelectorFactory;
import modules.config.DiscreteModeChoiceConfigGroup;
import modules.config.MultinomialLogitSelectorConfigGroup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class SelectorModule extends AbstractDiscreteModeChoiceExtension{
    public final static String MAXIMUM = "Maximum";
    public final static String MULTINOMIAL_LOGIT = "MultinomialLogit";
    public final static String RANDOM = "Random";

    public final static Collection<String> COMPONENTS = Arrays.asList(MAXIMUM, MULTINOMIAL_LOGIT, RANDOM);

    @Override
    public void installExtension() {
        bindSelectorFactory(MAXIMUM).to(MaximumSelector.Factory.class);
        bindSelectorFactory(MULTINOMIAL_LOGIT).to(MultinomialLogitSelector.Factory.class);
        bindSelectorFactory(RANDOM).to(RandomSelector.Factory.class);
    }
    @Provides
    public UtilitySelectorFactory provideTourSelectorFactory(DiscreteModeChoiceConfigGroup dmcConfig,
                                                             Map<String, Provider<UtilitySelectorFactory>> components) {
        Provider<UtilitySelectorFactory> provider = components.get(dmcConfig.getSelector());

        if (provider != null) {
            return provider.get();
        } else {
            throw new IllegalStateException(String
                    .format("There is no UtilitySelector component for tours called '%s',", dmcConfig.getSelector()));
        }
    }

    @Provides
    @Singleton
    public MaximumSelector.Factory provideMaximumTripSelector() {
        return new MaximumSelector.Factory();
    }

    @Provides
    @Singleton
    public MultinomialLogitSelector.Factory provideMultinomialLogitTripSelector(
            DiscreteModeChoiceConfigGroup dmcConfig) {
        MultinomialLogitSelectorConfigGroup config = dmcConfig.getMultinomialLogitSelectorConfig();
        return new MultinomialLogitSelector.Factory(config.getMinimumUtility(), config.getMaximumUtility(),
                config.getConsiderMinimumUtility());
    }

    @Provides
    @Singleton
    public RandomSelector.Factory provideRandomTripSelector() {
        return new RandomSelector.Factory();
    }
}
