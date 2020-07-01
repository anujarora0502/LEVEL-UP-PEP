import java.util.*;
public class june15 {
    public static class Edge{
        int v;
        int w;
    
        public Edge(int v, int w){
            this.v = v;
            this.w = w;
        }

        public String toString(){

            return "(" +this.v+","+this.w+")";
        }
    }
    
static ArrayList<Edge>[] graph = new ArrayList[7];
public static void  main(String[] args){
    
    for(int i =0;i<7;i++){
        graph[i] = new ArrayList<>();
    }

    addEdge(0,1,1);
    addEdge(0,3,1);
    addEdge(1,2,1);
    addEdge(2,3,1);
    addEdge(3,4,1);
    addEdge(4,5,1);
    addEdge(4,6,1);
    addEdge(5,6,1);
    
    // removeEdge(5,0);
    // removeVertice(3);
    
   for(int i =0;i<7;i++){ 
    if(graph[i].size()!=0){   
    System.out.print(i + "->");
    for(Edge e:graph[i]){
        System.out.print(e);
    }
    System.out.println();

}


}

System.out.println(hasPath(3,1, new boolean[graph.length]));
hasAllPath(0,6,new boolean[graph.length],"");
System.out.println(hwp+"@"+hw);
System.out.println(lwp+"@"+lw);
}

static int lw = Integer.MAX_VALUE;
static String lwp = "";

static int hw = 0;
static String hwp = ""; 
public static void AllPath(int src, int dest,int wsf, boolean[] vis, String path){
   if(src == dest){
        if(wsf>lw){
            lw =wsf;
            lwp = path+dest;
        }

        if(wsf<hw){
            hw =wsf;
            hwp = path+dest;
        }

        return;
   }

    vis[src]= true;
    for(Edge e: graph[src]){
        AllPath(e.v, dest,wsf+e.w,vis,path+src+" ");
    }
    vis[src]=false;

}
public static int hasAllPath(int src, int dest, boolean[] vis, String path){
    if(src==dest){
        System.out.println(path+dest);
        return 1;
}
    
    vis[src]=true;
    int count =0;

    for(Edge e: graph[src]){
        if(!vis[e.v]){
            count+= hasAllPath(e.v,dest,vis,path + src + " ");

        }
    }
        vis[src]=false;
        return count;
    
}

public static boolean hasPath(int src, int dest, boolean[] vis){
    if(src == dest){
        return true;
    }
    vis[src]=true;
    boolean res = false;
    for(Edge e: graph[src]){
        if(!vis[e.v]){
            res = res || hasPath(e.v,dest,vis);
        }
    }

    return res;
}

public static void  removeEdge(int u, int v){
  for(int i =0;i<graph[u].size();i++){
      if(graph[u].get(i).v==v){
        graph[u].remove(i);
        break;
      }
  }

  for(int i =0;i<graph[v].size();i++){
    if(graph[v].get(i).v==u){
      graph[v].remove(i);
      break;
    }
}
}

public static void removeVertice(int u){
  
    while(graph[u].size()!=0){
        int v = graph[u].get(graph[u].size()-1).v;
        removeEdge(u,v);
    }
}

public static void addEdge(int u, int v, int w){

    graph[u].add(new Edge(v,w));
    graph[v].add(new Edge(u,w));
    
}

}