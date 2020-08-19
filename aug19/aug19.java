import java.util.*;

public class aug19{


//LEETCODE 128 ------------------->  Longest Consecutive Sequence

    class Solution128 {
        public int longestConsecutive(int[] nums) {
            
            if(nums.length == 0) return 0;
            
            HashSet<Integer> set = new HashSet<>();
            for(int ele: nums) set.add(ele);
            
            int ans = 1;
            
            for(int ele: nums){
                if(!set.contains(ele)) continue;
                int prev = ele-1;
                int next = ele+1;
                while(set.contains(prev)){
                    set.remove(prev--);
                        }
                
                while(set.contains(next)){
                    set.remove(next++);
                }
                
                ans = Math.max(next-prev-1,ans);
            }
            
            
            return ans;
            
        }
    }

    //LEETCODE 49 GROUP ANAGRAMS

    class Solution49 {
        public List<List<String>> groupAnagrams(String[] strs) {
           char[] primeMap = new char[102];
            
            int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
            
            for(int i=0;i<arr.length;i++){
                primeMap[arr[i]] = (char)('a'+i);
            }
            
            HashMap<Integer,ArrayList<String>> map = new HashMap<>();
            
            for(String s: strs){
                int p = product(s,arr);
                if(map.containsKey(p)){
                    map.get(p).add(s);
                }else{
                    map.put(p,new ArrayList<>());
                    map.get(p).add(s);
                }
            }
            
            List<List<String>> ans = new ArrayList<>();
            
            for(int ele: map.keySet()){
                ans.add(map.get(ele));
            }
            
            
            return ans;
            
    
        }
        
        public int product(String s, int[] arr){
            int p = 1;
            for(int i=0;i<s.length();i++){
                p = p*arr[s.charAt(i)-'a'];
            }
            
            return p;
        }
    }

    
    // LEETCODE 295  ---------------------> Find Median from Data Stream
    class MedianFinder {
    
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->{
            return b-a;
        });
    
        /** initialize your data structure here. */
        public MedianFinder() {
            
        }
        
        public void addNum(int num) {
          if(maxHeap.size()==0 || num <= maxHeap.peek())
              maxHeap.add(num);
          else minHeap.add(num);
            
            
            if(maxHeap.size()>minHeap.size()+1){
                minHeap.add(maxHeap.peek());
                maxHeap.remove();
            }else if(maxHeap.size()<minHeap.size()){
                maxHeap.add(minHeap.peek());
                minHeap.remove();
            }
              
        }
        
        public double findMedian() {
            
            if(maxHeap.size() == minHeap.size() && maxHeap.size()!=0) return (maxHeap.peek()+minHeap.peek())/2.0;
            else if(maxHeap.size()!= minHeap.size()) return maxHeap.peek();
            
            return -1;
        }
    }
    
    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */




    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s/0

    public static int countSub(int[] arr){
	    if(arr.length == 0) return 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        for(int ele: arr){
            int val = ele;
            if(val == 0) val = -1;
            
            sum+= val;
            if(map.containsKey(sum)){
                map.put(sum,map.get(sum)+1);
            }else{
                map.put(sum,1);
            }
        }
        
        int count = 0;
        for(int p: map.keySet()){
            count+= (map.get(p)*(map.get(p)-1))/2;
        }
        
        return count;
	}

    // https://practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1

    int maxLen(int[] arr, int N)
    {
        if(arr.length == 0) return 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        int len = 0;
        for(int i = 0;i<N;i++){
            int val = arr[i];
            if(val == 0) val = -1;
            
            sum+= val;
            if(map.containsKey(sum)){
                
                len = Math.max(len,i-map.get(sum));
            }else{
                map.put(sum,i);
            }
            
            //System.out.println(len+"nkjkj");
        }
        
        return len;
    }

    public static void main(String[] args){
           
         

    }

}