package model;

import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.core.utils.misc.OptionalTime;

import java.util.List;

public class DiscreteModeChoiceTrip {
    private final Activity originActivity;
    private final Activity destinationActivity;
    private final String initialMode;
    private OptionalTime departureTime = OptionalTime.undefined();
    private final List<? extends PlanElement> initialElements;

    private final int hashCode;
    private final int index;

    public DiscreteModeChoiceTrip(Activity originActivity,
                                  Activity destinationActivity,
                                  String initialMode,
                                  List<? extends PlanElement> initialElements,
                                  int personHash,
                                  int tripHash,
                                  int index) {
        this.originActivity = originActivity;
        this.destinationActivity = destinationActivity;
        this.initialMode = initialMode;
        this.initialElements = initialElements;
        this.index = index;

        int hashCode = 12;
        hashCode += 37 * (int) (personHash ^ (personHash >>> 32));
        hashCode += 37 * (int) (tripHash ^ (tripHash >>> 32));
        this.hashCode = hashCode;
    }

    public Activity getOriginActivity() {
        return originActivity;
    }

    public Activity getDestinationActivity() {
        return destinationActivity;
    }

    public double getDepartureTime() {
        return departureTime.seconds();
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = OptionalTime.defined(departureTime);
    }

    public String getInitialMode() {
        return initialMode;
    }

    public List<? extends PlanElement> getInitialElements() {
        return initialElements;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    public int getIndex() {
        return index;
    }
}
