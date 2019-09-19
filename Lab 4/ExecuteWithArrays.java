public class ExecuteWithArrays {

    /* ChildrenRonde() "removes" every element in sth inteverals until one remains
     * If s is close to or greater than C.length, then time for method completion will
     * increase.
     */
    public static int ChildrenRonde(int[] C, int s) {
        int childrenLeft = C.length;
        int currIndex = 0;
        int count = 1;

            if (s < 1) //prevents infinite runtimes
                return -1;
                
            while(childrenLeft > 0){

                if (currIndex == C.length)
                    currIndex = 0; 

                //count increases only if a child is not out (element value not -1)
                if (C[currIndex] != -1){
                    if (count == s){
                        C[currIndex] = -1;
                        childrenLeft -= 1;
                        count = 0;
                    }  
                    count += 1;
                } 
                currIndex += 1;
                
            }
            return currIndex;
    }
    
    public static void main(String[] args) {
        int size = Integer.valueOf(args[0]);
        int step = Integer.valueOf(args[1]);
        int[] children = new int[size];
        int last = ChildrenRonde(children, step);
        System.out.println(last);
    }
}