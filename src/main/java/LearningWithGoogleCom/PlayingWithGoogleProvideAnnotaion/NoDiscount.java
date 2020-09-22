package LearningWithGoogleCom.PlayingWithGoogleProvideAnnotaion;

public class NoDiscount implements DiscountableWithAnnotation{
    @Override
    public double getDiscount() {
        return 0.D;
    }
}
