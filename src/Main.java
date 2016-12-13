public class Main{

	public static double log2(double x){
		return Math.log(x)/Math.log(2);
	}

	public static double entropy(double pos, double neg){
		//-P(+)log2(P(+)) - p(-)log2(P(-))
		if(pos == 0 || neg == 0)
			return 0;
		return -1*(pos*log2(pos)) - (neg*log2(neg));
		//return log2(neg);
	}

	public static double kgain(){
		//gain(system,attribute) = entropy(system) - 
		//summation of Sv/S * entropy(Sv)
		//where Sv/S is the ratio of the value/attribute

		return 0.0;
	}

	public static void main(String[] args){
		//System.out.println(log2(0.0));
		System.out.println(entropy(0.99,0.01));
	}

}
