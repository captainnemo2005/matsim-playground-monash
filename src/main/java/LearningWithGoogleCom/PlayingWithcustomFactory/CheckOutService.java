package LearningWithGoogleCom.PlayingWithcustomFactory;

import com.google.inject.Inject;

public class CheckOutService {

    private final CartDiscountFactory discountFactory;

    @Inject
    public CheckOutService(CartDiscountFactory discountFactory) {
        this.discountFactory = discountFactory;
    }

    public double checkout(ShoppingCart cart) {
        Discountable discountable = discountFactory.getDiscount(cart);
        double discount = discountable.getDiscount();
        double total = cart.getCartTotal();

        double totalAfterDiscount = total - (total * discount);
        System.out.printf("%nShopping cart initially [$%.2f] with a discount of %.2f%% = [$%.2f]%n%n",
                total,
                discount * 100,
                totalAfterDiscount);

        return totalAfterDiscount;
    }
}
