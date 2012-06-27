package submodel.flockersAndHeatBugs;

import submodel.flockersAndHeatBugs.flockers.FlockersWithUI;
import submodel.flockersAndHeatBugs.heatBugs.HeatBugsWithUI;
import sim.display.Controller;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.Inspector;
import sim.portrayal.SimpleInspector;
import sim.portrayal.inspector.TabbedInspector;

public class FlockersAndHeatBugsWithUI extends GUIState
{

	FlockersWithUI flockersWithUI;
	HeatBugsWithUI heatBugsWithUI;

	public FlockersAndHeatBugsWithUI(SimState state) {
		super(state);
		flockersWithUI = new FlockersWithUI(((FlockersAndHeatBugs)state).flockers);
		heatBugsWithUI = new HeatBugsWithUI(((FlockersAndHeatBugs)state).heatBugs);
	}

	public FlockersAndHeatBugsWithUI() {
		super(new FlockersAndHeatBugs(System.currentTimeMillis()));
		flockersWithUI = new FlockersWithUI(((FlockersAndHeatBugs)state).flockers);
		heatBugsWithUI = new HeatBugsWithUI(((FlockersAndHeatBugs)state).heatBugs);
	}

	public Object getSimulationInspectedObject() {
		return state;
	} // non-volatile
	
    @Override
    public Inspector getInspector() {
		TabbedInspector i = new TabbedInspector();
		i.addInspector(new SimpleInspector(((FlockersAndHeatBugs)state).flockers, this, null, getMaximumPropertiesForInspector()), "Flockers");
		i.addInspector(new SimpleInspector(((FlockersAndHeatBugs)state).heatBugs, this, null, getMaximumPropertiesForInspector()), "Heat Bugs");
		i.setVolatile(false);
		return i;
	}

	public void start() {
		super.start();
		flockersWithUI.start();
		heatBugsWithUI.start();
	}

	public void init(final Controller c) {
		super.init(c);
		flockersWithUI.init(c);
		heatBugsWithUI.init(c);
	}

	@Override
	public boolean step() {
		boolean result = super.step();
		flockersWithUI.step();
		heatBugsWithUI.step();

		return result;
	}

	public void quit() {
		super.quit();
		flockersWithUI.quit();
		heatBugsWithUI.quit();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FlockersAndHeatBugsWithUI().createController();
		// new FlockersWithUI().createController();
		// new HeatBugsWithUI().createController();
	}

}
