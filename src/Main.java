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

	public static double kgain(int[][] sys,int num){
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

	public static double mostGain(int[][] sys){
		double best = 0;
		double current = 0;
		for(int i = 0; i<2; ++i){
			current = kgain(sys,i);
			System.out.println(current);
			if(current > best)
				best = current;
		}
		return best;
	}

	public static void main(String[] args){
		//System.out.println(log2(0.0));
		System.out.println(entropy(0.99,0.01));
		//Humidity,Wind,PlayTennis
		int[][] example = {{1,1,0},
							{0,0,1},
							{1,0,1},
							{0,0,0}};
		double yo = kgain(example,1);
		System.out.println(kgain(example,0));
		System.out.println(yo);
		System.out.println("------------------------");
		System.out.println(mostGain(example));
		//System.out.println(sysepy(example));
	}

}
