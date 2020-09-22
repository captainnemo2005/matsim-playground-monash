package LearningWithGoogleCom.PlayingWithcustomFactory;

public class EarlyBirdDiscount implements Discountable{
    @Override
    public double getDiscount() {
        return 0.35D;
    }
}
