/* package codechef; // don't place package name! */

import java.util.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		//Scanner scn = new Scanner(System.in);
		
		//int t = scn.nextInt();
		
		
		BufferedReader reader =  
                   new BufferedReader(new InputStreamReader(System.in));
                   
        String tempT = reader.readLine();
        
        int t = Integer.parseInt(tempT.charAt(0)+"");
		
		while(t-->0){
		    
		    HashMap<Integer, Integer> x = new HashMap<>();
		    HashMap<Integer, Integer> y = new HashMap<>();
		    
		    //int n = scn.nextInt();
		    
		     String tempN = reader.readLine();
        
             int n = Integer.parseInt(tempN.charAt(0)+"");
		    
		    for(int i = 0;i<4*n-1;i++){
		      //  int xPoint = scn.nextInt();
		      //  int yPoint = scn.nextInt();
		        
		        String tempXY = reader.readLine();
        
                StringTokenizer s = new StringTokenizer(tempXY);
		        
		        int xPoint = Integer.parseInt(s.nextToken());
		        
		        int yPoint = Integer.parseInt(s.nextToken());
		        
		        if(x.containsKey(xPoint)){
		            
		            x.put(xPoint,x.get(xPoint)+1);
		            
		        }else{
		            
		            x.put(xPoint,1);
		            
		        }
		        
		        if(y.containsKey(yPoint)){
		            
		            y.put(yPoint,y.get(yPoint)+1);
		            
		        }else{
		            
		            y.put(yPoint,1);
		            
		        }
		    }
		    
		    
		    Set<Integer> sx = x.keySet();
		    Set<Integer> sy = y.keySet();
		    int ansx = 0;
		    int ansy = 0;
		    for(int ele: sx){
		       if(x.get(ele)%2!=0){
		           ansx = ele;
		       } 
		    }
		    
		    for(int ele: sy){
		       if(y.get(ele)%2!=0){
		           ansy = ele;
		       } 
		    }
		    
		    System.out.println(ansx+" "+ansy);
		}
	}
}
