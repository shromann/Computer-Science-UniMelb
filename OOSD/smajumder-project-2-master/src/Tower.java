import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * The type Tower.
 */
abstract class Tower extends Sprite {

    /**
     * The Pos.
     */
    protected Point pos;
    /**
     * The Angle.
     */
    protected double angle;
    /**
     * The Type.
     */
    protected String type;
    /**
     * The Has ended.
     */
    protected Boolean hasEnded = false;


    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     */
    public Tower(Point point, String imageSrc) {
        super(point, imageSrc);
        pos = point;
        angle = 0;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    // draw the tower
    public void update(){
        super.update(getCenter(), angle);
    }

    /**
     * Has ended boolean.
     *
     * @return the boolean
     */
    public boolean hasEnded(){
        return hasEnded;
    }







}
