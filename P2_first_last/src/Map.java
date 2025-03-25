
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
	public void setTile(Tile obj) {
		int rows = obj.getRow();
		int cols = obj.getCol();
		int rooms = obj.getLevel();
	    if (rows >= 0 && rows < row && cols >= 0 && cols < col && rooms >= 0 && rooms < levels) {
	        map[row][col][levels] = obj;
	    } 
		
	}

	/*public String returnMaze() {
		String maze = "";
		for(int level = 0; level < this.xlevels; level++) {
			
		}
	}*/
	//get my maze into an array
}
