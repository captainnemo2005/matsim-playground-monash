package components.constraints;

import org.matsim.api.core.v01.network.Network;

import java.util.Collection;

public class LinkAttributeConstraint {
    private final Network network;
    private final Collection<String> restrictedModes;

    private final String linkAttributeName;
    private final String linkAttributeValue;

    public enum Requirement {
        ORIGIN, DESTINATION, BOTH, ANY, NONE;
    }
    private final Requirement requirement;

    public LinkAttributeConstraint(Network network,
                                   Collection<String> restrictedModes,
                                   String linkAttributeName,
                                   String linkAttributeValue,
                                   Requirement requirement) {
        this.network = network;
        this.restrictedModes = restrictedModes;
        this.linkAttributeName = linkAttributeName;
        this.linkAttributeValue = linkAttributeValue;
        this.requirement = requirement;
    }
}
