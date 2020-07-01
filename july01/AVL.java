public class AVL{
    
    public static class Node{
        int data;
        Node left;
        Node right;

        int height = 0;
        int bal = 0;

        public Node(int data){
            this.data = data;
        }
    }

    /// AVL util
    
    public static void updateHeightAndBalance(Node node){
          int lh = -1;
          int rh = -1;

          if(node.left != null){
              lh = node.left.height;
          }
          if(node.right != null){
              rh = node.right.height;
          }

          node.height = Math.max(lh,rh)+1;
          node.bal = lh - rh;
    }
     //left left skew
     public static Node ll(Node A){
         Node B = A.left;
         Node BkaRight = B.right;

         B.right = A;
         A.left = BkaRight;

         updateHeightAndBalance(A);
         updateHeightAndBalance(B);

         return B;
     }

     // RIGHT RIGHT SKEW (ACTUALLY LL BUT WE ARE ROTATING RR NODES NOT DIRECTION)
     public static Node rr(Node A){
        Node B = A.right;
        Node BkaLeft = B.left;

        B.left = A;
        A.right = Bkaleft;

        updateHeightAndBalance(A);
        updateHeightAndBalance(B);

        return B;
    }

     public static Node rotateSubTree(Node node){
         updateHeightBalance(node);
         if(node.bal == 2){ //ll,lr
             if(node.left.bal == 1){ //ll
                return ll(node); 
             }else{  //lr
                 node.left = rr(node.left);
                 return ll(node);

             }
         }else if(node.bal == -2){
             if(node.right.bal == -1){ //rr{
                 return rr(node);
             }else{  //rl
                node.right = ll(node.right);
                return rr(node);
             }
         }
     }
    //BST functions 
    public static int maximum(Node root){
        while(root.right!=null){
            root = root.right;
        }
        
        return root.data;
    }


    public static Node addData(Node root, int data){
        if(root == null){
            return new Node (data);
        }
        if(data<root.data){
            root.left = addData(root.left,data);
        }
        else {
            root.right = addData(root.right,data);
        }

        return rotateSubTree(root);
    }

    public static Node removeData(Node root, int data){

        if(data<root.data){
            root.left = removeData(root.left,data);
        }
        else if(data>root.data){
            root.right = removeData(root.right,data);
        }
        else{
            if(root.left == null || root.right == null){
                return root.left!=null?root.left:root.right;
            }

            int maxEle = maximum(root.left);
            root.data = maxEle;
            root.left = removeData(root.left,maxEle);
        }
        
        return rotateSubTree(root);
        
    }
    public static void main(String[] args){


    }

}