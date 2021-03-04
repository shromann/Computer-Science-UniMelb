import bagel.Input;
import bagel.util.Point;
import org.lwjgl.system.CallbackI;

/**
 * The type Air support.
 */
public class AirSupport extends PassiveTower {
    private static final double SPEED = 3;
    private static final String EXPLOSIVE_IMAGE_FILE =  "res/images/explosive.png";
    private static final double DAMAGE = 500;

    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     * @param dir      the dir
     */
    public AirSupport(Point point, String imageSrc, boolean dir) {
        super(point, imageSrc);
        super.SPEED = SPEED;
        super.EXPLOSIVE_IMAGE = EXPLOSIVE_IMAGE_FILE;
        super.DAMAGE = DAMAGE;
        super.dir = dir;
    }

    @Override
    public void update() {
        super.update();
    }
}
