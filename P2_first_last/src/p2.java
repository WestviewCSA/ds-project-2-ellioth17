import java.util.Scanner;

public class p2 {
	public static Scanner scanner;
	public int rows, cols, maps;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(H:\\git\ds-project-2-ellioth17\\P2_first_last\\src\\TestMaze01);
		readMap("TestMaze01")
	}
	public static void readMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
		}
		catch(FileNotFoundException e) {
			System.out.println();
		}
	}

}
