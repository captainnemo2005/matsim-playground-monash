package model.mode_chain;

import model.DiscreteModeChoiceTrip;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.matsim.api.core.v01.population.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultModeChainGenerator implements ModeChainGenerator {

    final private List<String> availableModes;

    final private int numberOfTrips;
    final private int numberOfModes;

    final private long maximumAlternatives;

    private int index = 0;

    public DefaultModeChainGenerator(Collection<String> availableModes, int numberOfTrips) {
        this.availableModes = new ArrayList<>(availableModes);
        this.numberOfModes = availableModes.size();
        this.numberOfTrips = numberOfTrips;
        this.maximumAlternatives = ArithmeticUtils.pow((long) numberOfModes, numberOfTrips);
    }

    public long getNumberOfAlternatives() {
        return maximumAlternatives;
    }

    @Override
    public boolean hasNext() {
        return index < maximumAlternatives;
    }

    @Override
    public List<String> next() {
        if (!hasNext()) {
            throw new IllegalStateException();
        }

        List<String> chain = new ArrayList<>(numberOfTrips);
        int copy = index;

        for (int k = 0; k < numberOfTrips; k++) {
            chain.add(availableModes.get(copy % numberOfModes));
            copy -= copy % numberOfModes;
            copy /= numberOfModes;
        }

        index++;

        return chain;
    }
    static public class Factory implements ModeChainGeneratorFactory {
        @Override
        public ModeChainGenerator createModeChainGenerator(Collection<String> modes, Person person,
                                                           List<DiscreteModeChoiceTrip> trips) {
            return new DefaultModeChainGenerator(modes, trips.size());
        }
    }
}
