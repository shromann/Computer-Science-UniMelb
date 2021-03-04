import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Passive tower.
 */
abstract class PassiveTower extends Tower {

    /**
     * The Explosives.
     */
// List of weapons
    protected static List<Weapon> explosives;

    /**
     * The Explosive image.
     */
// Weapon Fields
    protected String EXPLOSIVE_IMAGE;
    /**
     * The Damage.
     */
    protected double DAMAGE;
    private double frameCount;

    // Explosive detonate time
    private static final double nextDropTime = ShadowDefend.FPS * 2;
    private double detonateTime;

    /**
     * The constant horizontal.
     */
    public static boolean horizontal;

    /**
     * The Speed.
     */
    protected double SPEED;
    /**
     * The Dir.
     */
    protected boolean dir;
    private boolean triggered;


    /**
     * Instantiates a new Passive tower.
     *
     * @param point    the point
     * @param imageSrc the image src
     */
    public PassiveTower(Point point, String imageSrc) {
        super(point, imageSrc);
        frameCount = 0;
        explosives = new ArrayList<>();
        detonateTime = ShadowDefend.FPS + (Math.random() * (ShadowDefend.FPS));
        super.type = "passive";
        triggered = false;
    }

    @Override
    public void update() {
        frameCount += ShadowDefend.getTimescale();

        Vector2 target;
        if (dir) {
            target = new Point(getCenter().x + SPEED, getCenter().y).asVector();
        }
        else {
            target = new Point(getCenter().x, getCenter().y + SPEED).asVector();
        }

        Vector2 curr = getCenter().asVector();
        Vector2 distance = target.sub(curr);

        super.move(distance.normalised().mul(SPEED * ShadowDefend.getTimescale()));
        if (frameCount >= detonateTime && !triggered){
            addExplosive(getCenter(),frameCount);
            detonateTime = ShadowDefend.FPS + (Math.random() * (ShadowDefend.FPS));
            triggered = true;
        }

        detonateExplosives();
        updateExplosives();


        if (explosives.size() == 0 && getRect().topLeft().x >= ShadowDefend.WIDTH || getRect().topLeft().y >= ShadowDefend.HEIGHT ){
            super.hasEnded = true;
        }

        super.update();
    }

    // Explosive will stay on at location Point and leave after
    private void addExplosive(Point point, double time){
        explosives.add(new Weapon(point, EXPLOSIVE_IMAGE, DAMAGE, nextDropTime + time));
    }

    private void detonateExplosives(){
        for (Weapon explosive: explosives){
            if (explosive.getTimeToDetonate() <= frameCount && triggered){
                detonateExplosive(explosive);
                explosives.remove(explosive);
                triggered = false;
                break;
            }
        }
    }

    private void updateExplosives(){
        for (Weapon explosive: explosives){
            explosive.draw();
        }
    }

    private void detonateExplosive(Weapon explosive){
        for (Event event: Wave.events){
            for (Slicer slicer: event.getSlicers()){
                System.out.println(explosive.getAREA_OF_DAMAGE());
                System.out.println(slicer.getCenter());
                if (explosive.getAREA_OF_DAMAGE().intersects(slicer.getCenter())){
                    System.out.println("*8");
                    slicer.shot(DAMAGE);
                }
                ShadowDefend.funds += slicer.isDead();
            }
        }
    }



    @Override
    public void setAngle(double angle) {
        super.setAngle(angle);
    }



}
