package LearningWithGoogleCom.PlayingWithGoogleProvideAnnotaion;

public class MorningDiscount implements DiscountableWithAnnotation {
    @Override
    public double getDiscount() {
        return 0.05D;
    }
}
