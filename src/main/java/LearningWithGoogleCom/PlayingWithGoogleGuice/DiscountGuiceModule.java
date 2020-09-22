package LearningWithGoogleCom.PlayingWithGoogleGuice;

import com.google.inject.AbstractModule;

public class DiscountGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Discountable.class).to(NightOwlDiscount.class);
    }
}
