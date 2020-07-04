//173 leet code


/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class BSTIterator {
public:
    stack<TreeNode*> st;
    
    
    BSTIterator(TreeNode* root) {
      insertLeftMost(root);  
    }
    
    void insertLeftMost(TreeNode* root){
        TreeNode* curr = root;
        while(curr!= nullptr){
            st.push(curr);
            curr = curr->left;
        }
    }
    
    
    /** @return the next smallest number */
    int next() {
       TreeNode *rnode = st.top();
       st.pop();
       insertLeftMost(rnode->right);
       return rnode->val; 
    }
    
    /** @return whether we have a next smallest number */
    bool hasNext() {
        if(st.size()!=0){
            return true;
        }else{
            return false;
        }
    }
};

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator* obj = new BSTIterator(root);
 * int param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */


//gfg BT to DLL

Node *prevDLL = nullptr;
Node *head = nullptr;
void DLL(Node *root){
    if(root == nullptr){
        return;
    }
    
    DLL(root->left);
    if(head == nullptr){
        head = root;
    }else{
        prevDLL->right = root;
        root->left = prevDLL;
    }
    
    prevDLL = root;
    DLL(root->right);
  
}
Node * bToDLL(Node *root)
{   prevDLL = nullptr;
    head = nullptr;
    DLL(root);
    return head;
    
}



// morris traversal 



Node *rightMost(Node *next, Node *curr)
{
    while (next->right != nullptr && next->right != curr)
        next = next->right;

    return next;
}

void morrisInOrderTraversal(Node *node)
{
    Node *curr = node;
    while (curr != nullptr)
    {
        Node *next = curr->left;
        if (next == nullptr)
        {
            cout << curr->data << " ";
            curr = curr->right;
        }
        else
        {
            Node *rMost = rightMost(next, curr);
            if (rMost->right == nullptr) // thread creation
            {
                rMost->right = curr;
                curr = curr->left;
            }
            else
            {
                cout << curr->data << " ";
                rMost->right = nullptr; // thread break
                curr = curr->right;
            }
        }
    }
}

void morrisPreOrderTraversal(Node *node)
{
    Node *curr = node;
    while (curr != nullptr)
    {
        Node *next = curr->left;
        if (next == nullptr)
        {
            cout << curr->data << " ";
            curr = curr->right;
        }
        else
        {
            Node *rMost = rightMost(next, curr);
            if (rMost->right == nullptr) // thread creation
            {
                cout << curr->data << " ";
                rMost->right = curr;
                curr = curr->left;
            }
            else
            {
                rMost->right = nullptr;
                curr = curr->right;
            }
        }
    }
}

void traversal(Node *root)
{
    // morrisInOrderTraversal(root);
    morrisPreOrderTraversal(root);
}



TreeNode *treeToDoublyList(TreeNode *root)
{
    if (root == nullptr)
        return root;

    prevDLL = nullptr;
    head = nullptr;
    DLL(root);

    prevDLL->right = head;
    head->left = prevDLL;

    return head;
}


// 106
TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        int n = preorder.size();
        return helper(preorder, inorder, 0,n-1,0,n-1);
    }
    
    TreeNode* helper(vector<int>& preorder, vector<int>& inorder,int psi, int pei, int isi, int iei){
        
        if(psi > pei){
            return nullptr;
        }
        
        int idx = isi;
        while (inorder[idx] != preorder[psi])
        idx++;
        
        TreeNode *node = new TreeNode(preorder[psi]);
        
        int tel = idx - isi;
        
        node->left = helper(preorder,inorder,psi + 1, psi + tel, isi, idx - 1);
        node->right = helper(preorder,inorder,psi + tel + 1, pei,idx + 1, iei);
            
        return node;    
    }


// 968 leetcode binary tree camera


int minCameraCover(TreeNode* root) {
     
      int finalAns = minCameraCover_(root);
      if(finalAns == -1){
          camera++;
      }  
      return camera;  
    
    }
    
    
    int camera = 0;
    
    int minCameraCover_(TreeNode* root) {
        if(root == nullptr){
            return 1;
        }
        if(root->left == nullptr&&root->right == nullptr){
            return -1;
        }
        
        int lans = minCameraCover_(root->left);
        int rans = minCameraCover_(root->right);
        
        if(lans == -1|| rans == -1){
            camera++;
            return 0;
        }
        else if(lans == 0|| rans == 0){
            return 1;
        }
        
        return -1;
    }



    // 230 


    int kthSmallest(TreeNode* root, int k) {
        return inorder(root,k);
    }
    int count = 0;
    int inorder(TreeNode* root,int k){
        
        if(root == nullptr){
            return -1;
        }
        
        
        
        int la = inorder(root->left,k);
        if(la!=-1){
            return la;
        }
        
        count++;
        if(k == count){
            return root->val;
        }
        
        int ra = inorder(root->right,k);
        if(ra!=-1){
            return ra;
        }
        
        return -1;
    }
