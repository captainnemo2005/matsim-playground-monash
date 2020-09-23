package model.trip_based.candidates;

import org.matsim.api.core.v01.population.PlanElement;

import java.util.List;

public class DefaultRoutedTripCandidate extends DefaultTripCandidate implements RoutedTripCandidate {
    private final List<? extends PlanElement> routedPlanElements;

    public DefaultRoutedTripCandidate(double utility, String mode,
                                      List<? extends PlanElement> routedPlanElements,
                                      double duration) {
        super(utility, mode, duration);
        this.routedPlanElements = routedPlanElements;
    }

    @Override
    public List<? extends PlanElement> getRoutedPlanElements() {
        return routedPlanElements;
    }
}
