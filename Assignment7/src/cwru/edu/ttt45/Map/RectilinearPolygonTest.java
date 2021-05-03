package cwru.edu.ttt45.Map;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RectilinearPolygonTest {

    @Test(expected = IllegalArgumentException.class)
    public void newInstance() {
        Rectangle r = Rectangle.newInstance(4, 0, 4, 0);
        Rectangle r1 = Rectangle.newInstance(6, 4, 6, 4);
        Rectangle r2 = Rectangle.newInstance(8, 6, 4, 0);
        // nominal case
        RectilinearPolygon rectilinearPolygon = RectilinearPolygon.newInstance(List.of(r, r1));
        RectilinearPolygon rectilinearPolygon1 = RectilinearPolygon.newInstance(List.of(r, r1, r2));
        // case with disjoint rectangles
        RectilinearPolygon rectilinearPolygon2 = RectilinearPolygon.newInstance(List.of(r, r2));
        // case with completely overlapped rectangles
        Rectangle r3 = Rectangle.newInstance(2, 1, 2, 1);
        RectilinearPolygon rectilinearPolygon3 = RectilinearPolygon.newInstance(List.of(r, r3));
        // case with partially overlapped rectangles
        Rectangle r4 = Rectangle.newInstance(5, 3, 5, 3);
        RectilinearPolygon rectilinearPolygon4 = RectilinearPolygon.newInstance(List.of(r, r4));
    }

    @Test
    public void contains() {
        Rectangle r = Rectangle.newInstance(2, 0, 2, 0);
        Rectangle r1 = Rectangle.newInstance(6, 2, 6, 2);
        RectilinearPolygon rectilinearPolygon = RectilinearPolygon.newInstance(List.of(r, r1));
        // location inside the polygon - inside rectangle r
        Location location = new Location(1, 1);
        assertTrue(rectilinearPolygon.contains(location));
        // location inside the polygon - inside rectangle r1
        Location location1 = new Location(5, 4);
        assertTrue(rectilinearPolygon.contains(location1));
        // location in the edge of the rectangle r
        Location location2 = new Location(2, 0);
        assertTrue(rectilinearPolygon.contains(location2));
        // location in the edge of the rectangle r1
        Location location3 = new Location(6, 2);
        assertTrue(rectilinearPolygon.contains(location3));
        // location in the connection point of 2 rectangles
        Location location4 = new Location(4, 2);
        assertTrue(rectilinearPolygon.contains(location4));
        // location outside the polygon
        Location location5 = new Location(10, 10);
        assertFalse(rectilinearPolygon.contains(location5));
    }
}