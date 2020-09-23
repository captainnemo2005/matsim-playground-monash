package replanning.time_interpreter;

import org.matsim.api.core.v01.population.Activity;

public class MinimumEndTimeAndDurationInterpreter extends AbstractTimeInterpreter {
    public MinimumEndTimeAndDurationInterpreter(double startTime, boolean onlyAdvance) {
        super(startTime, startTime, onlyAdvance);
    }

    private MinimumEndTimeAndDurationInterpreter(double currentTime, double previousTime, boolean onlyAdvance) {
        super(currentTime, previousTime, onlyAdvance);
    }

    @Override
    public void addActivity(Activity activity) {
        if (activity.getMaximumDuration().isUndefined() || activity.getEndTime().isUndefined()) {
            throw new IllegalStateException(
                    "Found an activity with undefined maximum duration or undefined activity end time. Both must be defined if minOfDurationAndEndTime is used!");
        }

        advance(Math.min(currentTime + activity.getMaximumDuration().seconds(), activity.getEndTime().seconds()));
    }

    @Override
    public TimeInterpreter fork() {
        return new MinimumEndTimeAndDurationInterpreter(currentTime, previousTime, onlyAdvance);
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
            return new MinimumEndTimeAndDurationInterpreter(startTime, onlyAdvance);
        }
    }
}
