import java.util.*;

public class aug07{
    
    public static void main(String[] args){
          int[] arr = {34, 8, 50};

          int n = arr.length;

          int[][] dp = new int[n][n];

          //System.out.println(MCM_rec(arr, 0, arr.length-1, dp));

          //print2d(dp);

          //System.out.println(MCM_rec_02(arr, 0, arr.length-1, new pair[arr.length][arr.length]).s);

          System.out.println(OptimalBST(arr, 0,arr.length-1, dp));
          
    } 


    public static void print2d(int[][] dp){
        for(int[] i:dp){
            for(int j:i){
                System.out.print(j+" ");
            }

            System.out.println();
        }
    }

    public static int MCM_rec(int[] arr, int si, int ei,int[][] dp){
        if(si+1 == ei) return dp[si][ei] = 0;

        if(dp[si][ei]!= 0){
            return dp[si][ei];
        }


        int myAns = (int)1e9;
        for(int cut = si+1;cut<ei;cut++){
            int leftRes = MCM_rec(arr, si, cut, dp);
            int rightRes = MCM_rec(arr, cut, ei, dp);


            int recRes = leftRes+arr[si]*arr[cut]*arr[ei]+rightRes;

            if(recRes<myAns) myAns = recRes;

        }

        return dp[si][ei] = myAns;
    }

    public static class pair{
        int cost;
        String s;
        pair(int cost, String s){
            this.cost = cost;
            this.s = s;
        }
    }
    public static pair MCM_rec_02(int[] arr, int si, int ei,pair[][] dp){
        if(si+1 == ei){
            return dp[si][ei] = new pair(0,(char)('A'+si)+"");
            
        } 

        if(dp[si][ei]!=null){
            return dp[si][ei];
        }


        int myAns = (int)1e9;
        String ans = "";
        for(int cut = si+1;cut<ei;cut++){
            pair leftRes = MCM_rec_02(arr, si, cut, dp);
            pair rightRes = MCM_rec_02(arr, cut, ei, dp);


            int recRes = leftRes.cost+arr[si]*arr[cut]*arr[ei]+rightRes.cost;



            if(recRes<myAns){
                 myAns = recRes;
                 ans = "("+leftRes.s+rightRes.s+")";
                }

        }

        return dp[si][ei] = new pair(myAns,ans);
    }

    public static int MCM_dp(int[] arr, int si, int ei,int[][] dp){

        for(int gap = 1;gap<arr.length;gap++){
            for(si=0,ei=gap;ei<arr.length;si++,ei++){
                if(si+1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }

                int myAns = (int)1e9;
           for(int cut = si+1;cut<ei;cut++){
            int leftRes = MCM_rec(arr, si, cut, dp);
            int rightRes = MCM_rec(arr, cut, ei, dp);


            int recRes = leftRes+arr[si]*arr[cut]*arr[ei]+rightRes;

            if(recRes<myAns) myAns = recRes;

        }

          dp[si][ei] = myAns;

        


            }
        }

        return dp[0][arr.length-1];
}

public static int summation(int si, int ei, int[] freq){
    int sum = 0;
    while(si<=ei) sum+= freq[si++];

    return sum;
}


public static int OptimalBST(int[] freq, int si, int ei,int[][] dp){ // prefix sum for n^3
    
    if(dp[si][ei] != 0) return dp[si][ei]; 

        int myAns=(int)1e9;
        for(int cut=si;cut<=ei;cut++){
            int leftRes = cut == si ? 0 : OptimalBST(freq,si,cut-1,dp);
            int rightRes = cut == ei ? 0 : OptimalBST(freq,cut+1,ei,dp);
            
            int recRes = leftRes + summation(si, ei, freq) + rightRes;
            if(recRes<myAns) myAns = recRes; 
        }

        return dp[si][ei] = myAns;
}

}