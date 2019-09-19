/*************************************************************************
 * This class is for a circular linked list. You will notice that it has 
 * the same attributes as in a regular linked list. What will be different 
 * though is the way we manipulate the list.
 * Also, instead of calling a node the start, we call it start since there 
 * is no more start of the list
 *************************************************************************/ 

public class CList<T> {
    private Node<T> start;
    private int size;
    
    // CONSTRUCTORS ******************************************************
    public CList() {}
    
    // Pre-condition: N is a single node
    public CList(Node<T> N) { 
        setStart(N);
        size = 1;
    }
    
    // SETTERS ***********************************************************
    public void setStart(Node<T> N) {
        start = N;
        N.setNext(start);
    }
    
    // no setter for the size since it is a consequence of other methods
        
    // GETTERS ***********************************************************
    public Node<T> getStart(){
        return start;
    }

    public int getSize(){
        return size;
    }
    
    // OTHER METHODS *****************************************************
    
    // ADDING NODES OR SEQUENCES OF NODES ////////////////////////////////
    /* Method addAtEnd: 
     *      Takes a node N 
     *      Adds it to the circle "at the end", i.e., just before start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtEnd(Node<T> N) {
        if (start == null){
            setStart(N);
        }
        else{
            Node<T> current = start;
            while(current.getNext() != start){
                current = current.getNext();
            }
            N.setNext(start);
            current.setNext(N);
        }
        this.size += 1;
    }
    
    /* Method addDataAtEnd: 
     *      Takes data of type T 
     *      Creates a node that contains T
     *      Adds it to the circle "at the end", i.e., just before start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addDataAtEnd(T data) {
        Node<T> newNode = new Node<T>(data);
        addAtEnd(newNode);
    }
    
    /* Method addAtStart: 
     *      Takes a node N 
     *      Adds it to the circle just before start and makes it the new start.
     *      Notes: 1/ take into account when the list is null
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtStart(Node<T> N) {
        if (start == null){
            setStart(N);
        }
        else{
            Node<T> current = start;
            while (current.getNext() != start){
                current = current.getNext();
            }
            N.setNext(start);
            current.setNext(N);
            start = N;
        } 
        size += 1;
    }
    
    /* Method addAtLocation: 
     *      Takes a node N and an integer n
     *      Adds N to the circle just after the n-th node in the circle
     *          (where start is the first node)
     *      Notes: 1/ take into account when the list is null or has 
     *          less than n nodes
     *          2/ Make sure to update the size
     ********************************************************************/
    public void addAtLocation(Node<T> N, int n) {
        if (n > this.size  || n < 0) { //invalid locations
        }
        else if (n == 0){
            addAtStart(N);
        }
        else{
            Node<T> curr = start;
            for (int i = 1; i < n; i++){
                curr = curr.getNext();
            }
            N.setNext(curr.getNext());
            curr.setNext(N);
            this.size += 1;
        }
    }   
    
    /* Method addMultiAtEnd: 
     *      Similar to addAtEnd
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtEnd(Node<T> N) {
        int amountAdded = 1;
        Node<T> lastNode  = N;

        while(lastNode.getNext() != null){
            lastNode = lastNode.getNext();
            amountAdded += 1;
        }

        if (start == null){
            start = N;
            lastNode.setNext(start);
            this.size = amountAdded;
        }
        else{
            Node<T> current = start;
            while(current.getNext() != start){
                current = current.getNext();
            }
            lastNode.setNext(start);
            current.setNext(N);
            this.size += amountAdded;
        }
    }
    
    /* Method addMultiAtStart: 
     *      Similar to addAtStart
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtStart(Node<T> N) {
        int amountAdded = 1;
        Node<T> lastNode  = N;

        while(lastNode.getNext() != null){
            lastNode = lastNode.getNext();
            amountAdded += 1;
        }

        if (start == null){
            start = N;
            lastNode.setNext(start);
            this.size = amountAdded;
        }
        else{
            Node<T> current = start;
            while(current.getNext() != start){
                current = current.getNext();
            }
            lastNode.setNext(start);
            current.setNext(N);
            this.size += amountAdded;
            start = N;
        }
    }
    
    /* Method addMultiAtLocation: 
     *      Similar to addAtLocation
     *      But adds N along with its possible sequence of following nodes
     ********************************************************************/
    public void addMultiAtLocation(Node<T> N, int n) {
        int amountAdded = 1;
        Node<T> lastNode  = N;

        while(lastNode.getNext() != null){
            lastNode = lastNode.getNext();
            amountAdded += 1;
        }

        if (n >= this.size  || n < 0) { // invalid locations
        }
        else if (n == 0){
            addAtStart(N);
        }
        else{
            Node<T> curr = start;
            for (int i = 1; i < n; i++){
                curr = curr.getNext();
            }
            lastNode.setNext(curr.getNext());
            curr.setNext(N);
            this.size += amountAdded;
        }
    }   
    
