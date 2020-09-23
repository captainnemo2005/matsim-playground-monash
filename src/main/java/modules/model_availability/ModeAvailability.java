package modules.model_availability;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public interface ModeAvailability {

    Collection<String> getAvailableModes(Person person, List<DiscreteModeChoiceTrip> trips);
}

