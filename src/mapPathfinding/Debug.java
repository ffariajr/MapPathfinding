package mapPathfinding;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves to handle debug mode changes.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class Debug extends Activity {
    
    private boolean wantsToDebug;
    
    public Debug(boolean debug) {
        wantsToDebug = debug;
    }

    @Override
    public void activate() {
        if (Init.debug) {
            if (wantsToDebug) {
                System.err.print("You are already in debug mode.\n");
            } else {
                Init.debug = false;
                System.out.print("Debugging disabled.\n");
            }
        } else {
            if (wantsToDebug) {
                Init.debug = true;
                System.out.print("Debugging enabled.\n");
            } else {
                System.err.print("You are already not in debug mode.\n");
            }
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
