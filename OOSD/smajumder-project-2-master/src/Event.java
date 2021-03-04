import bagel.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Event.
 */
public class Event{


    /**
     * The Slicers.
     */
// collection of slicers
    public List<Slicer> slicers;

    private int spawnCount;

    private int size;
    private String type;
    private double delay;


    /**
     * Instantiates a new Event.
     *
     * @param size  the size
     * @param type  the type
     * @param delay the delay
     */
    public Event(String size, String type, String delay) {
        this.size = Integer.parseInt(size);
        this.type = type;
        this.delay = Double.parseDouble(delay) / ShadowDefend.toSecondsConversion;
        slicers = new ArrayList<>();
        spawnCount = 0;
    }


    /**
     * Update.
     *
     * @param input the input
     */
    public void update(Input input){
        spawn();
        for (int i = slicers.size() - 1; i >= 0; i--) {
            slicers.get(i).update(input);
            if (slicers.get(i).isFinished() || slicers.get(i).health <= 0) {
                slicers.remove(i);
            }
        }
    }

    private void spawn(){
        if(ShadowDefend.frameCount / ShadowDefend.FPS >= delay && spawnCount != size){
            if(type.equals("slicer")) {
                slicers.add(new Slicer(ShadowDefend.polyline));
            }
            if(type.equals("superslicer")) {
                slicers.add(new SuperSlicer(ShadowDefend.polyline));
            }
            if(type.equals("megaslicer")) {
                slicers.add(new MegaSlicer(ShadowDefend.polyline));
            }
            if(type.equals("apexslicer")) {
                slicers.add(new ApexSlicer(ShadowDefend.polyline));
            }
            spawnCount++;
            ShadowDefend.frameCount = 0;
        }
    }

    /**
     * All spawned boolean.
     *
     * @return the boolean
     */
    public boolean allSpawned(){
        return spawnCount == size && ShadowDefend.frameCount / ShadowDefend.FPS >= delay;
    }

    /**
     * Gets slicers.
     *
     * @return the slicers
     */
    public List<Slicer> getSlicers() {
        return slicers;
    }
}
