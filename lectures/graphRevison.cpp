#include<iostream>
#include<vector>
#include<queue>
using namespace std;

class Edge{
  public:
  int v,w;

  Edge(int v, int w){

      this->v=v;
      this->w=w;
  }

};
int N = 7;
vector<vector<Edge>> graph(N,vector<Edge>());

void addEdge(int u, int v, int w){
    graph[u].push_back(Edge(v,w));
    graph[v].push_back(Edge(u,w));
}

void display(){

  for(int i = 0;i<N;i++){
      cout<<i<<" -> ";
      for(Edge e:graph[i]){
          cout<<"("<<e.v<<","<<e.w<<")";
      }
      cout<<endl;
  }

}

bool hasPath(int src,int dest,vector<bool>& vis){
   
   if(src==dest){
       return true;
   }

   vis[src] = true;
   bool res = false;

   for(Edge e: graph[src]){

       if(!vis[e.v]){
           res = res||hasPath(e.v,dest,vis);
       }
   }
  
  return res;
}

void allPath(int src,int dest,vector<bool>& vis,string ans){
   
   if(src==dest){
       cout<<ans<<dest<<endl;
       return;
   }

   vis[src] = true;
   for(Edge e: graph[src]){

       if(!vis[e.v]){
           allPath(e.v,dest,vis,ans+to_string(src));
       }
   }
  
   vis[src] = false;
}

void bfs(int src, vector<bool> &vis){
    queue<int> que;
    que.push(src);
    while(que.size()!=0){

        int rvtx = que.front();
        que.pop();

        if(vis[rvtx]){
            //cycle

            
        }
    }

}

int main(){

 addEdge(0,1,10);
 addEdge(0,3,10);
 addEdge(1,2,10);
 addEdge(2,3,40);
 addEdge(3,4,2);
 addEdge(4,5,2);
 addEdge(4,6,8);
 addEdge(6,1,2);

 display();

 vector<bool> vis(N,false);
 allPath(0,6,vis,"");
    return 0;
}