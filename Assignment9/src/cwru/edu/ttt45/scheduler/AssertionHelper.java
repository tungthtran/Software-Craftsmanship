package cwru.edu.ttt45.scheduler;

import java.util.function.Predicate;

final class AssertionHelper {

    static void assertionPrecondition(Predicate<TimePoint> precondition, SchedulerException.Error error, TimePoint timePoint) {
        assert precondition.test(timePoint) : new SchedulerException.Builder(error)
                .setTimePoint(timePoint).build();
    }

    static void assertionPreconditionWithDuration(boolean precondition, SchedulerException.Error error,
                                                  TimePoint timePoint, TimePoint other, long duration) {
        assert precondition : new SchedulerException.Builder(error)
                .setTimePoint(timePoint).setOtherTimePoint(other).setDuration(duration).build();
    }
}
