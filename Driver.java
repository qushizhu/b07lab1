public class Driver {

		public static void main(String [] args) { 
			  Polynomial p = new Polynomial(); 
			  System.out.println(p.evaluate(3)); 
			  double [] c1 = {5, 2, -4};
			  double [] e1 = {0, 1, 3};
			  Polynomial p1 = new Polynomial(c1,e1); 
			  double [] c2 = {-3, 1, 3, 3}; 
			  double [] e2 = {0, 2, 3, 4};
			  Polynomial p2 = new Polynomial(c2, e2);
			  
			  Polynomial s = p1.add(p2); 
			  
		
			  double eva1= p1.evaluate(2);
			  double eva2=s.evaluate(2);
			  double eva3=p2.evaluate(2);
		
			  
			  System.out.println("p1(2) = "+ eva1);
			  System.out.println("p2(2) = "+ eva3);
			  System.out.println("s(2) = "+ eva2);
			  
			  if(s.hasRoot(0)) 
			   System.out.println("1 is a root of s"); 
			  else 
			   System.out.println("1 is not a root of s"); 
			 } 

	}

