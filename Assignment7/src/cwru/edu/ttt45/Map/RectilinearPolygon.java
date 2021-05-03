package cwru.edu.ttt45.Map;

import java.util.List;

/** A class representing the rectilinear polygon for querying the data structure
 * @author <em>Kris Tran</em>
 */
public class RectilinearPolygon implements Polygon {
    /** a list of rectangles constituting the polygon
     */
    private List<Rectangle> listOfRec;

    /** Construct a rectilinear polygon with a list of rectangles
     * @param listOfRec a list of rectangles to constitute the polygon
     */
    private RectilinearPolygon(List<Rectangle> listOfRec) {
        this.listOfRec = listOfRec;
    }

    /** return an instance of the rectilinear polygon
     * @param listOfRec a list of rectangles to constitute the polygon
     * @return an instance of the rectilinear polygon
     */
    public static final RectilinearPolygon newInstance(List<Rectangle> listOfRec) {
        checkOverlap(listOfRec);
        checkDisjoint(listOfRec);
        return new RectilinearPolygon(listOfRec);
    }

    /** check if any pair of rectangles in the list are disjoint
     * @param listOfRec a list of rectangles to be checked
     */
    private static void checkDisjoint(List<Rectangle> listOfRec) {
        listOfRec.stream().forEach(rectangle -> {
            boolean isConnected = false;
            for (Rectangle toComparedRectangle : listOfRec) {
                if (rectangle != toComparedRectangle && !rectangle.isDisjoint(toComparedRectangle)) {
                    isConnected = true;
                }
            }
            if (!isConnected) {
                throw new IllegalArgumentException("The rectangles are disjoint");
            }
        });
    }

    /** check if any pair of rectangles in the list overlap
     * @param listOfRec a list of rectangles to be checked
     */
    private static void checkOverlap(List<Rectangle> listOfRec) {
        listOfRec.stream().forEach(rectangle -> {
            for (Rectangle toComparedRectangle : listOfRec) {
                if (rectangle != toComparedRectangle && rectangle.isOverlap(toComparedRectangle)) {
                    throw new IllegalArgumentException("The rectangles overlap");
                }
            }
        });
    }

    /** check if the rectilinear polygon contains the location
     * @param location the location to be checked
     * @return whether the rectilinear polygon contains the location
     */
    @Override
    public boolean contains(Location location) {
        return listOfRec.stream().anyMatch(rectangle -> rectangle.contains(location));
    }

    /** return a list of rectangles the rectilinear polygon contains
     * @return a list of rectangles the rectilinear polygon contains
     */
    @Override
    public List<Rectangle> getListOfRec() {
        return this.listOfRec;
    }

}
