#include<iostream>
#include<stack>
#include<vector>

using namespace std;

class Solution20leetcoce {
public:
    bool isValid(string s) {
        stack<int> st;
        
        for(int i = 0;i<s.length();i++){
           
           if(st.size() != 0){
            
            if(s[st.top()] == '{'&& s[i] == '}'){
                
                st.pop();
                continue;
            }else if(s[st.top()] == '('&& s[i] == ')'){
                st.pop();
                continue;
            }else if(s[st.top()] == '['&& s[i] == ']'){
                
                st.pop();
                continue;
                
            }
            
           }   
            st.push(i);
           
            
        }
        
        
        
        
        if(st.size()!=0){
        return false;
    }else return true;
    }
    
    
};

vector<int> ngor(vector<int>& arr){

 stack<int> st;
 st.push(-1);

 int n = arr.size();
 vector<int> ans(n,n);

 for(int i = 0;i<n;i++){
   while(st.top() != -1 && arr[st.top()]<arr[i]){
       ans[st.top()] = i;
       st.pop();
   }
    
    st.push(i);

 }

 return ans;

}


// LEETCODE 32 - LONGEST VALID PARENTHESES 
class Solution32 {
public:
    int longestValidParentheses(string s) {
        
        stack<int> st;
        for(int i = 0;i<s.length();i++){
           if(st.size()!=0){
               
               if(s[st.top()]== '(' && s[i] == ')'){
                   st.pop();
            
               }else{
                   st.push(i);
               }
               
           }else{
               st.push(i);
           }
        }
        
        int length = 0;
        int prev = -1;
        
        stack<int> temp;
        
        while(st.size()!=0){
            temp.push(st.top());
            st.pop();
        }
        
        st = temp;
        
        while(st.size()){
            
            int ele = st.top();
            st.pop();
            
            
            
            length = max(length,ele-prev-1);
            prev = ele;
            
            
        }
        
        if(s.length()-1-prev>length){
            length = s.length()-1-prev;
        }
        
        return length;
        
    }
};


//LEETCODE - 921   

class Solution921 {
public:
    int minAddToMakeValid(string s) {
        
        stack<char> st;
        for(int i = 0;i<s.length();i++){
           if(st.size()!=0){
               
               if(st.top() == '(' && s[i] == ')'){
                   st.pop();
            
               }else{
                   st.push(s[i]);
               }
               
           }else{
               st.push(s[i]);
           }
        }
    
        
        return st.size();
        
        
        
    }
};


//LEETCODE - 1249. Minimum Remove to Make Valid Parentheses

class Solution {
public:
    string minRemoveToMakeValid(string s) {
        
        stack<int> st;
        for(int i = 0;i<s.length();i++){
           
            if(st.size() == 0){
                if(s[i] == ')'){
                    s[i] = '#';
                }else if(s[i] == '('){
                    st.push(i);
                }
            }else{
                
                if(s[i] == '('){
                    st.push(i);
                }else if(s[i] == ')'){
                    if(s[st.top()] == '('){
                        st.pop();
                    }else{
                        s[i] = '#';
                    }
                }
                }
            
            
                
            }
        
        string ans = "";
        
        while(st.size()!=0){
            s[st.top()] = '#';
            st.pop();
        }
        
        for(int i = 0;i<s.length();i++){
            if(s[i]!='#') ans+= s[i];
        }
        
        return ans;
            
            
        }
    
};

int main(){
   vector<int> arr{10,1,2,11,3,4,5,9,7,8};

   vector<int> ans = ngor(arr);

   for(int ele: ans){
       cout<<ele<<" ";
   }


   return 0;
}