import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
public class p2 {
	static boolean Incoordinate = false;
	static boolean Outcoordinate = true;
	static boolean Opt = false;
	static boolean Time = true;;
	static boolean Help = false;
	static boolean Stack = true;
	static boolean Queue = false;
	static Map currentMap;
	static Map altMap;
	//public ArrayList[][] stackx = new ArrayList[][](); //why broke
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String filename = "C:\\Users\\Elliot Ho\\git\\New folder\\ds-project-2-ellioth17\\P2_first_last\\TEST\\TEST03";
	    //processCommandLineArgs(args);

        if (Help) {
            printHelpMessage();
            System.exit(0);
        }

        if ((Stack && Queue) || (Stack && Opt)) {
            System.out.println("Error: Only one of --Stack, --Queue, or --Opt is able to be specified.");
            System.exit(-1);
        }

        if (!Stack && !Queue && !Opt) {
            System.out.println("Error: One of --Stack, --Queue, or --Opt needs to be specified.");
            System.exit(-1);
        }
	    if (Incoordinate) {
	        readCoordinateMap(filename);
	    } else if (Outcoordinate) {
	        readtextMap(filename);
	    }
	 // Queuesolver time
	    if (Time) { 
            if (Stack) { 
                long stackstart = System.currentTimeMillis();
                stackSolver();
                long stackend = System.currentTimeMillis();
                double sduration = (stackend - stackstart) / 1000.0;
                System.out.println("Total Runtime: " + sduration + " seconds");
            } else if (Queue || Opt) {
                long queuestart = System.currentTimeMillis();
                queueSolver();
                long queueend = System.currentTimeMillis();
                double qduration = (queueend-queuestart) / 1000.0;
                System.out.println("Total Runtime: " + qduration + " seconds");
            }
        } else {
            if (Stack) { 
                stackSolver();
            } else if (Queue || Opt) { 
                queueSolver();
            }
        }
    }
	//public Tile(int rownum, int rowcol, )
	
	
	public static void readtextMap(String filename) {
		if(Outcoordinate) {
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
						//System.out.println(element + " " +rowIndex + " " + col);
						currentMap.setTile(obj);
					}
					rowIndex++;
				}
			}
			
		} catch (FileNotFoundException e){
			System.out.println(e);
		}
		}
	}
	
	public static void readCoordinateMap(String filename) {
	    if (Incoordinate) {
	        try {
	            File file = new File(filename);
	            Scanner scanner = new Scanner(file);

	            int numRows = scanner.nextInt();
	            int numCols = scanner.nextInt();
	            int numRooms = scanner.nextInt();
	            scanner.nextLine(); 

	            currentMap = new Map(numRows, numCols, numRooms);

	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] parts = line.split(" ");

	                if (parts.length == 4) {
	                    char element = parts[0].charAt(0);
	                    int row = Integer.parseInt(parts[1]);
	                    int col = Integer.parseInt(parts[2]);
	                    int room = Integer.parseInt(parts[3]); 
	                    Tile obj = new Tile(row, col, element);
	                    currentMap.setTile(obj);
	                }
	            }

	        } catch (FileNotFoundException e) {
	            System.out.println("File not found");
	        }
	    }
	}
	
	public static void queueSolver() {
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
	    if(Outcoordinate) {
	    System.out.println(activeMap.returnMaze());
	    } else if(Incoordinate) {
	    for (int i = 0; i < activeMap.getRows(); i++) {
			for (int j = 0; j < activeMap.getCols(); j++) {
			Tile t = activeMap.getTile(i, j, 0); 
				char elementtype = t.getType();
				System.out.println(elementtype + " " + i + " " + j + " 0");
			}
		}
	    }
	}	

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
			while (!foundgoal && !allstack.isEmpty()) {
			    Tile checking = allstack.peek();
			    boolean alrmoved = false;

			    if (checking == goal$) {
			        foundgoal = true;
			        break;
			    }

			    for (int[] dir : direction) {
			        int newrow = checking.getRow() + dir[0];
			        int newcol = checking.getCol() + dir[1];
			        //create tile for the next to be looked at that is neighboring
			        Tile next = activeMap.getTile(newrow, newcol, 0);

			        if (newrow >= 0 && newrow < rows && newcol >= 0 && newcol < cols) {
			            if (next != null && (next.getType() == '$' || next.getType() == '.') && !next.isVisited()) {
			                allstack.push(next);
			                next.setVisited(true);
			                next.setPrevious(checking);  // store previous tile in case of backtrack.
			                alrmoved = true;
			                break;
			            }
			        }
			    }

			    if (!alrmoved) {
			        allstack.pop();
			    }
			}
			if (foundgoal) {
			    Tile pathTile = goal$;
			    while (pathTile != null && pathTile != startW) {
			        pathTile.setType('+');
			        pathTile = pathTile.getPrevious();
			    }
			    startW.setType('W');
			    goal$.setType('$');
			}
				
			//}
			if(Outcoordinate) {
			System.out.println(activeMap.returnMaze());
			} else if(Incoordinate) {
			for (int i = 0; i < activeMap.getRows(); i++) {
				for (int j = 0; j < activeMap.getCols(); j++) {
				Tile t = activeMap.getTile(i, j, 0); 
					char elementtype = t.getType();
					System.out.println(elementtype + " " + i + " " + j + " 0");
				}
			}
			}
		}
		//*/
		public static void printHelpMessage() {
	        System.out.println("Maze Solver Program");
	        System.out.println("Switches:");
	        System.out.println("-s: Use stack-based approach.");
	        System.out.println("-q: Use queue-based approach.");
	        System.out.println("--Opt: Find shortest path (queue-based).");
	        System.out.println("--Time: Print runtime.");
	        System.out.println("--Incoordinate: Input is coordinate-based.");
	        System.out.println("--Outcoordinate: Output is coordinate-based.");
	        System.out.println("--Help: Print this help message.");
	    }
		public static void processCommandLineArgs(String[] args) {
	        for (String arg : args) {
	            switch (arg) {
	                case "-s":
	                    Stack = true;
	                    break;
	                case "-q":
	                    Queue = true;
	                    break;
	                case "--Opt":
	                    Opt = true;
	                    break;
	                case "--Time":
	                    Time = true;
	                    break;
	                case "--Incoordinate":
	                    Incoordinate = true;
	                    break;
	                case "--Outcoordinate":
	                    Outcoordinate = true;
	                    break;
	                case "--Help":
	                    Help = true;
	                    break;
	                default:
	                    System.out.println("Unknown command-line argument: " + arg);
	                    break;
	            }
	        }
	    }
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
