import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * The type Tank.
 */
public class Tank extends ActiveTower {

    private static final double RADIUS = 100;
    private static final double COOLDOWNPERIOD = (500 / ShadowDefend.toSecondsConversion) * ShadowDefend.FPS;

    private static final String WEAPON_IMAGE_FILE = "res/images/tank_projectile.png";
    /**
     * The constant DAMAGE.
     */
    protected static final double DAMAGE = 1;


    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     */
    public Tank(Point point, String imageSrc) {
        super(point, imageSrc, COOLDOWNPERIOD, DAMAGE);
        this.radius = RADIUS;
        Point mark = new Point(this.getCenter().x-RADIUS, this.getCenter().y-RADIUS);
        this.bounds = new Rectangle(mark, 2*RADIUS, 2*RADIUS);
        this.weapon = new Weapon(point, WEAPON_IMAGE_FILE, DAMAGE);
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    protected Slicer findTarget(){
        return super.findTarget();
    }




}
