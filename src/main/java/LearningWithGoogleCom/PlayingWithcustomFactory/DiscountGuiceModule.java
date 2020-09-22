package LearningWithGoogleCom.PlayingWithcustomFactory;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public class DiscountGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        MapBinder<DiscountOption, Discountable> mapBinder
                = MapBinder.newMapBinder(binder(), DiscountOption.class, Discountable.class);

        mapBinder.addBinding(DiscountOption.EarlyBird).to(EarlyBirdDiscount.class);
        mapBinder.addBinding(DiscountOption.NightOwl).to(NightOwlDiscount.class);
        mapBinder.addBinding(DiscountOption.NoDiscount).to(NoDiscount.class);

    }


    public enum DiscountOption {
        EarlyBird, NightOwl, NoDiscount;
    }
}
