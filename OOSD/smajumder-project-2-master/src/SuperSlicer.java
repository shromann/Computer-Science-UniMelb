import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.List;

/**
 * The type Super slicer.
 */
public class SuperSlicer extends Slicer {

    private static String IMAGE_FILE = "res/images/superslicer.png";
    /**
     * The constant SUPER_SPEED.
     */
    protected static final double SUPER_SPEED = (0.75) * Slicer.SLICER_SPEED;
    private static final int SUPER_REWARD = 15;
    private static final int CHILD_NUM = 2;
    /**
     * The Reward.
     */
    protected int reward;
    /**
     * The Penaly.
     */
    protected int penaly;

    /**
     * Creates a new Super Slicer
     *
     * @param polyline The polyline that the slicer must traverse (must have at least 1 point)
     */
    public SuperSlicer(List<Point> polyline) {
        super(polyline, IMAGE_FILE);
        this.SPEED = SUPER_SPEED;
        this.health = 15;
    }

    @Override
    public void update(Input input) {
        super.update(input);

    }
}

