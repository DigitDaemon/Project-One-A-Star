

public class TreeNode implements Comparable<TreeNode>{
	private Puzzle puz;
	//ArrayList<TreeNode> children;
	TreeNode parent;
	
	protected TreeNode(Puzzle puz) {
		this.puz = puz;
	}
	
	protected TreeNode(Puzzle puz, TreeNode parent) {
		this.puz = puz;
		this.parent = parent;
	}

	public Puzzle getPuz() {
		return puz;
	}

	//public ArrayList<TreeNode> getChildren() {
	//	return (ArrayList<TreeNode>) children.clone();
	//}

	public TreeNode getParent() {
		return parent;
	}
	
	//public void addChild(TreeNode child) {
	//	children.add(child);
	//}

	//public int compare(TreeNode puz1, TreeNode puz2) {
	//	TreeNode p1 = (TreeNode)puz1;
	//	TreeNode p2 = (TreeNode)puz2;
	//	if (p1.getPuz().getCost()==p2.getPuz().getCost())
	//		return 0;
	//	else if(p1.getPuz().getCost()>p2.getPuz().getCost())
	//		return 1;
	//	else
	//		return -1;
	//}

	@Override
	public int compareTo(TreeNode arg0) {
		if (this.getPuz().getCost() == arg0.getPuz().getCost())
			return 0;
		else if (this.getPuz().getCost() < arg0.getPuz().getCost())
			return -1;
		else
			return 1;
	}

	
}