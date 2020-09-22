package LearningWithGoogleCom.PlayingWithGoogleGuice;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class CheckoutService {

    private final Provider<Discountable> discountable;

    @Inject
    public CheckoutService(Provider<Discountable> discountable){
        this.discountable = discountable;
    }

    public double checkout(double shopppingCartTotal){

        double total = shopppingCartTotal*(1-discountable.get().getDiscount());
        System.out.println("the discount is " + discountable.get().getDiscount());
        return total;
    }
}
