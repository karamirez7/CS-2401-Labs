public class StackA{
    private int size = 0;
    private int[] stack = new int[1];

    /****Constructors****/
    public StackA(int size){
        stack = new int[size];
    }

    public int getSize(){
        return size;
    }

    /* push() adds the value
     * to the stack if it is not full
     */
    public void push(int value){
        if (size != stack.length){
            stack[size] = value;
            size += 1;
        }
        else{
            System.out.printf("Stack is full. %d not added.\n", value);
        }
            
    }

    /* pop() "removes" the top value of the stack by
     * setting the array value to 0
    */
    public void pop(){
        if (size != 0){
            stack[size - 1] = 0;
            size -= 1;
        }
        else{
            System.out.println("Attempted pop on empty stack.");
        }
    }

    // it is expected for peek to not be used on empty queue
    public int peek(){
        if (size != 0) return stack[size - 1];
        else {
            System.out.println("Attempted peek on empty stack. Returned MIN_VALUE");
            return Integer.MIN_VALUE;

        }
    }

    public void clear(){
        stack = new int[stack.length];
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == stack.length;
    }
}