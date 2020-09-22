package PlayingWithDIntoProvider;

public class NoDiscount implements Discountable{
    @Override
    public double getDiscount() {
        return 0D;
    }
}
