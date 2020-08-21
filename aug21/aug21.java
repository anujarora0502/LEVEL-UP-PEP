import java.util.*;

public class aug21{
    

    public static class Stack{

        private int[] st;
        private int tos = -1;
        private int size = 0;

        public Stack(){
           assignSize(10);
        }

        public Stack(int n){
            this.assignSize(n);
        }

        private void assignSize(int n){

          st = new int[n];

        }

        public boolean isEmpty(){
            return this.size == 0;
        }

        public int size(){
            return this.size;
        }

        public void push(int val) throws Exception{
            if(this.size == this.st.length){
                throw new Exception("StackIsFull");
            }

            this.st[++this.tos] = val;
            this.size++;
        }

        public int top() throws Exception{
          
            if(this.size == 0){
                throw new NullPointerException("NullPointerException: "+-1);
            }

            return st[this.tos];


        }

        public int pop() throws Exception{

           
            if(this.size == 0){
                throw new NullPointerException("NullPointerException: "+-1);
            }

            int rv = this.st[this.tos];
            this.st[this.tos--] = 0;
            return rv;
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int i = 0;i<this.size;i++){
                sb.append(this.st[i]);
                if(i!=this.size()-1){
                    sb.append(",");
                }
            }

            sb.append("]");

            return sb.toString();
                }

    }

    public static class DynamicStack extends Stack{
        
        DynamicStack(){
            super();
        }

        DynamicStack(int n){
            super(n);
        }

        DynamicStack(int[] arr){
            super(arr.length*2);
            for(int ele: arr) super.push(ele);
        }

        
        
    }

    public static void main(String[] args) throws Exception{
        
          DynamicStack s = new DynamicStack();

          s.push(10);
          s.push(20);
          s.push(30);
          s.push(40);
          s.push(10);
          s.push(20);
          s.push(30);
          s.push(40);
          s.push(10);
          s.push(20);
          s.push(30);
          s.push(40);
          System.out.println(s.size());
          System.out.println(s);
          
        //   s.pop();
        //   s.pop();
        //   s.pop();
        //   s.pop();
        //   try{
        //   s.pop();
        //   }catch(Exception e){
        //       System.out.println(e);
        //   }

        //   System.out.println(s);

    }

    
}