package PlayingWithGoogleProvideAnnotaion;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.time.LocalTime;

public class GuiceDiscountAnnotation extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    public DiscountableWithAnnotation get(){
        int time = LocalTime.now().getHour();
        if(isMorning(time)){
            return new MorningDiscount();
        }else if (isNight(time)){
            return new NightDiscount();
        }
        return new NoDiscount();
    }

    private boolean isMorning(int time){
        return ((time > 5)&& (time < 9));
    }
    private boolean isNight(int time){
        return ((time > 20)&& (time < 23));
    }
}
