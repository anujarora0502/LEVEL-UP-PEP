// GENERIC TREE
#include<iostream>
#include<vector>
#include<stack>
#include<queue>

using namespace std;


class Node{
    public:
     int data;
     vector<Node *> childs;

     Node(int data){
         this->data = data;
     }
};

Node* createTree(vector<int> &arr){
  
   stack<Node*> st;
   for(int i =0;i<arr.size()-1;i++){
       int ele = arr[i];
       if(ele == -1){
          
          Node *node = st.top();
          st.pop();
          st.top()->childs.push_back(node);

       }else{
           st.push(new Node(ele));
       }
   } 

   return st.top();

}


int size(Node* node){

    int sz = 0;
    for(Node* child : node->childs){
       sz+= size(child);
    }

    return sz+1;
}

int height(Node* node){

    int h = -1;
    for(Node* child: node->childs){
        h = max(height(child),h);
    }
    return h+1;
}

void preOrder(Node* node){

    cout<<node->data<<" ";
    for(Node* child: node->childs){
        preOrder(child);
    }
}

void postOrder(Node* node){

    for(Node* child: node->childs){
        postOrder(child);
    }

    cout<<node->data<<" ";
}

void display(Node* node){
    
    cout<<node->data<<" -> ";
    for(Node* ele:node->childs){
        cout<<ele->data<<" ";
    }
    cout<<endl;
    for(Node* ele:node->childs){
        display(ele);
    }
}

bool find(Node* node, int data){
    
    if(node->data == data){
        return true;
    }

    for(Node* child: node->childs){
        if(find(child,data))
          return true;
          }

    return false;      
}

void levelorderLineWise(Node* node){
   queue<Node* > q;
   q.push(node);

   while(q.size()!=0){
       int size = q.size();
       while(size-->0){
         cout<<q.front()->data<<" ";
         Node* rnode = q.front();
         q.pop();
         for(Node* n: rnode->childs){
             q.push(n);
         }
       }
       cout<<endl;
   }
}

void nodeToRootPath(Node* node,int data){
   
   

}

void distanceBetweenTwoNodes(Node* node, int d1, int d2){

}
Node* getTail(Node* node){
    while(node->childs.size()!=0){
        node = node->childs[0];
    }

    return node;
}
void linearize(Node* node){
    int n = node->childs.size();

    for(int i = n-1;i>=0;i--){
        Node* child = node->childs[i];
        linearize(child);

        if(i<n-1){
            Node* tail = getTail(child);
            tail->childs.push_back(node->childs[i+1]);
            node->childs.pop_back();
        }
    }
}

int main(){
    
    vector<int> arr{10,20,50,-1,60,-1,-1,30,70,-1,80,100,-1,110,-1,-1,90,-1,-1,40,120,140,-1,150,-1,-1,-1,-1};
    
    Node* root = createTree(arr);
    
    linearize(root);

    display(root);
    

    return 0;
}