package module;

import org.matsim.core.controler.AbstractModule;

public class ModelModule extends AbstractModule {

    @Override
    public void install() {

    }

    public enum ModelType {
        Trip, Tour
    }
}
