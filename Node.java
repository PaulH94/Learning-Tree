public class Node{
	private int[][] info;
	private double epy = 0;
	Node left;
	Node right;

	public Node(){
		info = null;
		left = null;
		right = null;
	}

	public Node(int[][] sys){
		info = sys;
		left = null;
		right = null;
	}

	public int[][] getinfo(){
		return info;
	}
}
