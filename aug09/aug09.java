import java.util.*;

public class aug09{
   
   public static int maxCoins(int[] nums, int si, int ei, int[][] dp){     // burst baloons
        
     if(dp[si][ei] != 0){
         return dp[si][ei];
     }
        
    
        int lval = si == 0 ? 1:nums[si-1];
        int rval = ei == nums.length-1 ?1: nums[ei+1];
        
        int maxAns = 0;
        for(int cut = si;cut<=ei;cut++){
            int leftRes = cut == si ? 0 : maxCoins(nums, si, cut-1, dp);
            int rightRes = cut == ei ? 0 : maxCoins(nums, cut+1, ei, dp);

            int myAns = leftRes + lval*nums[cut]*rval + rightRes;

            maxAns = Math.max(myAns,maxAns);
        }

        return dp[si][ei] = maxAns;
    }   

    public static int evaluate(int a, int b, char oper){
        if(oper == '+') return a+b;
        else if(oper == '-') return a-b;
        else if(oper == '*') return a*b;
        else return a/b;
    }
    
    public static int minMaxExpression(String str, int si , int ei, int[][] dp){
    
        
        if(si == ei){
            return (str.charAt(si)-'0');
        }

        if(dp[si][ei]!=0){
            return dp[si][ei];
        }
        

        int maxAns = 0;      
        for(int cut = si+1;cut<ei;cut+=2){
            
            int leftRes = minMaxExpression(str, si, cut-1, dp);
            int rightRes = minMaxExpression(str, cut+1, ei, dp);

            int myAns = evaluate(leftRes, rightRes,str.charAt(cut));
            maxAns = Math.max(maxAns,myAns);


        }

        return dp[si][ei] = maxAns;
    }
    
    public static int minPalindromicCut(String str, int si, int ei, int[][] dp, boolean[][] palindromicSubstring){
          
        if(palindromicSubstring[si][ei]) return 0;
        int minAns = (int)1e8;
        for(int cut = si;cut<ei;cut++){
            int leftTree = minPalindromicCut(str, si, cut, dp, palindromicSubstring);
            int rightTree = minPalindromicCut(str, cut+1,ei, dp, palindromicSubstring);

            int myAns = leftTree+1+rightTree;

            minAns = Math.min(minAns, myAns);
        }

        return dp[si][ei] = minAns;
         

    }

    public static int minPalindromicCut_best(String str, int si, int ei, int[]dp, boolean[][] palindromicSubstring){
          
        if(palindromicSubstring[si][ei]) return 0;
        int minAns = (int)1e8;
        for(int cut = si;cut<ei;cut++){
            if(palindromicSubstring[si][cut]){
                minAns = Math.min(minAns, minPalindromicCut_best(str, cut+1, ei, dp, palindromicSubstring)+1);
            }
        }

        return dp[si] = minAns;
         

    }

    public static int minCut(String str) {
		int n = str.length();
		int[][] dp=new int[n][n];
		boolean[][] isPalindrome=new boolean[n][n];

		for(int[] d: dp) Arrays.fill(d,-1);

		for (int gap = 0; gap < n; gap++) {
			for (int si = 0, ei = gap; ei < n; si++, ei++) {
				if (gap == 0) isPalindrome[si][ei] = true;
				else if (str.charAt(si) == str.charAt(ei) && gap == 1) isPalindrome[si][ei] = true;
				else isPalindrome[si][ei] = str.charAt(si) == str.charAt(ei) && isPalindrome[si + 1][ei - 1];
			}
		}

		return minPalindromicCut(str,0,n-1,dp,isPalindrome);
    }
    public static void main(String[] args){
         
        System.out.println(minMaxExpression("1+2*3+4*5", 0,8,new int[9][9]));
        
    }
}