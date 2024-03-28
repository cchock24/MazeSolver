/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        Stack<MazeCell> s = new Stack<MazeCell>();
        ArrayList<MazeCell> m = new ArrayList<MazeCell>();
        //Add End Cell
        s.push(maze.getEndCell());
        MazeCell parent = maze.getEndCell().getParent();
        //Takes Parent of each cell until reach start cell
        while(parent != maze.getStartCell()){
            s.push(parent);
            parent = parent.getParent();
        }
        s.push(maze.getStartCell());
        int size = s.size();
        for(int i = 0; i < size; i++){
            m.add(s.pop());
        }
        return m;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Stack<MazeCell> intersection = new Stack<MazeCell>();
        MazeCell current = maze.getStartCell();
        //Loops until all it finds End Cell
        while(current != maze.getEndCell()){
            //Sets Explored
            current.setExplored(true);
            int row = current.getRow();
            int col = current.getCol();
            //Check if the Neighbhours are valid
            //Sets Parent
            if(maze.isValidCell(row, col+1)){
                maze.getCell(row,col+1).setParent(current);
                intersection.push(maze.getCell(row,col+1));
            }
            if(maze.isValidCell(row+1, col)){
                maze.getCell(row+1,col).setParent(current);
                intersection.push(maze.getCell(row+1,col));
            }
            if(maze.isValidCell(row, col -1)){
                maze.getCell(row,col-1).setParent(current);
                intersection.push(maze.getCell(row,col-1));
            }
            if(maze.isValidCell(row-1,col)){
                maze.getCell(row-1,col).setParent(current);
                intersection.push(maze.getCell(row-1,col));
            }
            current = intersection.pop();
        }
        return this.getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        Queue<MazeCell> intersection = new LinkedList<MazeCell>();
        MazeCell current = maze.getStartCell();
        //Loops until End Cell
        while(current != maze.getEndCell()){
            //Sets explored
            current.setExplored(true);
            int row = current.getRow();
            int col = current.getCol();
            //Checks if Neighbhour valid and sets parent
            if(maze.isValidCell(row, col+1)){
                maze.getCell(row,col+1).setParent(current);
                intersection.add(maze.getCell(row,col+1));
            }
            if(maze.isValidCell(row+1, col)){
                maze.getCell(row+1,col).setParent(current);
                intersection.add(maze.getCell(row+1,col));
            }
            if(maze.isValidCell(row, col -1)){
                maze.getCell(row,col-1).setParent(current);
                intersection.add(maze.getCell(row,col-1));
            }
            if(maze.isValidCell(row-1,col)){
                maze.getCell(row-1,col).setParent(current);
                intersection.add(maze.getCell(row-1,col));
            }
            current = intersection.remove();
        }
        return this.getSolution();
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
