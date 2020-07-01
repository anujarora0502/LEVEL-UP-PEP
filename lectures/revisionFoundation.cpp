#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;
classListNode{
  public:

   int data = 0;
   ListNode* left = nullptr;
   ListNode* right = nullptr;

   ListNode(int data){
       this->data = data;
   }

};

int idx = 0;

ListNode* constructTree(vector<int>& arr){

    if(idx==arr.size()||arr[idx]==-1){
        idx++;
        return nullptr;
    }

    ListNode* node = new ListNode(arr[idx]);
    idx++;
    node->left = constructTree(arr);
    node->right = constructTree(arr);
    
    return node;

}

ListNode* removeLeaf(ListNode* node,int data){
    if(node == nullptr||node->data == data){
        return nullptr
    }

    if(node->left == nullptr && node->right == nullptr && node->data == data){
        return nullptr;
    }
}

ListNode* removeLeaves(ListNode* node){
    if(node->left==nullptr&&node->right== nullptr){
        return nullptr;
    }

    node->left = removeLeaves(node->left);
    node->right = removeLeaves(node->right);
}




///////////////////////////////////////////Generic Tree


class Node{
   public:

    int data;
    vector<Node*> childs;

    Node(int data){
        this->data = data;
    }  
};

int size(Node* node){
    for(Node* child: node->childs){
        sz+= size(child);
    }

    return sz+1;
}

int height(Node* node){
    int h =-1;
    
}

int main(){
    


    return 0;
}