    // REMOVING NODES OR SEQUENCES OF NODES ////////////////////////////////

    /* Method removeStart: 
     *      Removes the start node
     *      Makes the next node in sequence the start
     *  Notes: 1/ take into account the case where the list is empty or 
     *      has only one node
     *      2/ do not forget to update the value of size
     ********************************************************************/
    public void removeStart() {
        if (start == null){
            //do nothing
        }
        else if (size == 1){
            start = null;
            this.size = 0;
        }
        else{
            Node<T> current = start;
            while(current.getNext() != start){
                current = current.getNext();
            }
            current.setNext(start.getNext());
            start = start.getNext();
            this.size -= 1;
        }
    }
    
    /* Method removeLast: 
     *      Removes the node just before start in the circle (i.e., the last node)
     *  Notes: 1/ take into account the case where the list is empty or 
     *      has only one node
     *      2/ do not forget to update the value of size
     ********************************************************************/
    public void removeLast() {
        if (start == null){
            //do nothing
        }
        else if (size == 1){
            start = null;
            this.size -= 1;
        }
        else{
            Node<T> temp = start;
            while(temp.getNext().getNext() != start){
                temp = temp.getNext();
            }
            temp.setNext(start);
            this.size -= 1;
        }
    }
    
    /* Method removeNode: 
     *      Takes a node N
     *      Removes this node N from the list if it is in the list
     *  Notes: 1/ take into account the case where N is not in the list,
     *      or the list is empty 
     *      2/ do not forget to update the value of size if relevant
     ********************************************************************/
    public void removeNode(Node<T> N) {
        if (start == null){
        }
        else if (start == N){
            removeStart();
        } 
        else{
            Node<T> current = start;
            while (current.getNext() != start){
                if (current.getNext() == N){
                    current.setNext(current.getNext().getNext());
                    this.size -= 1;
                }
                current = current.getNext();
            }
        }
    }
    
    /* Method removeLocation: 
     *      Takes an integer n
     *      Removes the n-th node from the list if there is such a node
     *  Notes: 1/ take into account the case there are fewer nodes than n
     *      in the list
     *      2/ do not forget to update the value of size if relevant
     ********************************************************************/
    public void removeLocation(int n) {
        if (n < 0 || n > this.size-1){ //invalid locations
        }
        else if (n == 0){
            removeStart();
        }
        else{
            Node<T> current = start;
            for(int i = 1; i < n; i++){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            this.size -= 1;
        }
    }
    
    // PRINTING THE CONTENT OF A CLIST //////////////////////////////////
    /* Method print: 
     *      Prints every element of the circle once
     *      Prints "There is nothing to print" if the list is empty
     ********************************************************************/
    public void print() {
        if (start == null){
            System.out.println("There is nothing to print");
        }
        Node<T> curr = start;
        do {
            curr.printNode();
            curr = curr.getNext();
        } while (curr != start);
    }
    
    /*******************************************************************/
    /* Method: ChildrenRonde: 
     * It applies to a circular linked list and takes an integer s 
     *      (given a CList L, you will call it as L.ChildrenRonde(s)). 
     * It successively removes every s-th child from the circle until 
     *      only one child is left. 
     * It does not return anything, but it directly modifies the list 
     *      of children, which contains only one child at the end of 
     *      the game, the winner. 
     * NOTE: make sure to handle special cases like when the list may
     *      be empty
     * ALSO: if the list contains only one element, print out:
     *      "There is only one element remaining: "
     *      and then print the node (its content) using the appropriate
     *      method
     *******************************************************************/
    public void ChildrenRonde(int s) {
        if (this.size == 0){
            System.out.println("Cannot play the game without any children :(");
        }
        else if(s < 1){
            System.out.println("Step size cannot be less than 1"); 
        } 
        else{
            Node<T> current = start;
            while (this.size != 1){

                for (int i = 1; i < s; i++){ // traverse in CList for s amount
                    current = current.getNext();
                }
                // saves reference to the node after current (since it will be removed)
                // so current position is not lost
                Node<T> temp = current.getNext(); 
                removeNode(current);
                current = temp;

            }

            System.out.print("There is only one element remaining: ");
            start.printNode();
            System.out.println();
        }
    }

}