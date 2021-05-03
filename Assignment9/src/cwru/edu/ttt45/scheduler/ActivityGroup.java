package cwru.edu.ttt45.scheduler;

import defensive.BarricadeBetterSet;

import java.util.*;

public final class ActivityGroup {

    private Set<TimePoint> timePointsGroup;

    ActivityGroup() {
        this.timePointsGroup = new BarricadeBetterSet<>();
    }

    final void addTimePoints(Collection<TimePoint> timePoints) {

        assert Objects.nonNull(timePoints);
        for (TimePoint timePoint : timePoints) {
            assert Objects.nonNull(timePoint);
            AssertionHelper.assertionPrecondition(TimePoint::isFrozen, SchedulerException.Error.POINT_NOT_FROZEN, timePoint);
            AssertionHelper.assertionPrecondition(timePointsGroup::contains, SchedulerException.Error.TIME_POINT_EXISTS, timePoint);
            this.timePointsGroup.add(timePoint);
        }
    };

    public final List<TimePoint> sort() {
        return TimePointSorter.sort(timePointsGroup);
    }

    public static final LinkedHashMap<TimePoint, Long> timeLine(List<TimePoint> sortedPoints) {
        Objects.requireNonNull(sortedPoints);
        LinkedHashMap<TimePoint, Long> timeLineMap = new LinkedHashMap<>();

        for (TimePoint timePoint : sortedPoints) {
            timeLineMap.put(timePoint, maxTimeLine(timePoint, timeLineMap));
        }
        return timeLineMap;
    }

    // get the total amount of duration from one of the dependency
    private static Long totalDuration(Dependency dependency, Map<TimePoint, Long> timeLineMap) {
        TimePoint previous = dependency.getPrevious();
        long duration = dependency.getDuration();
        AssertionHelper.assertionPrecondition(timeLineMap::containsKey, SchedulerException.Error.CIRCULAR_DEPENDENCY, previous);
        return duration + timeLineMap.get(previous);
    }

    // return the maximum time line from the dependencies
    private static Long maxTimeLine(TimePoint timePoint, Map<TimePoint, Long> timeLineMap) {
        return timePoint.getDependencies().stream().mapToLong(dependency -> totalDuration(dependency, timeLineMap))
                                                    .max().orElse(0);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (timePointsGroup != null) {
            for (TimePoint timePoint : this.timePointsGroup) {
                builder.append(timePoint.toString());
            }
        }
        return builder.toString();
    }

}
