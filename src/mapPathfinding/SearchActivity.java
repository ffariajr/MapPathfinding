package mapPathfinding;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves as a template to handle to search algorithms, and sets up the map and timer for the searches.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public abstract class SearchActivity extends Activity {
    
    protected char[][] map;
    protected LinkedList<Point> visited;
    protected LinkedList<ArrayList<Point>> frontier;
    protected int width;
    protected int height;
    
    @SuppressWarnings("resource")
    protected SearchActivity(String filename) {
        visited = new LinkedList<Point>();
        frontier = new LinkedList<ArrayList<Point>>();
        
        File f = new File(filename);
        try {
            Scanner in = new Scanner(f);
            width = in.nextInt();
            height = in.nextInt();
            
            map = new char[width][height];
            in.nextLine();
            
            for (int y = 0; y < height; y++) {
                String row = in.nextLine();
                for (int x = 0; x < width; x++) {
                    map[x][y] = row.charAt(x);
                }
            }
            
        } catch (FileNotFoundException fnfe) {
            map = null;
        } catch (IndexOutOfBoundsException ioobe) {
            map = null;
        } catch (InputMismatchException ime) {
            map = null;
        }
    }
    
    protected abstract void addWhere(ArrayList<Point> newp);
    protected abstract void reorderFrontier();
    
    @SuppressWarnings("unchecked")
    @Override
    public void activate() {
        
        if (map == null) {
            System.err.print("A syntactically correct map file could not be found.\n");
            return;
        }
        
        long startTime = GregorianCalendar.getInstance().getTimeInMillis();
        
        Point start = null;
        
        for (int x = 0; x < width && start == null; x++) {
            for (int y = 0; y < height && start == null; y++) {
                if (map[x][y] == 's') {
                    start = new Point(x, y);
                }
            }
        }
        
        if (start == null) {
            System.err.print("Could not find start state.\n");
        }
        
        ArrayList<Point> current = new ArrayList<Point>();
        current.add(start);
        
        Point last = null;
        frontier.add(current);
        
        long lastElapsed = 0;
        
        while (frontier.size() > 0 && (last == null || map[last.x][last.y] != 'g')) {
            current = frontier.removeFirst();
            last = current.get(current.size() - 1);
            visited.add(last);
            
            debugCurrentPath(current);
            
            if (last.x - 1 >= 0 && (map[last.x - 1][last.y] == '.' || map[last.x - 1][last.y] == ',' || map[last.x - 1][last.y] == 'g' || map[last.x - 1][last.y] == 's')) {
                ArrayList<Point> newp = (ArrayList<Point>) current.clone();
                copy(current, newp);
                newp.add(new Point(last.x - 1, last.y));
                addWhere(newp);
            }
            if (last.x + 1 < width && (map[last.x + 1][last.y] == '.' || map[last.x + 1][last.y] == ',' || map[last.x + 1][last.y] == 'g' || map[last.x + 1][last.y] == 's')) {
                ArrayList<Point> newp = (ArrayList<Point>) current.clone();
                copy(current, newp);
                newp.add(new Point(last.x + 1, last.y));
                addWhere(newp);
            }
            if (last.y - 1 >= 0 && (map[last.x][last.y - 1] == '.' || map[last.x][last.y - 1] == ',' || map[last.x][last.y - 1] == 'g' || map[last.x][last.y - 1] == 's')) {
                ArrayList<Point> newp = (ArrayList<Point>) current.clone();
                copy(current, newp);
                newp.add(new Point(last.x, last.y - 1));
                addWhere(newp);
            }
            if (last.y + 1 < height && (map[last.x][last.y + 1] == '.' || map[last.x][last.y + 1] == ',' || map[last.x][last.y + 1] == 'g' || map[last.x][last.y + 1] == 's')) {
                ArrayList<Point> newp = (ArrayList<Point>) current.clone();
                copy(current, newp);
                newp.add(new Point(last.x, last.y + 1));
                addWhere(newp);
            }
            
            removeDuplicates();
            reorderFrontier();
            
            if (!Init.debug) {
                long elapsed = (GregorianCalendar.getInstance().getTimeInMillis() - startTime) / 1000;
                if (elapsed > lastElapsed) {
                    lastElapsed = elapsed;
                    System.out.print(".");
                }
            }
        }
        if (!Init.debug) {
            System.out.print("\n");
        }
        
        boolean found = true;
        if (last == null || map[last.x][last.y] != 'g') {
            found = false;
        }
        
        long time = GregorianCalendar.getInstance().getTimeInMillis() - startTime;
        int minutes = (int) (time / 1000 / 60);
        time = time - (minutes * 60 * 1000);
        int seconds = (int) (time / 1000);
        time = time - (seconds * 1000);
        int millies = (int) time;
        
        String minPlural = minutes != 1? "s" : "";
        String secPlural = seconds != 1? "s" : "";
        String milPlural = millies != 1? "s" : "";
        
        if (found) {
            int nodes = 0;
            int cost = 1;
            
            for (int q = 0; q < current.size(); q++) {
                if (q != 0 && q != current.size() - 1) {
                    Point c = current.get(q);
                    if (map[c.x][c.y] == ',') {
                        cost++;
                    }
                    map[c.x][c.y] = '*';
                    nodes++;
                    cost++;
                }
            }
            
            String nodesPlural = nodes != 1? "s" : "";
        
        
            System.out.print("The best solution was found in " + minutes + " minute" + minPlural + ", " + seconds + " second" + secPlural
                    + ", and " + millies + " millisecond" + milPlural + ".\n");
            System.out.print("The best solution traversed " + nodes + " node" + nodesPlural + " and at a cost of " + cost + ".\n");
        
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    System.out.print(map[x][y]);
                }
                System.out.print("\n");
            }
        } else {
            System.out.print("There is no solution to this path search.\nThis was discovered in " + minutes + " minute" + minPlural + ", " + seconds + " second" + secPlural
                    + ", and " + millies + " millisecond" + milPlural + ".\n");
        }
    }
    
    private void debugCurrentPath(ArrayList<Point> current) {
        if (!Init.debug) {
            return;
        }
        
        char[][] copy = new char[map.length][map[0].length];
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                copy[x][y] = map[x][y];
            }
        }
        
        for (int q = 0; q < current.size(); q++) {
            if (q != 0 && q != current.size() - 1) {
                Point c = current.get(q);
                copy[c.x][c.y] = '*';
            }
        }
        
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                System.out.print(copy[x][y]);
            }
            System.out.print("\n");
        }
    }
    
    private void removeDuplicates() {
        ArrayList<ArrayList<Point>> removes = new ArrayList<ArrayList<Point>>();
        for (ArrayList<Point> node : frontier) {
            Point last = node.get(node.size() - 1);
            for (Point p : visited) {
                if (p.x == last.x && p.y == last.y) {
                    removes.add(node);
                    break;
                }
            }
        }
        for (ArrayList<Point> nodes : removes) {
            frontier.remove(nodes);
        }
    }

    private void copy(ArrayList<Point> current, ArrayList<Point> newp) {
        for (int q = 0; q < current.size() - 1; q++) {
            newp.set(q, current.get(q));
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
