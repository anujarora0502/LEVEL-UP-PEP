#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

void test1()
{
    int a = 10;
    int *b = &a;
    int **c = &b;

    cout << a << " " << &a << endl;
    cout << *b << " " << &b << endl;
    cout << **c << " " << &c << endl;
}

int *test2()
{
    // int a[100]={324};
    // int a = 10;
    // int *b = a;
    // int *c = b;

    int *a = nullptr;
    // // a = new int(10);
    a = new int[10];
    for (int i = 1; i <= 10; i++)
        a[i] = i * 10;

    return a;
}

vector<int> test3()
{
    vector<int> a(11, 0);
    for (int i = 1; i <= 10; i++)
        a[i] = i * 10;

    return a;
}

int **test4()
{

    int **a = new int *[10];
    for (int i = 0; i < 10; i++)
    {
        a[i] = new int[10];
    }
    for (int i = 0; i < 10; i++)
        for (int j = 0; j < 10; j++)
            a[i][j] = i + j;

    return a;
}

vector<int> **test5()
{

    vector<int> **a = new vector<int> *[10];
    for (int i = 0; i < 10; i++)
    {
        a[i] = new vector<int>(10, 0);
    }
    for (int i = 0; i < 10; i++)
        for (int j = 0; j < 10; j++)
            a[i]->at(j) = i + j;
    return a;
}

void hashMap(string str)
{
    unordered_map<char, int> map; 
    for (int i = 0; i < str.length(); i++)
        map[str[i]]++;

    // insert: map[key]=value;
    // get: cout<<map[key];
    // contains: map.count(key)==0: not present:present
    // remove : map.erase(key);

    vector<char> keys;
    for (pair<char, int> a : map) // same as keySet();
    {
        keys.push_back(a.first);
    }
}

void solve()
{
    // test1();
    // int *a = test2();
    // auto a = test3();
    // for (int i = 1; i <= 100; i++)
    // {
    //     cout << a.at(i) << " ";
    // }
    // int **a = test4();
    vector<int> **a = test5();
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 10; j++)
            cout << a[i]->at(j) << " ";
        cout << endl;
    }
}

int main()
{
    solve();
    return 0;
}