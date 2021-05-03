package cwru.edu.ttt45.Map;

import java.util.List;

/** A class representing the rectangle for querying the data structure
 * @author <em>Kris Tran</em>
 */
public class Rectangle implements Polygon {

    /** the bigger x-coordinate of the rectangle
     */
    private final int bigXCord;
    /** the smaller x-coordinate of the rectangle
     */
    private final int smallXCord;
    /** the bigger y-coordinate of the rectangle
     */
    private final int bigYCord;
    /** the smaller y-coordinate of the rectangle
     */
    private final int smallYCord;


    /** Construct a rectangle with 4 given locations
     * @param bigXCord the big x-coordinate of the rectangle
     * @param smallXCord the small x-coordinate of the rectangle
     * @param bigYCord the big y-coordinate of the rectangle
     * @param smallYCord the small y-coordinate of the rectangle
     */
    private Rectangle(int bigXCord, int smallXCord, int bigYCord, int smallYCord) {
        this.bigXCord = bigXCord;
        this.smallXCord = smallXCord;
        this.bigYCord = bigYCord;
        this.smallYCord = smallYCord;
    }

    /** return an instance of the rectangle
     * @param bigXCord the big x-coordinate of the rectangle
     * @param smallXCord the small x-coordinate of the rectangle
     * @param bigYCord the big y-coordinate of the rectangle
     * @param smallYCord the small y-coordinate of the rectangle
     * @return a new instance of the rectangle
     */
    public static final Rectangle newInstance(int bigXCord, int smallXCord, int bigYCord, int smallYCord) {
        if (bigXCord <= smallXCord || bigYCord <= smallYCord) {
            throw new IllegalArgumentException("The big coordinate must greater than small coordinate");
        }
        return new Rectangle(bigXCord, smallXCord, bigYCord, smallYCord);
    }

    /** return the big x-coordinate of the rectangle
     * @return the big x-coordinate of the rectangle
     */
    public final int getBigXCord() {
        return bigXCord;
    }

    /** return the small x-coordinate of the rectangle
     * @return the small x-coordinate of the rectangle
     */
    public final int getSmallXCord() {
        return smallXCord;
    }

    /** return the big y-coordinate of the rectangle
     * @return the big y-coordinate of the rectangle
     */
    public final int getBigYCord() {
        return bigYCord;
    }

    /** return the small y-coordinate of the rectangle
     * @return the small y-coordinate of the rectangle
     */
    public final int getSmallYCord() {
        return smallYCord;
    }

    /** check if this rectangle is on the left of r (not overlap)
     * @param r the rectangle to be checked
     * @return whether this rectangle is on the left of r (not overlap)
     */
    private boolean onTheLeftOf(Rectangle r) {
        return this.getBigXCord() <= r.getSmallXCord();
    }

    /** check if this rectangle is on the right of r (not overlap)
     * @param r the rectangle to be checked
     * @return whether this rectangle is on the right of r (not overlap)
     */
    private boolean onTheRightOf(Rectangle r) {
        return r.getBigXCord() <= this.getSmallXCord();
    }

    /** check if this rectangle is over r (not overlap)
     * @param r the rectangle to be checked
     * @return whether this rectangle is over r (not overlap)
     */
    private boolean isOver(Rectangle r) {
        return this.getSmallYCord() >= r.getBigYCord();
    }

    /** check if this rectangle is under r (not overlap)
     * @param r the rectangle to be checked
     * @return whether this rectangle is under r (not overlap)
     */
    private boolean isUnder(Rectangle r) {
        return r.getSmallYCord() >= this.getBigYCord();
    }

    /** check if two rectangles overlap
     * @param r the rectangle to be checked
     * @return whether the two rectangles overlap
     */
    public boolean isOverlap(Rectangle r) {
        if (isOver(r) || isUnder(r) || onTheRightOf(r) || onTheLeftOf(r)) {
            return false;
        }
        else {
            return true;
        }
    }

    /** check if the two rectangles are disjoint horizontally
     * @param r the rectangle to be checked
     * @return whether the two rectangles are disjoint horizontally
     */
    private boolean disJointHorizontally(Rectangle r) {
        return this.getBigXCord() < r.getSmallXCord() || r.getBigXCord() < this.getSmallXCord();
    }


    /** check if the two rectangles are disjoint vertically
     * @param r the rectangle to be checked
     * @return whether the two rectangles are disjoint vertically
     */
    private boolean disJointVertically(Rectangle r) {
        return this.getSmallYCord() > r.getBigYCord() || r.getSmallYCord() > this.getBigYCord();
    }

    /** check if two rectangles are disjoint
     * @param r the rectangle to be checked
     * @return whether the two rectangles are disjoint
     */
    public boolean isDisjoint(Rectangle r) {
        return disJointHorizontally(r) || disJointVertically(r);
    }

    /** check if the rectangle contains the location
     * @param location the location to be checked
     * @return whether the rectangle contains the location
     */
    @Override
    public boolean contains(Location location) {
        return getSmallXCord() <= location.getXCord() && location.getXCord() <= getBigXCord()
                && getSmallYCord() <= location.getYCord() && location.getYCord() <= getBigYCord();
    }

    /** return a list of Rectangles the rectangle contains (itself)
     * @return a list of Rectangles the rectangle contains (itself)
     */
    @Override
    public List<Rectangle> getListOfRec() {
        return List.of(this);
    }
}
