package components.utils;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.matsim.pt.transitSchedule.api.TransitSchedule;

public class ScheduleWaitingTimeEstimatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PTWaitingTimeEstimator.class).to(ScheduleWaitingTimeEstimator.class);
    }

    @Provides
    @Singleton
    public ScheduleWaitingTimeEstimator provideScheduleWaitingTimeEstimator(TransitSchedule transitSchedule) {
        return new ScheduleWaitingTimeEstimator(transitSchedule);
    }
}
