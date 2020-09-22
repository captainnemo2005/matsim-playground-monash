package LearningWithGoogleCom.PlayingWithGoogleComProvider;

import com.google.inject.Provider;

import java.time.LocalTime;

public class DiscountableProvider implements Provider<Discountable> {
//    @Override
 //   public Discountable get() {
   //     return new EarlyBirdDiscount();
  //  }

   private boolean isEarlyMorning(int currentHour){
       return (currentHour >= 5 && currentHour <= 9);
   }

    private boolean isLateAtNight(int currentHour){
        return (currentHour >= 0 && currentHour <= 4);
    }
    @Override
    public Discountable get() {
        int time = LocalTime.now().getHour();
        if(isEarlyMorning(time)){
            return new EarlyBirdDiscount();
        }else if(isLateAtNight(time)){
            return new NightOwlDiscount();
        }
        return new noDiscount();

    }
}
