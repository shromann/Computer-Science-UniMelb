import bagel.AbstractGame;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import bagel.map.*;
import bagel.util.Point;
import java.util.List;

public class ShadowDefend extends AbstractGame {

    private final TiledMap map;
    private final List<Point> path;
    private Wave wave;
    private boolean waveActive;
    private int frameCount;

    /**
     * Entry point for Bagel game
     *
     * Explore the capabilities of Bagel: https://people.eng.unimelb.edu.au/mcmurtrye/bagel-doc/
     */
    public static void main(String[] args) {
        // Create new instance of game and run it

        new ShadowDefend().run();

    }

    /**
     * Setup the game
     */
    // Constructor
    public ShadowDefend(){

        super(1024, 768, "Shadow Defend");
        // 1. Get 'map' and the 'path' that the slicers will traverse in.
        map = new TiledMap("res/levels/1.tmx");
        path = map.getAllPolylines().get(0);



        // 2. Initialise an inactive wave.
        wave = new Wave(path);
        waveActive = false;
    }

    /**
     * Updates the game state approximately 60 times a second, potentially reading from input.
     * @param input The input instance which provides access to keyboard/mouse state information.
     */
    @Override
    protected void update(Input input) {
        // 1. Draw Map
        map.draw(0,0,0,0, Window.getWidth(), Window.getHeight());

        // 2. Start "wave" if the S key is pressed
        if (input.wasPressed(Keys.S)){
            waveActive = true;
            frameCount = 0;
        }

        // 3. Control the "wave" after if it has started.
        if (waveActive){

            // 3.1 Speed up the wave if L key is pressed
            if (input.wasPressed(Keys.L)){
                wave.speedUp();
                if (frameCount > wave.getFramerate()) {
                    frameCount = wave.getFramerate();
                }
            }

            // 3.2 Slow down the wave if K key is pressed
            if (input.wasPressed(Keys.K)){
                wave.slowDown();
                if (frameCount > wave.getFramerate()) {
                    frameCount = wave.getFramerate();
                }
            }

            // 3.3 spawn splicers that are yet to be appropriately spawned
            if (wave.spawn(frameCount)){
                frameCount = 0;
            }

            // 3.4 Get next points for splicers in the case of ending a lane traversal
            wave.slicerReachedProtocol(path);

            // 3.5 Move the splicer in the wave to their calculated locations above
            wave.move();

            // 3.6 Render the wave on the window
            wave.render();

            // 3.7 Deactivate and close window if the all splicers have reached global end
            if (wave.isWaveEnded()){
                waveActive = false;
                Window.close();
            }

            // 3.8 Count this frame as rendered.
            frameCount++;
        }
    }

}
