package modules.model_availability;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.Person;
import org.matsim.core.population.PersonUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarModeAvailability extends DefaultModeAvailability{

    public CarModeAvailability(Collection<String> modes) {
        super(modes);
    }

    @Override
    public Collection<String> getAvailableModes(Person person, List<DiscreteModeChoiceTrip> trips) {
        boolean carAvailability = !"no".equals(PersonUtils.getLicense(person));
        carAvailability &= !"never".equals(PersonUtils.getCarAvail(person));

        if (!carAvailability) {
            return super.getAvailableModes(person, trips).stream().filter(m -> !TransportMode.car.equals(m))
                    .collect(Collectors.toSet());
        }

        return super.getAvailableModes(person, trips);
    }
}
