package lab06;
/*************************************************************************
Code to generate and draw a maze to be used as starting point for lab 6
Code borrowed from http://introcs.cs.princeton.edu and modified by Olac Fuentes
Last modified October 20, 2016
 *************************************************************************/

import java.util.*;

public class Maze {
	private int N;                  // dimension of maze
	private boolean[][] v_wall;     
	private boolean[][] h_wall;


	public Maze(int N) {
		this.N = N;
		StdDraw.setXscale(0, N);
		StdDraw.setYscale(0, N);
		init();
	}

	//Who's the neighbor
	public int neighbor(int cell, char wall, int size){
		int neighbor = -1;
		int tempNeighbor = 0;

		if(wall == 'N'){
			tempNeighbor = cell + size;
			if(tempNeighbor < size*size)
				neighbor = tempNeighbor;
		}
		else if(wall == 'E'){
			tempNeighbor = cell + 1;
			if(tempNeighbor < size*size && tempNeighbor % size != 0)
				neighbor = tempNeighbor;
		}


		return neighbor;

	}

	//Get y-coordinate
	public int row(int cell, int N){
		int row = (cell / N);

		return row;
	}

	//Get x-coordinate
	public int col(int cell, int N){
		int col = (cell % N);

		return col;
	}

	//Picking a Random Cell
	public int randCell(int min, int max) {
		Random ran = new Random();
		int randomNum = ran.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	//Picking Random Wall
	public char randWall(int min, int max) { 
		char[] wall = {'N', 'E'};
		Random ran = new Random();

		int randomNum = ran.nextInt((max - min) + 1) + min;
		char side = wall[randomNum]; 

		return side;
	}


	private void init() {
		// initialze all walls as present
		// Vertical wall v_wall[x][y] separates cells (x + N*y) and (x + N*y + 1)
		// Horizontal wall h_wall[x][y] separates cells (x + N*y) and (x + N*(y + 1))
		// South (x + N*y) (x + N*(y - 1)
		// West  (x + N*y) (x + N*y - 1)

		h_wall = new boolean[N][N-1];        
		v_wall = new boolean[N-1][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N-1; j++)
				v_wall[j][i] = h_wall[i][j] = true;
	}

	public void remove_wall(int x, int y, char d){
		if ((d=='N') && (y<N-1))
			h_wall[x][y] = false;
		if ((d=='E') && (x<N-1))
			v_wall[x][y] = false;
	}

	public void printCellNumbers() { 
		StdDraw.setPenColor(StdDraw.BLUE);
		for (int x = 0; x < N; x++){
			for (int y = 0; y < N; y++){
				StdDraw.text(x+.5,y+.5,Integer.toString(x+N*y));    
			}
		}
		StdDraw.show(0);
	} 


	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(.5, .5, 0.375);
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(N-0.5, N-0.5, 0.375);

		//Draw Periphery. 
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.line(0, 0, N, 0); 
		StdDraw.line(N, 0, N, N); 
		StdDraw.line(N, N, 0, N);          
		StdDraw.line(0, N, 0, 0); 

		// Draw remaining walls
		for (int x = 0; x < N; x++)
			for (int y = 0; y < N-1; y++)
				if (h_wall[x][y]) StdDraw.line(x, y+1, x+1, y+1);      
		for (int x = 0; x < N-1; x++)
			for (int y = 0; y < N; y++)
				if (v_wall[x][y]) StdDraw.line(x+1, y, x+1, y+1);
		StdDraw.show(0);
	}


}
