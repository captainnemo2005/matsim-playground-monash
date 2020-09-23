package model.trip_based.candidates;

import org.matsim.api.core.v01.population.PlanElement;

import java.util.List;

public interface RoutedTripCandidate {

    List<? extends PlanElement> getRoutedPlanElements();
}
