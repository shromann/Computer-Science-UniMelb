import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * The type Super tank.
 */
public class SuperTank extends ActiveTower {

    private static final double RADIUS = 150;
    private static final double COOLDOWNPERIOD = (1000 / ShadowDefend.toSecondsConversion) * ShadowDefend.FPS;
    /**
     * The constant DAMAGE.
     */
    protected static final double DAMAGE = Tank.DAMAGE * 3;

    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     */
    public SuperTank(Point point, String imageSrc) {
        super(point, imageSrc, COOLDOWNPERIOD, DAMAGE);
        this.radius = RADIUS;
        Point mark = new Point(this.getCenter().x-RADIUS, this.getCenter().y-RADIUS);
        this.bounds = new Rectangle(mark, 2*RADIUS, 2*RADIUS);
        this.weapon = new Weapon(point, "res/images/supertank_projectile.png",3*Tank.DAMAGE);
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
