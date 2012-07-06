package submodel.flockersAndHeatBugs;

import java.awt.Color;

import javax.swing.JFrame;

import submodel.flockersAndHeatBugs.flockers.FlockersWithUI;
import submodel.flockersAndHeatBugs.heatBugs.HeatBugs;
import submodel.flockersAndHeatBugs.heatBugs.HeatBugsWithUI;
import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.Inspector;
import sim.portrayal.SimpleInspector;
import sim.portrayal.inspector.TabbedInspector;

public class HotFlockersAndHeatBugsWithUI extends GUIState
{
	FlockersWithUI flockersWithUI;
	HeatBugsWithUI heatBugsWithUI;

    public Display2D display;
    public JFrame displayFrame;

	public HotFlockersAndHeatBugsWithUI(SimState state) {
		super(state);
		flockersWithUI = new FlockersWithUI(((HotFlockersAndHeatBugs)state).flockers);
		heatBugsWithUI = new HeatBugsWithUI(((HotFlockersAndHeatBugs)state).heatBugs);
	}

	public HotFlockersAndHeatBugsWithUI() {
		super(new HotFlockersAndHeatBugs(System.currentTimeMillis()));
		flockersWithUI = new FlockersWithUI(((HotFlockersAndHeatBugs)state).flockers);
		heatBugsWithUI = new HeatBugsWithUI(((HotFlockersAndHeatBugs)state).heatBugs);
	}

	public Object getSimulationInspectedObject() {
		return state;
	} // non-volatile
	
    @Override
    public Inspector getInspector() {
		TabbedInspector i = new TabbedInspector();
		i.addInspector(new SimpleInspector((HotFlockersAndHeatBugs)state, this, null, getMaximumPropertiesForInspector()), "Hybrid");
		i.addInspector(new SimpleInspector(((HotFlockersAndHeatBugs)state).flockers, this, null, getMaximumPropertiesForInspector()), "Flockers");
		i.addInspector(new SimpleInspector(((HotFlockersAndHeatBugs)state).heatBugs, this, null, getMaximumPropertiesForInspector()), "Heat Bugs");
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
		
		// ----- Flockers
        // make the displayer
        display = new Display2D(750,750,this);
        display.setBackdrop(Color.black);

        displayFrame = display.createFrame();
        displayFrame.setTitle("HotFlockers and HeatBugs");
        c.registerFrame(displayFrame);   // register the frame so it appears in the "Display" list
        displayFrame.setVisible(true);

        // attach the portrayals
        display.attach(heatBugsWithUI.heatPortrayal,"Heat");
        display.attach(heatBugsWithUI.bugPortrayal,"Bugs");
        display.attach( flockersWithUI.flockersPortrayal, "Behold the Flock!" );

        // specify the backdrop color  -- what gets painted behind the displays
        display.setBackdrop(Color.black);

		((Console)controller).setSize(400, 600);

	}

	@Override
	public boolean step() {
		boolean result = super.step();
		
		// Explicitly calling the GUIState step functions results in calls to their
		// respective SimState's step functions. It also calls all the before and 
		// after stuff that keeps the Display2D up to date.
		if (!((HotFlockersAndHeatBugs)state).sharedSchedule) {
			flockersWithUI.step();
			heatBugsWithUI.step();
		}
		else {		
			flockersWithUI.display.step(state);
			heatBugsWithUI.display.step(state);
		}
		
		display.step(state);	// For some reason, the hybrid display only updates the first run. Subsequent runs don't update. TODO figure out why

		return result;
	}

	public void quit() {
		super.quit();
		flockersWithUI.quit();
		heatBugsWithUI.quit();
	}

	public static void main(String[] args) {
		new HotFlockersAndHeatBugsWithUI().createController();
	}

}

