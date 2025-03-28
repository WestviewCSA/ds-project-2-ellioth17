public class Map {
	private Tile[][][] map; //rows, cols, number of rooms
	private int row;
	private int col;
	private int levels;
	
	public Map(int xrow, int xcol, int xlevels) {
		this.row = xrow;
		this.col = xcol;
		this.levels = xlevels;
		this.map = new Tile[row][col][levels];
	}
	//getters and setters
	public void setTile(Tile obj) {
		int rows = obj.getRow();
		int cols = obj.getCol();
		int rooms = obj.getLevel();
	    if (rows >= 0 && rows < row && cols >= 0 && cols < col && rooms >= 0 && rooms < levels) {
	        map[rows][cols][rooms] = obj;
	    } 	
	}
	public Tile getTile(int rown, int coln, int leveln) {
		if(rown >= 0 && coln >= 0 && leveln >= 0 && rown < row && coln < col && leveln < levels) {
			return map[rown][coln][leveln];
		}
		return null;
		
	}
	
	public int getRows() {
		return row;
	}
	public int getCols() {
		return col;
	}
	public int getRooms() {
		return levels;
	}
	/*public String returnMaze() {
		String maze = "";
		for(int level = 0; level < this.xlevels; level++) {
			
		}
	}*/
	public String returnMaze() {
		// TODO Auto-generated method stub
		//implement this method
		String maze = "";
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				for(int k = 0; k < map[i][j].length; k++) {
					maze +=  map[i][j][k].getType();
					
				}
				
			}
			maze += "\n";
		}
		return maze;
	}
	//get my maze into an array
}
