package LearningWithGoogleCom.PlayingWithAssistDI;

import com.google.inject.AbstractModule;

public class CheckOutGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Discountable.class).to(EarlyBirdDiscount.class);
    }
}
