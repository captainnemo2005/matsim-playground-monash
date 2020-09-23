package components.utils;

import org.matsim.pt.routes.TransitPassengerRoute;

public interface PTWaitingTimeEstimator {
    double estimateWaitingTime(double departureTime, TransitPassengerRoute route);
}
