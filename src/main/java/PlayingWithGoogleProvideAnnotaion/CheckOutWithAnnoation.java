package PlayingWithGoogleProvideAnnotaion;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class CheckOutWithAnnoation {

    private final Provider<DiscountableWithAnnotation> discountable;

    @Inject
    public CheckOutWithAnnoation(Provider<DiscountableWithAnnotation> discountable) {
        this.discountable = discountable;
    }

    double checkOut(double shoppingCard){

        System.out.println("Amount of discount is " + discountable.get().getDiscount());
        return discountable.get().getDiscount();
    }
}
