package cwru.edu.ttt45.scheduler;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static cwru.edu.ttt45.scheduler.TimePoint.Side.BEGIN;
import static cwru.edu.ttt45.scheduler.TimePoint.Side.END;
import static org.junit.Assert.*;

public class TimePointSorterTest {

    @Test
    public void sort() {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        TimePoint timePoint = new TimePoint(activity, BEGIN);
        TimePoint timePoint1 = new TimePoint(activity, END);
        TimePoint timePoint2 = new TimePoint(activity, BEGIN);
        timePoint.addPrevious(timePoint1);
        timePoint1.addPrevious(timePoint2, 10);
        timePoint.freeze();
        timePoint1.freeze();
        timePoint2.freeze();
        Set<TimePoint> set = new HashSet<>();
        set.add(timePoint);
        set.add(timePoint1);
        set.add(timePoint2);
        List<TimePoint> list = new LinkedList<>();
        list.add(timePoint2);
        list.add(timePoint1);
        list.add(timePoint);
        assertEquals(TimePointSorter.sort(set), list);
    }
}