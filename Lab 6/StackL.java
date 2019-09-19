public class StackL{
    private Node top;
    private int size;

    /****Constructors****/
    public StackL(){
        size = 0;
    }

    public StackL(Node node){
        top = node;
        size += 1;
    }

    public int getSize(){
        return size;
    }

    /* creates a node that will be added to the top of
     * the stack. There is no max value to how many can be added.
     */
    public void push(int data){
        Node newNode = new Node(data);
        
        if (size == 0){
            top = newNode;
        }
        else{
            newNode.setNext(top);
            top = newNode;
        }

        size += 1;
        
    }
    /* pop() remvoes the current top and sets top to the next value
     */
    public void pop(){
        if (size != 0){
            top = top.getNext();
            size -= 1;
        }
        else{
            System.out.println("Attempted pop on empty stack");
        }
    }

    // it is expected for peek to not be used on empty queue
    public int peek(){
        if (top == null){
            System.out.println("Attempted peek on empty stack. Returned MIN_VALUE");
            return Integer.MIN_VALUE;
        }
        return top.getData();
    }

    public void clear(){
        top = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
