package cwru.edu.ttt45.scheduler;

import java.util.Objects;

/** A dependency captures the notion that a time point should only
 	occur after a certain length of time has passed since another time
 	point
 * @author <em>Kris Tran</em>
 */
public final class Dependency {

	private final TimePoint previous;
	
	private final long duration;

	Dependency(TimePoint previous, long duration) {
		assert Objects.nonNull(previous);
		this.previous = previous;
		this.duration = duration;
	}

	Dependency(TimePoint previous) {
		this(previous, 0);
	}

	/**
	 * return the previous time point
	 * @return the previous time point
	 */
	public final TimePoint getPrevious() {
		return this.previous;
	}

	/**
	 * return the time that must pass since the previous time point
	 * @return the time that must pass since the previous time point
	 */
	public final long getDuration() {
		return this.duration;
	}

	/**
	 * return the string representation of the dependency
	 * @return the string representation of the dependency
	 */
	@Override
	public String toString() {
		return "[Previous Time Point: " + "\n" + previous + "duration: " + duration + "]";
	}
}
