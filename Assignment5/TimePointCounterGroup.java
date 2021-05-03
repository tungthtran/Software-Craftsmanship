package cwru.edu.ttt45.scheduler;

import java.util.*;

class TimePointCounterGroup {

    private Map<TimePoint, TimePointCounter> counters;

    private List<TimePoint> independentTimePoints;

    private TimePointCounterGroup(Map<TimePoint, TimePointCounter> counters, List<TimePoint> independentTimePoints) {
        this.counters = counters;
        this.independentTimePoints = independentTimePoints;
    }

    static final TimePointCounterGroup create(Set<TimePoint> timePoints) {
        Map<TimePoint, TimePointCounter> countersMap = new HashMap<>();
        timePoints.stream()
                  .forEach(timePoint -> countersMap.put(timePoint, new TimePointCounter(timePoint.inDegree())));
        return new TimePointCounterGroup(countersMap, new LinkedList<TimePoint>());
    }

    boolean isAnyIndependent() {
        return independentTimePoints.size() > 0;
    }

    TimePoint removeIndependent() {
        assert isAnyIndependent() : "There is no independent time point";
        TimePoint removedTP = independentTimePoints.remove(0);
        return removedTP;
    }

    void decrementCounters(Set<TimePoint> timePoints) {
        for(TimePoint timePoint : timePoints) {
            assert counters.containsKey(timePoint) : "This time point is unknown to the group";
            TimePointCounter counter = counters.get(timePoint);
            assert !counter.isIndependent() : "The counter is already independent";
            counter.decrement();
            if (counter.isIndependent()) {
                independentTimePoints.add(timePoint);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("The counters: " + counters.toString());
        builder.append("The independent list: " + independentTimePoints.toString());
        builder.append("There is any independent time point: " + isAnyIndependent());
        return builder.toString();
    }
}
