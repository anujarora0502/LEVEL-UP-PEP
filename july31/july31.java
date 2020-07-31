import java.util.*;
public class july31{


    public static int LIS_rec(int[] arr, int ei, int[] dp){
        if(dp[ei]!=0) return dp[ei];
        
        
        int maxLen = 0;
        for(int i = 0;i<=ei;i++){
            int len = 1;
            if(arr[i]<arr[ei]){
                len = LIS_rec(arr, i, dp);
                maxLen = Math.max(maxLen, len+1);
            }
        }

        return dp[ei] = maxLen;
    }
   
    //left to right

    public static int LIS_LR(int[] arr, int[] dp){

        int max = 0;

        for(int i = 0;i<arr.length;i++){
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
                if(arr[j]<arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }

            max = Math.max(dp[i],max);
        }

        return max;
    }

    public static int LDS_RL(int[] arr, int[] dp){

        int max = 0;

        for(int i = arr.length-1;i>=0;i--){
            dp[i] = 1;
            for(int j = i+1;j<arr.length;j++){
                if(arr[j]<arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }

            max = Math.max(dp[i],max);
        }

        return max;
    }
    // min deletion to make an array in sorted order
    public static int minDeletion(int[] arr){
        int n = arr.length;
        int[] dp = new int[n];

        int max = 0;

        for(int i = 0;i < arr.length;i++){
            dp[i] = 1;
            for(int j=i-1;j>=0;j--){
                if(arr[j] <= arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }

            max = Math.max(max,dp[i]);
        }

        return n - max;
    }

    public static int maxEnvelope(int[][] arr){
        if(arr.length == 0) return 0;


        Arrays.sort(arr,(a,b)->{
             return a[0]-b[0]; // this - other
        });


        int n = arr.length;
        int[] dp = new int[n];
        int max = 0;

        for(int i = 0;i<n;i++){
            dp[i] = 1;
            for(int j = i-1;j>=0;j--){
                if(arr[j][0] < arr[i][0] && arr[j][1] < arr[i][1]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }

            max = Math.max(max,dp[i]);
        }
    }

    public static void main(String[] args){

       int[] arr = {0,1,2,3,4,6,-1,-2};

       int n = arr.length;
       int max = 0;

       for(int i = n-1;i>=0;i--){
           max = Math.max(max,LIS_rec(arr, i, new int[n]));
       }

       System.out.println(max);

    }
}