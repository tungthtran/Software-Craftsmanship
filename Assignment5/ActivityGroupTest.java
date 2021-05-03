package cwru.edu.ttt45.scheduler;

import org.junit.Test;

import java.util.*;

import static cwru.edu.ttt45.scheduler.TimePoint.Side.BEGIN;
import static cwru.edu.ttt45.scheduler.TimePoint.Side.END;
import static org.junit.Assert.*;

public class ActivityGroupTest {

    @Test
    public void timeLine() {
        ActivityGroup group = new ActivityGroup();
        Activity activityOS = Activity.create(0, group, "OS");
        Activity activityDB = Activity.create(0, group, "DB");
        TimePoint timePointOSB = new TimePoint(activityOS, BEGIN);
        TimePoint timePointDBB = new TimePoint(activityDB, BEGIN);
        TimePoint timePointDBE = new TimePoint(activityDB, END);
        timePointDBB.addPrevious(timePointOSB, 5);
        timePointDBE.addPrevious(timePointDBB, 4);
        timePointDBE.addPrevious(timePointOSB, 7);
        timePointOSB.freeze();
        timePointDBB.freeze();
        timePointDBE.freeze();
        Set<TimePoint> set = new HashSet<>();
        set.add(timePointOSB);
        set.add(timePointDBB);
        set.add(timePointDBE);
        TimePointSorter.sort(set);
        LinkedHashMap<TimePoint, Long> timeLineMap = ActivityGroup.timeLine(TimePointSorter.sort(set));
        assertEquals(timeLineMap.get(timePointDBE), new Long(9));
        assertEquals(timeLineMap.get(timePointDBB), new Long(5));
    }
}