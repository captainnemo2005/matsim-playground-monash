package model.utilities;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class MultinomialLogitSelector implements UtilitySelector {
    private final static Logger logger = Logger.getLogger(MultinomialLogitSelector.class);

    final private List<UtilityCandidate> candidates = new LinkedList<>();

    private final double maximumUtility;
    private final double minimumUtility;

    public MultinomialLogitSelector(double maximumUtility, double minimumUtility, boolean considerMinimumUtility) {
        this.maximumUtility = maximumUtility;
        this.minimumUtility = minimumUtility;
        this.considerMinimumUtility = considerMinimumUtility;
    }

    private final boolean considerMinimumUtility;

    @Override
    public void addCandidate(UtilityCandidate candidate) {
        candidates.add(candidate);
    }

    @Override
    public Optional<UtilityCandidate> select(Random random) {
        // I) If not candidates are available, give back nothing
        if (candidates.size() == 0) {
            return Optional.empty();
        }

        // II) Filter candidates that have a very low utility
        List<UtilityCandidate> filteredCandidates = candidates;

        if (considerMinimumUtility) {
            filteredCandidates = candidates.stream() //
                    .filter(c -> c.getUtility() > minimumUtility) //
                    .collect(Collectors.toList());

            if (filteredCandidates.size() == 0) {
                logger.warn(String.format(
                        "Encountered choice where all utilities were smaller than %f (minimum configured utility)",
                        minimumUtility));
                return Optional.empty();
            }
        }

        // III) Create a probability distribution over candidates
        List<Double> density = new ArrayList<>(filteredCandidates.size());

        for (UtilityCandidate candidate : filteredCandidates) {
            double utility = candidate.getUtility();

            // Warn if there is a utility that is exceeding the feasible range
            if (utility > maximumUtility) {
                logger.warn(String.format(
                        "Encountered choice where a utility (%f) is larger than %f (maximum configured utility)",
                        utility, maximumUtility));
                utility = maximumUtility;
            }

            density.add(Math.exp(utility));
        }

        // IV) Build a cumulative density of the distribution
        List<Double> cumulativeDensity = new ArrayList<>(density.size());
        double totalDensity = 0.0;

        for (int i = 0; i < density.size(); i++) {
            totalDensity += density.get(i);
            cumulativeDensity.add(totalDensity);
        }

        // V) Perform a selection using the CDF
        double pointer = random.nextDouble() * totalDensity;

        int selection = (int) cumulativeDensity.stream().filter(f -> f < pointer).count();
        return Optional.of(filteredCandidates.get(selection));
    }

    public static class Factory implements UtilitySelectorFactory {
        private final double minimumUtility;
        private final double maximumUtility;
        private final boolean considerMinimumUtility;

        public Factory(double minimumUtility, double maximumUtility, boolean considerMinimumUtility) {
            this.minimumUtility = minimumUtility;
            this.maximumUtility = maximumUtility;
            this.considerMinimumUtility = considerMinimumUtility;
        }

        @Override
        public UtilitySelector createUtilitySelector() {
            return new MultinomialLogitSelector(maximumUtility, minimumUtility, considerMinimumUtility);
        }
    }
}
