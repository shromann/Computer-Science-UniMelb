import bagel.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Wave.
 */
public class Wave {

	// Game's current wave event number
    private static int waveNumber;

    // The delay of a delay event
    private static double gameDelay;

    /**
     * The Events.
     */
// collection of events
    public static List<Event> events;

    // Reading Wave files
    private File file;
    private static Scanner scan;

    /**
     * Instantiates a new Wave.
     *
     * @param Wave_file the wave file
     */
// load the wave file and initialise an events list
    public Wave(String Wave_file) {
        file = new File(Wave_file);
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        events = new ArrayList<>();
    }

    // read the next event
    private void readEvent(){
        if(scan.hasNextLine()){
            String[] event = scan.nextLine().split(",");
            switch (event[1]) {
                case "spawn":
                    events.add(new Event(event[2], event[3],event[4]));
                    waveNumber = Integer.parseInt(event[0]);
                    ShadowDefend.frameCount = 0;
                    break;
                case "delay":
                    gameDelay = (Double.parseDouble(event[2]) / ShadowDefend.toSecondsConversion) * ShadowDefend.FPS;
                    waveNumber = Integer.parseInt(event[0]);
                    ShadowDefend.frameCount = 0;
                    break;
                default:
                    // This is must!
                    ShadowDefend.frameCount = 0;
                    break;
            }
        }
    }


    // we're only going to read the next event if the current event is done spawning.
    private boolean readNextEvent(){
        int i = 0;
        for(Event event: events){
            if (event.allSpawned()){
                i++;
            }
        }
        return i == events.size() && gameDelay == 0;
    }

    /**
     * Update.
     *
     * @param input the input
     */
    public void update(Input input){
        if(readNextEvent()){
            readEvent();
        }
        if(gameDelay > 0){
            gameDelay--;
        }
        for(Event event: events){
            event.update(input);
        }
//        System.out.println(gameDelay);
    }

    /**
     * Get wave number int.
     *
     * @return the int
     */
    public static int getWaveNumber(){
    	return waveNumber;
    }

    /**
     * Remove the dead.
     */
    public static void removeTheDead(){
        for (Event event: events){
            for (Slicer slicer: event.getSlicers()){
                if (slicer.health == 0){
                    event.slicers.remove(slicer);
                }
            }

        }
    }

}
