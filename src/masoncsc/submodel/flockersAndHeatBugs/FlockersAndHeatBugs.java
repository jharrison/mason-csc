package masoncsc.submodel.flockersAndHeatBugs;
import masoncsc.submodel.flockersAndHeatBugs.flockers.Flockers;
import masoncsc.submodel.flockersAndHeatBugs.heatBugs.HeatBugs;
import masoncsc.submodel.util.MetaSchedule;
import masoncsc.submodel.util.MultiSchedule;
import sim.engine.Schedule;
import sim.engine.SimState;
import sim.engine.Steppable;


public class FlockersAndHeatBugs extends SimState
{
	private static final long serialVersionUID = 1L;

	public Flockers flockers;
	public HeatBugs heatBugs;
	
	public boolean sharedSchedule = false;	// this allows for a comparison between the two scheduling approaches

	public FlockersAndHeatBugs(long seed) {
		super(seed);
		flockers = new Flockers(seed);
		heatBugs = new HeatBugs(seed);
		
		if (!sharedSchedule)
			schedule = new MetaSchedule(new SimState[] { flockers, heatBugs } );
//			schedule = new MultiSchedule(new SimState[] { flockers, heatBugs } );
	}
	
	
	/**
	 * This function allows a Steppable to get a specific type of simulation. 
	 * This would be handy in cases where the model contains submodels.
	 * @param c
	 * @return
	 */
	@Override
	public SimState getSimulation(Class<?> c) {
		if (c.equals(Flockers.class))	return flockers;
		if (c.equals(HeatBugs.class))	return heatBugs;
		
		return this;
	}
	
	/**
	 * This function exists so it can be overridden and stubbed out when running from the GUI.
	 */
	public void startSubmodels() {
		// TODO in shared schedule model, create new schedules
		flockers.schedule = new Schedule();
		heatBugs.schedule = new Schedule();
		flockers.start();
		heatBugs.start();	
		mergeSchedules();	
	}
	
	public void mergeSchedules() {
		if (sharedSchedule) {
			schedule.merge(flockers.schedule);
			schedule.merge(heatBugs.schedule);
			flockers.schedule = schedule;	// no longer necessary. TODO Or is it?
			heatBugs.schedule = schedule;	// no longer necessary. TODO Or is it?
		}
	}

	@SuppressWarnings("serial")
	@Override
	public void start() {
		super.start();
		startSubmodels();
		
//		if (!sharedSchedule) {
//			schedule.scheduleRepeating(new Steppable() { public void step(SimState state) {
//				if (flockers.schedule.getTime() <= heatBugs.schedule.getTime())
//					flockers.schedule.step(flockers);
//				if (heatBugs.schedule.getTime() <= flockers.schedule.getTime())
//					heatBugs.schedule.step(heatBugs);
//				
//
//				System.out.format("Super: %.2f (%d), Flockers: %.2f (%d), HeatBugs: %.2f (%d)\n", 
//						schedule.getTime(), schedule.getSteps(),
//						flockers.schedule.getTime(), flockers.schedule.getSteps(),
//						heatBugs.schedule.getTime(), heatBugs.schedule.getSteps());
//				
//			}}, 0, 1.0);
//		}
		
	}
	
	

	public static void main(String[] args) {
		doLoop(FlockersAndHeatBugs.class, args);
		System.exit(0);
	}

}
