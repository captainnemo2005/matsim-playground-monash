package LearningWithGoogleCom.PlayingWithcustomFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Map;

import static LearningWithGoogleCom.PlayingWithcustomFactory.DiscountGuiceModule.DiscountOption.EarlyBird;
import static LearningWithGoogleCom.PlayingWithcustomFactory.DiscountGuiceModule.DiscountOption.NightOwl;


@Singleton
public class CartDiscountFactory implements DiscountFactory{

    final Map<DiscountGuiceModule.DiscountOption,Discountable> discountableBinder;

    @Inject
    public CartDiscountFactory(Map<DiscountGuiceModule.DiscountOption, Discountable> mapBinder) {
        this.discountableBinder = mapBinder;
    }

    @Override
    public Discountable getDiscount(ShoppingCart cart) {
        int currentHour = cart.getTimeOfCheckout().getHour();

        if (isEarlyMorning(currentHour)) {
            return discountableBinder.get(EarlyBird);
        } else if (isLateAtNight(currentHour)) {
            return discountableBinder.get(NightOwl);
        }

        return discountableBinder.get(DiscountGuiceModule.DiscountOption.NoDiscount);
    }

    private boolean isEarlyMorning(int currentHour) {
        return (currentHour >= 5 && currentHour <= 9);
    }

    private boolean isLateAtNight(int currentHour) {
        return (currentHour >= 0 && currentHour <= 4);
    }
}
