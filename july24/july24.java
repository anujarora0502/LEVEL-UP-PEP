
//String DP problems

import java.util.*;

public class july24{
    

    public static int[][] longestPalindromeSubstring(String str){

        int n = str.length();
        int[][] dp = new int[n][n];
        
        int maxLen = 0;
        int count = 0;

        for(int gap = 0;gap<n;gap++){
            
            for(int i = 0, j = gap;j<n;j++,i++){

                if(gap == 0){
                    dp[i][j] = 1;
                }

                else if(gap == 1 && str.charAt(i) == str.charAt(j)){
                    dp[i][j] = 2;
                }
                else if(str.charAt(i) == str.charAt(j)&& dp[i+1][j-1]>0){
                   dp[i][j] = dp[i+1][j-1]+2;
                }

                maxLen = Math.max(maxLen,dp[i][j]);
                count+= dp[i][j]>0?1:0;
            }
        }
        System.out.println(maxLen+" "+count);
        return dp;
    }
   
    public static int longestPalindromicSubsequence(String str, int i, int j, int[][] dp){
        if(i>j){
            return 0;
        }

        if(i == j){
            return dp[i][j] = 1;
        }

        if(dp[i][j] != 0){
            return dp[i][j];
        }



         if(str.charAt(i) == str.charAt(j)){
             int ans = longestPalindromicSubsequence(str, i+1, j-1, dp)+2;
             dp[i][j] = ans;
             return ans;
         }else {
             int ans = Math.max(longestPalindromicSubsequence(str, i+1, j, dp),longestPalindromicSubsequence(str, i, j-1, dp));
             dp[i][j] = ans;
             return ans;
         }
    }

    public static int longestPalindromicSubsequence_DP(String str,int[][] dp){
        int n = str.length(); 
        int maxLen = 0;       
        for(int gap = 0;gap<n;gap++){
            
            for(int i = 0, j = gap;j<n;j++,i++){
                   
                if(i>j){
                    dp[i][j] = 0;
                    continue;
                }
        
                if(i == j){
                    dp[i][j] = 1;
                    continue;   
                }
        
                 if(str.charAt(i) == str.charAt(j)){
                     dp[i][j] = dp[i+1][j-1]+2;
                     maxLen = Math.max(maxLen,dp[i][j]);
                 }else {
                     int ans = Math.max(dp[i+1][j],dp[i][j-1]);
                     dp[i][j] = ans;
                     maxLen = Math.max(maxLen,dp[i][j]);
                 }
            }

        }

        return maxLen;

    }

    public static class pair {
        String str = "";
        int len = 0;

        pair(String str, int len) {
            this.str = str;
            this.len = len;
        }
    }
   
    public static pair longestPlaindromeSubsequence_02(String str, int i, int j, pair[][] dp) {
        if (i > j)
            return dp[i][j] = new pair("", 0);
        if (i == j)
            return dp[i][j] = new pair(str.charAt(i) + "", 1);

        if (dp[i][j] != null)
            return dp[i][j];

        pair maxPair = new pair("", 0);
        if (str.charAt(i) == str.charAt(j)) {
            pair recAns = longestPlaindromeSubsequence_02(str, i + 1, j - 1, dp);
            maxPair.str = str.charAt(i) + recAns.str + str.charAt(j);
            maxPair.len = recAns.len + 2;
        } else {
            pair recAns1 = longestPlaindromeSubsequence_02(str, i + 1, j, dp);
            pair recAns2 = longestPlaindromeSubsequence_02(str, i, j - 1, dp);

            if (recAns1.len > recAns2.len) {
                maxPair.len = recAns1.len;
                maxPair.str = recAns1.str;
            } else {
                maxPair.len = recAns2.len;
                maxPair.str = recAns2.str;
            }
        }

        return dp[i][j] = maxPair;

    }

    public static int longestPlaindromeSubsequence_DP(String str, int i, int j, int[][] dp) {
        int n = str.length();

        String[][] sdp = new String[n][n];
        for (String[] d : sdp)
            Arrays.fill(d, "");

        for (int gap = 0; gap < n; gap++) {
            for (i = 0, j = gap; j < n; j++, i++) {
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }

                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    sdp[i][j] = str.charAt(i) + sdp[i + 1][j - 1] + str.charAt(j);

                } else if (dp[i + 1][j] > dp[i][j - 1]) {
                    dp[i][j] = dp[i + 1][j];
                    sdp[i][j] = sdp[i + 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                    sdp[i][j] = sdp[i][j - 1];
                }

            }
        }

        System.out.println(sdp[0][n - 1] + " @ " + dp[0][n - 1]);
        return dp[0][n - 1];
    }

    public static int numDistinct_(String s, String t, int n, int m, int[][] dp){
         
        if(n<m){
            return dp[n][m] = 0;
        }

        if(m == 0){
            return dp[n][m] = 1;
        }

        if(dp[n][m]!=-1){
            return dp[n][m];
        }
        if(s.charAt(n-1)== t.charAt(m-1)){
            return dp[n][m] = numDistinct_(s, t, n-1, m-1, dp) + numDistinct_(s, t, n-1, m, dp);
        }else{
            return dp[n][m] = numDistinct_(s, t, n-1, m, dp);
        }
    }

    public static int numDistinctDP(String s, String t, int n, int m, int[][] dp){
        int N = n;
        int M = m;

        for(n = 0;n<=N;n++){
            for(m = 0;m<=M;m++){
                if(n<m){
                    dp[n][m] = 0;
                    continue;
                }
        
                if(m == 0){
                    dp[n][m] = 1;
                    continue;
                }
        
                if(s.charAt(n-1)== t.charAt(m-1)){
                    dp[n][m] = dp[n-1][m-1]+dp[n-1][m];
                }else{
                    dp[n][m] = dp[n-1][m];
                }
            }
        }

        return dp[N][M];
    }

    public static int numDistinct(String s, String t){
        int n = s.length();
        int m = t.length(); 
        int[][] dp = new int[n+1][m+1];

         for(int[] d:dp) Arrays.fill(d,-1);

         return numDistinct_(s, t, n, m, dp);
    }

    public static void main(String[] args){
       longestPalindromeSubstring("77bccb766");
    }


}