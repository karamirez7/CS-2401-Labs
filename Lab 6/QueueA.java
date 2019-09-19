public class QueueA{
    int head = 0;
    int tail = 0;
    int size = 0;
    int[] queue= new int[1];

    /****Constructors****/
    QueueA(int maxSize){
        queue = new int[maxSize];
    }

    public int getSize(){
        return size;
    }

    /* enqueue() takes an integer and adds the value
     * to the front of the queue if size is not full 
     */
    public void enqueue(int data){
        if (size != queue.length){
            queue[tail % queue.length] = data;
            tail += 1;
            size += 1;
        }
        else{
            System.out.printf("Queue is full. %d not added\n", data);
        }
    }

    /* dequeue() "removes" the front value of the queue and returns it
     * if called on empty queue, MIN_VALUE is returned
     */ 
    public int dequeue(){
        if (size != 0){
            int removedVal = queue[head];
            head = (head + 1) % queue.length;
            size -= 1;

            if (size == 0) clear();
            
            return removedVal;
        }
        else{
            System.out.println("Attempted dequeue on empty queue. Returned MIN_VALUE");
            return Integer.MIN_VALUE;
        }
    }

    //it is expected for peek to not be used on empty queue
    public int peek(){
        if (size != 0) return queue[head];
        System.out.println("Attempted peek on empty queue. Returned MIN_VALUE");
        return Integer.MIN_VALUE;
    }

    public void clear(){
        queue = new int[queue.length];
        size = 0;
        head = 0;
        tail = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == queue.length;
    }
}