package submodel.flockersAndHeatBugs;

import java.util.ArrayList;
import java.util.HashMap;

import sim.engine.Schedule;
import sim.engine.SimState;

public class MultiSchedule extends Schedule
{
	private static final long serialVersionUID = 1L;

	ArrayList<Schedule> schedules = new ArrayList<Schedule>();
	HashMap<Schedule, SimState> map = new HashMap<Schedule, SimState>();

	public MultiSchedule() {
		super();
	}
	
	public MultiSchedule(Schedule[] schedules, SimState[] states) {
		super();
		if (schedules.length != states.length)
			throw new RuntimeException("Arrays need to be the same length.");
		
		for (int i = 0; i < schedules.length; i++)
			addSchedule(schedules[i], states[i]);
	}
	
	public MultiSchedule(SimState[] states) {
		super();
		
		for (int i = 0; i < states.length; i++)
			addSchedule(states[i].schedule, states[i]);
	}
	
	/**
	 * Add the given schedule and the SimState to cooresponds to it.
	 */
	public void addSchedule(Schedule schedule, SimState state) {
		schedules.add(schedule);
		map.put(schedule, state);
	}
	
	@Override
	public double getTime() {
		double minTime = Schedule.AFTER_SIMULATION;
		for (Schedule schedule : schedules)
			minTime = Math.min(minTime, schedule.getTime());
		
		return minTime;
	}

	@Override
	public synchronized boolean step(SimState state) {
		double minTime = Schedule.AFTER_SIMULATION;
		Schedule minSchedule = null;
		for (Schedule schedule : schedules)
			if (schedule.getTime() < minTime) {
				minTime = schedule.getTime();
				minSchedule = schedule;
			}
		
		if (minSchedule != null)
			minSchedule.step(map.get(minSchedule));
		
		return (minTime < Schedule.AFTER_SIMULATION);
	}

	/**
	 * Reset this schedule and all the constituent schedules.
	 */
	@Override
	public void reset() {
		super.reset();
		for (Schedule schedule : schedules)
			schedule.reset();
	}

	@Override
	public void clear() {
		super.clear();
		for (Schedule schedule : schedules)
			schedule.clear();
	}
	
	
	
}
