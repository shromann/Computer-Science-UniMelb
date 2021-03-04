import bagel.util.Point;
import java.util.List;

public class Wave {

    /* Constants */
    private final int INITIAL_START = 0;
    private final int INITIAL_END = 1;
    private final int LAST_POINT;
    private final int NEXT_POINT = 1;
    private final int INITIAL_FRAMERATE = 300;
    private final int NUMBER_OF_SLICERS = 5;

    /* Attributes */
        /* Framerate of the Wave */
    private int framerate;

        /* Slicers of the Wave */
    public Slicer[] waveSlicers;

        /* Boolean flag for an complete Wave traversal */

    /* Constructor: Wave */
    public Wave(List<Point> path){
        waveSlicers = new Slicer[NUMBER_OF_SLICERS];
        for(int i = 0; i < NUMBER_OF_SLICERS; i++){
            waveSlicers[i] = new Slicer(path.get(INITIAL_START), path.get(INITIAL_END));
            waveSlicers[i].setFrameRate(INITIAL_FRAMERATE);
            waveSlicers[i].setMoving(false);
        }
        framerate = INITIAL_FRAMERATE;
        waveSlicers[0].setMoving(true);
        LAST_POINT = path.size() - 1;
    }

        /* Method: isWaveEnded
         * This wave method spawns one splicer at a time
         * at a given framerate provided by its input.
         */
    // Implement method with "boolean waveEnded" [line 22]
    public boolean isWaveEnded(){
        int i = 0;
        for (Slicer slicer : waveSlicers){
            if (slicer.onLastTraverse() && slicer.hasReachedEnd()){
                i++;
            }
        }
        return (i == NUMBER_OF_SLICERS);
    }


       /* Method: Spawn
        * This wave method spawns one splicer at a time
        * at a given framerate provided by its input.
        */
    public boolean spawn(double frameCount){
        for (Slicer slicer: waveSlicers){
            if (!slicer.isMoving() && frameCount == Math.round(slicer.getFrameRate())){
                slicer.setMoving(true);
                return true;
            }
        }
        return false;
    }

        /* Method: Move
         * This wave method moves the splicers of the wave
         */
    public void move(){
        for (Slicer slicer: waveSlicers){
            if (slicer.isMoving()){
                slicer.move();
            }
        }
    }

        /* Method: speedUp & slowDown
         * This wave method moves the splicers of the wave
         */
    public void speedUp(){
        for (Slicer slicer : waveSlicers){
            slicer.speedUp();
            slicer.setFrameRate((slicer.getFrameRate() / 2));
        }
        framerate /= 2;
    }
    public void slowDown(){
        for (Slicer slicer : waveSlicers){
            slicer.slowDown();
            slicer.setFrameRate((slicer.getFrameRate() * 2));
        }
        framerate *= 2;
    }

        /* Method: slicerReachedProtocol
         * This wave method gets next new points for the
         * splicers That have reached the end of the a lane/path
         */
    public void slicerReachedProtocol(List<Point> path){
        for(Slicer slicer : waveSlicers) {

            // if the slicer has reached end but not on its last traversal, get the
            // next point.
            if (slicer.onLastTraverse()){
                slicer.setPathIndex(slicer.getPathIndex());
            }

            else if (slicer.hasReachedEnd()){
                slicer.setPathIndex(slicer.getPathIndex() + NEXT_POINT);
                slicer.getNextPoint(path.get(slicer.getPathIndex()));
                // if the slicer is on it's last point at this stage,
                // mark the slicer as its on its last traversal.
                if(slicer.getPathIndex() == LAST_POINT){
                    slicer.setLastTraverse(true);
                }
            }
            else {
                continue;
            }

            // if the slicer has reached end and its on its last traversal, set it's
            // location as the last end point


        }
    }

        /* Method: getFramerate
         * This wave method returns the framerate of the
         * wave
         */
    public int getFramerate() {
        return framerate;
    }

        /* Method: render
         * This wave method renders the wave for a
         * particular frame
         */
    public void render(){
        for (Slicer slicer : waveSlicers){
            slicer.render();
        }
    }

}
