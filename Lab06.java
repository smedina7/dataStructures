package lab06;

public class Lab06 {
	public static void main(String[] args) {
		int N = 10; // Maze will have N rows and N columns
		Maze maze = new Maze(N);
		StdDraw.show(0);

		DisjointSet set = new DisjointSet();
		int[] s = set.initSets(N*N);

		while(set.getNumberOfSets(s) > 1){
			int C = maze.randCell(0, N*N);
			char w = maze.randWall(0, 1);
			int row = maze.row(C, N);
			int col = maze.col(C, N);
			int Cneighbor = maze.neighbor(C, w, N);
			
			if (Cneighbor == -1)
				continue;

			if(set.find(s, C) != set.find(s, Cneighbor)){
				set.StdUnion(s, C, Cneighbor);
				maze.remove_wall(col, row, w);
			}

		}
		int comp = set.getComparisons();
		//set.setComparisons(comp);
		
		System.out.println(comp);
		//Remove some walls
		//	        maze.remove_wall(15,10,'N'); 
		//	        maze.remove_wall(5,12,'E');  
		//	        maze.remove_wall(1,4,'E');
		//	        maze.remove_wall(10,12,'E');  
		//	        maze.remove_wall(7,9,'N');  
		//	        maze.remove_wall(11,14,'E');  
		//	        maze.remove_wall(12,10,'N');  
		maze.draw();

		maze.printCellNumbers();
	}
}

