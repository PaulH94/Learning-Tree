public class Main{

	public static double log2(double x){
		return Math.log(x)/Math.log(2);
	}

	public static double entropy(double pos, double neg){
		//-P(+)log2(P(+)) - p(-)log2(P(-))
		if(pos == 0 || neg == 0)
			return 0;
		return -1*(pos*log2(pos)) - (neg*log2(neg));

	}

	public static double kgain(int[][] x){
		//gain(system,attribute) = entropy(system) - 
		//summation of Sv/S * entropy(Sv)
		//where Sv/S is the ratio of the value/attribute
		int count = x.length;
		
		return 0.0;
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

	public static void main(String[] args){
		//System.out.println(log2(0.0));
		System.out.println(entropy(0.99,0.01));
		//Humidity,Wind,PlayTennis
		int[][] example = {{1,1,0},
							{0,0,1},
							{1,0,1},
							{0,0,0}};
		kgain(example);
		System.out.println(sysepy(example));
	}

}
