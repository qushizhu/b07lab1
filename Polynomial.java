import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class Polynomial{
	double[] Coefficient;
	double[] Exponent;
	
	public Polynomial(){
	      Coefficient= new double[1];
	      Exponent = new double[1];
	    
	}
	
	public Polynomial(double[] A, double[] B) {
		int len=A.length;
		this.Coefficient=new double[len];
		this.Exponent=new double[len];
		for(int i=0;i<len;i++) {
			this.Coefficient[i]=A[i];
		}
		for(int i=0; i<len; i++) {
			this.Exponent[i]=B[i];
		}
	}
	
    public Polynomial add(Polynomial P) {
		
    	int len1=this.Coefficient.length;
		int len2=P.Coefficient.length;
    	
    	double A_max=0;
		double B_max=0;
		for (int i=0;i<len1;i++) {
			if(this.Exponent[i]>=A_max) {
				A_max=this.Exponent[i];
			}
		}
		for (int i=0;i<len2;i++) {
			if(P.Exponent[i]>=B_max) {
				B_max=P.Exponent[i];
			}
		}
    	double max = Math.max(A_max, B_max);
    	
		
		double[] new_coe=new double[(int) (max+1)];
		double[] new_exp=new double[(int) (max+1)];
		
		for (int i=0;i<max+1;i++) {
			new_exp[i]=i;                               //setup exponent list
		}
		
		for (int i=0;i<max+1;i++) {                     //setup coefficient list
			double coe_sum=0;
			for (int j=0;j<len1;j++) {
				if (this.Exponent[j]==i) {
					coe_sum=coe_sum+this.Coefficient[j];
				}
			}
			for (int h=0; h<len2;h++) {
				if (P.Exponent[h]==i) {
					coe_sum=coe_sum+P.Coefficient[h];
				}
			}
			new_coe[i]=coe_sum;
		}
		
		int update_len=0;
		for (int i=0;i<max+1; i++) {
			if (new_coe[i]!=0) {
				update_len=update_len+1;
			}
		}
		
		double[] update_coe=new double[update_len];
		double[] update_exp=new double[update_len];
		
		int track=0;                                   //cancel redundant element in list
		for (int j=0; j<update_len; j++) {
			
			if(new_coe[j]!=0) {
				update_coe[track]=new_coe[j];
				update_exp[track]=new_exp[j];
			}
			else {
				continue;
			}
		}
		
		Polynomial R = new Polynomial(update_coe, update_exp); 
		return R;
	
		}
	
	public double evaluate(double x) {
		int len=this.Coefficient.length;
		double result=0;
		
		
		for (int i=0; i<len; i++) {
			double coe=this.Coefficient[i];
			double exp=this.Exponent[i];
			double var=Math.pow(x, exp);
			double single=coe*var;
			result = result+single;
		}
		return result;
		
	}
	
	public boolean hasRoot(double r) {
		double n = this.evaluate(r);
		return n==0;
		
	}
	
	public Polynomial multiply(Polynomial P) {
		int double_length = P.Exponent.length;
		int num_of_poly= this.Exponent.length;
		Polynomial Accumulator=new Polynomial();
		for (int i=0;i<num_of_poly;i++) {
			double[] new_coe=new double[double_length]; 
			double[] new_exp=new double[double_length];
			for (int j=0;j<double_length;j++) {
				new_coe[j]=this.Coefficient[i]*P.Coefficient[i];
				new_exp[j]=this.Exponent[i]+this.Exponent[i];
			}
			Polynomial to_sum=new Polynomial(new_coe,new_exp);
			Accumulator=Accumulator.add(to_sum);
			
		}
		return Accumulator;
	}
	public Polynomial(File f) throws IOException {
		
		BufferedReader input = new BufferedReader(new FileReader(f));
		String str=input.readLine();
		input.close();
		
		String[] pure=str.split("+|-");
		int size_of_poly=pure.length;
		if (pure[0]=="") {
			size_of_poly=size_of_poly-1;
		}
		double[] coe = new double[size_of_poly];
		double[] exp = new double[size_of_poly];
		int tracker = 0; 
		String[] splitByAdd = str.split("+");
		int len1=splitByAdd.length;
		for (int i=0; i<len1; i++) {
			String[] splitByMinus=splitByAdd[i].split("-");
			int len2=splitByMinus.length;
			
			if (len2==1) {
				String[] splitByX = splitByMinus[0].split("X");
				
				if (splitByX.length==1) {
					double x=Double.parseDouble(splitByX[0]);
					coe[tracker]=x;
					exp[tracker]=0;
					tracker=tracker+1;
				}
				else {
					double n=Double.parseDouble(splitByX[0]);
					double m=Double.parseDouble(splitByX[1]);
					coe[tracker]=n;
					exp[tracker]=m;
					tracker=tracker+1;
				}
			  }
			
			else {
				for (int j=0;j<len2;j++) {
				    if (splitByMinus[j]=="") {
				    	continue;
				    }
				    
				    else if(j==0) {
				    	String[] splitByX=splitByMinus[j].split("X");
				    	
				    	if (splitByX.length==1) {
				    		double m=Double.parseDouble(splitByX[0]);
				    		coe[tracker]=m;
				    		exp[tracker]=0;
				    		tracker=tracker+1;
				    	}
				    	
				    	else {
				    		double n=Double.parseDouble(splitByX[0]);
				    		double m=Double.parseDouble(splitByX[1]);
				    		coe[tracker]=n;
				    		exp[tracker]=m;
				    		tracker=tracker+1;
				    		
				    	}
				    	
				    }
				    
				    else {
				    	String[] splitByX=splitByMinus[j].split("X");
				    	if (splitByX.length==1) {
				    		double m = Double.parseDouble(splitByX[0]);
				    		coe[tracker]=m*(-1);
				    		exp[tracker]=0;
				    		tracker=tracker+1;
				    	}
				    	else {
				    		double n=Double.parseDouble(splitByX[0]);
				    		double m=Double.parseDouble(splitByX[1]);
				    		coe[tracker]=n*(-1);
				    		exp[tracker]=m;
				    		tracker=tracker+1;
				    	}
				    	
				    }
		
				}
			}
			
		}
	
		}
	
	public void saveToFile(String filename) throws IOException {
		
		String content="";
		int len=this.Coefficient.length;
		
		if (this.Exponent[0]==0) {
			String s=this.Coefficient[0]+"";
			content=content+s;
		}
		else {
			String s1=this.Coefficient[0]+"";
			String s2=this.Exponent[0]+"";
			content=content+s1+"X"+s2;
		}
		
		for (int i=1;i<len;i++) {
			if (this.Coefficient[i]>0) {
			    if (this.Exponent[i]==0) {
				    String s=this.Coefficient[i]+"";
				    content=content+""+"+"+s;
		        	}
			    else {
			    	String s1=this.Coefficient[i]+"";
			    	String s2=this.Exponent[i]+"";
			    	content=content+"+"+s1+"X"+s2;
			    }
		    }
			if (this.Coefficient[i]<=0) {
				if (this.Exponent[i]==0) {
				    String s=this.Coefficient[i]+"";
				    content=content+s;
		        	}
				else {
					String s1=this.Coefficient[i]+"";
			    	String s2=this.Exponent[i]+"";
			    	content=content+s1+"X"+s2;
				}
			}
		}
		
		
		PrintStream ps = new PrintStream("C:\\Users\\user\\myfile.txt");
		ps.println(content);
		ps.close();
	}

	
}
