
public class Map {
	private Tile[][][] map; //rows, cols, number of rooms
	private int row;
	private int col;
	private int levels;
	
	public Map(int xrow, int xcol, int xlevels) {
		this.row = xrow;
		this.col = xcol;
		this.levels = xlevels;
	}
	//getters and setters
	public void setTile(int row, int col, int level, Tile obj) {
		map[row][col][level] = obj;
	}
	/*public String returnMaze() {
		String maze = "";
		for(int level = 0; level < this.xlevels; level++) {
			
		}
	}*/
	//get my maze into an array
}
