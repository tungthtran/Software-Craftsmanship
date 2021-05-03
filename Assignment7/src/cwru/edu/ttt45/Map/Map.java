package cwru.edu.ttt45.Map;

import java.util.*;

/** A class representing a map of objects
 * @author <em>Kris Tran</em>
 */
public class Map {

    /** Enum containing the types of the objects
     */
    public enum Type{WELL, ALIEN, MANAGER, MINE, FIELD};

    /** a Hashtable mapping the location to a type
     */
    private final java.util.Map<Location, Type> typeTable;

    /** Construct a Map with an empty type map
      */
    private Map() {
        this.typeTable = new Hashtable<>();
    }


    /** return an instance of the map
     * @return an instance of the data structure
     */
    public static final Map newInstance() {
        return new Map();
    }


    /** check the availability of a location
     * @param location the location to be checked
     * @return whether the location has been occupied or not
     */
    private boolean isOccupied(Location location) {
        assert Objects.nonNull(location);
        return typeTable.containsKey(location);
    }


    /** insert an object of type t to a location
     * @param t the type of the object to be inserted
     * @param x the x-coordinate of the location
     * @param y the y-coordinate of the location
     */
    public void insert(Type t, int x, int y) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
        Location location = new Location(x, y);
        if (!isOccupied(location)) {
            //put the location as key and t as value to the typeMap
            typeTable.put(location, t);
        }
        // else inserted unsuccessfully
        else {
            throw new IllegalStateException("The location is already occupied");
        }
    }

    /** search inside a polygon and return a set of satisfied locations
     * @param t the type of those objects being searched for
     * @param p the polygon to be searched inside
     * @return a set of locations contains the objects of type t
     */
    private Set<Location> setOfMatchedLocation(Type t, Polygon p) {
        //pre-condition: the type and the rectangle are not null
        Objects.requireNonNull(t);
        Objects.requireNonNull(p);
        Set<Location> locationSet = new HashSet<>();
        // Note: object lying on the border line of the rectangle will be counted
        for (Location current : typeTable.keySet()) {
            //if the value corresponding to the key current in typeMap is in r and equals t
            if (p.contains(current) && t.equals(typeTable.get(current))) {
                locationSet.add(current);
            }
            // else do nothing because it is empty or not the wanted type
        }
        return locationSet;
    }


    /** count the number of objects of type t inside polygon p
     * @param t the type of those objects being searched for
     * @param p the polygon to be searched inside
     * @return the number of objects of type t inside the polygon p
     */
    public int count(Type t, Polygon p) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(p);
        Set<Location> countingSet = new HashSet<>();
        for (Rectangle rectangle : p.getListOfRec()) {
                // As Set contains only unique elements, we no longer worry about duplicating
                // elements in the border
                countingSet.addAll(setOfMatchedLocation(t, rectangle));
        }
        return countingSet.size();
    }

    class Test {
        // testing private method of the class
        public boolean isOccupied(Map map, Location location) {
            java.util.Map<Location, Type> table = map.typeTable;
            return table.containsKey(location);
        }
    }
}


