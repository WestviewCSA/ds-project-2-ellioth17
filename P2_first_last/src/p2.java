import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
public class p2 {
	static boolean Incoordinate;
	static boolean Outcoordinate;
	static boolean Opt;
	static boolean Time;
	static boolean Help;
	static Map currentMap;
	static Map altMap;
	//public ArrayList[][] stackx = new ArrayList[][](); //why broke
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String filename = "C:\\Users\\Elliot Ho\\git\\New folder\\ds-project-2-ellioth17\\P2_first_last\\TEST\\Maze1";
		readMap(filename);
		stackSolver();
		//queueSolver();
	}
	//public Tile(int rownum, int rowcol, )
	
	public static void readMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			scanner.nextLine();
			currentMap = new Map(numRows, numCols, numRooms);
			int rowIndex = 0;
			//process the map!
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				
				if(row.length()>0 && (rowIndex<numRows)) {
					for(int col = 0; col < numCols && col < row.length(); col++) {
						char element = row.charAt(col);
						Tile obj = new Tile(rowIndex, col, element);
						//System.out.println(rowIndex + " " + col + " " + element);
						currentMap.setTile(obj);
					}
					rowIndex++;
				}
			}
			
		} catch (FileNotFoundException e){
			System.out.println(e);
		}
		
	}
	
	public static void readCoordinateMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			int rowIndex = 0;
			//process the map!
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				//Find first char --> store as the element type for all
				//then
				if(row.length()>0) {
					for(int col = 0; col < numCols && col < row.length(); col++) {
						char element = row.charAt(col);
						Tile obj = new Tile(rowIndex, col, element);
					}
				}
			}
			
		} catch (FileNotFoundException e){
			System.out.println("File not found");
		}
	}
	
	/* public static void queueSolver() {
	    if (currentMap == null) {
	        System.out.println("Map not loaded.");
	        return;
	    }
	    Map activeMap = currentMap;
	    int rows = activeMap.getRows();
	    int cols = activeMap.getCols();
	    Tile startW = null;
	    Tile goal$ = null;

	    // Find start and end
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            Tile t = activeMap.getTile(i, j, 0);
	            if (t != null) {
	                if (t.getType() == 'W') {
	                    startW = t;
	                } else if (t.getType() == '$') {
	                    goal$ = t;
	                }
	            }
	        }
	    }

	    if (startW == null || goal$ == null) {
	        System.out.println("No start or goal.");
	        return;
	    }

	    // Initialize queue. start is visited, add start to queue
	    Queue<Tile> queue = new LinkedList<>();
	    queue.add(startW);
	    startW.setVisited(true);

	    // NESW directions
	    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	    boolean foundGoal = false;

	    // Traversal
	    while (!queue.isEmpty() && !foundGoal) {
	        Tile checking = queue.remove();

	        // End if goal is reached
	        if (checking == goal$) {
	            foundGoal = true;
	            break;
	        }

	        // Check each direction.
	        for (int[] dir : direction) {
	            int newRow = checking.getRow() + dir[0];
	            int newCol = checking.getCol() + dir[1];
	            Tile next = activeMap.getTile(newRow, newCol, 0);

	            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
	                // Check if tile is right type and not visited
	                if (next != null && (next.getType() == '.' || next.getType() == '$') && !next.isVisited()) {
	                    next.setVisited(true);
	                    next.setPrevious(checking); // Track the previous tile
	                    queue.add(next);
	                }
	            }
	        }
	    }

	    // If goal is found, track backwards and update the map by setting . on the solution to +
	    if (foundGoal) {
	        Tile pathTile = goal$;
	        while (pathTile != null && pathTile != startW) {
	            pathTile.setType('+');  // Mark the path with '+'
	            pathTile = pathTile.getPrevious();  // Move back to the previous tile
	        }
	        //Make sure start and end are W and $
	        startW.setType('W');  
	        goal$.setType('$');   
	    }

	    // Print
	    System.out.println(activeMap.returnMaze());
	}	
*/
	// /*
		public static void stackSolver() {
			if(currentMap == null) {
				System.out.println("Map not loaded.");
				return;
			}
			//outcoordinate should be true here
			Map activeMap;
			activeMap = currentMap;
			int rows = activeMap.getRows();
			int cols = activeMap.getCols();
			Tile startW = null;
			Tile goal$ = null;
			//find start and end
			for (int i = 0; i < rows; i++) {
		        for (int j = 0; j < cols; j++) {
		            Tile t = activeMap.getTile(i, j, 0);
		            if (t != null) {
		                if (t.getType() == 'W') {
		                    startW = t;
		                } else if (t.getType() == '$') {
		                    goal$ = t;
		                }
		            }
		        }
		    }
			if(startW == null || goal$ == null) {
				System.out.println("No start or goal.");
				return;
			}
			Stack<Tile> allstack = new Stack<>();
			Stack<Tile> solstack = new Stack<>();
			allstack.push(startW);
			startW.setVisited(true);
			//Order of movement : NESW
			int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
			boolean foundgoal = false;
			while(!foundgoal && !allstack.isEmpty()) { //run until found
				Tile checking = allstack.peek();
				boolean alrmoved= false;
				if(checking == goal$) {
					foundgoal = true;
					solstack.push(checking); //if the one youre checking is the goal then put that in the solution, otherwise...
					break;
				}
				for(int[] dir: direction) {
					int newrow = checking.getRow() + dir[0]; //move north
					int newcol = checking.getCol() + dir[1]; // move East
					Tile next = activeMap.getTile(newrow, newcol, 0);
					if (newrow >= 0 && newrow < rows && newcol >= 0 && newcol < cols) {
					
					//if the next one is a . or $, and it is not visited, put it into the all stack and it goes to top of stack so its the new one you are checking
					if(next != null && (next.getType() == '$' || next.getType() == '.') && (next.isVisited() == false)) {
						allstack.push(next);
						next.setVisited(true);
						solstack.push(next);
						//add an already moved to prevent back track. if you can't move, pop it and it will go back.
						alrmoved = true;
						break;
					}
				}
					
				}
				if(alrmoved == false) {
					allstack.pop();
					if(solstack.isEmpty() == false) {
						solstack.pop();
					}
				}
				
				//remove if already visited
			
				//if goal$ is found, print it out
				if(foundgoal) {
					while(solstack.isEmpty() == false) { //while still tiles in solution
						Tile path = solstack.pop();
						if(path.getType() == 'W') {
							System.out.println("W" + " " + path.getRow() + " " + path.getCol());
						}
						if(path.getType() == '$') {
							System.out.println("$" + " " + path.getRow() + " " + path.getCol());
						}
						if(path.getType() == '.') {
							System.out.println("+" + " " + path.getRow() + " " + path.getCol());
						}
					}
				}
				//replace each . in the solution stack with a +
			        while (!solstack.isEmpty()) {
			            Tile path = solstack.pop();		
			            if(path.getType() != 'W' && path.getType() != '$' && path.getType() != '@') {
			                path.setType('+');
			            }
			        }
				
			}
			System.out.println(activeMap.returnMaze());
		}
		//*/
}
/*
 * first getting rows and columns
 * create a current map that is a Map with rows columns and rooms
 * find W to start: traverse the Tile[][][]
 * two stacks of solution and every visited
 * 2d array of directions DONE UP UNTIL
 * create a new row by moving up right left or down
 * check if equal to $ and not visited. then push to visited. set visited as true. 
 * NESW. 
 * pop back to previous tile if there are no moves
 * if element of solution stack then set that tile to a plus
 */
