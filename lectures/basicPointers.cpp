#include<iostream>


using namespace std;


int main(){
    int a = 10;

    int* b = &a;  //(type)* name = &var;

    int** c =&b;
    cout<<a<<endl;

    cout<<b<<" "<<*b<<endl;

    cout<<c<<" "<<*c<<" "<<**c<<endl;

    return 0;
}