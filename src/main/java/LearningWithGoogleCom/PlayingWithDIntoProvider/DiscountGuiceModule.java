package LearningWithGoogleCom.PlayingWithDIntoProvider;

import com.google.inject.AbstractModule;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.multibindings.MapBinder;

public abstract class CheckoutExtension extends AbstractModule {

    private MapBinder<Integer,Discountable> DiscountMapBinder;

    @Override
    protected void configure() {
        DiscountMapBinder = MapBinder.newMapBinder(binder(), Integer.class, Discountable.class);
        installExtension();
    }
    /**/
    public void  bindDiscountMapBinder(){
         DiscountMapBinder.addBinding(0).to(BigDiscount.class);
         DiscountMapBinder.addBinding(1).to(SmallDiscount.class);
         DiscountMapBinder.addBinding(2).to(NoDiscount.class);
    }

    public abstract void installExtension();
}
