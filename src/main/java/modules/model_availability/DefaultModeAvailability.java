package modules.model_availability;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public class DefaultModeAvailability implements  ModeAvailability{

    final private Collection<String> modes;

    public DefaultModeAvailability(Collection<String> modes) {

        this.modes = modes;
    }

    @Override
    public Collection<String> getAvailableModes(Person person, List<DiscreteModeChoiceTrip> trips) {
        return modes;
    }
}
