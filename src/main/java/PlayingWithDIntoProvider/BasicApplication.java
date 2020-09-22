package PlayingWithDIntoProvider;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class BasicApplication {

    private final CheckOutService service;

    @Inject
    public BasicApplication(CheckOutService service) {
        this.service = service;
    }

    void start(){
        service.getDiscount(100);
    }

    public static void main(String[] args) {
        Injector guice = Guice.createInjector(new DiscountGuiceModule());
        BasicApplication basicApplication = guice.getInstance(BasicApplication.class);
        basicApplication.start();

    }
}
