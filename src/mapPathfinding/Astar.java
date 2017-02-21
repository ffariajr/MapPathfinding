package mapPathfinding;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves to perform an A* search on a map for the best path from start to finish, using some heuristic.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class Astar extends SearchActivity {
    
    private Heuristic h;
    
    public Astar(String file, String heuristic) {
        super(file);
        if ("StraightLineDistance".equalsIgnoreCase(heuristic)) {
            h = new StraightLineDistance();
        } else if ("ManhattenDistance".equalsIgnoreCase(heuristic)) {
            h = new ManhattenDistance();
        } else {
            System.err.print("Invalid Heuristic");
            System.exit(-1);
        }
    }

    @Override
    protected void addWhere(ArrayList<Point> newp) {
        frontier.addFirst(newp);
    }

    @Override
    protected void reorderFrontier() {
        h.rearrange(map, frontier);
    }
}
