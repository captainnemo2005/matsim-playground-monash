package replanning.time_interpreter;

import org.matsim.api.core.v01.population.Activity;

@Deprecated
public class EndTimeOnlyInterpreter extends AbstractTimeInterpreter{
    public EndTimeOnlyInterpreter(double startTime, boolean onlyAdvance) {
        super(startTime, startTime, onlyAdvance);
    }

    private EndTimeOnlyInterpreter(double currentTime, double previousTime, boolean onlyAdvance) {
        super(currentTime, previousTime, onlyAdvance);
    }
    @Override
    public void addActivity(Activity activity) {
        if (activity.getEndTime().isDefined()) {
            advance(activity.getEndTime().seconds());
        } else {
            throw new IllegalStateException("Found activity that has no end time defined");
        }
    }

    @Override
    public TimeInterpreter fork() {
        return new EndTimeOnlyInterpreter(currentTime, previousTime, onlyAdvance);
    }

    static public class Factory implements TimeInterpreter.Factory {
        private final double startTime;
        private final boolean onlyAdvance;

        public Factory(double startTime, boolean onlyAdvance) {
            this.startTime = startTime;
            this.onlyAdvance = onlyAdvance;
        }

        @Override
        public TimeInterpreter createTimeInterpreter() {
            return new EndTimeOnlyInterpreter(startTime, onlyAdvance);
        }
    }
}
