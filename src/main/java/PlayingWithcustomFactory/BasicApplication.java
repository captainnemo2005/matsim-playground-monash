package PlayingWithcustomFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;

public class BasicApplication {

    private final CheckOutService checkoutService;

    @Inject
    public BasicApplication(CheckOutService checkoutService){
        this.checkoutService = checkoutService;
    }

    public void start(){
        while (true) {
            checkoutService.checkout(getNewUserCheckout());
        }
    }

    public static void main(String[] args) {
        Injector guice = Guice.createInjector(new DiscountGuiceModule());
        BasicApplication basicApplication = guice.getInstance(BasicApplication.class);
        basicApplication.start();
    }

    ShoppingCart getNewUserCheckout() {
        ShoppingCart cart = new ShoppingCart();
        cart.setCartTotal(getTotalFromInput());
        cart.setTimeOfCheckout(getCheckoutTimeFromInput());

        return cart;
    }

    private double getTotalFromInput() {
        String total = null;
        System.out.print("Enter cart total: ");

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            total = bufferRead.readLine();
        } catch (IOException doh) {
        }

        return Double.valueOf(total);
    }

    private LocalTime getCheckoutTimeFromInput() {
        LocalTime checkoutTime = null;
        String hour = null;
        System.out.print("Enter Checkout hour: ");

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            hour = bufferRead.readLine();
        } catch (IOException doh) {
        }

        return LocalTime.of(Integer.valueOf(hour), 0);
    }

}
