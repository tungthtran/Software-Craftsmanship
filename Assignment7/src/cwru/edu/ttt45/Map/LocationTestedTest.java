package cwru.edu.ttt45.Map;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTestedTest {

    @Test
    public void testEquals() {
        Location location = new Location(7, 7);
        //column 1
        assertEquals(location.equals(null), false);
        //column 2
        Rectangle r = Rectangle.newInstance(8,0,8,0);
        assertEquals(location.equals(r), false);
        //column 3
        Location location1 = new Location(8,7);
        assertEquals(location1.equals(location), false);
        //column 4
        assertEquals(location.equals(location1), false);
        //column 5
        Location location2 = new Location(7, 7);
        assertEquals(location.equals(location2), true);
        //column 6
        Location location3 = new Location(7, 8);
        assertEquals(location.equals(location3), false);
        //column 7
        assertEquals(location3.equals(location), false);
    }

    @Test
    public void testHashCode() {
        Location a = new Location(2, 2);
        Location b = new Location(3, 3);
        Location c = new Location(3, 3);
        assertEquals(b.hashCode(), c.hashCode());
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
    }
}