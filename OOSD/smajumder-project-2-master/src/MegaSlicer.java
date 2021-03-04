import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.List;

/**
 * The type Mega slicer.
 */
public class MegaSlicer extends Slicer {

    private static final String IMAGE_FILE = "res/images/megaslicer.png";
    /**
     * The constant MEGA_SPEED.
     */
    protected static final double MEGA_SPEED = SuperSlicer.SUPER_SPEED;
    private static final int CHILD_NUM = 2;
    private static final int MEGA_REWARD = 10;
    /**
     * The Penaly.
     */
    protected int penaly;

    /**
     * Creates a new Super Slicer
     *
     * @param polyline The polyline that the slicer must traverse (must have at least 1 point)
     */
    public MegaSlicer(List<Point> polyline) {
        super(polyline, IMAGE_FILE);
        this.SPEED = MEGA_SPEED;
    }

    @Override
    public void update(Input input) {
        super.update(input);

    }
}

