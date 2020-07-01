public class linkedList{

  public static class LinkedList{

     public class Node{
         int data=0;
         Node next = null;

         public Node(int data){
            this.data = data;
        }
     }

     int size = 0;
     Node head = null;
     Node tail = null;

      //Basic functions

      public int size(){
          return this.size;
      }

      public boolean isEmpty(){
          return this.size==0;
      }

      //Addfunctions ----------------------------------------------
       





      //remove functions ------------------------------------------





      //get Funcions ----------------------------------------------



  }

  public static void main(String[] args){
      LinkedList ll = new LinkedList();

      System.out.println(ll.size);
  }


}