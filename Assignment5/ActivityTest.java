package cwru.edu.ttt45.scheduler;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static cwru.edu.ttt45.scheduler.TimePoint.Side.BEGIN;
import static cwru.edu.ttt45.scheduler.TimePoint.Side.END;
import static org.junit.Assert.*;

public class ActivityTest {

    @Test
    public void extremePoint() throws SchedulerException {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        assertEquals(activity.extremePoint(BEGIN), new TimePoint(activity, BEGIN));
        assertEquals(activity.extremePoint(END), new TimePoint(activity, END));
    }

    @Test
    public void dependencies() throws SchedulerException {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        Activity activity2 = Activity.create(10, group, "");
        TimePoint timePoint = new TimePoint(activity2, BEGIN);
        timePoint.freeze();
        Set<Dependency> set = new HashSet<>();
        activity.addDependency(BEGIN, timePoint);
        set.add(new Dependency(timePoint, 0));
        assertEquals(activity.dependencies(BEGIN), set);
    }

    @Test
    public void create() throws SchedulerException {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        assertEquals(activity.getActivities(), group);
        assertEquals(activity.getDescription(), "");
        assertEquals(activity.getDuration(), 0);
    }

    @Test
    public void addDependency() throws SchedulerException {
        ActivityGroup group = new ActivityGroup();
        Activity activity = Activity.create(0, group, "");
        Activity activity2 = Activity.create(10, group, "");
        TimePoint timePoint = new TimePoint(activity2, BEGIN);
        activity.addDependency(BEGIN, timePoint);
        Set<Dependency> set = new HashSet<>();
        set.add(new Dependency(timePoint, 0));
        assertEquals(activity.dependencies(BEGIN), set);
    }
}