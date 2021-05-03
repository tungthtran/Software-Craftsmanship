package cwru.edu.ttt45.scheduler;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimePointCounterTest {

    @Test
    public void decrement() {
        TimePointCounter counter = new TimePointCounter(3);
        counter.decrement();
        assertEquals(counter.isIndependent(), false);
        counter.decrement();
        assertEquals(counter.isIndependent(), false);
        counter.decrement();
        assertEquals(counter.isIndependent(), true);
    }

    @Test
    public void isIndependent() {
        TimePointCounter counter = new TimePointCounter(1);
        counter.decrement();
        assertEquals(counter.isIndependent(), true);
    }
}