#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class ListNode
{
public:
    int data = 0;
    ListNode *left = nullptr;
    ListNode *right = nullptr;

    ListNode(int data)
    {
        this->data = data;
    }
};

int idx = 0;
ListNode *constructTree(vector<int> &arr)
{
    if (idx == arr.size() || arr[idx] == -1)
    {
        idx++;
        return nullptr;
    }

    ListNode *node = new ListNode(arr[idx]);
    idx++;
    node->left = constructTree(arr);
    node->right = constructTree(arr);
    return node;
}

void display(ListNode *node)
{
    if (node == nullptr)
    {
        return;
    }

    string str = "";
    str += node->left == nullptr ? "." : to_string(node->left->data) + "";
    str += " <- " + to_string(node->data) + " -> ";
    str += node->right == nullptr ? "." : to_string(node->right->data) + "";
    cout << (str);

    display(node->left);
    display(node->right);
}

int height(ListNode *node)
{
    // if (node == nullptr)
    //     return -1;

    // int lh = height(node->left);
    // int rh = height(node->right);

    // return max(lh, rh) + 1;

    return node == nullptr ? -1 : max(height(node->left), height(node->right)) + 1;
}

int size(ListNode* node)
{
    return node == nullptr ? 0 : size(node->left) + size(node->right) + 1;
}

int maximumInTree(ListNode *node)
{
    return node == nullptr ? -1e9 : max(node->data, max(maximumInTree(node->left), maximumInTree(node->right)));
}

int minimumInTree(ListNode *node)
{
    return node == nullptr ? 1e9 : min(node->data, min(minimumInTree(node->left), minimumInTree(node->right)));
}

ListNode *removeLeaf(ListNode *node, int data)
{
    if (node == nullptr)
        return nullptr;
    if (node->left == nullptr && node->right == nullptr && node->data == data)
    {
        // delete node;
        return nullptr;
    }

    node->left = removeLeaf(node->left, data);
    node->right = removeLeaf(node->right, data);
    return node;
}

ListNode *removeLeaves(ListNode *node)
{
    if (node == nullptr)
        return nullptr;
    if (node->left == nullptr && node->right == nullptr)
    {
        // delete node;
        return nullptr;
    }

    node->left = removeLeaves(node->left);
    node->right = removeLeaves(node->right);
    return node;
}

ListNode *clear(ListNode *node)
{
    if (node == nullptr)
        return nullptr;

    node->left = clear(node->left);
    node->right = clear(node->right);
    if (node->left == nullptr && node->right == nullptr)
    {
        // delete node;
        return nullptr;
    }
    return node;
}

void solve()
{
}

int main()
{
    solve();
    return 0;
}