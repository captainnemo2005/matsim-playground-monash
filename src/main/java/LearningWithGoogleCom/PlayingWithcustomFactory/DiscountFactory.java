package LearningWithGoogleCom.PlayingWithcustomFactory;


import com.google.inject.ImplementedBy;

@ImplementedBy(CartDiscountFactory.class)
public interface DiscountFactory {
    Discountable getDiscount(ShoppingCart cart);
}
