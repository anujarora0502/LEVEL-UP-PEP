#include <iostream>
#include <vector>
using namespace std;

class Node
{
public:
    int data;
    Node *left = nullptr;
    Node *right = nullptr;
    Node(int data)
    {

        this->data = data;
    }
};

int idx = 0;
Node *constructTree(vector<int> &arr)
{
    if (idx == arr.size() || arr[idx] == -1)
    {
        idx++;
        return nullptr;
    }

    Node *node = new Node(arr[idx++]);

    node->left = constructTree(arr);
    node->right = constructTree(arr);

    return node;
}

void inOrder(Node *node)
{

    if (node == nullptr)
    {
        return;
    }

    inOrder(node->left);

    cout << node->data << " ";

    inOrder(node->right);
}

int size(Node *node)
{
    return node == nullptr ? 0 : size(node->left) + size(node->right) + 1;
}

int height(Node *node)
{
    return node == nullptr ? -1 : max(height(node->left), height(node->right)) + 1;
}

int maximum(Node *node)
{
    return node == nullptr ? -1e8 : max(max(maximum(node->left), maximum(node->right)), node->data);
}

int minimum(Node *node)
{
    return node == nullptr ? 1e8 : min(min(minimum(node->left), minimum(node->right)), node->data);
}
bool find(Node *node, int data)
{

    if (node == nullptr)
    {
        return false;
    }

    if (node->data == data)
    {
        return true;
    }

    return find(node->left, data) || find(node->right, data);
}

void display(Node *node)
{

    if (node == nullptr)
    {
        return;
    }

    string str = "";

    str += node->left != nullptr ? to_string(node->left->data) : ".";
    str += " <- " + to_string(node->data) + " -> ";
    str += node->right != nullptr ? to_string(node->right->data) : ".";
    cout<<str<<endl;
    display(node->left);
    display(node->right);
}

vector<Node *> NodeToRootPath(Node *node, int data){
    if(node == nullptr){
        return {};
    }

    if(node->data){
        vector<Node *> base;
        base.push_back(node);
        return base; 
    }

    vector<Node *> la = NodeToRootPath(node->left,data);

    if(la.size()!=0){
        la.push_back(node);
        return la;
    }

    vector<Node *> ra = NodeToRootPath(node->right,data);

    if(ra.size()!=0){
        ra.push_back(node);
        return ra;
    }

    return {};
}
int main()
{

    vector<int> arr = {10, 20, 30, 40, -1, -1, 50, -1, -1, 60, 70, -1, 80, -1, -1, -1, 90, 100, -1, 120, -1, -1, 110, 130, -1, -1, -1};

    Node *root = constructTree(arr);

    // inOrder(root);
    // cout<<endl;
    // cout<<find(root,1220);
    display(root);
    return 0;
}