package submodel.flockersAndHeatBugs;
import submodel.flockersAndHeatBugs.flockers.Flockers;
import submodel.flockersAndHeatBugs.heatBugs.HeatBugs;
import sim.engine.SimState;
import sim.engine.Steppable;


public class FlockersAndHeatBugs extends SimState
{
	private static final long serialVersionUID = 1L;

	public Flockers flockers;
	public HeatBugs heatBugs;

	public FlockersAndHeatBugs(long seed) {
		super(seed);
		flockers = new Flockers(seed);
//		flockers.schedule = schedule;	// because Flockers doesn't overload the constructor that takes a schedule
		heatBugs = new HeatBugs(seed);
//		heatBugs.schedule = schedule;	// because HeatBugs doesn't overload the constructor that takes a schedule
		
	}
	
//	@Override
//	public SimState getSimulation(Class<?> c) {
//		if (c.equals(Flockers.class))	return flockers;
//		if (c.equals(HeatBugs.class))	return heatBugs;
//		
//		return this;
//	}

	@SuppressWarnings("serial")
	@Override
	public void start() {
		super.start();
		flockers.start();
		heatBugs.start();
		
		// unless we schedule something the simulation will just end, so do the NO-OP
		schedule.scheduleRepeating(new Steppable() { public void step(SimState state) {	} });
	}

	public static void main(String[] args) {
		doLoop(FlockersAndHeatBugs.class, args);
		System.exit(0);
	}

}
