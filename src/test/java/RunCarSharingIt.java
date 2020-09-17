
import static org.matsim.core.config.groups.PlansCalcRouteConfigGroup.ModeRoutingParams;
import static org.matsim.core.config.groups.PlansCalcRouteConfigGroup.AccessEgressType;


import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.contrib.carsharing.config.CarsharingConfigGroup;
import org.matsim.contrib.carsharing.config.FreeFloatingConfigGroup;
import org.matsim.contrib.carsharing.config.OneWayCarsharingConfigGroup;
import org.matsim.contrib.carsharing.config.TwoWayCarsharingConfigGroup;
import org.matsim.contrib.carsharing.runExample.RunCarsharing;
import org.matsim.contrib.dvrp.run.DvrpConfigGroup;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.FacilitiesConfigGroup;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.replanning.modules.SubtourModeChoice;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.testcases.MatsimTestUtils;

//import static org.matsim.core.config.groups.PlansCalcRouteConfigGroup.AccessEgressType;
public class RunCarSharingIt {

    private final static Logger log = Logger.getLogger(RunCarSharingIt.class);

    @Rule public MatsimTestUtils utils = new MatsimTestUtils();

    @Test
    public final void test(){
        Config config = ConfigUtils.loadConfig("/Users/cptnemo2005/IdeaProjects/matsim-examples-tri/src/test/input/config.xml",
                                                    new FreeFloatingConfigGroup(),
                                                    new OneWayCarsharingConfigGroup(),
                                                    new TwoWayCarsharingConfigGroup(),
                                                    new CarsharingConfigGroup(),
                                                    new DvrpConfigGroup());

        config.controler().setOutputDirectory(utils.getOutputDirectory());
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.overwriteExistingFiles);

        config.network().setInputFile("/Users/cptnemo2005/IdeaProjects/matsim-examples-tri/src/test/input/network.xml");

        config.plans().setInputFile("/Users/cptnemo2005/IdeaProjects/matsim-examples-tri/src/test/input/10persons.xml");

        config.facilities().setInputFile("/Users/cptnemo2005/IdeaProjects/matsim-examples-tri/src/test/input/facilities.xml");
        config.facilities().setFacilitiesSource(FacilitiesConfigGroup.FacilitiesSource.fromFile);

        config.plansCalcRoute().setAccessEgressType(AccessEgressType.none);
        config.plansCalcRoute().setRoutingRandomness(0.);

        CarsharingConfigGroup csConfig = (CarsharingConfigGroup) config.getModule(CarsharingConfigGroup.GROUP_NAME);
        csConfig.setvehiclelocations("/Users/cptnemo2005/IdeaProjects/matsim-examples-tri/src/test/input/CarsharingStations.xml");
        csConfig.setmembership("/Users/cptnemo2005/IdeaProjects/matsim-examples-tri/src/test/input/CSMembership.xml");

        config.subtourModeChoice().setBehavior(SubtourModeChoice.Behavior.fromAllModesToSpecifiedModes);
        config.subtourModeChoice().setProbaForRandomSingleTripMode(0.);
        {
          ModeRoutingParams params = new ModeRoutingParams(TransportMode.non_network_walk);
          params.setTeleportedModeSpeed(0.8333333333333);
          params.setBeelineDistanceFactor(1.3);
          config.plansCalcRoute().addModeRoutingParams(params);
        }
        {
            config.plansCalcRoute().removeModeRoutingParams(TransportMode.walk);
            ModeRoutingParams params = new ModeRoutingParams(TransportMode.walk);
            params.setTeleportedModeSpeed(0.833333333333333);
            params.setBeelineDistanceFactor(1.3);
            config.plansCalcRoute().addModeRoutingParams(params);
        }

        // running
        Scenario scenario = ScenarioUtils.loadScenario(config);
        config.plansCalcRoute().setAccessEgressType(AccessEgressType.accessEgressModeToLink);

        final Controler controler = new Controler(scenario);

        RunCarsharing.installCarSharing(controler);

        controler.run();
        log.info("Done");


    }
    public static void main(String[] args) {

    }
}
