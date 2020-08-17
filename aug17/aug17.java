import java.lang.reflect.Array;
import java.util.*;

public class aug17{
     
    
    public static void main(String[] args){
     
        solve();

    }

    public static void solve(){

        freqMap("fhdhkdshksdhfkshfkds121678999");
        positionMap("fhdhkdshksdhfkshfkds121678999");
    }

    public static void freqMap(String str){

        HashMap<Character,Integer> map = new HashMap<>();

        for(int i = 0;i<str.length();i++){

           map.put(str.charAt(i),map.getOrDefault(str.charAt(i), 0)+1);
        }

        System.out.println(map.keySet());

        System.out.println(map);
    }

    public static void positionMap(String str){
        HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
               map.get(c).add(i);
            }else{
               ArrayList<Integer> al = new ArrayList<>();
               al.add(i); 
               map.put(c,al);
            }
           
         }

         for(Character c: map.keySet()){
             System.out.print(c+"  ");
             System.out.println(map.get(c));
         }
        
    }

   //leetcode 349
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            HashMap<Integer,Integer> map1 = new HashMap<>();
            HashMap<Integer,Integer> map2 = new HashMap<>();
            
            for(int i = 0;i<nums1.length;i++){
                if(map1.containsKey(nums1[i])){
                    map1.put(nums1[i],map1.get(nums1[i])+1);
                }else{
                    map1.put(nums1[i],1);
                }
            }
            
            for(int j = 0;j<nums2.length;j++){
                if(map2.containsKey(nums2[j])){
                    map2.put(nums2[j],map2.get(nums2[j])+1);
                }else{
                    map2.put(nums2[j],1);
                }
            }
            
            ArrayList<Integer> al = new ArrayList<>();        
            for(int e: map1.keySet()){
                if(map2.containsKey(e)){
                  if(map1.get(e)<map2.get(e))
                      for(int i = 0;i<map1.get(e);i++) al.add(e);
                          
                          else for(int i = 0;i<map2.get(e);i++) al.add(e);
                }
            }
            
            int[] ans = new int[al.size()];
            
            for(int i = 0;i<al.size();i++){
                ans[i] = al.get(i);
            }
            
            return ans;
        }
    }


    // Leetcode 350

    class Solution350 {
        public int[] intersect(int[] nums1, int[] nums2) {
            
            HashMap<Integer,Integer> map1 = new HashMap<>();
            HashMap<Integer,Integer> map2 = new HashMap<>();
            
            for(int i = 0;i<nums1.length;i++){
                if(map1.containsKey(nums1[i])){
                    map1.put(nums1[i],map1.get(nums1[i])+1);
                }else{
                    map1.put(nums1[i],1);
                }
            }
            
            for(int j = 0;j<nums2.length;j++){
                if(map2.containsKey(nums2[j])){
                    map2.put(nums2[j],map2.get(nums2[j])+1);
                }else{
                    map2.put(nums2[j],1);
                }
            }
            
            ArrayList<Integer> al = new ArrayList<>();        
            for(int e: map1.keySet()){
                if(map2.containsKey(e)){
                  if(map1.get(e)<map2.get(e))
                      for(int i = 0;i<map1.get(e);i++) al.add(e);
                          
                          else for(int i = 0;i<map2.get(e);i++) al.add(e);
                }
            }
            
            int[] ans = new int[al.size()];
            
            for(int i = 0;i<al.size();i++){
                ans[i] = al.get(i);
            }
            
            return ans;
        }
    }

    // Leetcode 219

    class Solution219 {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            HashMap<Integer,Integer> map = new HashMap<>();
            
            for(int i=0;i<nums.length;i++){
                
                if(map.containsKey(nums[i])){
                    
                    if(i - map.get(nums[i])<=k) return true;
                    
                    map.put(nums[i],i);
                    
                }else{
                    
                    map.put(nums[i],i);
                }
                
            }
            
            return false;
        }
    }

    // Leetcode 451

    class Solution451 {
        public String frequencySort(String s) {
            HashMap<Character,Integer> map = new HashMap<>();
            
            for(int i=0;i<s.length();i++){
              char c = s.charAt(i);  
              if(map.containsKey(c)){
                  
                  map.put(c,map.get(c)+1);
                  
              }else{
                  
                  map.put(c,1);
                  
              }
                
            }
            
            PriorityQueue<Character> pq = new PriorityQueue<>((a,b)->{
                return map.get(b) - map.get(a);
            });
            
            for(Character c: map.keySet()){
                pq.add(c);
            }
            
            //System.out.println(map);
            
            StringBuilder ansS = new StringBuilder();
            
            while(pq.size()!=0){
                //System.out.println(pq);
                char rc = pq.remove();
                
                int freq = map.get(rc);
                
                for(int i= 0;i<freq;i++){
                    ansS.append(rc);
                }
            }
            
            return ansS.toString();
        }
    }

    //Leetcode 380

    class RandomizedSet {
        HashSet<Integer> set;
        /** Initialize your data structure here. */
        public RandomizedSet() {
           this.set = new HashSet<>();
        
        }
        
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(set.contains(val)){
                return false;
            }else{
               set.add(val);
                return true;
            }
        }
        
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(set.contains(val)){
                set.remove(val);
                return true;
            }else{
                return false;
            }
        }
        
        /** Get a random element from the set. */
        public int getRandom() {
            int sz = set.size();
            int randIndex = (int)Math.floor(Math.random()*sz);
            Iterator value = set.iterator(); 
            int ans = 0;
            for(int i=0;i<=randIndex;i++){
                ans =(int)value.next();
            }
            
            return ans;
        }
    }
    
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
}