#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Edge
{
public:
    int v, w;
    Edge(int v, int w)
    {
        this->v = v;
        this->w = w;
    }
};

int N = 9;
vector<vector<Edge>> graph(N, vector<Edge>());

void addEdge(int u, int v, int w)
{

    graph[u].push_back(Edge(v, w));
    graph[v].push_back(Edge(u, w));
}

void display()
{
    for (int i = 0; i < N; i++)
    {
        cout << i << " -> ";
        for (Edge e : graph[i])
        {
            cout << "(" << e.v << ", " << e.w << ") ";
        }
        cout << endl;
    }
}

bool hasPath(int src, int desti, vector<bool> &vis)
{
    if (src == desti)
        return true;

    vis[src] = mark;
    bool res = false;
    for (Edge e : graph[src])
        if (!vis[e.v])
            res = res || hasPath(e.v, desti, vis);

    return res;
}

void allPath(int src, int desti, vector<bool> &vis, string ans)
{
    if (src == desti)
    {
        cout << ans << desti << endl;
        return true;
    }

    vis[src] = mark;
    for (Edge e : graph[src])
        if (!vis[e.v])
            allPath(e.v, desti, vis, ans + to_string(src) + " ");

    vis[src] = false;
}

bool bfs(int src, int desti, vector<bool> &vis)
{
    queue<int> que;
    que.push(src);
    while (que.size() != 0)
    {
        int rvtx = que.front();
        que.pop();

        if (vis[rvtx])
        { //Cycle.
            cout << "cycle" << endl;
            continue;
        }

        if (rvtx == desti)
        {
            cout << true << endl;
            return true;
        }

        vis[rvtx] = true;
        for (Edge e : graph[src])
            if (!vis[e.v])
                que.push(e.v);
    }

    return false;
}

void construction()
{
    addEdge(0, 1, 10);
    addEdge(0, 3, 10);
    addEdge(1, 2, 10);
    addEdge(2, 3, 40);
    addEdge(3, 4, 2);
    addEdge(4, 5, 2);
    addEdge(4, 6, 8);
    addEdge(5, 6, 3);

    addEdge(7, 8, 3);
    addEdge(2, 7, 3);
    addEdge(2, 8, 3);

    display();
}

void solve()
{
    construction();
}

int main()
{
    solve();
    return 0;
}