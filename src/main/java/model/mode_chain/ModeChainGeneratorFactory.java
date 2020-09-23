package model.mode_chain;

import model.DiscreteModeChoiceTrip;
import org.matsim.api.core.v01.population.Person;

import java.util.Collection;
import java.util.List;

public interface ModeChainGeneratorFactory {
    ModeChainGenerator createModeChainGenerator(Collection<String> availableModes, Person person,
                                                List<DiscreteModeChoiceTrip> trips);
}
