public class QueueL{
    private int size;
    private Node head;
    private Node tail;

    /****Constructors****/
    QueueL(){
        size = 0;
    }

    QueueL(Node head){
        this.head = tail = head;
        size = 1;
    }

    public int getSize(){
        return size;
    }

    /* enqueue() takes an integer and creates a Node that will be added
     * to the end of the queue
     */
    public void enqueue(int data){
        Node newNode = new Node(data);

        if (head == null){
            head = newNode;
            tail = head;
        }
        else{
            tail.setNext(newNode);
            tail = newNode;
        }

        size += 1;
    }

    /*dequeue() removes the current head and sets the head 
     * to the next value in the queue
     */
    public int dequeue(){
        if (head == null){
            System.out.println("Attempted dequeue on empty queue. Returned MIN_VALUE");
            return Integer.MIN_VALUE;
        }
        
        int dataRemoved = head.getData();
        head = head.getNext();
        size -= 1;

        if (size == 0) tail = null;

        return dataRemoved;

    }

    // it is expected for peek to not be used on empty queue
    public int peek(){
        if (head == null) {
            System.out.println("Attempted peek on empty queue. Returned MIN_VALUE");
            return Integer.MIN_VALUE;

        }
        return head.getData();
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}