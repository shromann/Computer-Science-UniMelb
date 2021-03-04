import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

/**
 * The type Active tower.
 */
public abstract class ActiveTower extends Tower{

    /**
     * The Cooldown period.
     */
    protected final double cooldownPeriod;
    private double count;
    /**
     * The Radius.
     */
    protected double radius;
    /**
     * The Bounds.
     */
    protected Rectangle bounds;
    /**
     * The Weapon.
     */
    protected Weapon weapon;
    private boolean triggered;
    private Slicer target;
    private double damage;
//    private Rectangle target;

    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     * @param c        the c
     * @param damage   the damage
     */
    public ActiveTower(Point point, String imageSrc, double c, double damage) {
        super(point, imageSrc); // sets the pos and angle
        triggered = false;
        count = 0;
        cooldownPeriod = c;
        this.damage = damage;
        super.type = "active";
    }

    public void update() {
        setAngle(weapon.getAngle());
        super.update(super.pos, super.angle);
        if(ShadowDefend.waveStarted) {
            if (!triggered && count == 0) {
                target = findTarget();
                weapon.setTarget(target.getCenter().asVector());
                setAngle(weapon.getAngle());
                triggered = true;
            }
            else if (triggered){
                weapon.update();
            }
            if (triggered && weapon.getRect().intersects(target.getRect())){
                if(weapon.getRect().intersects(target.getCenter())){
                    weapon.setRect(this.pos);
                }
                weapon.setRect(this.pos);
                triggered = false;
                count = cooldownPeriod;
                target.shot(damage);
                targetHit();
            }
            if (count > 0){
                count--;
            }
        }



        Wave.removeTheDead();

    }

    /**
     * Target hit.
     */
    protected void targetHit(){
        for (Event event: Wave.events){
            for (Slicer slicer: event.getSlicers()){
                if (slicer.health == 0){
                    event.getSlicers().remove(slicer);
                    break;
                }

            }
        }
    }

    /**
     * Find target slicer.
     *
     * @return the slicer
     */
// find target method
    protected Slicer findTarget(){
        for (Event event: Wave.events){
            for(Slicer slicer: event.getSlicers()){
                if (bounds.intersects(slicer.getRect())) return slicer;
            }
        }
        return new Slicer(this.pos);
    }

//    // find target method
//    protected Rectangle findTarget(){
//        for (Event event: Wave.events){
//            for(Slicer slicer: event.getSlicers()){
//                if (bounds.intersects(slicer.getRect())) return slicer.getRect();
//            }
//        }
//        return this.getRect();
//    }





}
