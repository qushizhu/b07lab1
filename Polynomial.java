public class Polynomial{
	double[] Coefficient;
	
	public Polynomial(){
		Coefficient= new double[1];
	     
	}
	
     public Polynomial(double[] A) {
		int len = A.length;
          Coefficient = new double[len];
		for(int i=0;i<len;i++) {
			Coefficient[i]=A[i];
		}
	}
	
	public Polynomial add(Polynomial P) {
		
		int len1=Coefficient.length;
		int len2=P.Coefficient.length;
		int len = Math.max(len1,len2);
		int min = Math.min(len2, len1);
		double[] new_double = new double[len];
		for(int i=0;i<min;i++) {
			new_double[i]=Coefficient[i]+P.Coefficient[i];
		}
		
		if (len1<len2) {
			for (int i=min;i<len;i++) {
			new_double[i]=P.Coefficient[i];
			}
		}	
		else if (len1>len2){
			for (int i=min;i<len;i++) {
			new_double[i]= Coefficient[i];
			}
		}
		else {
		}
			
			
		Polynomial R = new Polynomial(new_double);
		return R;
	}
	
	public double evaluate(double x) {
		int len=Coefficient.length;
		double result=0;
		
		
		for (int i=0; i<len; i++) {
			double coe=Coefficient[i];
			double var=Math.pow(x, i);
			double single=coe*var;
			result=result+single;
		}
		return result;
		
	}
	
	public boolean hasRoot(double r) {
		double n = evaluate(r);
		return n==0;
		
	}
}