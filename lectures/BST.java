import java.util.*;

public class BST{

    public static class Node{

        int data = 0;
        Node left = null;
        Node right = null;

        public Node(int data){
            this.data = data;
        }
    }


    public static Node constructTree(int[] arr, int si , int ei){
       if(si>ei){
           return null;
       }

       int mid = (si+ei)/2;
       Node node = new Node(arr[mid]);

       node.left = constructTree(arr, si, mid-1);
       node.right = constructTree(arr, mid+1, ei);
       return node;
    }

    public static void display(Node node){
        if(node == null){
            return;
        }

        String str = "";
        str+= node.left != null?node.left.data : ".";
        str+= " <- "+node.data+" -> ";
        str+= node.right != null ? node.right.data : ".";
        System.out.println(str);

        display(node.left);
        display(node.right);

    }
    
    public static int height(Node node){
        return node == null ? -1 : Math.max(height(node.left),height(node.right))+1;
    }

    public static int size(Node node){
        return node == null ? 0 : size(node.left)+size(node.right)+1;
    }

    public static boolean find(Node node, int data){
        Node curr = node;
        while(curr!= null){

            if(curr.data == data){
                return true;
            }else if(curr.data > data){
                curr = curr.left;
            }else {
                curr = curr.right;
            }
        }

        return false;
    }

    public static ArrayList<Node> rootToNodePath(Node node, int data){
        ArrayList<Node> ans = new ArrayList<>();

        Node curr = node;

        while(curr!=null){
            if(curr.data == data){
                ans.add(curr);
                break;
            }else if(curr.data<data){
                curr = curr.right;
            }else {
                curr = curr.left;
            }
        }

        return ans;
    }

    public static int maximum(Node node){
        Node curr = node;

        while(curr.right!= null){
            curr = curr.right;
        }

        return curr.data;
    }

    public static int minimum(Node node){
        Node curr = node;

        while(curr.left!= null){
            curr = curr.left;
        }

        return curr.data;
    }

    public static Node lowestCommonAncestor(Node root, Node p, Node q) {
        Node curr = root;
        while(curr.data>p.data&&curr.data>q.data||curr.data<=p.data&&curr.data<=q.data){
            if(curr.data == p.data||curr.data == q.data){
                break;
            }else if(curr.data>p.data&&curr.data>q.data){
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }
    
      return find(curr,p.data)&&find(curr,q.data)?curr:null;   
    }
    static ArrayList<Integer> ans = new ArrayList<>();
    public static void inRange(Node root, int p, int q){
        
        if(root==null){
            return;
        }
        inRange(root.left,p,q);
        if(root.data>=p&&root.data<=q){
            ans.add(root.data);
        }
        inRange(root.right,p,q);
        
    }

    public static void preorder(Node root){
      if(root == null){
          return;
      }

      System.out.print(root.data+" ");
      preorder(root.left);
      preorder(root.right);

    }

    public static void postorder(Node root){
        if(root == null){
            return;
        }
  
        
        postorder(root.left);
        postorder(root.right);

        System.out.println(root.data);
  
      }
   static int idx = 0;
   public static Node cTpreOrder(int lr, int rr, int[] arr){
       
       if(idx == arr.length || arr[idx]<lr || arr[idx]>rr){
           return null;
       }


       Node node = new Node(arr[idx++]);

       node.left = cTpreOrder(lr, node.data, arr);
       node.right = cTpreOrder(node.data, rr, arr);

       return node;
   }

   public static int heightPreOrder(int lr, int rr, int[] arr){
       
    if(idx == arr.length || arr[idx]<lr || arr[idx]>rr){
        return -1;
    }

    int data = arr[idx];
    idx++;
    int lh = heightPreOrder(lr, data, arr);
    int rh = heightPreOrder(data, rr, arr);

    return Math.max(lh,rh)+1;
}


public static void predSuccInBST(Node node, int data) {
    Node curr = node;
    Node pred = null;
    Node succ = null;
    while (curr != null) {
        if (data < curr.data) {
            succ = curr;
            curr = curr.left;
        } else if (data > curr.data) {
            pred = curr;
            curr = curr.right;
        } else {

            Node tPred = curr.left;
            if (tPred != null) {
                while (tPred.right != null)
                    tPred = tPred.right;
                pred = tPred;
            }

            Node tSucc = curr.right;
            if (tSucc != null) {
                while (tSucc.left != null)
                    tSucc = tSucc.left;
                succ = tSucc;
            }
        }

    }

}

public static void set1(ArrayList<Integer> ans) {
    int[] ar = new int[ans.size()];
    for (int i = 0; i < ans.size(); i++)
        ar[i] = ans.get(i);

    // Node node = constructTreeUsingPreOrder(ar);
    // System.out.println(heightTreeUsingPreOrder(ar));

    Node node = constructTreeUsingPostOrder(ar);
    display(node);

}
    public static void main(String[] args){
        int[] arr = new int[15];

        for(int i = 0;i<arr.length;i++){
            arr[i]= (i+1)*10;
        }
        int[] arr1 = {80 ,40 ,20, 10,5, 30, 60, 50, 70, 120, 100, 90, 110,140, 130, 150};
        Node root = constructTree(arr,0,arr.length-1);
        // display(root);
        // System.out.println(maximum(root));
        // System.out.println(minimum(root));

        // inRange(root,61,99);

        // System.out.println(ans);
        // preorder(root);
        // System.out.println();
        // Node root1 = cTpreOrder((int)-1e8,(int)1e8,arr1);
        // display(root1);
        // System.out.println();
        // display(root);

        System.out.println(heightPreOrder(-1000000,1000000, arr1));
        // System.out.println(lowestCommonAncestor(root,));
        postorder(root);
    }

}