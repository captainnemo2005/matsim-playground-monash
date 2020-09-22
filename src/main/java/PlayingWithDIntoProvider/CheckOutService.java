package PlayingWithDIntoProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class CheckOutService {

    private final Provider<Discountable> discountable;

    @Inject
    public CheckOutService(Provider<Discountable> discountable){
        this.discountable = discountable;
    }

    double getDiscount(double currentAmount){
        System.out.println("Amount of discount is " + discountable.get().getDiscount());
        return discountable.get().getDiscount();
    }
}
