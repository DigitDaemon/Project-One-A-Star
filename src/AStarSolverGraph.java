import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStarSolverGraph {
	Tree tree;
	Heuristic h;
	PriorityQueue<TreeNode> Frontier;
	ArrayList<TreeNode> ExploredSet;
	private static final int[][] Goal = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
	int cutOff;
	static final int CUT_OFF_POINT = 1000;
	
	public void AstarSolverGraph() {
	}
	
	public String[] Solve(Puzzle puz, Heuristic h) {
		this.h = h;
		Frontier = new PriorityQueue<TreeNode>();
		tree = new Tree(puz);
		ExploredSet = new ArrayList<TreeNode>();
		ExploredSet.add(tree.getRoot());
		cutOff = 0;
		String[] solution = new String[2];
		TreeNode lastVisited = tree.getRoot();
		
		while(!compareToGoal(lastVisited.getPuz().getPuzzle())) {
			/*for(int num[] : lastVisited.getPuz().getPuzzle()){
				for(int numb : num) {
					System.out.println(numb);
				}
			}*/
			if (cutOff >= CUT_OFF_POINT) {
				solution[0] = "-1";
				solution[1] = "-1";
				System.out.println("reset");
				return solution;
			}
			generateChildren(lastVisited);
			lastVisited = visitNext();
			cutOff++;
		}
		
		
		solution[0] = generateSolution(tree, lastVisited);
		solution[1] =  Integer.toString(lastVisited.getPuz().getDepth());
		return solution;
		
	}
	
	public void generateChildren(TreeNode lastVisited) {
		int[] zeroLoc = lastVisited.getPuz().getZeroLoc().clone();
		int[][] currentPuz = lastVisited.getPuz().getPuzzle();
		int depth = lastVisited.getPuz().getDepth() +1;
		if( zeroLoc[0] > 0) {
			int[][] newPuz = new int[3][3];
			for(int i = 0; i<3; i++) {
				for(int j = 0; j<3; j++)
				{
					newPuz[i][j] = currentPuz[i][j];
				}
			}
			int swap1 = currentPuz[zeroLoc[0]][zeroLoc[1]];
			int swap2 = currentPuz[zeroLoc[0]-1][zeroLoc[1]];
			newPuz[zeroLoc[0]][zeroLoc[1]] = swap2;
			newPuz[zeroLoc[0]-1][zeroLoc[1]] = swap1;
			if (!isExplored(newPuz)) 
			{
				Puzzle pChild = new Puzzle(newPuz, depth);
				pChild.setCost(h.operation(pChild) + depth);
				TreeNode child = new TreeNode(pChild, lastVisited);
			
				//lastVisited.addChild(child);
				ExploredSet.add(child);
				Frontier.add(child);
			}
		}
		
		if( zeroLoc[0] < 2) {
			int[][] newPuz = new int[3][3];
			for(int i = 0; i<3; i++) {
				for(int j = 0; j<3; j++)
				{
					newPuz[i][j] = currentPuz[i][j];
				}
			}
			int swap1 = currentPuz[zeroLoc[0]][zeroLoc[1]];
			int swap2 = currentPuz[zeroLoc[0]+1][zeroLoc[1]];
			newPuz[zeroLoc[0]][zeroLoc[1]] = swap2;
			newPuz[zeroLoc[0]+1][zeroLoc[1]] = swap1;
			if (!isExplored(newPuz)) 
			{
				Puzzle pChild = new Puzzle(newPuz, depth);
				pChild.setCost(h.operation(pChild) + depth);
				TreeNode child = new TreeNode(pChild, lastVisited);
			
				//lastVisited.addChild(child);
				ExploredSet.add(child);
				Frontier.add(child);
			}
			
		}
		if( zeroLoc[1] > 0) {
			int[][] newPuz = new int[3][3];
			for(int i = 0; i<3; i++) {
				for(int j = 0; j<3; j++)
				{
					newPuz[i][j] = currentPuz[i][j];
				}
			}
			int swap1 = currentPuz[zeroLoc[0]][zeroLoc[1]];
			int swap2 = currentPuz[zeroLoc[0]][zeroLoc[1]-1];
			newPuz[zeroLoc[0]][zeroLoc[1]] = swap2;
			newPuz[zeroLoc[0]][zeroLoc[1]-1] = swap1;
			if (!isExplored(newPuz)) 
			{
				Puzzle pChild = new Puzzle(newPuz, depth);
				pChild.setCost(h.operation(pChild) + depth);
				TreeNode child = new TreeNode(pChild, lastVisited);
			
				//lastVisited.addChild(child);
				ExploredSet.add(child);
				Frontier.add(child);
			}
		}
		if( zeroLoc[1] < 2) {
			int[][] newPuz = new int[3][3];
			for(int i = 0; i<3; i++) {
				for(int j = 0; j<3; j++)
				{
					newPuz[i][j] = currentPuz[i][j];
				}
			}
			int swap1 = currentPuz[zeroLoc[0]][zeroLoc[1]];
			int swap2 = currentPuz[zeroLoc[0]][zeroLoc[1]+1];
			newPuz[zeroLoc[0]][zeroLoc[1]] = swap2;
			newPuz[zeroLoc[0]][zeroLoc[1]+1] = swap1;
			if (!isExplored(newPuz)) 
			{
				Puzzle pChild = new Puzzle(newPuz, depth);
				pChild.setCost(h.operation(pChild) + depth);
				TreeNode child = new TreeNode(pChild, lastVisited);
			
				//lastVisited.addChild(child);
				ExploredSet.add(child);
				Frontier.add(child);
			}
		}
		
	}
	
	private TreeNode visitNext() {
		TreeNode node = Frontier.remove();
		return node;
	}
	
	private String generateSolution(Tree tree, TreeNode currentNode) {
		String solution = "";
		return generateSolution(tree, currentNode, solution);
	}
	
	private String generateSolution(Tree tree, TreeNode currentNode, String solution) {
		if(currentNode != tree.getRoot()) 
		{
			solution = generateSolution(tree, currentNode.getParent(), solution);
			solution += Puzzle.printPuzzle(currentNode.getPuz().getPuzzle());
			return solution;
		}
		else
		{
			return Puzzle.printPuzzle(currentNode.getPuz().getPuzzle());
		}
	}
	
	private boolean compareToGoal(int[][] puz) {
		int i = 0;
		while(i < 9) {
			if(Goal[i/3][i%3] != puz[i/3][i%3])
				return false;
			i++;
		}
		return true;
	}
	
	private boolean isExplored(int[][] puz)
	{
		for (TreeNode node: ExploredSet) {
			int e;
			int n;
			boolean same = true;
			for(int i = 0; i < 9; i++) {
				n = puz[i/3][i%3];
				e =	node.getPuz().getPuzzle()[i/3][i%3];
				if(n != e) {
					same = false;
					break;
				}
			}
			if (same == true)
			{
				return true;
			}
		}
		return false;
	}
	
}
