#include<iostream>
#include<vector>
using namespace std;

class TreeNode{
 public:

   int val;
   TreeNode* left ;
   TreeNode *right;

   TreeNode(){}
   TreeNode(int val) {this->val = val;}
   TreeNode(int val, TreeNode* left , TreeNode* right){

       this->val = val;
       this->left = left;
       this->right = right;
   }


};
int maxSUM = 0;
vector<int> maxSumBST(TreeNode *root){
   if(root == nullptr){
       return {1,(int)-1e8,(int)1e8,0};
   }

   vector<int> la = maxSumBST(root->left);
   vector<int> ra = maxSumBST(root->right);

   bool isBST = la[0]==1 && ra[0]==1 && la[1]<root->val && root->val<ra[2];

   int sum = la[3]=ra[3]+root->val;

   if(isBST){
       maxSUM = max(sum,maxSUM);
   }

   return {isBST?1:0,max(root->val,max(la[1],ra[1])),min(root->val,min(la[2],ra[2])),sum};
}