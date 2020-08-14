import java.util.*;

import jdk.internal.org.jline.reader.Completer;
public class aug14{

    public static int height(int[] arr,int vidx){
        if(vidx >= arr.length) return -1;
        
        int lh = height(arr, 2 * vidx + 1);
        int rh = height(arr, 2 * vidx + 2);

        return Math.max(lh,rh) + 1;
    }


    public static class Heap{

        ArrayList<Integer> arr=new ArrayList<>();
        boolean isMax = true;

        public Heap(int[] data){
            constructHeap(data);
        }

        public void printIt(){
            System.out.println(arr);
        }

        public Heap(int[] data,boolean isMax){
            this.isMax = isMax;
            constructHeap(data);
        }

        public void constructHeap(int[] data){
            for(int ele: data) arr.add(ele);
            int n = this.arr.size(); 

            for(int i = n - 1; i >= 0 ;i--){  // O(nlogn) -> O(n)
                downheapify(i);
            }
        }

        public int compareTo(int a,int b){  // O(1)
            if(isMax){
                return arr.get(a) - arr.get(b);
            }else
               return arr.get(b) - arr.get(a);
        }


        private void upheapify(int ci){  //O(logn)
            int pi = (ci - 1) / 2;

            if(pi >=0 && compareTo(ci,pi) > 0) {
                swap(ci,pi);
                upheapify(pi);
            }
        }

        private void downheapify(int pi){    // O(logn)
            int maxidx = pi;
            int lci = 2 * pi + 1;
            int rci = 2 * pi + 2;

            if(lci < arr.size() && compareTo(lci, maxidx) > 0) 
                maxidx = lci;
            if(rci < arr.size() && compareTo(rci, maxidx) > 0) 
                maxidx = rci;

            if(maxidx != pi){
                swap(pi,maxidx);
                downheapify(maxidx);
            }
        }

        private void swap(int a ,int b){  //O(1)
            int val1 = arr.get(a);
            int val2 = arr.get(b);

            this.arr.set(a,val2);
            this.arr.set(b,val1);
        }


        public int size(){
            return this.arr.size();
        }

        public boolean isEmpty(){
            return this.arr.size() == 0;
        }

        public void push(int val){  //(logn)
            this.arr.add(val);
            int n =  size(); 
            upheapify(n-1);
        }

        public int top(){   // O(1)
            return arr.get(0);
        }

        public int pop(){  // O(logn)
            swap(0,arr.size()-1);
            
            int n=size();
            int rv = this.arr.remove(n-1);

            downheapify(0);

            return rv;
        }
    }

    public static void swap(int a, int b, int[] arr){

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void downheapify(int pi,int[] arr, int n){    // O(logn)
        int maxidx = pi;
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;

        if(lci < n+1){ 
            if(arr[maxidx]<arr[lci])
            maxidx = lci;
        }
        if(rci < n+1){ 
            if(arr[maxidx]<arr[rci])
            maxidx = rci;
        }

        if(maxidx != pi){
            swap(pi,maxidx,arr);
            downheapify(maxidx,arr,n);
        }
    }
    public static void heapSort(int[] arr){
        int n = arr.length-1;
        for(int i = arr.length-1;i>=0;i--){
            downheapify(0, arr,n);
            swap(0, n--, arr);
        }
    }
    public static void solve(){
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        //Heap pq=new Heap(arr,true);

        // while(pq.size()!=0){
        //     System.out.print(pq.pop() + " ");
        // }

        //System.out.println();

        //pq.printIt();

        for(int i = arr.length-1;i>=0;i--){
            downheapify(i, arr,arr.length-1);
        }

        for(int ele: arr){
            System.out.print(ele+" ");
        }

        System.out.println();

        heapSort(arr);

        for(int ele: arr){
            System.out.print(ele+" ");
        }

        // System.out.println();

    }
    
    //leetcode kth largest question number 215

    public static int KthLargest(int[] nums,int k){
      // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverse()); // min heap by default

      PriorityQueue<Integer> pq = new PriorityQueue<>();

      for(int ele: nums){

        pq.add(ele);
        if(pq.size()>k) pq.remove();
      }

      return pq.remove();
    }

 //leetcode 703
    class KthLargest {
    
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int K;
        public KthLargest(int k, int[] nums) {
          
          this.K = k;
          for(int ele: nums){
            pq.add(ele);
            if(pq.size()>k) pq.remove();
          }
            
            
        }
        
        public int add(int val) {
            pq.add(val);
            if(pq.size()>K)
            pq.remove();
            
            return pq.peek();
        }
    }
    
    //leetcode 378

    class Solution {
    
        public int kthSmallest(int[][] matrix, int k) {
          
           PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
               return a[0]-b[0];
           });
           
           for(int i= 0;i<matrix.length;i++){
               pq.add(new int[]{matrix[i][0],i,0});
           }
           
           for(int i = 0;i<k-1;i++){
               int[] temp = pq.remove();
               if(temp[2]+1<matrix[0].length)
               pq.add(new int[]{matrix[temp[1]][temp[2]+1],temp[1],temp[2]+1});
           }
           
           return pq.peek()[0];
           
       }
   }

   //leetcode 973

   public class pair_ implements Comparable<pair_>{
      int i = 0;
      int j = 0;

      public pair_(int i, int j){
          this.i = i;
          this.j = j;
      }

      @Override
      public int compareTo(pair_ o){
         int r1 = this.i*this.i + this.j*this.j;
         int r2 = o.i*o.i + o.j*o.j;

         return r2-r1; // other - this
      }
   }

   public int[][] kClosest(int[][] points, int k){
       PriorityQueue<pair_> pq = new PriorityQueue<>();

       for(int[]p: points){

        pq.add(new pair_(p[0],p[1]));
        if(pq.size()>k) pq.remove();
       }

       int[][] ans = new int[k][2];
       
       int i = 0;
       while(pq.size()!=0){
           pair_ p = pq.remove();
           ans[i][0] = p.i;
           ans[i][1] = p.j;
           i++;
       }

       return ans;
   }
    public static void main(String[] args){
        solve();
    }







}