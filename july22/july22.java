/*package whatever //do not write package name here */

import java.util.*;

public class july22{
	public static void main (String[] args) {
		Scanner scn = new Scanner(System.in);
		
		int t = scn.nextInt();
		
		while(t-->0){
		    int n = scn.nextInt();
		    int m = scn.nextInt();
		    
		    int[][] mine = new int[n][m];
		    
		    for(int i =0;i<n;i++){
		        for(int j = 0;j<m;j++){
		            mine[i][j] = scn.nextInt();
		        }
		    }
		    
		    int ans = 0;
		    
		    int[][] dp = new int[n][m];
		    
		    for(int i =0;i<n;i++){
		      ans = Math.max(goldMine(i,0,n-1,m-1,mine,dp),ans);
		    }
		    
		    System.out.println(ans);
		    
		}
	}
	
	static int[][] dir = {{-1,1},{0,1},{1,1}};
	
	public static int goldMine(int sr, int sc, int er, int ec, int[][] mine, int[][] dp){
	     
	     if(sr>er||sc>ec||sr<0||sc<0){
	         return 0;
	     }
	     
	     if(sc == ec){
	         return dp[sr][sc] = mine[sr][sc]; 
	     }
	     
	     if(dp[sr][sc]!=0){
	         return dp[sr][sc];
	     }
	     
	     int ans = 0;
	     
	     for(int i = 0;i<dir.length;i++){
	         int rr = goldMine(sr+dir[i][0],sc+dir[i][1],er,ec,mine,dp);
	         ans = Math.max(rr,ans);
	     }
	     
	     return dp[sr][sc] = mine[sr][sc] + ans;
	     
    }
    

    public static int goldMineDP(int sr, int sc, int er, int ec, int[][] mine, int[][] dp){
	     
        for(sc = ec;sc>=0;sc--){
            for(sr = 0;sr<=er;sr++){
                if(sc == ec){
                dp[sr][sc] = mine[sr][sc]; 
                continue;
                }
        
        int ans = 0;
        
        for(int i = 0;i<dir.length;i++){
            if(sr+dir[i][0]>=0&&sr+dir[i][0]<=er&&sc+dir[i][1]>=0&&sc+dir[i][1]<=ec)
             ans = Math.max(dp[sr+dir[i][0]][sc+dir[i][1]],ans);
        }
        
        dp[sr][sc] = mine[sr][sc] + ans;
            }
        }
        
        
        int MaxCoin = 0;
        
        for(int i = 0;i<=er;i++){
            MaxCoin = Math.max(MaxCoin,dp[i][0]);
        }
        
        return MaxCoin;
        
   }
}



//LEETCODE 64 done 

static int mod = (int)1e9 +7;


public static long friendsPairingProblem(int n, long[] dp) {
    if (n <= 1)
        return dp[n] = 1;
    if (dp[n] != 0)
        return dp[n];

    long single = friendsPairingProblem(n - 1, dp) % mod;
    long pairUp = friendsPairingProblem(n - 2, dp) % mod * (n - 1) % mod;

    return dp[n] = (single + pairUp) % mod;
}

public static long friendsPairingProblem_DP(int n, long[] dp) {
    int N = n;
    for (n = 0; n <= N; n++) {
        if (n <= 1) {
            dp[n] = 1;
            continue;
        }

        long single = dp[n - 1] % mod;// friendsPairingProblem(n - 1, dp);
        long pairUp = dp[n - 2] % mod * (n - 1) % mod;// friendsPairingProblem(n - 2, dp) * (n - 1);

        dp[n] = single + pairUp;
    }

    return dp[N] % mod;
}

public static int friendsPairingProblem_Opti(int n) {
    int single = 1;
    int pairUp = 1;

    int ans = 0;
    for (int i = 2; i <= n; i++) {
        ans = single + pairUp * (i - 1);

        pairUp = single;
        single = ans;
    }

    return ans;
}

public static void friendsPairingProblem() {
    // int n = scn.nextInt();
    int n = 5;
    long[] dp = new long[n + 1];
    long ans = friendsPairingProblem(n, dp);

    System.out.println(ans);
}

// https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/

public static int count_of_ways(int n, int k, int[][] dp) {
    if (k == n || k == 1)
        return dp[k][n] = 1;

    if (dp[k][n] != -1)
        return dp[k][n];

    int ownGroup = count_of_ways(n - 1, k - 1, dp);
    int partOfOtherGroup = count_of_ways(n - 1, k, dp) * k;

    return dp[k][n] = ownGroup + partOfOtherGroup;
}

public static int count_of_ways_DP(int n, int k, int[][] dp) {
    int K = k;
    int N = n;
    for (k = 1; k <= K; k++) {
        for (n = k; n <= N; n++) {
            if (k == n || k == 1) {
                dp[k][n] = 1;
                continue;
            }

            int ownGroup = dp[k - 1][n - 1]; // count_of_ways(n - 1, k - 1, dp);
            int partOfOtherGroup = dp[k][n - 1] * k;// count_of_ways(n - 1, k, dp) * k;

            dp[k][n] = ownGroup + partOfOtherGroup;
        }
    }

    return dp[K][N];
}

public static void count_of_ways(int n, int k) {
    int[][] dp = new int[k + 1][n + 1];
    for (int[] d : dp)
        Arrays.fill(d, -1);

    // System.out.println(count_of_ways(n, k, dp));
    System.out.println(count_of_ways_DP(n, k, dp));

    display2D(dp);
}