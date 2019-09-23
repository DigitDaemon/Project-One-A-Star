public class Tree {
	TreeNode root;
	TreeNode currentNode;
	
	public Tree(Puzzle puz) {
		root = new TreeNode(puz);
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
}

