package submodel.flockersAndHeatBugs;

import submodel.flockersAndHeatBugs.flockers.FlockersWithUI;
import submodel.flockersAndHeatBugs.heatBugs.HeatBugsWithUI;
import sim.display.Console;
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

		((Console)controller).setSize(400, 600);
	}

	@Override
	public boolean step() {
		boolean result = super.step();
		
		// Explicitly calling the GUIState step functions results in calls to their
		// respective SimState's step functions. It also calls all the before and 
		// after stuff that keeps the Display2D up to date.
		if (!((FlockersAndHeatBugs)state).sharedSchedule) {
			flockersWithUI.step();
			heatBugsWithUI.step();
		}
		else {		
			flockersWithUI.display.step(state);
			heatBugsWithUI.display.step(state);
		}

		return result;
	}

	public void quit() {
		super.quit();
		flockersWithUI.quit();
		heatBugsWithUI.quit();
	}

	public static void main(String[] args) {
		new FlockersAndHeatBugsWithUI().createController();
		// This would actually create three separate controllers:
		// new FlockersWithUI().createController();
		// new HeatBugsWithUI().createController();
	}

}
