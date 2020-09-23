package replanning.time_interpreter;

import org.matsim.api.core.v01.population.Activity;

public class EndTimeThenDurationInterpreter extends AbstractTimeInterpreter{
    public EndTimeThenDurationInterpreter(double startTime, boolean onlyAdvance) {
        super(startTime, startTime, onlyAdvance);
    }

    private EndTimeThenDurationInterpreter(double currentTime, double previousTime, boolean onlyAdvance) {
        super(currentTime, previousTime, onlyAdvance);
    }

    @Override
    public void addActivity(Activity activity) {
        if (activity.getEndTime().isDefined()) {
            advance(activity.getEndTime().seconds());
        } else if (activity.getMaximumDuration().isDefined()) {
            advance(currentTime + activity.getMaximumDuration().seconds());
        } else {
            throw new IllegalStateException("Found activity having neither an end time nor a maximum duration");
        }
    }

    @Override
    public TimeInterpreter fork() {
        return new EndTimeThenDurationInterpreter(currentTime, previousTime, onlyAdvance);
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
            return new EndTimeThenDurationInterpreter(startTime, onlyAdvance);
        }
    }
}
