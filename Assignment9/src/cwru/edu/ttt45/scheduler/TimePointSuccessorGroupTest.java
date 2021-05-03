package cwru.edu.ttt45.scheduler;

import defensive.BarricadeBetterSet;
import org.junit.Test;

import java.util.Set;

import static cwru.edu.ttt45.scheduler.TimePoint.Side.BEGIN;
import static cwru.edu.ttt45.scheduler.TimePoint.Side.END;
import static org.junit.Assert.*;

public class TimePointSuccessorGroupTest {

    @Test
    public void getSuccessors() {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        TimePoint timePoint = new TimePoint(activity, BEGIN);
        TimePoint timePoint1 = new TimePoint(activity, END);
        timePoint.addPrevious(timePoint1);
        Set<TimePoint> set = new BarricadeBetterSet<>();
        set.add(timePoint);
        set.add(timePoint1);
        TimePointSuccessorGroup group1 = TimePointSuccessorGroup.create(set);
        assertEquals(group1.getSuccessors(timePoint), Set.of());
    }
}