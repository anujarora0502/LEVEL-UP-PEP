import java.util.*;

public class aug05{

 

   public static int knapSack_rec(int cap, int[] weight, int[] value,int idx, int[][] dp){
     if(idx == 0||cap == 0){
         return dp[idx][cap] = 0;
     }

     if(dp[idx][cap]!=-1)  return dp[idx][cap];
     int maxProfit = 0;
     if(cap - weight[idx-1]>=0)
     maxProfit = Math.max(maxProfit,knapSack_rec(cap-weight[idx-1],weight, value,idx-1,dp))+value[idx-1];
     maxProfit = Math.max(maxProfit,knapSack_rec(cap, weight, value, idx-1,dp));

    return dp[idx][cap] = maxProfit;
   }
    

   public static int knapSack_DP(int cap, int[] weight, int[] value,int idx,int[][] dp){
       int I = idx;
       int C = cap;

       for(idx = 0;idx<=I;idx++){
           for(cap = 0;cap<=C;cap++){
            if(idx == 0||cap == 0){
                dp[idx][cap] = 0;
                continue;
            }
            int maxProfit = 0;
            if(cap - weight[idx-1]>=0)
            maxProfit = Math.max(maxProfit,dp[idx-1][cap-weight[idx-1]]+value[idx-1]);
            maxProfit = Math.max(maxProfit,dp[idx-1][cap]);
       
            dp[idx][cap] = maxProfit;
           }
       }

       return dp[dp.length-1][dp[0].length-1];
   }

   public static int unbounded_knapsack(int cap, int[] weight, int[] value){
       int n = weight.length;
       int[] dp = new int[cap+1];

       for(int i = 0;i<n;i++){
           for(int c = weight[i];c<=cap;c++){
               dp[c] = Math.max(dp[c],dp[c-weight[i]]+value[i]);
               
           }
           for( int j = 0;j<=cap;j++){
            System.out.print(dp[j]+" ");
           }

           System.out.println();
           
       }

    

       return dp[dp.length-1];
   }
   public static void solve(){
         
        int[] weight = {5,4,1};
        int[] value = {6,10,1};
        int[][] dp = new int[value.length+1][6];
        for(int[] i:dp)Arrays.fill(i,-1);
        System.out.println(knapSack_DP(5, weight, value, value.length,dp));


        for(int[] i: dp){
            for(int j = 0;j<i.length;j++){
                System.out.print(i[j]+" ");
            }

            System.out.println();
        }

        System.out.println();

        unbounded_knapsack(5, weight, value);
        
   } 
 
 
    public static void main(String[] args){
      
        solve();


  }

}