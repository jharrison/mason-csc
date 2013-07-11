package masoncsc.submodel.flockersAndHeatBugs;
import masoncsc.submodel.MetaSchedule;
import masoncsc.submodel.MetaSimState;
import masoncsc.submodel.MultiSchedule;
import masoncsc.submodel.flockersAndHeatBugs.flockers.Flockers;
import masoncsc.submodel.flockersAndHeatBugs.heatBugs.HeatBugs;
import sim.engine.Schedule;
import sim.engine.SimState;
import sim.engine.Steppable;


public class FlockersAndHeatBugs extends MetaSimState
{
	private static final long serialVersionUID = 1L;

	public Flockers flockers;
	public HeatBugs heatBugs;
	
	public FlockersAndHeatBugs(long seed) {
		super(seed);
		flockers = new Flockers(seed);
		heatBugs = new HeatBugs(seed);

		setSimStates(new SimState[] { flockers, heatBugs });
	}
	
	
//
//
//	public void startSubmodels() {
//		// TODO in shared schedule model, create new schedules
//		flockers.schedule = new Schedule();
//		heatBugs.schedule = new Schedule();
//		flockers.start();
//		heatBugs.start();	
//		mergeSchedules();	
//	}
//	
//	public void mergeSchedules() {
//		if (sharedSchedule) {
//			schedule.merge(flockers.schedule);
//			schedule.merge(heatBugs.schedule);
//			flockers.schedule = schedule;	// no longer necessary. TODO Or is it?
//			heatBugs.schedule = schedule;	// no longer necessary. TODO Or is it?
//		}
//	}

	@SuppressWarnings("serial")
	@Override
	public void start() {
		super.start();
//		startSimStates();
		
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
