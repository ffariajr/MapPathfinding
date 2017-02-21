package mapPathfinding;

import java.util.Scanner;

/**
 * Artificial Intelligence Spring 2017
 * MapPathfinding
 * This file serves as the starting point for the program and calls the other components when requested.
 * 
 * @author Fernando Faria
 * @date 2/12/17
 */

public class Init
{
    public static boolean debug = false;
    
    public static void main(String[] args) {
        System.out.print("Welcome to MapPathfinding!\n\n");        
        System.out.print("For help, please type \"help\".  To exit this program, please type \"exit\" or \"quit\".\n");
        
        Activity next;
        do {
            next = input();
            if (next != null) {
                next.activate();
            } else {
                System.err.print("Error: Incorrect Input\n");
                System.err.flush();
            }
        } while (next == null || !next.isFinished());
    }
    
    @SuppressWarnings("resource")
    private static Activity input() {
        
        Scanner in = new Scanner(System.in);
        System.out.print("> ");
        String command = in.nextLine();
        
        
        if ("help".equalsIgnoreCase(command)) {
            return new Help();
        } else if ("exit".equalsIgnoreCase(command) || "quit".equalsIgnoreCase(command)) {
            return new Finished();
        } else if ("debug".equalsIgnoreCase(command)) {
            return new Debug(true);
        } else if ("regular".equalsIgnoreCase(command)) {
            return new Debug(false);
        } else if ("DFS".equalsIgnoreCase(command)) {
            String file = getFile();
            if (file == null) {
                return null;
            }
            return new DFS(file);
        } else if ("BFS".equalsIgnoreCase(command)) {
            String file = getFile();
            if (file == null) {
                return null;
            }
            return new BFS(file);
        } else if ("A*".equalsIgnoreCase(command)) {
            String heuristic = getHeuristic();
            if (heuristic == null) {
                return null;
            }
            String file = getFile();
            if (file == null) {
                return null;
            }
            return new Astar(file, heuristic);
        }
        return null;
    }

    @SuppressWarnings("resource")
    private static String getHeuristic() {
        Scanner in = new Scanner(System.in);
        System.out.print("Heuristic: ");
        return in.nextLine();
    }

    @SuppressWarnings("resource")
    private static String getFile() {
        Scanner in = new Scanner(System.in);
        System.out.print("Path: ");
        return in.nextLine();
    }
    
    public static void edb(String msg) {
        if (debug) {
            System.err.print(msg);
            System.err.flush();
        }
    }
    
    public static void db(String msg) {
        if (debug) {
            System.out.print(msg);
            System.out.flush();
        }
    }
}
