#include<iostream>



int maxSum = -1e8;
int maxPathSum(Node* root){
    maxSum = -1e8;
    return maxPathSum_(root);
}
int maxPathSum_(Node* root)
{ 
    if(root == nullptr){
        return 0;
    }
    
    int lpathSum = maxPathSum(root->left);
    int rpathSum = maxPathSum(root->right);
    
    if(root->left != nullptr && root->right !=nullptr){
      maxSum = max(maxSum,lpathSum+rpathSum+root->data);
      return max(lpathSum,rpathSum)+root->data;
        
    }
      
     return (root->left == nullptr?rpathSum:lpathSum)+root->data; 
}


//LevelOrder/BFS.=======================================================================

void levelOrder_01(Node *node)
{
    queue<Node *> que;
    que.push(node);
    while (que.size() != 0)
    {
        Node *rvtx = que.front();
        que.pop();
        cout << rvtx->data << " ";

        if (rvtx->left != nullptr)
            que.push(rvtx->left);
        if (rvtx->right != nullptr)
            que.push(rvtx->right);
    }
}

void levelOrder_02(Node *node)
{
    queue<Node *> que;
    que.push(node);
    que.push(nullptr);
    while (que.size() != 1)
    {
        Node *rvtx = que.front();
        que.pop();
        cout << rvtx->data << " ";

        if (rvtx->left != nullptr)
            que.push(rvtx->left);
        if (rvtx->right != nullptr)
            que.push(rvtx->right);

        if (que.front() == nullptr)
        {
            cout << endl;
            que.pop();
            que.push(nullptr);
        }
    }
}

void levelOrder_03(Node *node)
{
    queue<Node *> que;
    que.push(node);
    int level = 0;
    while (que.size() != 0)
    {
        int size = que.size();
        cout << "Level: " << level << " -> ";
        while (size-- > 0)
        {
            Node *rvtx = que.front();
            que.pop();
            cout << rvtx->data << " ";

            if (rvtx->left != nullptr)
                que.push(rvtx->left);
            if (rvtx->right != nullptr)
                que.push(rvtx->right);
        }
        level++;
        cout << endl;
    }
}

//Views.======================================================================

void leftView(Node *node)
{
    queue<Node *> que;
    que.push(node);
    while (que.size() != 0)
    {
        int size = que.size();
        cout << que.front()->data << " ";
        while (size-- > 0)
        {
            Node *rvtx = que.front();
            que.pop();

            if (rvtx->left != nullptr)
                que.push(rvtx->left);
            if (rvtx->right != nullptr)
                que.push(rvtx->right);
        }
        cout << endl;
    }
    cout << endl;
}

void rightView(Node *node)
{
    queue<Node *> que;
    que.push(node);
    while (que.size() != 0)
    {
        int size = que.size();
        Node *prev = nullptr;
        while (size-- > 0)
        {
            Node *rvtx = que.front();
            que.pop();

            if (rvtx->left != nullptr)
                que.push(rvtx->left);
            if (rvtx->right != nullptr)
                que.push(rvtx->right);

            prev = rvtx;
        }
        cout << prev->data << " ";
        cout << endl;
    }
    cout << endl;
}

void width(Node *node, int level, pair<int, int> &maxMin)
{
    if (node == nullptr)
        return;

    maxMin.first = min(maxMin.first, level);
    maxMin.second = max(maxMin.second, level);

    width(node->left, level - 1, maxMin);
    width(node->right, level + 1, maxMin);
}

void verticalOrderTraversal(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<vector<int>> ans(w, vector<int>());

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            ans[rpair.second].push_back(rpair.first->data);

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (vector<int> &ar : ans)
    {
        for (int ele : ar)
            cout << ele << " ";
        cout << endl;
    }
    cout << endl;
}

void verticalOrderSum(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<int> ans(w, -1);

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            ans[rpair.second] += rpair.first->data;

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (int ele : ans)
        cout << ele << " ";
    cout << endl;
}


void verticalView(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<int> ans(w, -1);

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            if (ans[rpair.second] == -1)
                ans[rpair.second] = rpair.first->data;

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (int ele : ans)
        cout << ele << " ";
    cout << endl;
}

void BottomView(Node *node)
{
    pair<int, int> maxMin = {0, 0};
    width(node, 0, maxMin);
    int w = maxMin.second - maxMin.first + 1;
    vector<int> ans(w, -1);

    queue<pair<Node *, int>> que;
    que.push({node, -maxMin.first});

    while (que.size() != 0)
    {
        int size = que.size();
        while (size-- > 0)
        {
            pair<Node *, int> rpair = que.front();
            que.pop();

            ans[rpair.second] = rpair.first->data;

            if (rpair.first->left != nullptr)
                que.push({rpair.first->left, rpair.second - 1});

            if (rpair.first->right != nullptr)
                que.push({rpair.first->right, rpair.second + 1});
        }
    }

    for (int ele : ans)
        cout << ele << " ";
    cout << endl;
}

void display(Node *node)
{
    if (node == nullptr)
        return;
    string str = "";
    str += node->left != nullptr ? to_string(node->left->data) : ".";
    str += " <- " + to_string(node->data) + " -> ";
    str += node->right != nullptr ? to_string(node->right->data) : ".";
    cout << str << endl;

    display(node->left);
    display(node->right);
}

void set1(Node *root)
{
    // levelOrder_01(root);
    // levelOrder_02(root);
    // levelOrder_03(root);

    leftView(root);
    rightView(root);
    verticalOrderTraversal(root);
    verticalView(root);
    BottomView(root);
}
