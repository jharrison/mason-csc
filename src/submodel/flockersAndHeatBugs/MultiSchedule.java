package submodel.flockersAndHeatBugs;

import sim.engine.Schedule;
import sim.engine.SimState;

public class MultiSchedule extends Schedule
{
	private static final long serialVersionUID = 1L;

	Schedule[] schedules;

	public MultiSchedule() {
		super();
	}
	
	public MultiSchedule(Schedule[] schedules) {
		super();
		this.schedules = schedules;
	}
	
	@Override
	public double getTime() {
		double minTime = Schedule.AFTER_SIMULATION;
		for (int i = 0; i < schedules.length; i++)
			minTime = Math.min(minTime, schedules[i].getTime());
		
		return minTime;
	}

	@Override
	public synchronized boolean step(SimState state) {
		double minTime = Schedule.AFTER_SIMULATION;
		Schedule minSchedule = null;
		for (int i = 0; i < schedules.length; i++)
			if (schedules[i].getTime() < minTime) {
				minTime = schedules[i].getTime();
				minSchedule = schedules[i];
			}
		
		if (minSchedule != null)
			minSchedule.step(state);	//TODO pass it the right SimState
		
		return (minTime < Schedule.AFTER_SIMULATION);
	}

	/**
	 * Reset this schedule and all the constituent schedules.
	 */
	@Override
	public void reset() {
		super.reset();
		for (int i = 0; i < schedules.length; i++)
			schedules[i].reset();
	}
	
}
