package cwru.edu.ttt45.scheduler;

import defensive.BarricadeBetterSet;

import java.util.Set;
import java.util.stream.Collectors;

/** A time point represents a point in time, and typically
    corresponds to the beginning and end of an activity
 * @author <em>Kris Tran</em>
 */
public final class TimePoint {
	public enum Side{BEGIN, END};

	private final Set<Dependency> dependencies;

	private boolean frozen;
	private final Activity activity;
	private final Side side;
	private final String description;

	TimePoint(Activity activity, Side side) {
		dependencies = new BarricadeBetterSet<>();
		this.frozen = false;
		this.activity = activity;
		this.side = side;
		this.description = "[" + activity.getDescription() + "]:[" + side + "]";
	}

	public final Activity getActivity() {
		return activity;
	}

	public final Side getSide() {
		return side;
	}

	public final String getDescription() {
		return description;
	}

	/**
	 * return a COPY of all the dependencies
	 * @return a COPY of all the dependencies
	 */
	public final Set<Dependency> getDependencies() {
		Set<Dependency> copy = new BarricadeBetterSet<>(dependencies);
		return copy;
	}

	/**
	 * add a dependency to this time point
	 * @param previousTimePoint the previous time point
	 * @param duration the duration between 2 time points
	 */
	final boolean addPrevious(TimePoint previousTimePoint, long duration) {

		AssertionHelper.assertionPrecondition(timePoint -> !timePoint.isFrozen(), SchedulerException.Error.POINT_FROZEN, this);
		AssertionHelper.assertionPreconditionWithDuration(duration >= 0, SchedulerException.Error.INVALID_DURATION, this,
														   previousTimePoint, duration);
		return dependencies.add(new Dependency(previousTimePoint, duration));
	}

	/**
	 * add a dependency of zero duration to this time point
	 * @param previousTimePoint the previous time point
	 */
	final boolean addPrevious(TimePoint previousTimePoint) {
		return this.addPrevious(previousTimePoint, 0);
	}

	/**
	 * freeze the Time Point */
	public final void freeze() {
		this.frozen = true;
	}

	/**
	 * return whether this TimePoint is frozen
	 * @return whether this TimePoint is frozen
	 */
	public final boolean isFrozen() {
		return this.frozen;
	}

	/**
	 * return a set of all the previous time point
	 * @return a set of all the previous time point
	 */
	public final Set<TimePoint> previousTimePoints() {
		// extra-credit implementation
		return dependencies.stream()
					       .map(dependency -> dependency.getPrevious())
				           .collect(Collectors.toSet());
	}

	/**
	 * return the number of dependencies
	 * @return the number of dependencies
	 */
	public final int inDegree() {
		return dependencies.size();
	}

	/**
	 * return whether this TimePoint has any dependencies
	 * @return whether this TimePoint has any dependencies
	 */
	public final boolean isIndependent() {
		return dependencies.isEmpty();
	}

	/**
	 * return the string representation of this time point but omit the dependencies
	 * @return the string representation of this time point but omit the dependencies
	 */
	public final String toSimpleString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This Time Point is frozen: " + isFrozen());
		builder.append("\n");
		builder.append("This Time Point is independent: " + isIndependent());
		builder.append(activity.toString());
		builder.append("Description: " + description);
		return builder.toString();
	}

	/**
	 * return the string representation of this time point
	 * @return the string representation of this time point
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("This Time Point has ");
		builder.append(inDegree());
		builder.append(" dependencies");
		builder.append("\n");
		if (this.inDegree() > 0) {
			builder.append("The dependencies are: ");
			builder.append("\n");
			for (Dependency dependency : getDependencies()) {
				builder.append(dependency.toString());
				builder.append("\n");
			}
		}
		//else
			//since there is no dependencies, no need to print more
		return toSimpleString() + "\n" + builder.toString();
	}
}
