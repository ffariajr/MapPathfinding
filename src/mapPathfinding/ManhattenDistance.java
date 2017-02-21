package mapPathfinding;

import java.awt.Point;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves to inform the A* search based on the Manhatten distance between the current point and the goal point.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class ManhattenDistance extends Heuristic {

    @Override
    protected double costOf(Point current, Point goal) {
        return (goal.x - current.x) + (goal.y - current.y); 
    }
}
