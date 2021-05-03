package cwru.edu.ttt45.scheduler;

import java.util.*;

final class TimePointSuccessorGroup {
    private final Map<TimePoint, Set<TimePoint>> successors;

    private TimePointSuccessorGroup(Map<TimePoint, Set<TimePoint>> successors) {
        this.successors = successors;
    }

    final Set<TimePoint> getSuccessors(TimePoint timePoint) {
        assert Objects.nonNull(timePoint);
        assert successors.containsKey(timePoint) : "The time point is not in the map";

        return successors.get(timePoint);
    }

    static final TimePointSuccessorGroup create(Set<TimePoint> timePoints) {
        Map<TimePoint, Set<TimePoint>> map = new HashMap<>();
        for (TimePoint timePoint : timePoints) {
            Set<TimePoint> previousTimePoints = timePoint.previousTimePoints();
            for (TimePoint previousTimePoint : previousTimePoints) {
                Set<TimePoint> successorSet = new HashSet<>();
                // add the time point to the successor set
                successorSet.add(timePoint);
                if (map.containsKey(previousTimePoint)) {
                    // and include all existing successors if contains key
                    successorSet.addAll(map.get(previousTimePoint));
                }
                map.put(previousTimePoint, successorSet);
            }
            // add the time point as key to the map with an empty set associated
            if (!map.containsKey(timePoint)) {
                map.put(timePoint, new HashSet<TimePoint>());
            }
        }
        return new TimePointSuccessorGroup(map);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        successors.keySet().stream().forEach(timePoint -> builder.append("TimePoint: " + timePoint + "Successors: " + successors.get(timePoint)));
        return builder.toString();
    }
}