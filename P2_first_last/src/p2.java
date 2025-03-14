import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class p2 {
	private static Scanner scanner;
	private int rows, cols, maps;
	public ArrayList[][] stackx = new ArrayList[][](); //why broken? this is curious
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String filename = "Maze1";
		readMap(filename);
	}
	//public Tile(int rownum, int rowcol, )
	
	public static void readMap(String filename) {
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
				
				if(row.length()>0) {
					for(int col = 0; col < numCols && col < row.length(); col++) {
						char element = row.charAt(col);
						Tile obj = new Tile(rowIndex, col, element);
						stackx.push(obj);
					}
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
			System.out.println(e);
		}
	}
	
}



