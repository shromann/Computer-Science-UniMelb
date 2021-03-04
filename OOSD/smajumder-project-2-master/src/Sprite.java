import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

/**
 * Represents a game entity
 */
public abstract class Sprite {

    private final Image image;
    private Rectangle rect;
    private double angle;
    private boolean finished;

    /**
     * Creates a new Sprite (game entity)
     *
     * @param point    The starting point for the entity
     * @param imageSrc The image which will be rendered at the entity's point
     */
    public Sprite(Point point, String imageSrc) {
        this.image = new Image(imageSrc);
        this.rect = image.getBoundingBoxAt(point);
        this.angle = 0;
        this.finished = false;
    }


    /**
     * Gets rect.
     *
     * @return the rect
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Sets rect.
     *
     * @param pos the pos
     */
    public void setRect(Point pos) {
        this.rect = image.getBoundingBoxAt(pos);
    }

    /**
     * Moves the Sprite by a specified delta
     *
     * @param dx The move delta vector
     */
    public void move(Vector2 dx) {
        rect.moveTo(rect.topLeft().asVector().add(dx).asPoint());
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return getRect().centre();
    }

    /**
     * Sets angle.
     *
     * @param angle the angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Updates the Sprite. Default behaviour is to render the Sprite at its current position.
     *
     * @param input the input
     */
// to update the slicer
    public void update(Input input) {
        image.draw(getCenter().x, getCenter().y, new DrawOptions().setRotation(angle));
    }

    /**
     * Update.
     *
     * @param pos   the pos
     * @param angle the angle
     */
// to update the towers
    public void update(Point pos, double angle) {
        image.draw(pos.x, pos.y, new DrawOptions().setRotation(angle));
    }

    /**
     * Update.
     */
    public void update() {
        image.draw(getCenter().x, getCenter().y);
    }

}