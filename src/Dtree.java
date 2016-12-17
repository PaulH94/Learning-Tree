public class Dtree{

	//public int[] order;
	private Node root;
	

	public Dtree(int[][] sys){
		root = new Node(sys);
		order = new int[2];
	}

	public void newlvl(){
		newlvl(root);
	}

	private void newlvl(Node root){

		if(root.getepy() == 0)
			return;
		if(root.left == null && root.right == null){
			int[][] set = root.getinfo();
			int most = mostGain(set);
			//System.out.println(most);
			int count = set.length;
			int high = 0;
			for(int i = 0; i < count; ++i){
				if(set[i][most] == 1)
					++high;
			}

			int[][] temph = new int[high][3];
			int[][] templ = new int[count-high][3];
			int ch = 0;
			int cl = 0;

			for(int i = 0; i < count; ++i){
				if(set[i][most] == 1){
					temph[ch] = set[i];
					++ch;
				}
				if(set[i][most] == 0){
					templ[cl] = set[i];
					++cl;
				}
			}
/*
			if(root.order == null){
				root.order = new int[1];
				root.order[0] = most;
			}
*/			int[] tempo = new int[root.order.length+1];

			for(int i = 0; i < root.order.length; ++i){
				tempo[i] = root.order[i];

			}
			temp[root.order.length] = most;

			root.left = new Node(temph);
			root.left.variable = root.right.variable = most;
			root.right = new Node(templ);
			return;
		}

		newlvl(root.left);
		newlvl(root.right);
	}

	public double log2(double x){
		return Math.log(x)/Math.log(2);
	}

	public double entropy(double pos, double neg){
		//-P(+)log2(P(+)) - p(-)log2(P(-))
		if(pos == 0 || neg == 0)
			return 0;
		return -1*(pos*log2(pos)) - (neg*log2(neg));
	}

	//Entropy of the system that is passed in
	public double sysepy(int[][] set){
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

	public double kgain(int[][] sys,int num){
		//gain(system,attribute) = entropy(system) - 
		//summation of Sv/S * entropy(Sv)
		//where Sv/S is the ratio of the value/attribute
		double sy = sysepy(sys);
		int count = sys.length;
		int high = 0;
		for(int i = 0; i < count; ++i){
			if(sys[i][num] == 1)
				++high;
		}

		int[][] temph = new int[high][3];
		int[][] templ = new int[count-high][3];
		int ch = 0;
		int cl = 0;

		for(int i = 0; i < count; ++i){
			if(sys[i][num] == 1){
				temph[ch] = sys[i];
				++ch;
			}
			if(sys[i][num] == 0){
				templ[cl] = sys[i];
				++cl;
			}
		}
		double hi = (double) high/count;
		double low = (double) (count - high)/count;
		return sy - (hi*sysepy(temph)) - (low*sysepy(templ));
	}

	public int mostGain(int[][] sys){
		double best = 0;
		double current = 0;
		int b = -1;
		for(int i = 0; i<2; ++i){
			current = kgain(sys,i);
			System.out.println(current);
			if(current > best){
				best = current;
				b = i;
			}
		}
		return b;
	}

	public void displaytree(){
		displaytree(root);
	}

	private void displaytree(Node root){
		if(root == null)
			return;
		int[][] set = root.getinfo();
		for(int i = 0; i< set.length; ++i){
			for(int j = 0; j < 3; ++j){
				System.out.print(set[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------------");

		displaytree(root.left);
		displaytree(root.right);
	}
}
