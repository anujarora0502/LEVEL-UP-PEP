#include<iostream>
#include<vector>

using namespace std;


class Edge {
   
   public: 
     int v;
     int w;

     Edge(int v, int w){
       
       this->v = v;
       this->w = w;

     }

};

vector<vector<Edge>> graph;
int N;

void addEdge(int u , int v, int w){
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));
}


void constructGraph(){
    N = 7;
    graph.resize(N,vector<Edge>());

    addEdge(0,1,10);
    addEdge(1,2,10);
    addEdge(2,3,40);
    addEdge(3,0,10);
    addEdge(3,4,2);
    addEdge(4,5,2);
    addEdge(4,6,8);
    addEdge(5,6,3);
    //addEdge(6,0,10);


}

//void allPath(int u, int v, )
void display(){

  for(int i = 0;i<N;i++){
      cout<<i<<" -> ";
      for(Edge e: graph[i]){

          cout<<"("<<e.v<<","<<e.w<<") ";
      }

      cout<<endl;
  }


}

void removeEdge(int u, int v){
    for(int i = 0 ;i<graph[u].size();i++){
        Edge e = graph[u][i];
        if(e.v == v){
            graph[u].erase(graph[u].begin()+i);
            break;
        }
    }

    for(int i = 0 ;i<graph[v].size();i++){
        Edge e = graph[v][i];
        if(e.v == u){
            graph[v].erase(graph[v].begin()+i);
            break;
        }
    }
}

void removeVertex(int u){
    for(Edge e: graph[u]){
        removeEdge(u,e.v);
    }

}

bool hasPath(int u, int v, vector<bool>& visited){
    if(u == v){
        return true;
    }
    visited[u]= true;
    for(Edge e: graph[u]){
        bool res = false;
        if(!visited[e.v])
        res = hasPath(e.v,v,visited);
        if(res){
            return true;
        }
    }

    return false;
}

int allPath(int u, int v,int weight, string path, vector<bool>& visited2){
    
    if(u == v){
        path+=to_string(u);
        cout<<path<<" @ "<<weight<<endl;
        return 1;
    }

    visited2[u] = true;
     int count = 0;
     for(Edge e: graph[u]){
         if(!visited2[e.v]){
            count+= allPath(e.v,v,weight+e.w,path+to_string(u)+" ",visited2);
         }
     }
     
    visited2[u] = false;
    return count;

}

class pathPair
{
public:
    int len = 0;
    string path = "";

    pathPair(int a, string b)
    {
        len = a;
        path = b;
    }
};

pathPair smallestPath(int src, int dest, vector<bool> &vis)
{
    if (src == dest)
    {
        return pathPair(0, to_string(src));
    }

    vis[src] = true;
    pathPair myAns(1e8, "");
    for (Edge e : graph[src])
    {
        if (!vis[e.v])
        {
            pathPair recAns = smallestPath(e.v, dest, vis);
            if (recAns.len + 1 < myAns.len)
            {
                myAns.len = recAns.len + 1;
                myAns.path = to_string(src) + " " + recAns.path;
            }
        }
    }

    vis[src] = false;
    return myAns;
}

int kthSmal = 1e8;
void KthSmallest_(int src, int dest, int weight, int floor, vector<bool> &vis)
{
    if (src == dest)
    {
        if (weight > floor)
            kthSmal = min(kthSmal, weight);
        return;
    }

    vis[src] = true;
    for (Edge e : graph[src])
        if (!vis[e.v])
            KthSmallest_(e.v, dest, weight + e.w, floor, vis);

    vis[src] = false;
}

void KthSmallest(int src, int dest, int k)
{
    int floor = -1e8;
    vector<bool> vis(N, false);
    while (k-- > 0)
    {
        kthSmal = 1e8;
        KthSmallest_(src, dest, 0, floor, vis);
        floor = kthSmal;
    }
}

int KthSmallest02_(int src, int dest, int floor, vector<bool> &vis)
{
    return 0;
}

void KthSmallest02(int src, int dest, int k)
{
    int floor = -1e8;
    vector<bool> vis(N, false);
    int ans = 1e8;
    while (k-- > 0)
    {
        ans = KthSmallest02_(src, dest, floor, vis);
        floor = ans;
    }
    cout << ans << endl;
}

int hamiltonian(int src, int osrc, int pathLength , vector<bool> &vis,string path){

   if(pathLength == N-1){

       int idx = -1;

       for(Edge e: graph[src]){
           if(e.v == osrc){
               idx = osrc;
           }
       }
       path+= to_string(src);
       if(idx!= -1){
           cout<<"Hamiltonian cycle :"<<path<<endl;
       }else{
           cout<<"Hamiltonian path : "<<path<<endl;
       }

       return 1;

   }

   
    vis[src] = true;
    int ans = 0;
    for(Edge e: graph[src]){

        if(!vis[e.v]){
            ans+= hamiltonian(e.v,osrc,pathLength+1,vis,path+to_string(src)+" ");
        }
    }
    vis[src]= false;

    return ans;
}

void GCC_(int src, vector<bool> &vis){
    vis[src] = true;
    for(Edge e: graph[src]){

        if(!vis[e.v]){
            GCC_(e.v,vis);
        }
    }
}

int GCC(){

  vector<bool> vis(N,false);

  int count = 0;

  for(int i = 0;i<N;i++){

      if(!vis[i]){
          GCC_(i,vis);
          count++;
      }
  }

  return count;
    
}

int main(){

   constructGraph();
   display();
//    vector<bool> visited2(N,false);
//    cout<<allPath(0,6,0,"",visited2)<<endl;

    vector<bool> visited(N,false);
    removeEdge(3,4);
    display();
//    cout<<hasPath(1,3,visited)<<endl;  
//    display();
//    cout<<endl;
//    removeEdge(1,3);
//    display();
//    cout<<endl;
//    removeVertex(3);
//    display();
//    cout<<endl;

cout<<hamiltonian(0,0,0,visited,"")<<endl;

cout<<GCC();

    return 0;
}


