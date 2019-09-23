
public class Driver {

	public static void main(String[] args) {
		AStarSolverTree TSolver = new AStarSolverTree();
		AStarSolverGraph GSolver = new AStarSolverGraph();
		
		Heuristic h1 = (puz) -> {
			int num = 0; 
			for(int i = 0; i < 9; i++) { 
				if (puz.getPuzzle()[i/3][i%3] != i && puz.getPuzzle()[i/3][i%3] != 0) {
					num++;
				}
			} 
			return num;};
		
		Heuristic h2 = (puz) -> {
			int num = 0; 
			for(int i = 0; i < 9; i++) { 
				if(puz.getPuzzle()[i/3][i%3] != 0)
					num += Math.abs(puz.getPuzzle()[i/3][i%3] - i);
			}
			return num;};
		
		int[][] input = new int[][] {{-1,-1,-1}, {-1, -1, -1},{-1,-1,-1}};
		boolean graph = true;
		Heuristic h = h1;
		int i = 0;
		
		for (String arg : args) {
			try {
				if (Integer.parseInt(arg) >= 0 && Integer.parseInt(arg) < 9) {
					input[i/3][i%3] = Integer.parseInt(arg);
					i++;
				}
			}
			catch(Exception e){
				
			}
			
			if (arg.equals("--tree"))
			{
				graph = false;
				System.out.println("Running Tree Search");
			}
			
			if (arg.equals("--h2")) {
				h = h2;
				System.out.println("Running Heuristic 2");
			}
		}
		
		Puzzle puzz;
		try {
		if(input[2][2] > 0)
		{
			puzz = new Puzzle(input);
		}
		else {
			puzz = new Puzzle();
		}
		
		String[] solution = new String[2];
		
		if(graph = true) {
			solution = GSolver.Solve(puzz, h);
		}
		else {
			solution = TSolver.Solve(puzz, h);
		}
		
		System.out.println("The solution is at depth " + solution[1]);
		System.out.println("The steps to the solution are:\n" + solution[0]);
		}
		catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static int[] runGraphTrial(Heuristic h, AStarSolverGraph Solver){
		int[] output = new int[2];
		
		long starttime = System.nanoTime();
		String[] solution; 
		try {
		Puzzle puz = new Puzzle();
		solution = Solver.Solve(puz, h);
		System.out.print(solution[0] + solution[1]);
		long endtime = System.nanoTime();
		output[0] = Integer.parseInt(solution[1]);
		output[1] = (int) (endtime - starttime);
		return output;
		}
		catch(Exception e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			output[0] = -1;
			output[1] = -1;
			return output;
		}
		
	}
	
	static int[] runTreeTrial(Heuristic h, AStarSolverTree Solver){
		int[] output = new int[2];
		
		long starttime = System.nanoTime();
		String[] solution; 
		try {
		Puzzle puz = new Puzzle();
		solution = Solver.Solve(puz, h);
		System.out.print(solution[0] + solution[1]);
		long endtime = System.nanoTime();
		output[0] = Integer.parseInt(solution[1]);
		output[1] = (int) (endtime - starttime);
		return output;
		}
		catch(Exception e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			output[0] = -1;
			output[1] = -1;
			return output;
		}
		
	}

}
