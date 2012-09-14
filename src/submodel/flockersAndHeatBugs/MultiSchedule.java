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
	public synchronized boolean step(SimState state) {
		return super.step(state);
	}
}
