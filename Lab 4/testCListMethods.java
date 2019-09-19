public class testCListMethods{
    //tests all the methods of CList.java
    public static void main(String[] args){
        CList<Integer> CList_test = new CList<Integer>();
        Node<Integer> nodeTest = new Node<Integer>(1);

        System.out.println("add at start 1 // expect 1");
        CList_test.addAtStart(nodeTest);
        CList_test.print();
        System.out.println("Size: " + CList_test.getSize());

        System.out.println();

        System.out.println("add data at end 2, remove start // expect 2");
        CList_test.addDataAtEnd(2);
        CList_test.removeStart();
        CList_test.print();
        System.out.println("Size: " + CList_test.getSize());

        System.out.println();

        System.out.println("add at start 1, add at end 3, add at location(1) 8 // expect 1,8,2,3");
        CList_test.addAtStart(new Node<Integer>(1));
        CList_test.addAtEnd(new Node<Integer>(3));
        CList_test.addAtLocation(new Node<Integer>(8), 1);
        CList_test.print();
        System.out.println("Size: " + CList_test.getSize());

        System.out.println();

        System.out.println("addmultiatend 4-5, addMultiatlocation(2) 9-10, addmultiatstart -1-0 // expect -1,0,1,8,9,10,2,3,4,5");
        Node<Integer> i1 = new Node<Integer>(4);
        Node<Integer> i2 = new Node<Integer>(5);
        Node<Integer> i3 = new Node<Integer>(9);
        Node<Integer> i4 = new Node<Integer>(10);
        Node<Integer> i5 = new Node<Integer>(-1);
        Node<Integer> i6 = new Node<Integer>(0);
        i1.setNext(i2);
        i3.setNext(i4);
        i5.setNext(i6);
        CList_test.addMultiAtEnd(i1);
        CList_test.addMultiAtLocation(i3, 2);
        CList_test.addMultiAtStart(i5);
        CList_test.print();
        System.out.println("Size: " + CList_test.getSize());

        System.out.println();

        System.out.println("removestart, removelast, removelocation(4) // expect 0,1,8,9,2,3,4");
        CList_test.removeStart();
        CList_test.removeLast();
        CList_test.removeLocation(4);
        CList_test.print();
        System.out.println("Size: " + CList_test.getSize());

        System.out.println();

        System.out.println("removenode (obj 9), removeLocation(2), childrenRonde(2) // expect 2 from list: 0,1,2,3,4");
        CList_test.removeNode(i3);
        CList_test.removeLocation(2);
        CList_test.ChildrenRonde(2);

        System.out.println();

        System.out.println("set start(1234) // expect 1234");
        CList_test.setStart(new Node<Integer>(1234));
        CList_test.print();
        System.out.println("Size: " + CList_test.getSize());

        System.out.println("\nend");
    }
}