package LearningWithGoogleCom.PlayingWithAssistDI;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CheckOutGuiceModule());
        CheckOutService checkout = injector.getInstance(CheckOutService.class);
        checkout.toMoney(100);
    }
}
