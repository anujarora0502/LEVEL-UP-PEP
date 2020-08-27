import java.util.*;

public class aug27{
  
// LEETCODE 735  -- -- ASTEROID COLLISION
    class Solution735 {
        public int[] asteroidCollision(int[] asteroids) {
            Stack<Integer> st = new Stack<>();
            
            for(int ele: asteroids){
                
                if(ele > 0){
                    st.push(ele);
                    continue;
                }
                
                while(st.size() !=0 && st.peek()>0 && st.peek()<-1*ele){
                    st.pop();
                }
                
                if(st.size() != 0 && st.peek() == -1*ele)
                    st.pop();
                else if(st.size() == 0|| st.peek() < 0){
                    st.push(ele);
                }else{
                    
                }
                
            }
            
            int[] arr = new int[st.size()];
            int i = st.size()-1;
            while(st.size()!=0){
                arr[i] = st.peek();
                st.pop();
                i--;
            }
            
            return arr;
        }
    }


// LEETCODE 1081 -- smallest subequence and same for leetcode 316

    class Solution1081 {
        public String smallestSubsequence(String text) {
            HashMap<Character,Integer> map = new HashMap<>();
            boolean[] mark = new boolean[26];
            
            Stack<Character> st = new Stack<>();
            
            for(int i = 0;i<text.length();i++){
                char c = text.charAt(i);
                if(map.containsKey(c)){
                    
                    map.put(c,map.get(c)+1);
                    
                }else{
                    
                    map.put(c,1);
                    
                }
                
            }
            
            
            for(int i = 0;i<text.length();i++){
                
                char c = text.charAt(i);
                
                if( i == 0){
                    st.push(c);
                    map.put(c,map.get(c)-1);
                    mark[c-'a'] = true;
                    continue;
                }
                
                if(mark[c-'a'] == false){
                    
                    while(st.size()!=0 && st.peek()>c){
                        if(map.get(st.peek())>0){
                            char temp = st.pop();
                            mark[temp - 'a'] = false;
                        }else{
                            break;
                        }
                    }
                        st.push(c);
                        map.put(c,map.get(c)-1);
                        mark[c-'a'] = true;
                    
                    
                }else{
                    
                    map.put(c,map.get(c)-1);
                    
                }
                
               
                
            }
            
            char[] ans = new char[st.size()];
            int i = st.size()-1;
            while(st.size()!=0){
                ans[i] = st.peek();
                st.pop();
                i--;
            }
            
            
            
            return String.valueOf(ans);
            
        }
    }


    // LEETCODE 402 -- REMOVE K DIGITS

    class Solution402 {
        public String removeKdigits(String num, int k) {
            Stack<Integer> st = new Stack<>();
            
            for(int i = 0;i<num.length();i++){
                int digit = num.charAt(i) - '0';
                
                while(st.size()!=0 && k>0 && st.peek()>digit){
                    st.pop();
                    k--;
                }
                st.push(digit);
            }
            
            while(st.size()!=0 && k-->0) st.pop();
            
            StringBuilder sb = new StringBuilder();
            
            while(st.size() !=0){
                
                sb.append(st.pop());
            }
            
            
            
            while(sb.length() !=0 && sb.charAt(sb.length()-1) == '0'){
                sb.deleteCharAt(sb.length()-1);
            }
            
            if(sb.length() == 0){
                return "0";
            }
            
            
            
            
        
            return sb.reverse().toString();
        }
        
    }
    

    // LRU CACHE

    class LRUCache
    {
    
        class Node
        {
            int key = 0;
            int value = 0;
    
            Node next = null;
            Node prev = null;
    
            Node(int key, int value)
            {
                this.key = key;
                this.value = value;
            }
        }
    
        Node head = null;
        Node tail = null;
        int size = 0;
        int maxSize = 0;
    
        void addLast(Node node)
        {
            if (this.size == 0)
            {
                this.head = node;
                this.tail = node;
            }
            else
            {
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }
            this.size++;
        }
    
        void removeNode(Node node)
        {
    
            if (this.size == 1)
            {
                this.head = node;
                this.tail = node;
            }
            else if (node.prev == null)
            {
                this.head = node.next;
    
                this.head.prev = null;
                node.next = null;
            }
            else if (node.next == null)
            {
                this.tail = node.prev;
    
                this.tail.next = null;
                node.prev = null;
            }
            else
            {
                Node prev = node.prev;
                Node next = node.next;
    
                prev.next = next;
                next.prev = prev;
    
                node.next = null;
                node.prev = null;
            }
            this.size--;
        }
    
        HashMap<Integer, Node> map=new HashMap<>(); // key, node
        LRUCache(int capacity)
        {
            this.maxSize = capacity;
        }
    
        int get(int key)
        {
            if (!map.containsKey(key))
                return -1;
    
            Node node = map.get(key);
            int rv = node.value;
    
            removeNode(node);
            addLast(node);
    
            return rv;
        }
    
        void put(int key, int value)
        {
            if (!map.containsKey(key)){
                Node node = new Node(key, value);
                map.put(key , node);
                addLast(node);
                if(this.size > this.maxSize){
                   node = this.head;
                    
                   map.remove(node.key);
                   removeNode(node);  
                }
            }
            else
            {
                int val = get(key);
                if (val != value)
                    map.get(key).value = value;
            }
        }
    }

    public static void main(String[] args){



    }



}