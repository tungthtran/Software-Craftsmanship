package cwru.edu.ttt45.scheduler;

import java.util.EnumMap;
import java.util.Objects;
import java.util.Set;

public final class Activity {

    private final EnumMap<TimePoint.Side, TimePoint> timePointMap;

    private final ActivityGroup activities;
    private final String description;
    private final long duration;
    private boolean frozen;

    private Activity(ActivityGroup activities, String description, long duration) {
        this.timePointMap = new EnumMap<TimePoint.Side, TimePoint>(TimePoint.Side.class);
        this.activities = activities;
        this.description = description;
        this.duration = duration;
        TimePoint initialTP = new TimePoint(this, TimePoint.Side.BEGIN);
        TimePoint finalTP = new TimePoint(this, TimePoint.Side.END);
        finalTP.addPrevious(initialTP, duration);
        timePointMap.put(TimePoint.Side.BEGIN, initialTP);
        timePointMap.put(TimePoint.Side.END, finalTP);
    }

    public ActivityGroup getActivities() {
        return activities;
    }

    public String getDescription() {
        return description;
    }

    public long getDuration() {
        return duration;
    }

    final TimePoint extremePoint(TimePoint.Side side) {
        return timePointMap.get(side);
    }

    public final Set<Dependency> dependencies(TimePoint.Side side) {
        Objects.requireNonNull(side, "This side is null");
        return extremePoint(side).getDependencies();
    }

    public static final Activity create(long duration, ActivityGroup activities, String description) {
        if (duration < 0) {
            throw new IllegalArgumentException(new SchedulerException.Builder(SchedulerException.Error.INVALID_DURATION)
                    .setDuration(duration).build());
        }

        Objects.requireNonNull(activities, "The activities are null");
        Objects.requireNonNullElse(description, "");

        //else : keep the description
        return new Activity(activities, description, duration);
    }

    public final void freeze() {
        this.frozen = true;
        for(TimePoint tp : timePointMap.values()) {
            tp.freeze();
        }
        this.activities.addTimePoints(timePointMap.values());
    }

    private boolean isFrozen() {
        return this.frozen;
    }

    public final boolean addDependency(TimePoint.Side side, TimePoint other) {
        Objects.requireNonNull(side, "The side is null");
        Objects.requireNonNull(other, "The other time point is null");

        if (this.isFrozen()) {
            throw new IllegalStateException(new SchedulerException.Builder(SchedulerException.Error.INVALID_DEPENDENCY)
                    .setTimePoint(extremePoint(side)).setOtherTimePoint(other).build());
        }
        return extremePoint(side).addPrevious(other);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(activities.toString());
        builder.append("The description is " + description + "\n" + "The duration is " + duration + "\n");
        builder.append("This activity is frozen: " + frozen);
        return builder.toString();
    }
}
