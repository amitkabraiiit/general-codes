package com.practice.design;

public class RatInMaze2 {
	 
    public int[][] solution;
    public int N;
 
    //initialize the solution matrix in constructor.
    public RatInMaze2(int N) {
    	this.N = N;
        solution = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = 0;
            }
        }
    }
 
    // this function will check if mouse can move to this cell
    public boolean isSafeToGo(int[][] maze, int x, int y) {
        // check if x and y are in limits and cell is not blocked
        if (x >= 0 && y >= 0 && x < N  && y < N && maze[x][y] != 0) {
            return true;
        }
        return false;
    }
    public void print(int [][] solution){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + solution[i][j]);
            }
            System.out.println();
        }
    }
    
    public void solveMaze(int[][] maze, int N) {
        if (findPath(maze, 0, 0)) {
            print(solution);
        }else{
            System.out.println("NO PATH FOUND");
        }
         
    }
 
    public boolean findPath(int[][] maze, int x, int y) {
        // check if maze[x][y] is feasible to move
        if(x==N-1 && y==N-1){//we have reached
            solution[x][y] = 1;
            return true;
        }
        if (isSafeToGo(maze, x, y)) {
            // move to maze[x][y]
            solution[x][y] = 1;         
            // now rat has four options, either go right OR go down or left or go up
            if(findPath(maze, x+1, y)){ //go down
                return true;
            }
            //else go down
            if(findPath(maze, x, y+1)){ //go right
                return true;
            }
            if(findPath(maze, x-1, y)){ //go up
                return true;
            }
            if(findPath(maze, x, y-1)){ //go left
                return true;
            }
            //if none of the options work out BACKTRACK undo the move
            solution[x][y] = 0;
            return false;
        }
        return false;
    }
 

    public static void main(String[] args) {
        int N = 5;
        int[][] maze = { { 1, 0, 1, 1,1 }, { 1, 1, 1, 0,1 }, { 0, 0,0, 1, 1 },
                { 0, 0, 0, 1,0 },{ 0, 0,0, 1, 1 } };
        //int[][] maze = { { 1, 0, 1, 1,1 }, { 1, 1, 1, 0,1 }, { 0, 1,0, 1, 1 },
          //      { 0, 1, 0, 1,0 },{ 0, 0,0, 1, 1 } };
        RatInMaze r = new RatInMaze(N);
        r.solveMaze(maze, N);
    }
 
}