package components.utils;

import org.matsim.pt.routes.TransitPassengerRoute;

public class NullWaitingTimeEstimator implements PTWaitingTimeEstimator{
    @Override
    public double estimateWaitingTime(double departureTime, TransitPassengerRoute route) {
        return 0.0;
    }
}
