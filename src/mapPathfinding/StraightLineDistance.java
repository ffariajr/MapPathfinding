package mapPathfinding;

import java.awt.Point;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves to inform the A* search based on the straight line distance between the current point and goal point.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class StraightLineDistance extends Heuristic {

    @Override
    protected double costOf(Point current, Point goal) {
        return Math.sqrt(Math.pow(goal.x - current.x, 2) + Math.pow(goal.y - current.y, 2));
    }
}
