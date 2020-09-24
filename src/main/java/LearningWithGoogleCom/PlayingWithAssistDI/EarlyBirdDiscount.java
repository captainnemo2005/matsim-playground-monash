package LearningWithGoogleCom.PlayingWithAssistDI;

public class EarlyBirdDiscount implements Discountable{
    @Override
    public double getDiscount() {
        return 0.3D;
    }
}
