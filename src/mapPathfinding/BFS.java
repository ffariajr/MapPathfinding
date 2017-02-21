package mapPathfinding;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves to perform a BFS search on a map for the best path from start to finish.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class BFS extends SearchActivity {

    public BFS(String file) {
        super(file);
    }

    @Override
    protected void addWhere(ArrayList<Point> newp) {
        frontier.addLast(newp);
    }

    @Override
    protected void reorderFrontier() {
        return;
    }
}
