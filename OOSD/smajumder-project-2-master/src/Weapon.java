import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

/**
 * The type Weapon.
 */
public class Weapon extends Sprite{
    private final double damage;
    private final double SPEED = 10;

    public double getAngle() {
        return angle;
    }

    private double angle;

    /**
     * Gets time to detonate.
     *
     * @return the time to detonate
     */
    public double getTimeToDetonate() {
        return timeToDetonate;
    }

    private final double timeToDetonate;

    /**
     * Gets area of damage.
     *
     * @return the area of damage
     */
    public Rectangle getAREA_OF_DAMAGE() {
        return AREA_OF_DAMAGE;
    }

    private Rectangle AREA_OF_DAMAGE;
    private static final double explosive_radius = 200;

    private Vector2 target;

    /**
     * Sets target.
     *
     * @param target the target
     */
    public void setTarget(Vector2 target) {
        this.target = target;
    }

    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     * @param damage   the damage
     */
    public Weapon(Point point, String imageSrc, double damage) {
        super(point, imageSrc);
        this.damage = damage;
        timeToDetonate = 0;
        AREA_OF_DAMAGE = new Rectangle(point.x - explosive_radius, point.y - explosive_radius, explosive_radius*2, 2*explosive_radius);

    }

    /**
     * Instantiates a new Weapon.
     *
     * @param point    the point
     * @param imageSrc the image src
     * @param damage   the damage
     * @param time     the time
     */
    public Weapon(Point point, String imageSrc, double damage, double time) {
        super(point, imageSrc);
        this.damage = damage;
        this.timeToDetonate = time;
        AREA_OF_DAMAGE = new Rectangle(point.x - explosive_radius, point.y - explosive_radius, explosive_radius, explosive_radius);
    }

    public void update(){
        Vector2 trajectory = target.sub(getCenter().asVector()).normalised();
        angle = Math.atan2(trajectory.x, trajectory.y) - 90;
        super.move(trajectory.mul(SPEED * ShadowDefend.getTimescale()));
        super.update(getCenter(),0);
    }

    /**
     * Update.
     *
     * @param point the point
     */
    public void update(Point point){
        super.update(point,0);
    }


    /**
     * Draw.
     */
    public void draw() {
        super.update();
    }
}
