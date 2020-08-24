#include<iostream>
#include<vector>
#include<stack>

using namespace std;

vector<int> nsor(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, n);

    for (int i = 0; i < n; i++)
    {
        while (st.top() != -1 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

vector<int> nsol(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = n - 1; i >= 0; i--)
    {
        while (st.top() != -1 && arr[st.top()] > arr[i])
        {
            ans[st.top()] = i;
            st.pop();
        }
        st.push(i);
    }

    return ans;
}

//Leetcode 503
vector<int> nextGreaterElements(vector<int> &arr)
{
    stack<int> st;
    st.push(-1);

    int n = arr.size();
    vector<int> ans(n, -1);

    for (int i = 0; i < 2 * n; i++)
    {
        while (st.top() != -1 && arr[st.top()] < arr[i % n])
        {
            ans[st.top()] = arr[i % n];
            st.pop();
        }
        if (i < n)
            st.push(i);
    }

    return ans;
}

//Leetcode 921
int minAddToMakeValid(string S)
{
    stack<int> st;

    int n = S.length();
    for (int i = 0; i < n; i++)
    {
        if (st.size() != 0 && S[st.top()] == '(' && S[i] == ')')
            st.pop();
        else
            st.push(i);
    }

    return st.size();
}

int minAddToMakeValid_02(string s)
{
    int openingBracketReq = 0;
    int closingBracketReq = 0;

    int n = s.length();

    for (int i = 0; i < n; i++)
    {
        if (s[i] == '(')
            closingBracketReq++;
        else if (closingBracketReq > 0)
            closingBracketReq--;
        else
            openingBracketReq++;
    }

    return openingBracketReq + closingBracketReq;
}

//Leetcode 32
int longestValidParentheses(string s)
{
    stack<int> st;
    st.push(-1);
    int n = s.length();
    int len = 0;
    for (int i = 0; i < n; i++)
    {
        if (st.top() != -1 && s[st.top()] == '(' && s[i] == ')')
        {
            st.pop();
            len = max(len, i - st.top());
        }
        else
            st.push(i);
    }

    return len;
}

//Leetcode 1249
string minRemoveToMakeValid(string s)
{
    stack<int> st;

    int n = s.length();
    for (int i = 0; i < n; i++)
    {
        if (s[i] == ')')
        {
            if (st.size() != 0)
                st.pop();
            else
                s[i] = '#';
        }
        else if (s[i] == '(')
            st.push(i);
    }

    while (st.size() != 0)
    {
        s[st.top()] = '#';
        st.pop();
    }

    string ans = "";
    for (int i = 0; i < n; i++)
    {
        if (s[i] != '#')
            ans += s[i];
    }

    return ans;
}

//Leetcode 84

int largestRectangleArea_01(vector<int> &heights)
{
    vector<int> sl = nsol(heights);
    vector<int> sr = nsor(heights);

    int area = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        int w = sr[i] - sl[i] - 1;
        int h = heights[i];

        area = max(area, w * h);
    }

    return area;
}

int largestRectangleArea_02(vector<int> &heights)
{
    int n = heights.size();
    if (n == 0)
        return 0;

    stack<int> st;
    st.push(-1);
    int area = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        while (st.top() != -1 && heights[i] <= heights[st.top()])
        {
            int h = heights[st.top()];
            st.pop();
            int w = i - st.top() - 1;

            area = max(area, h * w);
        }
        st.push(i);
    }

    while (st.top() != -1)
    {
        int h = heights[st.top()];
        st.pop();
        int w = n - st.top() - 1;

        area = max(area, h * w);
    }
    return area;
}

int maximalRectangle(vector<vector<char>> &matrix)
{
    if (matrix.size() == 0 || matrix[0].size() == 0)
        return 0;
    int n = matrix.size();
    int m = matrix[0].size();

    vector<int> heights(m, 0);
    int area = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
        }

        area = max(area, largestRectangleArea_02(heights));
    }

    return area;
}

int trap_01(vector<int> &height)
{
    int water = 0;
    int n = height.size();
    vector<int> left(n,0);
    vector<int> right(n,0);
    
    int prev = 0;
    for(int i = 0; i < n; i++){
       left[i] = max(prev,height[i]);
       prev = left[i];
    } 

    prev = 0;
    for(int i =  n - 1;i>=0; i--){
       right[i] = max(prev,height[i]);
       prev = right[i];
    } 

    for(int i=0;i<n;i++){
        water += min(left[i],right[i])-height[i];
    }

    return water;
}

int trap_02(vector<int> &heights)
{
    int n = heights.size();
    if (n == 0)
        return 0;

    stack<int> st;
    int water = 0;
    for (int i = 0; i < heights.size(); i++)
    {
        while (st.size()!=0 && heights[st.top()] < heights[i])
        {
            int h = heights[st.top()];
            st.pop();
            if(st.size()==0) break;
            int w = i - st.top() - 1;

            water += (min(heights[st.top()],heights[i]) - h) * w;
        }
        st.push(i);
    }

    return water;
}

int trap_03(vector<int> &heights)
{
    int n = heights.size();
    if (n == 0)
        return 0;
    int li = 0, ri = n - 1, lmax = 0, rmax = 0; 
    int water = 0;
    while (li<=ri)
    {
        lmax = max(lmax, heights[li]);
        rmax = max(rmax, heights[ri]);

        // water += lmax <= rmax ? lmax - heights[li++] : rmax - heights[ri--];
        if(lmax <= rmax)
          water += lmax - heights[li++];
        else
          water += rmax - heights[ri--];
    }

    return water;
}

int main(){


    return 0;
}