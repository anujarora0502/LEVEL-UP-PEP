import java.util.*;

public class aug02{
    

    public int findNumberOfLIS(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];
        int[] count = new int[n];

        int maxLen = 0;
        int maxCount = 0;

        for(int i = 0;i<n;i++){
            dp[i] = 1;
            count[i] = 1;

            for(int j = i-1;j>=0;j--){
                if(arr[i]>arr[j]){
                    if(dp[j]+1 > dp[i]){
                        dp[i] = dp[j]+1;
                        count[i] = count[j];
                    }else if(dp[i] == dp[j]+1){
                        count[i]+= count[j];
                    }
                }
            }

            if(dp[i]>maxLen){
                maxLen = dp[i];
                maxCount = count[i];
            }else if(dp[i] == maxLen){
                maxCount+= count[i];
            }
        }

        return maxCount;
    }

    ///    TARGET SET DP

    public static int coinChangePermutation(int[] arr, int tar, int[] dp){
      if(tar<0){
          return 0;
      }
        if(tar == 0){
          return dp[tar] = 1;
      }

      if(dp[tar]!=0){
          return dp[tar];
      }

      int count = 0;
        
      for(int i = 0;i<arr.length;i++){
          count+=coinChangePermutation(arr, tar-arr[i], dp);
      }

      return dp[tar] = count;

    }

    public static int coinChangePermutation_DP(int[] arr, int tar, int[] dp){
      int t = tar;
      for(tar = 0;tar<=t;tar++){
        if(tar == 0){
            dp[tar] = 1;
            continue;
        }
          
        for(int i = 0;i<arr.length;i++){
            if(tar-arr[i]>=0)
            dp[tar]+=dp[tar-arr[i]];
        }

    }
  
        return dp[dp.length-1];
  
      }


      public static int coinChangeCombination(int[] arr, int tar, int idx){

        if(tar<0){
            return 0;
        }
          if(tar == 0){
            return 1;
        }
  
        int count = 0;
          
        for(int i = idx;i<arr.length;i++){
            count+=coinChangeCombination(arr, tar-arr[i], i);
        }
  
        return count;



      }

      public static int coinChangeCombination_DP(int[] arr, int tar, int[] dp){
        int Tar = tar;

        dp[0] = 1;

        for(int ele: arr){
            for(tar = ele;tar<=Tar;tar++){
               if( tar - ele >= 0){
                   dp[tar]+= dp[tar-ele];
               }
            }
        }

        return dp[dp.length -1];
      }

      public int coinChange_(int[] coins, int amount,int[] dp) {
        if(amount==0){
            return dp[amount] = 0;
        }

        if(dp[amount]!=0) return dp[amount];
        
        int minCoins=(int)1e8;
        for(int ele: coins){
            if(amount - ele >= 0){
                minCoins = Math.min(minCoins,coinChange_(coins,amount-ele,dp)+1);
               
            }
        }

        return dp[amount]=minCoins;
    }

    public int coinChange_02(int[] coins, int amount,int[] dp) {
        Arrays.fill(dp,100000000);
        int Amount = amount;
        dp[0] = 0;
        for(int ele: coins){
            for(amount = ele;amount<=Amount;amount++){
               if(dp[amount-ele] + 1 < dp[amount]){
                   dp[amount]=dp[amount-ele] + 1;
               }
            }
        }
      return dp[Amount];
    }



      public static int coinChange(int[] coins, int amount, int[] dp){

        if(amount == 0){
            return dp[amount] = 0;
        }

        if(dp[amount]!=0) return dp[amount];

        int minCoins = (int)1e8;

        for(int ele : coins){
            if(amount - ele >=0){
                int Mcoin = coinChange(coins, amount-ele,dp);
                if(Mcoin!= (int)1e8 && Mcoin+1 < minCoins) minCoins = Mcoin+1;
            }
        }

        return dp[amount] = minCoins;
      }

      // Geeks fot you: https://www.geeksforgeeks.org/find-number-of-solutions-of-a-linear-equation-of-n-variables/

    public static int targetSum(int[] coins,int tar,int vidx,int[][] dp){
        if(tar==0 || vidx == coins.length){
            return dp[vidx][tar] = (tar==0)?1:0;
        }

        if(dp[vidx][tar]!=-1) return dp[vidx][tar];

        int count=0;
        if(tar-coins[vidx]>=0)
           count+=targetSum(coins,tar-coins[vidx],vidx+1,dp);
        count+=targetSum(coins,tar,vidx+1,dp);

        return dp[vidx][tar]=count;
    }

    public static int targetSum_01(int[] coins,int tar,int n,int[][] dp){
        if(tar==0 || n == 0){
            return dp[n][tar] = (tar==0)?1:0;
        }

        if(dp[n][tar]!=-1) return dp[n][tar];

        int count=0;
        if(tar-coins[n-1]>=0)
           count+=targetSum_01(coins,tar-coins[n-1],n-1,dp);
        count+=targetSum_01(coins,tar,n-1,dp);

        return dp[n][tar]=count;
    }

    public static int targetSum_01_DP(int[] coins,int tar,int n,int[][] dp){
        int N=n;
        int Tar=tar;

        for(n=0;n<=N;n++){
            for(tar=0;tar<=Tar;tar++){
                if(tar == 0 || n == 0){
                    dp[n][tar] = (tar==0)?1:0;
                    continue;
                }
                
                dp[n][tar]=0;
                if(tar-coins[n-1]>=0)
                   dp[n][tar]+=dp[n-1][tar-coins[n-1]];
                dp[n][tar]+=dp[n-1][tar];
            }
        }

        return dp[N][Tar];
    }

    public static void targetSum_01_BE(int[] coins,int tar,int n,int[][] dp,String str){
        if(tar==0 || n == 0){
            System.out.println(str);
            return;
        }

        
        if(tar-coins[n-1]>=0 && dp[n-1][tar-coins[n-1]]!=0)
           targetSum_01_BE(coins,tar-coins[n-1],n-1,dp,coins[n-1] + " " +str);
        
        if(dp[n-1][tar]!=0)
           targetSum_01_BE(coins,tar,n-1,dp,str);
    }




    public static void targetSum(){
        //  int[] coins={1,2,3,4,5,6,7,8};
         int[] coins={2,1,3,5,6};
         int tar = 10;

         int[][] dp=new int[coins.length + 1][tar+1];
         for(int[] d:dp) Arrays.fill(d,-1);

        //  System.out.println(targetSum(coins,tar,0,dp));
        //  System.out.println(targetSum_01(coins,tar,coins.length,dp));
         System.out.println(targetSum_01_DP(coins,tar,coins.length,dp));
         targetSum_01_BE(coins,6,coins.length,dp,"");
    }


    public static void main(String[] args){
        int[] arr = {2,3,5,7};

        int tar = 10;

        int[] dp = new int[tar+1];
        System.out.println(coinChangeCombination_DP(arr, tar, new int[tar+1]));

    }

}