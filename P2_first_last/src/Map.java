
public class Map {
	private Tile[][][] map; //rows, cols, number of rooms
	private int row;
	private int col;
	private int levels;
	
	public Map(int xrow, int xcol, int xlevels) {
		this.row = xrow;
		this.col = xcol;
		this.levels = xlevels;
		map[row][col][levels] = obj;
		
	}
	//getters and setters
	public void setTile(int row, int col, int oom, Tile obj) {
		map[row][col][room] = obj;
	}
	public String returnMaze() {
		String maze = "";
	}
	//get my maze into an array
}
