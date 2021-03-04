import bagel.Input;
import bagel.util.Point;
import bagel.util.Vector2;

import java.util.List;

/**
 * The type Apex slicer.
 */
public class ApexSlicer extends Slicer {
    private static final String IMAGE_FILE = "res/images/apexslicer.png";
    private final double APEX_SPEED = 0.5 * MegaSlicer.MEGA_SPEED;
    private static final int CHILD_NUM = 4;
    private static final int FACTOR = 2;
    private static final int HEALTH_FACTOR = 25;
    private static final int APEX_REWARD = 150;

    /**
     * Creates a new Slicer
     *
     * @param polyline The polyline that the slicer must traverse (must have at least 1 point)
     */

    public ApexSlicer(List<Point> polyline) {
        super(polyline, IMAGE_FILE);
        this.SPEED = APEX_SPEED;

    }

    @Override
    public void update(Input input){
        super.update(input);
    }

}
