package model;

import model.trip_based.candidates.TripCandidate;
import org.matsim.api.core.v01.population.Person;

import java.util.List;
import java.util.Random;

public interface DiscreteModeChoiceModel {


    static public enum FallbackBehaviour {
        IGNORE_AGENT, INITIAL_CHOICE, EXCEPTION
    }
    List<TripCandidate> chooseModes(Person person, List<DiscreteModeChoiceTrip> trips, Random random)
            throws NoFeasibleChoiceException;

    static public class NoFeasibleChoiceException extends Exception {
        private static final long serialVersionUID = -7909941248706791794L;

        public NoFeasibleChoiceException(String message) {
            super(message);
        }
    }
}
