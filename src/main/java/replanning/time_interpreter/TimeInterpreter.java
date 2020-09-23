package replanning.time_interpreter;

import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.PlanElement;

import java.util.List;

public interface TimeInterpreter {

    double getCurrentTime();

    double getPreviousTime();

    void addLeg(Leg leg);

    void addActivity(Activity activity);

    void addPlanElement(PlanElement element);

    void addPlanElements(List<? extends PlanElement> elements);

    void setTime(double time);

    void addTime(double duration);

    TimeInterpreter fork();

    static interface Factory {
        TimeInterpreter createTimeInterpreter();
    }
}
