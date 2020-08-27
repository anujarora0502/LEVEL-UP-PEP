#include<iostream>
#include<vector>
#include<stack>

using namespace std;



// MIN STACK AND LRU CACHE THESE ARE THE IMPORTANT QUESTIONS 

//Leetcode 155
class MinStack
{
public:
    stack<long> st;
    long minSf = 0;

    MinStack()
    {
    }

    void push(int x)
    {
        if (st.size() == 0)
        {
            st.push(x);
            minSf = x;
            return;
        }

        if (x < minSf)
        {
            st.push((x - minSf) + x);
            minSf = x;
        }
        else
            st.push(x);
    }

    void pop()
    {
        if (st.top() < minSf)
        {
            minSf = (minSf - st.top()) + minSf;
        }

        st.pop();
    }

    int top()
    {
        if (st.top() < minSf)
            return (int)minSf;
        return (int)st.top();
    }

    int getMin()
    {
        return (int)minSf;
    }
};

class LRUCache
{

    class Node
    {
    public:
        int key = 0;
        int value = 0;

        Node *next = nullptr;
        Node *prev = nullptr;

        Node(int key, int value)
        {
            this->key = key;
            this->value = value;
        }
    };

    Node *head = nullptr;
    Node *tail = nullptr;
    int size = 0;
    int maxSize = 0;

    void addLast(Node *node)
    {
        if (this->size == 0)
        {
            this->head = node;
            this->tail = node;
        }
        else
        {
            this->tail->next = node;
            node->prev = this->tail;
            this->tail = node;
        }
        this->size++;
    }

    void removeNode(Node *node)
    {

        if (this->size == 1)
        {
            this->head =  nullptr;
            this->tail = nullptr;
        }
        else if (node->prev == nullptr)
        {
            this->head = node->next;

            this->head->prev = nullptr;
            node->next = nullptr;
        }
        else if (node->next == nullptr)
        {
            this->tail = node->prev;

            this->tail->next = nullptr;
            node->prev = nullptr;
        }
        else
        {
            Node *prev = node->prev;
            Node *next = node->next;

            prev->next = next;
            next->prev = prev;

            node->next = nullptr;
            node->prev = nullptr;
        }
        this->size--;
    }

    unordered_map<int, Node *> map; // key, node

public:
    LRUCache(int capacity)
    {
        this->maxSize = capacity;
    }

    int get(int key)
    {
        if (map.find(key) == map.end())
            return -1;

        Node *node = map[key];
        int rv = node->value;

        removeNode(node);
        addLast(node);

        return rv;
    }

    void put(int key, int value)
    {
        if (map.find(key) == map.end())
        {
            Node *node = new Node(key, value);
            map[key] = node;
            addLast(node);
            if(this->size > this->maxSize){
               node = this->head;
                
               map.erase(node->key);
               removeNode(node);  
            }
        }
        else
        {
            int val = get(key);
            if (val != value)
                map[key]->value = value;
        }
    }
};

int main(){


    return 0;
}