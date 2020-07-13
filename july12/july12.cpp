#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#include <algorithm>

using namespace std;

class Edge
{

public:
    int v;
    int w;

    Edge(int v, int w)
    {

        this->v = v;
        this->w = w;
    }
};

vector<vector<Edge>> graph;
int N;

void addEdge(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
    //graph[v].push_back(Edge(u, w));
}

void constructGraph()
{
    N = 7;
    graph.resize(N, vector<Edge>());

    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);
    //addEdge(6,0,10);
}

//void allPath(int u, int v, )
void display()
{

    for (int i = 0; i < N; i++)
    {
        cout << i << " -> ";
        for (Edge e : graph[i])
        {

            cout << "(" << e.v << "," << e.w << ") ";
        }

        cout << endl;
    }
}

void removeEdge(int u, int v)
{
    for (int i = 0; i < graph[u].size(); i++)
    {
        Edge e = graph[u][i];
        if (e.v == v)
        {
            graph[u].erase(graph[u].begin() + i);
            break;
        }
    }

    for (int i = 0; i < graph[v].size(); i++)
    {
        Edge e = graph[v][i];
        if (e.v == u)
        {
            graph[v].erase(graph[v].begin() + i);
            break;
        }
    }
}

void removeVertex(int u)
{
    for (Edge e : graph[u])
    {
        removeEdge(u, e.v);
    }
}

// Topological sort





void topoDFS(int src, vector<bool> &vis, stack<int> &st)
{
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            topoDFS(e.v, vis, st);
    }

    st.push(src);
}

void topologicalSort()
{
    vector<bool> vis(N, false);
    stack<int> st;

    for (int i = 0; i < N; i++)
    {
        if (!vis[i])
            topoDFS(i, vis, st);
    }
}

void kahnsAlgo()
{

    vector<int> indegre(N, 0);
    for (int i = 0; i < N; i++)
    {
        for (Edge e : graph[i])
            indegre[e.v]++;
    }

    queue<int> que;
    vector<int> ans;

    for (int i = 0; i < N; i++)
    {
        if (indegre[i] == 0)
            que.push(i);
    }

    while (que.size() != 0)
    {
        int rvtx = que.front();
        que.pop();

        ans.push_back(rvtx);
        for (Edge e : graph[rvtx])
        {
            if (--indegre[e.v] == 0)
                que.push(e.v);
        }
    }

    if (ans.size() != N)
        cout << "There is a Cycle:" << endl;
    else
    {
        for (int ele : ans)
            cout << ele << " ";
    }
}

int main(){


  return 0;
}

// ///////////////////////////////////////////Leetcode 207

class Solution {
public:

    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        return kahnsAlgo(numCourses,prerequisites);
    }
    
    
 bool kahnsAlgo(int N,vector<vector<int>>& graph) 
  {

    vector<int> indegre(N, 0);
    for (int i = 0; i < graph.size(); i++)
    {
       indegre[graph[i][1]]++;   
    }

    queue<int> que;
    vector<int> ans;

    for (int i = 0; i < N; i++)
    {
        if (indegre[i] == 0)
            que.push(i);
    }

    while (que.size() != 0)
    {
        int rvtx = que.front();
        que.pop();

        ans.push_back(rvtx);
        for (int i = 0;i<graph.size();i++)
        {
            if (graph[i][0] ==rvtx && --indegre[graph[i][1]] == 0)
                que.push(graph[i][1]);
        }
    }

    if (ans.size() != N)
        return false;
    else
    {
        return true;
    }
}
    
    
    
};


////////////////////////////////////////// Leetcode 210

class Solution {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
      
        vector<int> ans;
        kahnsAlgo(numCourses,prerequisites,ans);
        
        if(ans.size()!= numCourses){
            
            vector<int> emptyarr;
            return emptyarr;
            
        }else{
            reverse(ans.begin(),ans.end());
            return ans;
        }
        
    }
    
    void kahnsAlgo(int N,vector<vector<int>>& graph,vector<int>& ans) 
  {

    vector<int> indegre(N, 0);
    for (int i = 0; i < graph.size(); i++)
    {
       indegre[graph[i][1]]++;   
    }

    queue<int> que;

    for (int i = 0; i < N; i++)
    {
        if (indegre[i] == 0)
            que.push(i);
    }

    while (que.size() != 0)
    {
        int rvtx = que.front();
        que.pop();

        ans.push_back(rvtx);
        for (int i = 0;i<graph.size();i++)
        {
            if (graph[i][0] ==rvtx && --indegre[graph[i][1]] == 0)
                que.push(graph[i][1]);
        }
    }

}
  
};

//by dfs


bool topoCycle(int src, vector<bool>& vis, vector<int> myPath){
    vis[s]
}


vector<int> findorder(int numCourses,vector<vector<int>> &prerequisite){
     
     int N = numCourses;
     vector<vector<int>> graph(N,vector<int>());

     for(vector<int>& ar: prerequisite)
       graph[ar[0]]
}