//Leetcode 200 

class Solution {
public:
    void solve(vector<vector<char>>& board) {
        if(board.size() == 0){
            return;
        }
        for(int i = 0;i<board[0].size();i++){
            if(board[0][i] == 'O'){
                travelIsland(board,0,i);
            }
            
            if(board[board.size()-1][i]=='O'){
                travelIsland(board,board.size()-1,i);
            }
        }
        
        for(int i = 0;i<board.size();i++){
            if(board[i][0] == 'O'){
                travelIsland(board,i,0);
            }
            
            if(board[i][board[0].size()-1]=='O'){
                travelIsland(board,i,board[0].size()-1);
            }
        }
        
        for(int i = 0;i<board.size();i++){
            for(int j = 0;j<board[0].size();j++){
                if(board[i][j] == 'T'){
                    board[i][j]='O';
                }else{
                    board[i][j]='X';
                }
            }
        }
    }
    
    int dir[4][2] = {{0,1},{1,0},{0,-1},{-1,0}};
    
    void travelIsland(vector<vector<char>>& grid, int x, int y){
        grid[x][y] = 'T';
        
        
        for(int i = 0;i<4;i++){
            
            int nextrow = x+dir[i][0];
            int nextcol = y+dir[i][1];
            
      if(nextrow>=0&&nextcol>=0&&nextrow<grid.size()&&nextcol<grid[0].size()&&grid[nextrow][nextcol]=='O'){
                travelIsland(grid,nextrow,nextcol);
            }
        }
        
    }
};


// LeetCode 695

class Solution {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int ans = 0;
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid[0].size();j++){
                if(grid[i][j]==1){
                    int area = 1;
                    travelIsland(grid,i,j,area);
                    ans = max(ans,area);
                }
            }
        }
        
        return ans;
    }
   int dir[4][2] = {{0,1},{1,0},{0,-1},{-1,0}};

    
    void travelIsland(vector<vector<int>>& grid, int x, int y, int& area){
        grid[x][y] = 0;
        
        for(int i = 0;i<4;i++){
            
            int nextrow = x+dir[i][0];
            int nextcol = y+dir[i][1];
            
      if(nextrow>=0&&nextcol>=0&&nextrow<grid.size()&&nextcol<grid[0].size()&&grid[nextrow] [nextcol]==1){  area++;
                travelIsland(grid,nextrow,nextcol,area);
            }
        }
        
    }
};

// Leetcode 463

class Solution {
public:
    int islandPerimeter(vector<vector<int>>& grid) {
        
        int perimeter = 0;
        vector<vector<bool>> vis(grid.size(),vector<bool>(grid[0].size(),false));
        for(int i = 0;i<grid.size();i++){
            for(int j = 0;j<grid[0].size();j++){
                if(grid[i][j]==1&&vis[i][j]==false)
                travelIsland(grid,i,j,perimeter,vis);
            }
        }
        
        return perimeter;
    }
    
    int dir[4][2] = {{0,1},{1,0},{0,-1},{-1,0}};
    
    void travelIsland(vector<vector<int>>& grid, int x, int y, int & perimeter, vector<vector<bool>>& vis){
        
        vis[x][y] = true;
        
        for(int i = 0;i<4;i++){
            
            int nextrow = x+dir[i][0];
            int nextcol = y+dir[i][1];
            
            if(nextrow<0||nextcol<0||nextrow>=grid.size()||nextcol>=grid[0].size()){
                perimeter++;
            }else if(grid[nextrow][nextcol]==0){
                perimeter++;
            }
            
            
        }
        
        for(int i = 0;i<4;i++){
            
            int nextrow = x+dir[i][0];
            int nextcol = y+dir[i][1];
            
      if(nextrow>=0&&nextcol>=0&&nextrow<grid.size()&&nextcol<grid[0].size()&&grid[nextrow][nextcol]==1&&vis[nextrow][nextcol]==false){
                travelIsland(grid,nextrow,nextcol,perimeter,vis);
            }
        }
        
    }
};


// Leetcode 130 


class Solution {
public:
    void solve(vector<vector<char>>& board) {
        if(board.size() == 0){
            return;
        }
        for(int i = 0;i<board[0].size();i++){
            if(board[0][i] == 'O'){
                travelIsland(board,0,i);
            }
            
            if(board[board.size()-1][i]=='O'){
                travelIsland(board,board.size()-1,i);
            }
        }
        
        for(int i = 0;i<board.size();i++){
            if(board[i][0] == 'O'){
                travelIsland(board,i,0);
            }
            
            if(board[i][board[0].size()-1]=='O'){
                travelIsland(board,i,board[0].size()-1);
            }
        }
        
        for(int i = 0;i<board.size();i++){
            for(int j = 0;j<board[0].size();j++){
                if(board[i][j] == 'T'){
                    board[i][j]='O';
                }else{
                    board[i][j]='X';
                }
            }
        }
    }
    
    int dir[4][2] = {{0,1},{1,0},{0,-1},{-1,0}};
    
    void travelIsland(vector<vector<char>>& grid, int x, int y){
        grid[x][y] = 'T';
        
        
        for(int i = 0;i<4;i++){
            
            int nextrow = x+dir[i][0];
            int nextcol = y+dir[i][1];
            
      if(nextrow>=0&&nextcol>=0&&nextrow<grid.size()&&nextcol<grid[0].size()&&grid[nextrow][nextcol]=='O'){
                travelIsland(grid,nextrow,nextcol);
            }
        }
        
    }
};