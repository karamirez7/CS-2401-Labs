public class BSTree<T extends Comparable<T>> extends BTree<T> {
 
    // A Binary Search Tree has no additional attributes compared to a Binary Tree
    
    // Constructors ****************************************************************
    public BSTree() {
        super();
    }
    
    public BSTree(BTNode<T> N) {
        super(N);
    }
    
    // Other method ***************************************************************
    /* Method insert:
     * Takes data to be added to the binary search tree
     * Insert a node that contains this data at the correct position in the BST
     */
    public void insert(T data) {

        if(super.getRoot() == null){
            super.setRoot(new BTNode<T>(data));
        }
        BTNode<T> current = super.getRoot();
        BTNode<T> parent = null;
        int side = 0; //side to insert. left is negative, right is positive

        while(current != null){
            if (current.getData().compareTo(data) > 0){
                parent = current;
                current = current.getLeft();
                side = -1;
            }
            else if (current.getData().compareTo(data) < 0){
                parent = current;
                current = current.getRight();
                side = 1;
            }
            else break; //node is the same, no duplicates.
        }

        if (side < 0) parent.setLeft(new BTNode<T>(data));
        else if (side > 0) parent.setRight(new BTNode<T>(data));
        else {}// it was a duplicate

        resetHeight();
        resetSize();

    }

}