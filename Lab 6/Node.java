public class Node{
    private int data;
    private Node next;

    Node(int data){
        this.data = data;
    }

    public void setNext(Node node){
        next = node;
    }

    public void setData(int data){
        this.data = data;
    }

    public Node getNext(){
        return next;
    }

    public int getData(){
        return data;
    }

    public String toString(){
        return Integer.toString(data);
    }
    

}