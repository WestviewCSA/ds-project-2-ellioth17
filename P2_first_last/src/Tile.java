public class Tile {
		private int row, col, level;
		private char type;
		private boolean visited;
		private String output;
		
		public Tile(int row, int col, char type) {
			super();
			this.row = row;
			this.col = col;
			this.type = type;
			this.level = 0;
			this.visited = false;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getCol() {
			return col;
		}
		public void setCol(int col) {
			this.col = col;
		}
		public char getType() {
			return type;
		}
		public void setType(char type) {
			this.type = type;
		}
		
		public boolean getVisited() {
			return visited;
		}
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		public boolean isVisited() {
			return visited;
		}
		public int getLevel() {
			return this.level;
		}
		public String toString() {
			return getType() +"";
		}
}
