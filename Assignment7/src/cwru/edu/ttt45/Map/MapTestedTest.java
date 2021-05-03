package cwru.edu.ttt45.Map;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import cwru.edu.ttt45.Map.Map.Type;

import static cwru.edu.ttt45.Map.Map.Type.*;
import static org.junit.Assert.*;

public class MapTestedTest {

    @Test
    public void testNewInstance() {
        Map map = Map.newInstance();
    }

    @Test
    public void testIsOccupied() {
        Map map = Map.newInstance();
        map.insert(ALIEN, 1, 2);
        map.insert(Map.Type.MINE, 2, 4);
        map.insert(Map.Type.FIELD, 4, 8);
        Map.Test testingMap = map.new Test();
        assertEquals(testingMap.isOccupied(map, new Location(1, 2)), true);
        assertEquals(testingMap.isOccupied(map, new Location(2, 4)), true);
        assertEquals(testingMap.isOccupied(map, new Location(4, 8)), true);
        assertEquals(testingMap.isOccupied(map, new Location(0, 0)), false);
    }

    @Test(expected=IllegalStateException.class)
    public void testInsert() {
        Map map = Map.newInstance();
        map.insert(WELL, 1, 2);
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        //column 1
        assertEquals(map.count(WELL, r), 1);
        //column 2
        map.insert(ALIEN, 1, 2);
        assertEquals(map.count(ALIEN, r), 0); // count is 0 because the location is already occupied
        //test for bad data: null argument
        map.insert(null, 2, 2);
        assertEquals(map.count(null, r), 0);


        //Good data(legacy) from the previous assignment's test
        Map map2 = Map.newInstance();
        map2.insert(WELL, 1, 2);
        map2.insert(WELL, 2, 3);
        map2.insert(WELL, 3, 4);
        Rectangle r2 = Rectangle.newInstance(5, 0, 5, 0);
        assertEquals(map.count(WELL, r2), 3);
        map2.insert(WELL, 2, 4);
        map2.insert(WELL, 3, 2);
        assertEquals(map2.count(WELL, r2), 5);
    }

    @Test(expected=NullPointerException.class)
    public void testCountWithRectangle() {
        Map map = Map.newInstance();
        map.insert(MANAGER, 1, 2);
        map.insert(MANAGER, 2, 1);
        Rectangle r = Rectangle.newInstance(4, 0, 4, 0);
        //column 1
        assertEquals(map.count(MANAGER, r), 2);
        Map map1 = Map.newInstance();
        //column 2
        assertEquals(map1.count(MANAGER, r), 0);
        map1.insert(MANAGER, 5, 5);
        //column 3
        assertEquals(map1.count(MANAGER, r), 0);
        //column 4
        map1.insert(WELL, 3, 4);
        assertEquals(map1.count(ALIEN, r), 0);
        //test for bad data: null argument
        assertEquals(map1.count(null, r), 0);
        assertEquals(map1.count(ALIEN, null), 0);
        //Another bad data: a rectangle with small coordinates > big coordinates
        Rectangle r1 = Rectangle.newInstance(3, 4, 3, 4);
        assertEquals(map.count(MANAGER, r1), 0);


        //Good data(legacy) from previous assignment's test
        Map map2 = Map.newInstance();
        Rectangle r2 = Rectangle.newInstance(3, 0, 3, 0);
        assertEquals(map2.count(ALIEN, r2), 0);
        map2.insert(ALIEN, 1, 2);
        map2.insert(ALIEN, 2, 3);
        map2.insert(ALIEN, 3, 4);
        assertEquals(map2.count(ALIEN, r2), 1);
        map2.insert(WELL, 1, 3);
        map2.insert(MANAGER, 1, 1);
        assertEquals(map2.count(ALIEN, r2), 1);
        assertEquals(map2.count(MANAGER, r2), 1);
        assertEquals(map2.count(WELL, r2), 0);
    }

    @Test(expected=NullPointerException.class)
    public void testCountWithRectilinearPolygon() {
        Map map = Map.newInstance();
        map.insert(MANAGER, 1, 2);
        map.insert(MANAGER, 2, 1);
        Rectangle r = Rectangle.newInstance(3, 0, 3, 0);
        Rectangle r1 = Rectangle.newInstance(5, 3, 5, 3);
        Rectangle r2 = Rectangle.newInstance(8, 5, 5, 3);
        RectilinearPolygon rectilinearPolygon = RectilinearPolygon.newInstance(List.of(r, r1, r2));
        // nominal case with locations inside the rectilinear polygon
        assertEquals(map.count(ALIEN, rectilinearPolygon), 0);
        assertEquals(map.count(MANAGER, rectilinearPolygon), 2);
        // case with locations shared by 2 different rectangles (edge case)
        map.insert(MANAGER, 3, 3);
        assertEquals(map.count(MANAGER, rectilinearPolygon), 3);
        // another edge case
        map.insert(MANAGER, 5, 4);
        assertEquals(map.count(MANAGER, rectilinearPolygon), 4);
        // locations outside the polygon
        map.insert(MANAGER, 10, 10);
        assertEquals(map.count(MANAGER, rectilinearPolygon), 4);


        // bad data cases
        //test for bad data: null argument
        assertEquals(map.count(null, r), 0);
        assertEquals(map.count(ALIEN, null), 0);
        //Another bad data: a rectilinear polygon with disjoint rectangles
        Rectangle r3 = Rectangle.newInstance(11, 9, 11, 9);
        RectilinearPolygon rectilinearPolygon1 = RectilinearPolygon.newInstance(List.of(r, r3));
        assertEquals(map.count(MANAGER, rectilinearPolygon1), 0);
        //Another bad data: a rectilinear polygon with overlapped rectangles
        Rectangle r4 = Rectangle.newInstance(2, 0, 2, 0);
        RectilinearPolygon rectilinearPolygon2 = RectilinearPolygon.newInstance(List.of(r, r4));
        assertEquals(map.count(MANAGER, rectilinearPolygon2), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void stressTest() {
        Random ran = new Random();
        for (int i = 0; i < 10000; i++) {
            Map map = Map.newInstance();
            Rectangle r1 = Rectangle.newInstance(ran.nextInt(), ran.nextInt(), ran.nextInt(), ran.nextInt());
            Rectangle r2 = Rectangle.newInstance(ran.nextInt(), ran.nextInt(), ran.nextInt(), ran.nextInt());
            Rectangle r3 = Rectangle.newInstance(ran.nextInt(), ran.nextInt(), ran.nextInt(), ran.nextInt());
            Rectangle r4 = Rectangle.newInstance(ran.nextInt(), ran.nextInt(), ran.nextInt(), ran.nextInt());
            Rectangle r5 = Rectangle.newInstance(ran.nextInt(), ran.nextInt(), ran.nextInt(), ran.nextInt());
            Rectangle r6 = Rectangle.newInstance(ran.nextInt(), ran.nextInt(), ran.nextInt(), ran.nextInt());
            RectilinearPolygon rectilinearPolygon = RectilinearPolygon.newInstance(List.of(r1, r2, r3, r4, r5, r6));
            Type[] typeList = {WELL, ALIEN, MANAGER, MINE, FIELD};
            Type randomType = typeList[(int)Math.random() * typeList.length];
            map.count(randomType, rectilinearPolygon);
        }
    }
}