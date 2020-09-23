package modules;

import java.util.Arrays;
import java.util.Collection;

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
}
