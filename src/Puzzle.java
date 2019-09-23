import java.util.Random;

public class Puzzle {
	private int[][] puzzle;
	private int depth;
	private int cost;
	private int[] zeroLoc;

	public Puzzle() {
		int[][] set = generate();
		this.puzzle = set;
		this.depth = 0;
		this.cost = 0;
		this.zeroLoc = findZero(set);
		System.out.println(printPuzzle(set));
	}

	public Puzzle(int[][] set) {
		int inversions = checkInversions(set);
		if (inversions % 2 == 0) {
			this.puzzle = set.clone();
			this.depth = 0;
			this.cost = 0;
			this.zeroLoc = findZero(set);
		} else {
			throw new IllegalArgumentException("Puzzle cannot be solved\nNumber of inversions: " + inversions);
		}
	}

	public Puzzle(int[][] set, int depth) {
		this.puzzle = set.clone();
		this.depth = depth;
		this.zeroLoc = findZero(set);
	}

	private int[][] generate() {
		int[][] puzzle = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
		int inversions = -1;
		Random rand = new Random();
		int numSwitch;
		int posSwitch;

		do {

			for (int i = 0; i < 9; i++) {
				if (true) 
				{
					posSwitch = rand.nextInt(9);
					numSwitch = puzzle[posSwitch / 3][posSwitch % 3];
					puzzle[posSwitch / 3][posSwitch % 3] = puzzle[i / 3][i % 3];
					puzzle[i / 3][i % 3] = numSwitch;
				}

			}

		} while (checkInversions(puzzle) % 2 != 0);

		return puzzle;
	}

	public static int checkInversions(int puzzle[][]) {
		int inversions = 0;
		
		for (int i = 0; i < 9; i++) {
			for (int j = i+1; j < 9; j++) {
				if (puzzle[i/3][i%3] != 0){
					if (puzzle[i/3][i%3] < puzzle[j/3][j%3] && puzzle[j/3][j%3] != 0) {
						inversions++;
					}
				}
			}
		}
		
		return inversions;
		
	}

	public static String printPuzzle(int puzzle[][]) {
		String outString = "[";
		for (int i = 0; i < 9; i++) {
			outString += " " + puzzle[i / 3][i % 3];
		}
		outString += "] \n";
		return outString;
	}

	public int[][] getPuzzle() {
		int[][] puzzle = this.puzzle.clone();
		return puzzle;
	}

	public int getDepth() {
		return this.depth;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	private int[] findZero(int[][] puzzle) {
		int i = 0;
		zeroLoc = new int[] { -1, -1 };
		do {
			//System.out.print(puzzle[i / 3][i % 3]);
			if (puzzle[i / 3][i % 3] == 0) {
				zeroLoc[0] = i / 3;
				zeroLoc[1] = i % 3;
			}
			i++;
		} while (zeroLoc[0] < 0);

		return zeroLoc;
	}

	public int[] getZeroLoc() {
		return zeroLoc;
	}
}
