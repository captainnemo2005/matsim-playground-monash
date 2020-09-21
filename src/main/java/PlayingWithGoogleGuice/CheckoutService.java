package PlayingWithGoogleGuice;

import com.google.inject.Inject;

public class CheckoutService {

    private final Discountable discountable;

    @Inject
    public CheckoutService(Discountable discountable){
        this.discountable = discountable;
    }

    public double checkout(double shopppingCartTotal){
        double total = shopppingCartTotal*(1-discountable.getDiscount());
        System.out.println("the discount is " + discountable.getDiscount());
        return total;
    }
}
