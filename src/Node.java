public class Node{
	private int[][] info;
	private double epy = 0;
	public int variable;
	public int[] order;
	Node left;
	Node right;

	public Node(){
		info = null;
		order = null;
		left = null;
		right = null;
	}

	public Node(int[][] sys){
		info = sys;
		epy = sysepy(info);
		//epy = e;
		left = null;
		right = null;
	}

	public int[][] getinfo(){
		return info;
	}

	public double getepy(){
		return epy;
	}

	public static double log2(double x){
		return Math.log(x)/Math.log(2);
	}

	public static double entropy(double pos, double neg){
		//-P(+)log2(P(+)) - p(-)log2(P(-))
		if(pos == 0 || neg == 0)
			return 0;
		return -1*(pos*log2(pos)) - (neg*log2(neg));

	}

	//Entropy of the system that is passed in
	public static double sysepy(int[][] set){
		int count = set.length;
		int positive = 0;
		for(int i = 0; i<count;++i){
			if(set[i][set[i].length - 1] == 1)
				++positive;
		}
		double pos = (double) positive/count;
		//System.out.println(pos);
		double neg = (double) (count-positive)/count;
		return entropy(pos,neg);
	}
}
