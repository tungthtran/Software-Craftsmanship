package cwru.edu.ttt45.Map;

import java.util.List;

/** An interface representing a polygon
 * @author <em>Kris Tran</em>
 */
public interface Polygon {

    /** check if the polygon contains the location
     * @param location the location to be checked
     * @return whether the polygon contains the location
     */
    boolean contains(Location location);

    /** return a list of Rectangle the Polygon contains
     * @return a list of Rectangle the Polygon contains
     */
    List<Rectangle> getListOfRec();
}
