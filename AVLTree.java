//http://courses.csail.mit.edu/6.006/fall09/lecture_notes/lecture04.pdf
//http://eniac.cs.qc.cuny.edu/andrew/csci700-11/lecture7.pdf

//height of a null node is -1
//height of a leaf node is 0

import java.util.ArrayList;

public class AVLTree<T extends Comparable<T>> { //implements Iterable<T> {



        private Node root;
        private Node successor; // only used for deletion
        
        private class Node {
                
                T data;
                int h;
                Node left;
                Node right;
                
                private Node( Node left, Node right, int height ,  T data) {
                
                        this.left = left;
                        this.right = right;
                        this.h = height;
                        this.data = data;
                }
        }
        
      
        
        private void setNodeHeight(Node n){
        
                int leftChildHeight = getHeight(n.left);
                int rightChildHeight = getHeight(n.right);
                
                if(leftChildHeight > rightChildHeight)
                        n.h = leftChildHeight + 1;
                else
                        n.h = rightChildHeight + 1;
                        
        }                                                                        
                
        private int getHeight(Node n) {
        
                return (n==null) ? -1 :  n.h;
        
        }
        
        
        private Node restoreBalance(Node n) {
        
                int leftChildHeight = getHeight(n.left);
                int rightChildHeight = getHeight(n.right);
                
                //Node n where violation has occurred is left heavy
                if( (leftChildHeight > rightChildHeight)  && ( (leftChildHeight - rightChildHeight ) > 1 )  ) {
                
                        Node leftChild = n.left;
                        int leftChildLeftHeight = getHeight(leftChild.left);
                        int leftChildRightHeight = getHeight(leftChild.right);
                        
                        //If n's left child is left heavy or balanced ( Left - Left case)
                        
                        if(leftChildLeftHeight >= leftChildRightHeight) {

                                n = rightRotate(n);
                        
                        }
                        
                        else { //Left - Right case
                        
                                n.left = leftRotate(n.left);
                                n = rightRotate(n);
                        }
                }
                
                //Node n where violation has occurred is right heavy
                else if( (rightChildHeight > leftChildHeight)  && ( (rightChildHeight - leftChildHeight) > 1 ) ) {
                
                        Node rightChild = n.right;
                        int rightChildRightHeight = getHeight(rightChild.right);
                        int rightChildLeftHeight = getHeight(rightChild.left);
                        
                        //If n's right child is right heavy or balanced (Right - Right case)
                        
                        if( rightChildRightHeight >= rightChildLeftHeight) {
                        
                                n = leftRotate(n);
                        
                        }
                        
                        else { //Right - Left case
                        
                                n.right = rightRotate(n.right);
                                n = leftRotate(n);
                        }
                        
                
                
                }
                
                return n;                              
        }
       
       //Right rotate a Node n
       //Look at examples in the references at the very top
       
       private Node rightRotate(Node n) {
       
                Node leftNode = n.left;
                n.left = leftNode.right;
                leftNode.right = n;
                
                setNodeHeight(n);
                setNodeHeight(leftNode); //this cannot be n.left

                return leftNode;
       
       }
       
       //Left rotate a Node n
       //Look at examples in the references at the very top
       
       private Node leftRotate(Node n) {
       
                Node rightNode = n.right;
                n.right = rightNode.left;
                rightNode.left = n;
                
                setNodeHeight(n);
                setNodeHeight(rightNode); //this cannot be n.right
                
                return rightNode;        
       
       }
       
       public void insert(T data) {
        
                root = insert(root, data);
        
        }   
       
        private Node insert(Node root, T data) {
        
                if(root == null)
                        return new Node(null,null,0,data);
                        
                else if( root.data.compareTo(data) > 0 ) {
                
                        root.left = insert(root.left, data);
                        setNodeHeight(root);
                        //Balancing code
                        root = restoreBalance(root);
 
                }
                
                else if(root.data.compareTo(data ) < 0 ) {
                
                        root.right = insert(root.right, data);
                        setNodeHeight(root);
                        //Balancing code
                        root = restoreBalance(root);
               }                           

                //Don't put balancing code here, because if the inserted data is equal to a node data, there is no balancing needed
                return root;                        
 
        }
        
        
        public void delete(T data) {
        
                this.root = delete(this.root, data);
        
        }
        
        private Node delete(Node root , T data) {
        
                if(root == null)
                        return root;

                else if(root.data.compareTo(data) > 0) {
                
                        root.left = delete(root.left, data);
                        setNodeHeight(root); //Adjust height and restore balance
                        root = restoreBalance(root);
                }
                
                else if(root.data.compareTo(data) < 0 ) {
                
                        root.right = delete(root.right, data);
                        setNodeHeight(root); //Adjust height and restore balance
                        root = restoreBalance(root);
                }
                
                else {

                        if(root.right == null)
                                return root.left;

                        else if( root.left == null)
                                return root.right;
                                
                        else {
                                if(root.right.left == null) {
                                        root.right.left = root.left;

                                        //Adjust height of root.right
                                        setNodeHeight(root.right);
                                        
                                        // Also restore balance on root.right and the returned node will be the new node to be sent back
                                        root = restoreBalance(root.right);

                                }
                                
                                else {

                                        Node n = root.right.left;

                                        root.right.left = deleteHelper(n);
                                        
                                        //Adjust height and restore balance on root.right
                                        setNodeHeight(root.right);
                                        root.right = restoreBalance(root.right);
                                        
                                        //Now set up the successor
                                        successor.right = root.right;
                                        successor.left = root.left;

                                        //Adjust height and restore balance on successor
                                        
                                        setNodeHeight(successor);
                                        root = restoreBalance(successor);
                                        
                                                                                
                                }                                                                                               
                        }
                }
                
                return root;            
        }
        
        //This method finds the successor ( for Hibbard's deletion) and also recursively fixes the height for all Nodes beginning from successor's parent
        private Node deleteHelper(Node n) {
                
                if(n.left == null) {
                
                        this.successor = n;
                        return n.right;
                
                }
                
                else {
                
                        n.left = deleteHelper(n.left);
                        setNodeHeight(n);
                        n = restoreBalance(n);
                        
                        return n;
                }
        }
        
        public void levelOrderTraversal() {
        
                if(this.root == null)
                        return;
                        
                ArrayList<Node> al = new ArrayList<Node>();
                al.add(this.root);
                levelOrderTraversal(al);                        
        
        
        }
        
        
        private void levelOrderTraversal(ArrayList<Node> al) {
        
                ArrayList<Node> alNext = new ArrayList<Node>();
                
                for(Node n : al) {
                        
                        if(n != null) {
                                System.out.print(""+ n.data + "   ");
                                alNext.add(n.left);
                                alNext.add(n.right);
                                
                        }
                        
                        else {
                        
                        
                        }                                
                                                        
                        
                }
                
                if(alNext.size() > 0 ) {
                        System.out.println();
                        levelOrderTraversal(alNext);
                        
                }                        
        
        }
        
        
        public static void main(String[] args) {
         
                AVLTree<Integer> avlt = new AVLTree<Integer>();
                int[] intArr  = {1,2,3,4,5,6,7};
         
                for(int i : intArr)
                        avlt.insert(i);
                
                avlt.levelOrderTraversal();
                
                System.out.println();
                
                avlt.delete(4);
                
                
                avlt.levelOrderTraversal();
                
        
        }
        
        
}        
