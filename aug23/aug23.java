import java.util.*;

public class aug23{
    

    // leetcode 1021 -- remove outermost paretheses

    public class Solution1021 {
        public String removeOuterParentheses(String S) {
            
            Stack<Character> st = new Stack<>();
            
            StringBuilder sb = new StringBuilder();
            
            for(int i = 0;i<S.length();i++){
                
                char ch = S.charAt(i);
                
                if(st.size() == 0 && ch == '('){
                    st.push(ch);
                    continue;
                }
                
                
                while(st.size()!= 0){
                    ch = S.charAt(i);
                    if(st.peek() == '(' && ch == ')'){
                        st.pop();
                        
                    }else{
                        st.push(ch);
                    }
                    
                    if(st.size() != 0){ 
                        
                        sb.append(ch);
                        
                    }
        
                    i++;
                }
                
                i--;
                
                
                
                
            }
            
            return sb.toString();
            
        }
    }

    public static void main(String[] args){



    }
}