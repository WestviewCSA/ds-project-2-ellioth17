
public class Map {
	private Tile[][][] map; //rows, cols
	private int row;
	private int col;
	private int levels;
	
	public Map(int xrow, int xcol, int xlevels) {
		this.row = xrow;
		this.col = xcol;
		this.levels = xlevels;
		
		
	}
	//getters and setters
	readMap("Maze1");
	//get my maze into an array
}
