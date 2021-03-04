import bagel.AbstractGame;
import bagel.Input;
import bagel.Keys;
import bagel.map.TiledMap;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * ShadowDefend, a tower defence game.
 */
public class ShadowDefend extends AbstractGame {

    /**
     * The constant HEIGHT.
     */
// -------------------------------------------- Attributes --------------------------------------------
	// Game Window Dimensions
    public static final int HEIGHT = 768;
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 1024;

    // Map
    private static String MAP_FILE = "res/levels/1.tmx";
    /**
     * The constant map.
     */
    public static TiledMap map;

    /**
     * The Polyline.
     */
// Polyline: path for slicers to follow
    public static List<Point> polyline;

    // Wave
    private static String WAVE_FILE ="res/levels/waves.txt";
    private Wave wave;
    /**
     * The constant waveStarted.
     */
    public static boolean waveStarted;

    // Timescale: speed of the game
    private static final int INITIAL_TIMESCALE = 1;
    private static int timescale = INITIAL_TIMESCALE;

    /**
     * The constant toSecondsConversion.
     */
// The time conversion from milliseconds to seconds
    public static final double toSecondsConversion = 1000.0;

    /**
     * The constant FPS.
     */
// FPS and FrameCount: to keep track of time thruout the game
    public static final double FPS = 60.0;
    /**
     * The constant frameCount.
     */
    public static double frameCount;

    // Buy and Status Panels 
    private Panels buyPanel = new BuyPanel();
    private Panels statusPanel = new StatusPanel();

    // Player's starting lives and funds
    private static final int INITIAL_LIVES = 25;
    private static final int INITIAL_FUNDS = 1000;
    /**
     * The constant lives.
     */
    public static int lives = INITIAL_LIVES;
    /**
     * The constant funds.
     */
    public static int funds = INITIAL_FUNDS;

    /**
     * The constant towers.
     */
    public static List<Tower> towers = new ArrayList<>();

    private void renderTowers(){
        for (Tower tower: towers){
            if(tower.type.equals("passive")){
                if (tower.hasEnded()){
                    towers.remove(tower);
                    break;
                }
            }
            tower.update();
        }
    }

    /**
     * Instantiates a new Shadow defend.
     */
// -------------------------------------------- Constructor -------------------------------------------
    // Creates a new instance of the ShadowDefend game
    public ShadowDefend() {
        super(WIDTH, HEIGHT, "ShadowDefend");
        map = new TiledMap(MAP_FILE);
        polyline = map.getAllPolylines().get(0);
        wave = new Wave(WAVE_FILE);
        waveStarted = false;
        frameCount = Integer.MAX_VALUE;
        PassiveTower.horizontal = true;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
// -------------------------------------------- Main Method -------------------------------------------
    // The entry-point for the game
    public static void main(String[] args) {
        new ShadowDefend().run();
    }


    // ----------------------------------------- Game Update Method ---------------------------------------
    // Update the state of the game, potentially reading from input
    @Override
    protected void update(Input input) {
        frameCount += getTimescale();
        map.draw(0, 0, 0, 0, WIDTH, HEIGHT);

        buyPanel.render();

        if (input.wasPressed(Keys.S)) {
            waveStarted = true;
        }
        if (input.wasPressed(Keys.L)) {
            increaseTimescale();
        }
        if (input.wasPressed(Keys.K)) {
            decreaseTimescale();
        }

        if (waveStarted){
            wave.update(input);
            for (Event event: Wave.events){
                for (Slicer slicer: event.slicers){
                    if (slicer.isFinished()){
                        event.slicers.remove(event.slicers.get(0));
                        lives--;
                    }
                }
            }
        }

        buyPanel.purchasing(input);
        renderTowers();






        statusPanel.render();
    }


    // ----------------------------------------- Helper Methods ------------------------------------------- 

    /**
     * Gets timescale.
     *
     * @return the timescale
     */
    public static int getTimescale() {
        return timescale;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public static int getLives() {
        return lives;
    }

    /**
     * Gets funds.
     *
     * @return the funds
     */
    public static int getFunds() {
        return funds;
    }

    /**
     * Sets funds.
     *
     * @param funds the funds
     */
    public static void setFunds(int funds) {
        ShadowDefend.funds = funds;
    }
    
    private void increaseTimescale() {
        if (!(timescale > 5)){
            timescale++;
        }
    }
    

    private void decreaseTimescale() {
        if (timescale > INITIAL_TIMESCALE) {
            timescale--;
        }
    }

    /**
     * Get height int.
     *
     * @return the int
     */
// returns the width of the game window
    public static int getHeight(){
        return HEIGHT;
    }



}
