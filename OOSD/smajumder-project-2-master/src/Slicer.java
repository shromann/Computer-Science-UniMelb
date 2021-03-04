import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;
import java.util.List;

/**
 * A regular slicer.
 */
public class Slicer extends Sprite {

    private static String IMAGE_FILE = "res/images/slicer.png";
    private final List<Point> polyline;
    private int targetPointIndex;
    private boolean finished;
    /**
     * The constant SLICER_SPEED.
     */
    protected static final double SLICER_SPEED = 2;
    /**
     * The constant HEALTH.
     */
    protected static final int HEALTH = 1;
    /**
     * The constant REWARD.
     */
    protected static final int REWARD = 2;
    /**
     * The constant PENALTY.
     */
    protected static final int PENALTY = 1;

    /**
     * The Speed.
     */
    protected double SPEED = SLICER_SPEED;
    /**
     * The Health.
     */
    protected int health = HEALTH;
    /**
     * The Reward.
     */
    protected int reward = REWARD;
    /**
     * The Penalty.
     */
    protected int penalty = PENALTY;

    private Vector2 current;

    /**
     * Creates a new Slicer
     *
     * @param polyline The polyline that the slicer must traverse (must have at least 1 point)
     * @param image    the image
     */
    public Slicer(List<Point> polyline, String image) {
        super(polyline.get(0), image);
        this.polyline = polyline;
        this.targetPointIndex = 1;
        this.finished = false;
    }

    /**
     * Instantiates a new Slicer.
     *
     * @param polyline the polyline
     */
    public Slicer(List<Point> polyline) {
        super(polyline.get(0), IMAGE_FILE);
        this.polyline = polyline;
        this.targetPointIndex = 1;
        this.finished = false;
    }

    /**
     * Instantiates a new Slicer.
     *
     * @param point the point
     */
    public Slicer(Point point) {
        super(point, IMAGE_FILE);
        this.polyline = ShadowDefend.polyline;
        this.targetPointIndex = 1;
        this.finished = false;
    }

    /**
     * Updates the current state of the slicer. The slicer moves towards its next target point in
     * the polyline at its specified movement rate.
     */

    public void update(Input input) {
        if (finished) {
            return;
        }
        // Obtain where we currently are, and where we want to be
        Point currentPoint = getCenter();
        Point targetPoint = polyline.get(targetPointIndex);
        // Convert them to vectors to perform some very basic vector math
        Vector2 target = targetPoint.asVector();
        current = currentPoint.asVector();
        Vector2 distance = target.sub(current);
        // Distance we are (in pixels) away from our target point
        double magnitude = distance.length();
        // Check if we are close to the target point
        if (magnitude < SPEED * ShadowDefend.getTimescale()) {

            // Check if we have reached the end
            if (targetPointIndex == polyline.size() - 1) {
                finished = true;
                return;
            } else {
                // Make our focus the next point in the polyline
                targetPointIndex += 1;

            }
        }
        // Move towards the target point
        // We do this by getting a unit vector in the direction of our target, and multiplying it
        // by the speed of the slicer (accounting for the timescale)
        super.move(distance.normalised().mul(SPEED * ShadowDefend.getTimescale()));
        // Update current rotation angle to face target point
        setAngle(Math.atan2(targetPoint.y - currentPoint.y, targetPoint.x - currentPoint.x));
        super.update(input);
    }


    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Shot.
     *
     * @param DAMAGE the damage
     */
    public void shot(double DAMAGE){
        health -= DAMAGE;
    }

    /**
     * Is dead int.
     *
     * @return the int
     */
    public int isDead(){
        if(health <= 0){
            return REWARD;
        }
        return 0;
    }

}
