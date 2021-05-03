package cwru.edu.ttt45.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTestedTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNewInstance() {
        //column 1
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        //column 2
        Rectangle r1 = Rectangle.newInstance(3, 4, 3, 0);
        //column 3
        Rectangle.newInstance(3, 3, 3, 0);
        //column 4
        Rectangle.newInstance(3, 0, 3, 5);
        //column 5
        Rectangle.newInstance(3, 0, 3, 3);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testContains() {
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        Location location = new Location(2, 2);
        assertEquals(r.contains(location), true);
        // column 2
        Location location1 = new Location(-1, 2);
        assertEquals(r.contains(location1), false);
        // column 3
        Location location2 = new Location(0, 2);
        assertEquals(r.contains(location2), true);
        // column 4
        Location location3 = new Location(3, 2);
        assertEquals(r.contains(location3), true);
        // column 5
        Location location4 = new Location(4, 2);
        assertEquals(r.contains(location4), false);
        // column 6
        Location location5 = new Location(2, -1);
        assertEquals(r.contains(location5), false);
        // column 7
        Location location6 = new Location(2, 0);
        assertEquals(r.contains(location6), true);
        // column 8
        Location location7 = new Location(2, 3);
        assertEquals(r.contains(location7), true);
        // column 9
        Location location8 = new Location(2, 4);
        assertEquals(r.contains(location8), false);
        // column 10
        Rectangle r1 = Rectangle.newInstance(3, 3, 3, 0);
        assertEquals(r1.contains(location), false);
        // column 11
        Rectangle r2 = Rectangle.newInstance(3, 4, 3, 0);
        assertEquals(r2.contains(location), false);
        // column 12
        Rectangle r3 = Rectangle.newInstance(3, 0, 3, 3);
        assertEquals(r3.contains(location), false);
        // column 13
        Rectangle r4 = Rectangle.newInstance(3, 0, 3, 4);
        assertEquals(r4.contains(location), false);
    }

    @Test
    public void testOverlapFalse() {
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        Rectangle r1 = Rectangle.newInstance(5, 4, 5, 4);
        assertFalse(r.isOverlap(r1));
    }

    @Test
    public void testOverlapTrue() {
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        Rectangle r1 = Rectangle.newInstance(2, 0, 2, 0);
        assertTrue(r.isOverlap(r1));
    }

    @Test
    public void testDisjointFalse() {
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        Rectangle r1 = Rectangle.newInstance(5, 3, 5, 3);
        assertFalse(r.isDisjoint(r1));
    }

    @Test
    public void testDisjointTrue() {
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        Rectangle r1 = Rectangle.newInstance(10, 8, 10, 8);
        assertTrue(r.isDisjoint(r1));
    }
}