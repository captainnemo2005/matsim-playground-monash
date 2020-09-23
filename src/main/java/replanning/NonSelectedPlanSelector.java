package replanning;

import org.matsim.api.core.v01.population.HasPlansAndId;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.core.replanning.selectors.PlanSelector;

public class NonSelectedPlanSelector implements PlanSelector<Plan, Person> {

    static public final String NAME = "NonSelectedPlanSelector";

    @Override
    public Plan selectPlan(HasPlansAndId<Plan, Person> member) {
        if (member.getPlans().size() > 2) {
            throw new IllegalStateException(
                    "NonSelectedPlanSelector only makes sense if there is no more than two plans!");
        }

        for (Plan plan : member.getPlans()) {
            if (!plan.equals(member.getSelectedPlan())) {
                return plan;
            }
        }
        return null;
    }
}
