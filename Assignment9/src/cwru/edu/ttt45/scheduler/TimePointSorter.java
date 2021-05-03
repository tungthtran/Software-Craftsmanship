package cwru.edu.ttt45.scheduler;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

final class TimePointSorter {

    static final List<TimePoint> sort(Set<TimePoint> timePoints) {

        List<TimePoint> sortedList = new LinkedList<>();
        TimePointCounterGroup counterGroup = TimePointCounterGroup.create(timePoints);
        TimePointSuccessorGroup successorGroup = TimePointSuccessorGroup.create(timePoints);

        while (counterGroup.isAnyIndependent()) {
            TimePoint current = counterGroup.removeIndependent();
            sortedList.add(current);
            Set<TimePoint> currentSuccessor = successorGroup.getSuccessors(current);
            counterGroup.decrementCounters(currentSuccessor);
        }

        if (sortedList.size() >= timePoints.size()) {
            throw new IllegalArgumentException("Error in the dependencies");
        }

        return sortedList;
    }
}
