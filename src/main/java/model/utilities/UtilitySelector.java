package model.utilities;

import java.util.Optional;
import java.util.Random;

public interface UtilitySelector {
    void addCandidate(UtilityCandidate candidate);

    Optional<UtilityCandidate> select(Random random);
}
