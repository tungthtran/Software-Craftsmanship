package cwru.edu.ttt45.Map;

import java.util.Objects;

/** A class representing a location in the xy coordinate
 * @author <em>Kris Tran</em>
 */
public class Location {
    /** x-coordinate of the location
     */
    private final int xCord;
    /** y-coordinate of the location
     */
    private final int yCord;

    /** Construct a location with x and y coordinates
     * @param xCord the x-coordinate of the location
     * @param yCord the y-coordinate of the location
     */
    public Location(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
    }

    /** return the x-coordinate of the location
     * @return the x-coordinate of the location
     */
    public final int getXCord() {
        return xCord;
    }

    /** return the y-coordinate of the location
     * @return the y-coordinate of the location
     */
    public final int getYCord() {
        return yCord;
    }

    /** Indicates whether 2 locations have the same x and y coordinates
     * @param obj the object to be compared
     * @return whether 2 objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass()!= this.getClass()) {
            return false;
        }
        Location location = (Location) obj;
        return (this.getXCord() == location.getXCord())
                && (this.getYCord() == location.getYCord());
    }

    /** return the hash code of each location
     * @return the hash code of each location
     */
    @Override
    public int hashCode() {
        return Objects.hash(xCord,yCord);
    }
}
