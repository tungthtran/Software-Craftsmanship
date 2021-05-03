package cwru.edu.ttt45.scheduler;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static cwru.edu.ttt45.scheduler.TimePoint.Side.BEGIN;
import static cwru.edu.ttt45.scheduler.TimePoint.Side.END;
import static org.junit.Assert.*;

public class TimePointTest {

    @Test
    public void testAddPrevious() throws SchedulerException {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        TimePoint timePoint = new TimePoint(activity, BEGIN);
        TimePoint timePoint1 = new TimePoint(activity, END);
        TimePoint timePoint2 = new TimePoint(activity, BEGIN);
        timePoint.addPrevious(timePoint1);
        timePoint.addPrevious(timePoint2, 10);
        Set<TimePoint> set = new HashSet<>();
        set.add(timePoint1);
        set.add(timePoint2);
        assertEquals(timePoint.previousTimePoints(), set);
    }

    @Test
    public void testPreviousTimePoints() throws SchedulerException {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        TimePoint timePoint = new TimePoint(activity, BEGIN);
        TimePoint timePoint1 = new TimePoint(activity, END);
        TimePoint timePoint2 = new TimePoint(activity, BEGIN);
        timePoint.addPrevious(timePoint1);
        timePoint.addPrevious(timePoint2, 10);
        Set<TimePoint> set = new HashSet<>();
        set.add(timePoint1);
        set.add(timePoint2);
        assertEquals(timePoint.previousTimePoints(), set);
    }
}