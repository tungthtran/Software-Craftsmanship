package cwru.edu.ttt45.scheduler;

public final class SchedulerException extends Exception {
    public enum Error{POINT_FROZEN, POINT_NOT_FROZEN, INVALID_DURATION, INVALID_DEPENDENCY, TIME_POINT_EXISTS, CIRCULAR_DEPENDENCY};
    static final long serialVersionUID = 77L;

    private final Error error;
    private final TimePoint timePoint;
    private final TimePoint otherTimePoint;
    private final long duration;

    private SchedulerException(Builder builder) {
        this.error = builder.getError();
        this.timePoint = builder.getTimePoint();
        this.otherTimePoint = builder.getOtherTimePoint();
        this.duration = builder.getDuration();
    }

    public final Error getError() {
        return error;
    }

    public final TimePoint getTimePoint() {
        return timePoint;
    }

    public final TimePoint getOtherTimePoint() {
        return otherTimePoint;
    }

    public final long getDuration() { return duration; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(timePoint.toString());
        //builder.append(otherTimePoint.toString());
        builder.append("The error is: " + error);
        builder.append("The Serial Version UID is: " + serialVersionUID);
        return builder.toString();
    }

    final static class Builder {
        private final Error error;
        private long duration;
        private TimePoint timePoint;
        private TimePoint otherTimePoint;

        Builder(Error error) {
            this.error = error;
        }

        final Error getError() {
            return error;
        }

        long getDuration() {
            return duration;
        }

        TimePoint getTimePoint() {
            return timePoint;
        }

        TimePoint getOtherTimePoint() {
            return otherTimePoint;
        }

        Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        Builder setTimePoint(TimePoint timePoint) {
            this.timePoint = timePoint;
            return this;
        }

        Builder setOtherTimePoint(TimePoint otherTimePoint) {
            this.otherTimePoint = otherTimePoint;
            return this;
        }

        final SchedulerException build() {

            return new SchedulerException(this);
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("The error is: " + error);
            builder.append("The duration is: " + duration);
            builder.append(timePoint.toString());
            builder.append(otherTimePoint.toString());
            return builder.toString();
        }
    }

}
