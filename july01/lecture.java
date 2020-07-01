public class lecture{
    
   // BST ADD AND REMOVE
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

        return root;
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

        return root;
    }
     ///   LEETCODE 701
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        if(val>root.val){
           root.right =  insertIntoBST(root.right,val);
        }else{
           root.left = insertIntoBST(root.left,val);
        }
        
        return root;
    }


     ////   LEETCODE 450

     public int maximum(TreeNode root){
        while(root.right!=null){
            root = root.right;
        }
        
        return root.val;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }
        if(key<root.val){
            root.left = deleteNode(root.left,key);
        }
        else if(key>root.val){
            root.right = deleteNode(root.right,key);
        }
        else{
            if(root.left == null || root.right == null){
                return root.left!=null?root.left:root.right;
            }

            int maxEle = maximum(root.left);
            root.val = maxEle;
            root.left = deleteNode(root.left,maxEle);
        }

        return root;
    }

    
    public static void main(String[] args){

        System.out.println("test");
    }
}