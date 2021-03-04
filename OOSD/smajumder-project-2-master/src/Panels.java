import bagel.DrawOptions;
import bagel.Input;
import bagel.util.Colour;


/**
 * The type Panels.
 */
abstract class Panels{
    /**
     * The Red.
     */
// colours
    DrawOptions red = new DrawOptions().setBlendColour(Colour.RED);
    /**
     * The Green.
     */
    DrawOptions green = new DrawOptions().setBlendColour(Colour.GREEN);
    /**
     * The White.
     */
    DrawOptions white = new DrawOptions().setBlendColour(Colour.WHITE);

    /**
     * Render.
     */
// rendering and color methods
    public abstract void render();

    /**
     * Purchasing.
     *
     * @param input the input
     */
    public abstract void purchasing(Input input);

    /**
     * Color draw options.
     *
     * @param target the target
     * @return the draw options
     */
    protected abstract DrawOptions color(double target);
}
