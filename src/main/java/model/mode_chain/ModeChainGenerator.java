package model.mode_chain;

import java.util.Iterator;
import java.util.List;

public interface ModeChainGenerator extends Iterator<List<String>> {
    long getNumberOfAlternatives();
}
