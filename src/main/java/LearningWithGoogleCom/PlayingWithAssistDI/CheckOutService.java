package LearningWithGoogleCom.PlayingWithAssistDI;

import com.google.inject.Inject;

public class CheckOutService{

    private final Discountable discountable;
    @Inject
    public CheckOutService(Discountable discountable){
        this.discountable = discountable;
    }

    public void toMoney(double totalCart){
        System.out.println(totalCart*(1-discountable.getDiscount()));
    }
}
