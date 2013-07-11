package masoncsc.submodel.flockersAndHeatBugs;

import masoncsc.submodel.MetaGUIState;
import masoncsc.submodel.flockersAndHeatBugs.flockers.FlockersWithUI;
import masoncsc.submodel.flockersAndHeatBugs.heatBugs.HeatBugsWithUI;
import sim.display.Console;
import sim.display.Controller;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.Inspector;
import sim.portrayal.SimpleInspector;
import sim.portrayal.inspector.TabbedInspector;

public class FlockersAndHeatBugsWithUI extends MetaGUIState
{
	FlockersWithUI flockersWithUI;
	HeatBugsWithUI heatBugsWithUI;

//	public FlockersAndHeatBugsWithUI(SimState state) {
//		super(state);
//		flockersWithUI = new FlockersWithUI(((FlockersAndHeatBugs)state).flockers);
//		heatBugsWithUI = new HeatBugsWithUI(((FlockersAndHeatBugs)state).heatBugs);
//	}

	@SuppressWarnings("serial")
	public FlockersAndHeatBugsWithUI() {
		super(new FlockersAndHeatBugs(System.currentTimeMillis()));
		flockersWithUI = new FlockersWithUI(((FlockersAndHeatBugs)state).flockers);
		heatBugsWithUI = new HeatBugsWithUI(((FlockersAndHeatBugs)state).heatBugs);
		setGUIStates(new GUIState[] { flockersWithUI, heatBugsWithUI });
	}

	public Object getSimulationInspectedObject() {
		return state;
	} // non-volatile
	
//    @Override
//    public Inspector getInspector() {
//		TabbedInspector i = new TabbedInspector();
//		i.addInspector(new SimpleInspector(((FlockersAndHeatBugs)state).flockers, this, null, getMaximumPropertiesForInspector()), "Flockers");
//		i.addInspector(new SimpleInspector(((FlockersAndHeatBugs)state).heatBugs, this, null, getMaximumPropertiesForInspector()), "Heat Bugs");
//		i.setVolatile(false);
//		return i;
//	}
//
//    @Override
//	public void start() {
//		super.start();
//		flockersWithUI.start();
//		heatBugsWithUI.start();
////		((FlockersAndHeatBugs)state).mergeSchedules();
//	}
//
//	@Override
//	public void init(final Controller c) {
//		super.init(c);
//		flockersWithUI.init(c);
//		heatBugsWithUI.init(c);
//
//		((Console)controller).setSize(400, 600);
//	}
//
//	@Override
//	public boolean step() {
//		boolean result = super.step();
//		// I don't remember why I thought this was necessary...
////		flockersWithUI.display.step(state);
////		heatBugsWithUI.display.step(state);
//		
//		// Instead of this
////		flockersWithUI.step();
////		heatBugsWithUI.step();
//
//		return result;
//	}
//
//	@Override
//	public void quit() {
//		super.quit();
//		flockersWithUI.quit();
//		heatBugsWithUI.quit();
//	}

	public static void main(String[] args) {
		new FlockersAndHeatBugsWithUI().createController();
		// This would actually create three separate controllers:
		// new FlockersWithUI().createController();
		// new HeatBugsWithUI().createController();
	}

}
