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

void addEdge2(int u, int v, int w)
{
    graph[u].push_back(Edge(v, w));
}

int searchVrtx(int u, int v)
{
    int idx = -1;
    for (int i = 0; i < graph[u].size(); i++)
    {
        Edge e = graph[u][i];
        if (e.v == v)
        {
            idx = i;
            break;
        }
    }

    return idx;
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


//                                     KOSARAJU ALGO


void dfs(int src, vector<bool> &vis, vector<vector<Edge>> &graph, vector<int> &res)
{
    vis[src] = true;
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
            dfs(e.v, vis, graph, res);
    }

    res.push_back(src);
}

void KosaRajuAlgoFor_SCC()
{
    vector<bool> vis(N, false);
    vector<int> res;

    for (int i = 0; i < N; i++)
        if (!vis[i])
            dfs(i, vis, graph, res);

    vector<vector<Edge>> gp(N, vector<Edge>());
    for (int i = 0; i < N; i++)
        for (Edge e : graph[i])
            gp[e.v].push_back(Edge(i, e.w));

    for (int i = 0; i < N; i++)
        vis[i] = false;
    int count = 1;
    vector<int> vtx;

    for (int i = res.size() - 1; i >= 0; i--)
        if (!vis[res[i]])
        {
            dfs(res[i], vis, gp, vtx);
            cout << count++ << " -> ";
            while (vtx.size() != 0)
            {
                cout << vtx.back() << ", ";
                vtx.pop_back();
            }
            cout << endl;
        }
}

void BFS()
{

    vector<bool> vis(N, false);
    BFS_01(0, vis);
}

void constructDirectedGraph()
{
    N = 11;
    graph.resize(N, vector<Edge>());

    addEdge2(0, 5, 10);
    addEdge2(6, 0, 10);
    addEdge2(5, 6, 40);

    addEdge2(6, 8, 10);

    addEdge2(8, 9, 10);
    addEdge2(9, 10, 2);
    addEdge2(10, 8, 2);

    addEdge2(10, 7, 10);

    addEdge2(7, 3, 8);
    addEdge2(3, 1, 3);
    addEdge2(1, 2, 8);
    addEdge2(2, 7, 3);

    addEdge2(2, 4, 8);

    KosaRajuAlgoFor_SCC();
}

void constructGraph()
{
    N = 7;
    graph.resize(N, vector<Edge>());
    // for (int i = 0; i < N; i++)
    // {
    //     vector<Edge> g;
    //     graph.push_back(g);
    // }

    addEdge(0, 1, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 0, 10);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);

    // addEdge(0, 6, 10);

    display();
}


//////////////////////////////             UNION FIND

vector<int> par;
vector<int> size;


int findPar(int u){
    if(par[u] == u) return u;
     
     return par[u] = findPar(par[u]);

}

void merge(int p1, int p2){
    if(size[p1]<size[p2]){
        par[p1] = p2;
        size[p2] += size[p1];
    }else{
        par[p2] = p1;
        size[p1] += size[p2];
    }
}




void UnionFind(int n, vector<vector<int>> &edges)
{
    graph.resize(n, vector<Edge>());
    for (int i = 0; i < n; i++)
    {
        par.push_back(i);
        size.push_back(1);
    }

    for (vector<int> &e : edges)
    {
        int u = e[0];
        int v = e[1];
        int w = e[2];

        int p1 = findPar(u);
        int p2 = findPar(v);

        if (p1 != p2)
        {
            merge(p1, p2);
            addEdge(u, v, w);
        }
    }

    //display();
}

