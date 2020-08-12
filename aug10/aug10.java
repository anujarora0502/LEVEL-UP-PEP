import java.util.*;

public class aug10{

    public static void main(String[] args){

    
    }

    //leetcode 1039 this is a good question

    public static int minTriangulation(int[] arr, int si, int ei, int[][] dp){
        
        if(ei - si < 2) return 0;

        if(dp[si][ei]!=0) return dp[si][ei];

        int minAns = (int)1e8;
        
        for(int cut = si+1;cut<ei;cut++){
           int leftAns = minTriangulation(arr,si,cut,dp);
           int rightAns = minTriangulation(arr, cut, ei, dp);
           
           int myAns = leftAns + arr[si]*arr[cut]*arr[ei] + rightAns;
           minAns = Math.min(minAns,myAns);
        }

        return dp[si][ei] = minAns;
    }

   
    // Leetcode 44 - wildcard matching
    public boolean isMatch(String s, String p) {
        
        p = removeStar(p);
        
        int[][] dp = new int[s.length()+1][p.length()+1];
        
        for(int[] i:dp){
            Arrays.fill(i,-1);
        }
        
        return wildCard(s,p,s.length(),p.length(), dp) == 1?true:false;
     
    }
    
    
    public static int wildCard(String str1, String str2, int n, int m, int[][] dp){
        
        if(n == 0 || m == 0){
            if(n == 0 && m == 0){
                return dp[n][m] = 1;
            }
            else if(m == 0){
                return dp[n][m] = 0;
            }

            else{  //n == 0
                   
                return dp[n][m] = (str2.charAt(m-1) == '*' && (m-1 == 0))?1:0;
            }
        }

       
       if(dp[n][m]!=-1) return dp[n][m];
       
       
       if(str1.charAt(n-1)==str2.charAt(m-1)) {
            return dp[n][m] = wildCard(str1, str2, n-1, m-1, dp);
        }
        
       if(str2.charAt(m-1) == '?'){ 
           return dp[n][m] = wildCard(str1, str2, n-1, m-1, dp);
       }
        
       if(str2.charAt(m-1) == '*'){
           int emptyMapping = wildCard(str1, str2, n, m-1, dp);
           int sequenceMapping = wildCard(str1, str2, n-1, m, dp);

           return dp[n][m] = (emptyMapping == 1 || sequenceMapping == 1) ? 1 : 0;
       }
        
       else{ 
            return dp[n][m] = 0;
        }
        
        //return false;
    }
    
    public static String removeStar(String p){

        StringBuilder str = new StringBuilder(); 
        for (int i=0;i<p.length();i++){
            if(p.charAt(i)=='*'){
                str.append('*');
                while (i<p.length() && p.charAt(i)=='*'){
                    i++;
                }
                i--;
            }else{
                str.append(p.charAt(i));
            }
            
        }

        return str.toString();

    }
}