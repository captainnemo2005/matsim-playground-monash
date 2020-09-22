package PlayingWithGoogleComProvider;

public class noDiscount implements Discountable {
    @Override
    public double getDiscount() {
        return 0;
    }
}
