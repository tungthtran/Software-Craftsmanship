package cwru.edu.ttt45.scheduler;

final class TimePointCounter {

    private int counter;

    TimePointCounter(int counter) {
        assert counter >= 0 : "The counter is negative";
        this.counter = counter;
    }

    final int decrement() {
        assert counter > 0 : "The counter is non-positive";
        return --counter;
    }

    final boolean isIndependent() {
        return counter == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("The counter is " + counter);
        builder.append("It is independent: " + isIndependent());
        return builder.toString();
    }
}
