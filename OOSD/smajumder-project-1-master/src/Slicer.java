import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Vector2;

// Slicer Object
public class Slicer {

    /* Constants */
        // "Image" and the Location of the slicer
    private final Image image = new Image("res/images/slicer.png");
    private final int MINIMUM_SPEED = 0;
    private final int INITIAL_SPEED = 1;

    /* Attributes */
        // Slicer starts at point "starting" and ends at vector "ending"
    private Vector2 starting;
    private Vector2 ending;

        // Slicer's current "location" and last location initialised.
    private boolean moving;
    private Point location;
    private Point last_location = new Point(0,0);
    private boolean lastTraverse;


        // Slicer's "direction" of travel and its "speed"
    private Vector2 direction;
    private double speed = INITIAL_SPEED;
    private double FrameRate;
    private int pathIndex; // index of the path it currently is on.

        // Slicer's "angle" that its facing
    private DrawOptions angle = new DrawOptions();

    /* Constructor: Slicer (sets all the necessary
       variables of the slicer to travel in its lane)
    */
    public Slicer(Point starting, Point ending) {
        this.starting = starting.asVector();
        this.ending = ending.asVector();
        this.location = this.starting.asPoint();
        this.direction = this.ending.sub(this.starting).normalised();
        this.pathIndex = 1;
        this.setAngel();
        this.lastTraverse = false;
    }

    /* Methods */
        // Method: Move the splicer to its new point in its direction.
    public void move(){
        last_location = location;
        location = location.asVector().add(direction.mul(speed)).asPoint();
    }

        // Method: speed up or slow down the splicer.
    public void speedUp(){
        speed++;
    }
    public void slowDown(){
        // not letting the slicer go backwards;
        speed--;
        if (speed <= MINIMUM_SPEED){
            speed++;
        }
    }

        // Method: sets the angle of the splicer should be facing in the frame.
    public void setAngel(){
        Vector2 side = ending.sub(starting);
        double x = side.x;
        double y = side.y;
        double theta = Math.atan2(y,x);
        angle.setRotation(theta);
    }

        // Method: checks if the splicer has reached the end of its lane
    public boolean hasReachedEnd(){
        return (location.equals(last_location)) ||
                location.distanceTo(ending.asPoint()) > last_location.distanceTo(ending.asPoint());

    }

        // Method: gets the next point in the path for the splicer
    public void getNextPoint(Point nextEndPoint){
        if(this.lastTraverse){
            this.location = nextEndPoint;
        }
        else {
            this.starting = this.ending;
            this.ending = nextEndPoint.asVector();
            this.location = this.starting.asPoint();
            this.direction = this.ending.sub(this.starting).normalised();
            this.setAngel();
        }

    }

    public int getPathIndex() {
        return pathIndex;
    }
    public void setPathIndex(int pathIndex) {
        this.pathIndex = pathIndex;
    }

        // Method: returns the framerate of the splicer.
    public double getFrameRate() {
        return this.FrameRate;
    }

        // Method: sets the framerate of the splicer. (also used for finding its spawn frame later)
    public void setFrameRate(double FrameRate) {
        this.FrameRate = FrameRate;
    }

        // Method: returns if the splicer at a moving or not
    public boolean isMoving() {
        return moving;
    }

        // Method: marks the splicer as actively moving
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

        // Method: renders out the splicer at a particular frame.
    public void render(){
        image.draw(location.x, location.y, angle);
    }


    public boolean onLastTraverse() {
        return lastTraverse;
    }
    public void setLastTraverse(boolean lastTraverse) {
        this.lastTraverse = lastTraverse;
    }


}
