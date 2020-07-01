#include<iostream>

using namespace std;

class LinkedList{
 public:
     class Node{
         int data=0;
         Node* next = nullptr;

         Node(int data){
            this->data = data;
        }
     };

     int sz = 0;
     Node* head = nullptr;
     Node* tail = nullptr;

      //Basic functions

      int size(){
          return this->sz;
      }

      bool isEmpty(){
          return this->sz==0;
      }

      //Addfunctions ----------------------------------------------
       





      //remove functions ------------------------------------------





      //get Funcions ----------------------------------------------



  };

  int main(){
      LinkedList ll;

      cout<<ll.sz<<endl;

      return 0;
  }