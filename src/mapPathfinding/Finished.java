package mapPathfinding;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves to terminate the program.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class Finished extends Activity {

    @Override
    public void activate() {
        System.out.print("Goodbye!\n");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
