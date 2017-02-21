package mapPathfinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves as a template for the A* heuristics.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public abstract class Heuristic {
    
    protected abstract double costOf(Point current, Point goal);

    public void rearrange(char[][] map, LinkedList<ArrayList<Point>> frontier) {
        int width = map.length;
        int height = map[0].length;
        double[] costs = new double[frontier.size()];
        
        Point goal = null;
        for (int x = 0; x < width && goal == null; x++) {
            for (int y = 0; y < height && goal == null; y++) {
                if (map[x][y] == 'g') {
                    goal = new Point(x, y);
                }
            }
        }
        
        for (int q = 0; q < frontier.size(); q++) {
            Point last = frontier.get(q).get(frontier.get(q).size() - 1);
            costs[q] = costOf(last, goal);
            if (map[last.x][last.y] == ',') {
                costs[q]++;
            }
        }
        
        for (int q = 0; q < frontier.size(); q++) {
            boolean done = false;
            int qq;
            for (qq = q + 1; qq < frontier.size() && !done; qq++) {
                if (costs[q] > costs[qq]) {
                    done = true;
                }
            }
            if (done && qq < frontier.size()) {
                double temp = costs[q];
                costs[q] = costs[qq];
                costs[qq] = temp;
                
                frontier.add(q, frontier.remove(qq));
                frontier.add(qq, frontier.remove(q + 1));
            }
        }
    }
}
