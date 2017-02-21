package mapPathfinding;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves to provide a help message for using this program.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class Help extends Activity {

    @Override
    public void activate() {
        System.out.print("Usage of this program is as follows:\n"
                + "\t> help\n\t\tPrint out this help message.\n"
                + "\t> quit\n\t> exit\n\t\tTerminates this program.\n"
                + "\t> debug\n\t\tEnables debugging.\n"
                + "\t> regular\n\tDisables debugging.\n"
                + "\t> DFS\n\t\tRuns a DFS search on the map specified.\n"
                + "\t> BFS\n\t\tRuns a BFS search on the map specified.\n"
                + "\t> A*\n\t\tRuns an A* search on the map specified using the"
                + " heuristic specified.\n\t\tPossible heuristics are:\n"
                + "\t\t\tStraightLineDistance\n\t\t\tManhattenDistance\n");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
