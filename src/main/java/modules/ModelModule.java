package modules;

import model.mode_chain.DefaultModeChainGenerator;
import model.mode_chain.ModeChainGeneratorFactory;
import org.matsim.core.controler.AbstractModule;

public class ModelModule extends AbstractModule {

    @Override
    public void install() {
        install(new ModeAvailabilityModule());
        install(new EstimatorModule());
        install(new TourFinderModule());
        install(new SelectorModule());
        install(new ConstraintModule());
        install(new FilterModule());
        install(new HomeFinderModule());

        bind(ModeChainGeneratorFactory.class).to(DefaultModeChainGenerator.Factory.class);

    }

    public enum ModelType {
        Trip, Tour
    }
}
