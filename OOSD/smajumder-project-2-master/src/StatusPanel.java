import bagel.DrawOptions;
import bagel.Font;
import bagel.Image;
import bagel.Input;
import bagel.util.Colour;


/**
 * The type Status panel.
 */
public class StatusPanel extends Panels{

	// -------------------------------------------- Attributes -------------------------------------------- 
    // Background
    private static Image background = new Image("res/images/statuspanel.png");
    
    // Font, Position Co-ordination, Coluor
    private final Font text = new Font("res/fonts/DejaVuSans-Bold.ttf", 15);
    private double y = ShadowDefend.getHeight() - background.getHeight();
    private int yOffset = 17;
    private final int xwave = 5;
    private final int xtimeScale  = xwave * 50;
    private final int xstatus = xtimeScale * 2;
    private final double xlives = background.getWidth() - 100;
    private final DrawOptions green = new DrawOptions().setBlendColour(Colour.GREEN);

// ------------------------------------------------- Methods ----------------------------------------------- 
    // render the status panel
    @Override
    public void render(){
        background.drawFromTopLeft(0, y);
        text.drawString("Wave: " + Wave.getWaveNumber(),xwave ,y + yOffset);
        text.drawString("Time Scale: " + ShadowDefend.getTimescale(),xtimeScale,y + yOffset,
                color(ShadowDefend.getTimescale()));
        text.drawString("Status: " + getStatus(),xstatus,y + yOffset);
        text.drawString("Lives: " + ShadowDefend.lives,xlives,y + yOffset);
    }

    @Override
    public void purchasing(Input input) {
    }

    // set the colour of the timescale
    @Override
    protected DrawOptions color(double ts){
        if (ts > 1.0){
            return green;
        }
        return white;
    }

    // set the status of the wave. WORK IN PROGRESS
    private String getStatus(){
        if(!ShadowDefend.waveStarted){
            return "Awaiting Start";
        }
        else{
            return "Wave In Progress";
        }
    }

}
