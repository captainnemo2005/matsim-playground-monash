package LearningWithGoogleCom.PlayingWithGoogleProvideAnnotaion;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {

        Injector guice = Guice.createInjector(new GuiceDiscountAnnotation());
        CheckOutWithAnnoation service = guice.getInstance(CheckOutWithAnnoation.class);
        service.checkOut(100);
    }
}