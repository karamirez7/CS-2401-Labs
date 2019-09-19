import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class sortingLab {

    /* mergeSort() recursively divides a given array by 2 until 1 element remains.
     * mergeSort() then uses the helper method merge() to sort the arrays created 
     * by each split in ascending order
     * This method is based on the code provided on Zybooks for merge sort
     */
    public static void mergeSort (int[] numbers, int low, int high) {
        int midPoint;

        if (low < high) {
            midPoint = (low + high) / 2;

            mergeSort(numbers, low, midPoint);
            mergeSort(numbers, midPoint + 1, high);

            merge(numbers, low, midPoint, high);
        }
    }

    /* merge() sorts arrays by comparing 2 values starting from the beginning and midpoint of the array
     * the lower value of each comparison gets assigned to the respective position of the numbers array
     */
    public static void merge (int[] numbers, int low, int midPoint, int high){
        int mergedSize = high - low + 1;
        int mergePos = 0;
        int leftPos = low;
        int rightPos = midPoint + 1;
        int[] mergedNumbers = new int[mergedSize];

        while ((leftPos <= midPoint) && (rightPos <= high)){
            if (numbers[leftPos] <= numbers[rightPos]){
                mergedNumbers[mergePos] = numbers[leftPos];
                leftPos += 1;
            }
            else{
                mergedNumbers[mergePos] = numbers[rightPos];
                rightPos += 1; 
            }
            mergePos += 1;
        }

        while (leftPos <= midPoint){
            mergedNumbers[mergePos] = numbers[leftPos];
            leftPos += 1;
            mergePos += 1;
        }

        while (rightPos <= high){
            mergedNumbers[mergePos] = numbers[rightPos];
            rightPos += 1;
            mergePos += 1;
        }

        for (mergePos = 0; mergePos < mergedSize; mergePos++){
            numbers[low + mergePos] = mergedNumbers[mergePos];
        }
    }
        
    /* quickSort() recursively splits an array into a lower and higher partition 
     * through the helper method partition()
     * the method follows the quicksort algorithm found in Zybooks and will sort in ascending order
     */ 
    public static void quickSort (int[] numbers, int low, int high) {
        int partitionedHigh;

        if (low >= high){
            return;
        }

        partitionedHigh = partition(numbers, low, high);

        quickSort(numbers, low, partitionedHigh);
        quickSort(numbers, partitionedHigh + 1, high);

    }

    /* partition() will swap values in the given array so that integers less than or
     * equal to the value at the midPoint is on the left and integers greater than or
     * equal to that value on the right
     */
    public static int partition (int[] numbers, int low, int high){
        int midPoint = (low + high) / 2;
        int pivot = numbers[midPoint];
        boolean done = false;

        while (!done){
            while(numbers[low] < pivot){
                low += 1;
            }

            while(numbers[high] > pivot){
                high -= 1;
            }

            if (low >= high){
                done = true;
            }
            else{
                int temp = numbers[low];
                numbers[low] = numbers[high];
                numbers[high] = temp;
                low += 1;
                high -= 1;
            }
        }
        return high;

    }
    
    /* insertionSort() sorts a 1D array of numbers in ascending order
     */
    public static void insertionSort (int[] numbers) {
        int sortedIndex;
        int index;
        int temp;

        //this loop represents what has been sorted so far in the 1D array
        for (sortedIndex = 1; sortedIndex < numbers.length; sortedIndex++){
            index = sortedIndex;
            //values in the "unsorted" part of the array are compared to the values in
            //the "sorted" part and are swapped until placed in their proper position
            while (index > 0 && numbers[index] < numbers[index - 1]){
        
                temp = numbers[index];
                numbers[index] = numbers[index - 1];
                numbers[index - 1] = temp;
                index -= 1;
            }
        }
    }

    /* this version of insertionSort() is slightly modified to work 
     * with the proposed algorithm of the lab and quickInsertion()
     * the new parameters low and high are used to apply insertion sort at 
     * a specific range of a given array
     */ 
    public static void insertionSort (int[] numbers, int low, int high)  {
        int sortedIndex;
        int index;
        int temp;

        for (sortedIndex = low + 1; sortedIndex <= high; sortedIndex++){
            index = sortedIndex;
            while (index > low && numbers[index] < numbers[index - 1]){
        
                temp = numbers[index];
                numbers[index] = numbers[index - 1];
                numbers[index - 1] = temp;
                index -= 1;
            }
        }
    }
    
    /* quickInsertion() combines quicksort and insertion sort to sort
     * a given array of integers in ascending order
     * the method partions values until an array of size 10 is reached 
     * once the desired size is reached, the numbers are sorted using insertion sort
     */
    public static void quickInsertion (int[] numbers, int low, int high) {
        int partitionedHigh;

        if (high - low < 10){// base case: an array of size 10 or less is reached
            insertionSort(numbers, low, high); 
            return;
        }

        partitionedHigh = partition(numbers, low, high);

        //recursively partitions values into 2 halves
        quickInsertion(numbers, low, partitionedHigh);
        quickInsertion(numbers, partitionedHigh + 1, high);
    }

    /* reads a line of numbers from a given file and returns it as an array
     * this method assumes all values in the file are seperated by a single space and
     * are on a single line 
     */ 
    public static int[] readNumbers(String fileName){
        int[] numbers = {};
        String[] fileNums;
        String fileLine;

            try{ 
                BufferedReader inFile = new BufferedReader(new FileReader(fileName));
                fileLine = inFile.readLine();
                fileNums = (fileLine.split(" "));
                int[] numbersRead = new int[fileNums.length];

                // parses the values read from the file to integers
                for (int i = 0; i < fileNums.length; i++){ 
                    numbersRead[i] = Integer.parseInt(fileNums[i]);
                }
       
                numbers = numbersRead;
                inFile.close();
            }
            catch(FileNotFoundException f){
                System.out.println("\nSorry, that file can not be found.");
                System.out.println("Please restart the program with a different file name.\n");
                System.exit(0);
            }
            catch(IOException i){
                System.out.println("Sorry, that file does not seem right");
            }

        return numbers;
    }

    /***********************************************************************/
    /*****Everything below are methods used for testing and gathering data**/
    /***********************************************************************/

    //createRandomArray() creates an array of a given size with random values ranging from 1-9
    public static int[] createRandomArray(int size){
        int[] randomArray = new int[size];

        for (int i = 0; i < size; i++){
            randomArray[i] = (int) (Math.random() * 10);
        }

        return randomArray;
    }

    //printArray() prints out the values of a provided 1D int array
    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /* this method tests mergeSort() and calculates the time 
     * taken to be executed using System.nanoTime()
     * a copy of the provided array is created so the original is not modified
    */
    public static void mergeSortTest(int[] numbers, int low, int high){
        long timeStart, timeEnd;
        int[] numsCopy = new int[numbers.length];
        System.arraycopy(numbers, 0, numsCopy, 0, numbers.length);
        
        System.out.println("\n--------The array given to mergeSort()--------\n");
        printArray(numsCopy);

        timeStart = System.nanoTime();
        mergeSort(numsCopy, low, high);
        timeEnd = System.nanoTime();

        System.out.println("\n--------The array after mergeSort()--------\n");
        printArray(numsCopy);
        System.out.println("\nmergeSort took " + (timeEnd - timeStart) + "ns to be completed.\n\n\n");

    }

    /* this method tests quickSort() and calculates the time 
     * taken to be executed using System.nanoTime()
     * a copy of the provided array is created so the original is not modified
    */
    public static void quickSortTest(int[] numbers, int low, int high){
        long timeStart, timeEnd;
        int[] numsCopy = new int[numbers.length];
        System.arraycopy(numbers, 0, numsCopy, 0, numbers.length);

        System.out.println("\n--------The array given to quickSort()--------\n");
        printArray(numsCopy);

        timeStart = System.nanoTime();
        quickSort(numsCopy, low, high);
        timeEnd = System.nanoTime();

        System.out.println("\n--------The array after quickSort()--------\n");
        printArray(numsCopy);
        System.out.println("\nquickSort took " + (timeEnd - timeStart) + "ns to be completed.\n\n\n" );
        
    }

    /* this method tests insertionSort() and calculates the time 
     * taken to be executed using System.nanoTime()
     * a copy of the provided array is created so the original is not modified
    */
    public static void insertionSortTest(int[] numbers){
        long timeStart, timeEnd;
        int[] numsCopy = new int[numbers.length];
        System.arraycopy(numbers, 0, numsCopy, 0, numbers.length);

        System.out.println("\n--------The array given to insertionSort()--------\n");
        printArray(numsCopy);

        timeStart = System.nanoTime();
        insertionSort(numsCopy);
        timeEnd = System.nanoTime();

        System.out.println("\n--------The array after insertionSort()--------\n");
        printArray(numsCopy);
        System.out.println("\ninsertionSort took " + (timeEnd - timeStart) + "ns to be completed.\n\n\n"); 
    }

    /* this method tests auickInsertion() and calculates the time 
     * taken to be executed using System.nanoTime()
     * a copy of the provided array is created so the original is not modified
    */
    public static void quickInsertionTest(int[] numbers, int low, int high){
        long timeStart, timeEnd;
        int[] numsCopy = new int[numbers.length];
        System.arraycopy(numbers, 0, numsCopy, 0, numbers.length);

        System.out.println("\n--------The array given to quickInsertion()--------\n");
        printArray(numsCopy);

        timeStart = System.nanoTime();
        quickInsertion(numsCopy, low, high);
        timeEnd = System.nanoTime();

        System.out.println("\n--------The array after quickInsertion()--------\n");
        printArray(numsCopy);
        System.out.println("\nquickInsertion took " + (timeEnd - timeStart) + "ns to be completed.") ;

    }
    
    /* testSortingMethods() takes a 1D array of integers and 
     * and collectively tests mergeSort(), quickSort(), insertionSort()
     * and quickInsertion() 
     */
    public static void testSortingMethods(int[] numbers){
        int low = 0; //initial position to sort
        int high = numbers.length - 1; //final position to sort

        // Tests the 4 sorting methods and prints the amount of time each method takes
        mergeSortTest(numbers, low, high);
        quickSortTest(numbers, low, high);
        insertionSortTest(numbers);
        quickInsertionTest(numbers, low, high);

    }

    // Main method
    public static void main (String[] args) {
        int[] testArray = {};

        if (args.length != 0){  // first checks if an argument(file name) was provided at the start of the program
            testArray = readNumbers(args[0]);
        }
        else {// else a randomly generated array of a given size will be created
            try{
                Scanner in = new Scanner(System.in);

                System.out.println("No file name entered at the start of the program.");
                System.out.print("Enter the size for an array of random numbers: ");
                testArray = createRandomArray(in.nextInt());
                in.close();
            }
            catch(InputMismatchException i){// occurs if an integer is not entered
                System.out.println("Not an integer. Terminating program.");
                System.exit(0);
            }
        }  

        testSortingMethods(testArray);
    }
}