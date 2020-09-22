package PlayingWithGoogleProvideAnnotaion;

public class NightDiscount implements DiscountableWithAnnotation {
    @Override
    public double getDiscount() {
        return 0.35D;
    }
}
