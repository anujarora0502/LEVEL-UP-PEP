import java.util.*;

public class july29{

   
    public static int LCSubseq(String str1, int n, String str2, int m, int[][] dp){

        if(n == 0|| m==0){
            return dp[n][m] = 0;
        }
        
        if(dp[n][m]!=-1){
            return dp[n][m];
        }
       
        
        if(str1.charAt(n-1) == str2.charAt(m-1)){
           return dp[n][m] = LCSubseq(str1, n-1, str2, m-1, dp)+1;
       }

       else{
           return dp[n][m] = Math.max(LCSubseq(str1, n-1, str2, m, dp),LCSubseq(str1, n, str2, m-1, dp));
       }



    }


    public static int LCSubseq_DP(String str1, int n, String str2, int m, int[][] dp){
        int N = n;
        int M  = m;

        for(n = 0;n<=N;n++){
            for(m = 0;m<=M;m++){
                
               if(n == 0|| m==0){
                  dp[n][m] = 0;
                  continue; 
               }
               
               if(str1.charAt(n-1) == str2.charAt(m-1)){
                  dp[n][m] = dp[n-1][m-1]+1;
                   continue;
              }
       
              else{
                  dp[n][m] = Math.max(dp[n-1][m],dp[n][m-1]);
              }

            }
        }
       
       return dp[N][M];
         }


         public static int  maxUncrossedLines(int[] A, int[] B) {
            int N = A.length;
            int M = B.length;
            
            int[][] dp = new int[N+1][M+1];
            
            for(int n = 0;n<=N;n++){
                for(int m = 0;m<=M;m++){
                    if(n == 0||m==0){
                        continue;
                    }
                    
                    if(A[n-1] == B[m-1]){
                        dp[n][m] = dp[n-1][m-1]+1;
                    }
                    else{
                        dp[n][m] = Math.max(dp[n-1][m],dp[n][m-1]);
                    }
                }
            }
            
            return dp[N][M];
        }
    

        public static int editDistance(String str1, int n, String str2, int m, int[][] dp){
        if(n == 0||m == 0){
            return n == 0? m:n;   
        }
        
        if(dp[n][m]!=0) return dp[n][m];
        
        if(str1.charAt(n-1)==str2.charAt(m-1)){
            return dp[n][m] = editDistance(str1,n-1,str2,m-1,dp);
        }
        
        else{
            int insert = editDistance(str1,n,str2,m-1,dp);
            int delete = editDistance(str1,n-1,str2,m,dp);
            int replace = editDistance(str1,n-1,str2,m-1,dp);
            
            return dp[n][m] = Math.min(insert,Math.min(delete,replace))+1;
        }
    } 
    
   
    public static int countPalindromicSubSeq(String str, int i, int j, int[][] dp){
        
        if(i>j){
            return dp[i][j] = 0;
        }

        if(i == j){
            return dp[i][j] = 1;
        }
        if(dp[i][j]!=0){
            return dp[i][j];
        }



        int a = countPalindromicSubSeq(str, i+1, j-1,dp);
        int b = countPalindromicSubSeq(str, i, j-1, dp);
        int c = countPalindromicSubSeq(str, i+1, j, dp);

        if(str.charAt(i) == str.charAt(j)){
            return dp[i][j] = b+c+1;
        }

        else{

            return dp[i][j] = b+c-a;
        }
    }

    public static int countPalindromicSubSeq_DP(String str,int[][] dp){
      for(int j = 0;j<dp.length;j++){
          for(int i = dp.length-1;i>=0;i--){
            if(i>j){
                dp[i][j] = 0;
            }
    
            if(i == j){
                dp[i][j] = 1;
            }
    
            int a = dp[i+1][j-1];
            int b = dp[i][j-1];
            int c = dp[i+1][j];
    
            if(str.charAt(i) == str.charAt(j)){
                dp[i][j] = b+c+1;
            }
    
            else{
    
                dp[i][j] = b+c-a;
            }
          }
      }

      return dp[0][dp.length-1];
        

    }
   /// leetcode 940
    public static int distinctSubseq(String str){
        int mod = (int)1e9 + 7;
        str = '$'+str;
        int n = str.length();
        long[] dp = new long[n];
        dp[0] = 1;
        int[] loc = new int[26];
    

        for(int i = 1;i<n;i++){
           dp[i] = (2*dp[i-1])%mod;
           
           int idx = str.charAt(i)-'a';
           if(loc[idx]!=0){
               
               dp[i] = dp[i]%mod-dp[loc[idx]-1]%mod;
               if(dp[i]<0){
                   dp[i]+=mod;
               }
           }

           loc[idx] = i;
        }
         
        
        return (int)dp[n-1]-1;
    }
   
    public static void main(String[] args){

   }



}