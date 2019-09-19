import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class sudoku{
    
    /* readSudoku() reads a file of the given name and returns a 9x9 2Darray of integers
     * the program will terminate intentionally if the file is not formatted correctly
    */
    public static int[][] readSudoku(String fileName){
        int[][] sudokuBoard = new int[9][9];
            try{ 
                BufferedReader inFile = new BufferedReader(new FileReader(fileName));

                //reads a formated file up to 9 lines and 9 seperated integers
                for (int i = 0; i < 9; i++){
                    String fileLine = inFile.readLine();
                    String[] numbers = (fileLine.split(" ", 10));
                    for (int k = 0; k < 9; k++){
                        sudokuBoard[i][k] = Integer.parseInt(numbers[k]);
                    }
                }   
                inFile.close();
            }
            catch(FileNotFoundException f){
                System.out.println("\nSorry, that file can not be found.");
                System.out.println("Please restart the program with a different file name.\n");
                System.exit(0);
            }
            catch(NumberFormatException | IOException | NullPointerException e){
                System.out.println("\nThat file doesnt seem right.");
                System.out.println("Make sure your file is a 9 by 9 grid of numbers(1-9) seperated by a single space.");
                System.out.println("Please modify the file and restart the program.\n");
                System.exit(0);
            }
        return sudokuBoard;
    }

    /* printSudoku() outputs a formatted table of numbers
     * it is expected that the provided 2D array is of size 9x9
    */
    public static void printSudoku(int[][] sudokuPuzzle){
        System.out.println();
        for (int i = 0; i < sudokuPuzzle.length; i++){
            for (int j = 0; j < sudokuPuzzle[0].length; j++){
                System.out.print(sudokuPuzzle[i][j]);
                if (j != sudokuPuzzle[0].length - 1)
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /* checkRowAndCol() expects that the given array is of size 9x9
     * the method may throw an ArrayIndexOutOfBounds if the 2D array
     * does not contain only numbers 1-9
    */
    public static boolean checkRowAndCol(int[][] sudokuPuzzle){
        for (int i = 0; i < sudokuPuzzle.length; i++){
            int[] testArrayRow = new int[10]; //testArrayRow and testArrayCol uses the values of sudokuPuzzle to 
            int[] testArrayCol = new int[10]; //keep count of how many times a number occurs*/
            for (int k = 0; k < sudokuPuzzle[0].length; k++){
                testArrayRow[sudokuPuzzle[i][k]] += 1;
                testArrayCol[sudokuPuzzle[k][i]] += 1;
                if ( (testArrayRow[sudokuPuzzle[i][k]] > 1) || (testArrayRow[0] > 0) )
                    return false;
                if ( (testArrayCol[sudokuPuzzle[k][i]] > 1) || (testArrayCol[0] > 0) )
                    return false;
            }
        }
        return true;
    }

    /* checkNonet() traverses through a 9x9 2D array as 9 3x3's
     * the method may throw an ArrayIndexOutOfBounds if the 2D array
     * does not contain only numbers 1-9
    */
    public static boolean checkNonet(int[][] sudokuPuzzle){
        for (int puzzleRow = 0; puzzleRow < sudokuPuzzle.length; puzzleRow += 3){
            for (int puzzleCol = 0; puzzleCol < sudokuPuzzle[0].length; puzzleCol += 3){              
                int[] testNonet = new int[10]; /*testNonet uses the values of sudokuPuzzle to 
                                                keep count of how many times a number occurs*/
                for(int subRow = puzzleRow; subRow < puzzleRow + 3; subRow++){
                    for(int subCol = puzzleCol; subCol < puzzleCol + 3; subCol++){
                        testNonet[sudokuPuzzle[subRow][subCol]] += 1; 
                        if ( (testNonet[sudokuPuzzle[subRow][subCol]] > 1) || (testNonet[0] > 0) )
                        return false;   
                    }
                }
            }
        }  
        return true;
    }

    /* checkSudoku() uses helpers checkNonet() and checkRowAndCol()
     * to determine the winning status of a 9x9 sudoku following the rules of the game
    */
    public static boolean checkSudoku(int[][] sudokuPuzzle){
        try{
            if (checkRowAndCol(sudokuPuzzle) && checkNonet(sudokuPuzzle)){
                return true;
            }
        }
        catch(ArrayIndexOutOfBoundsException a){
            System.out.println("Puzzle did not contain numbers 1-9.");
        }
        return false;
    }

    /* resultSudoku() outputs a message based on whether or not the sudoku was completed
    */
    public static void resultSudoku(boolean sudokuResult){
        if (sudokuResult){
            System.out.println("**********************************");
            System.out.println("Congratulations, sudoku completed!");
            System.out.println("**********************************");
        }
        else{
            System.out.println("That aint it chief. Try again.");
        }
    }

    public static void main(String[] args){
        if (args.length != 0){  // first checks if an argument(file name) was provided at the start of the program
            int[][] sudokuBoard = readSudoku(args[0]);
            printSudoku(sudokuBoard);
            resultSudoku(checkSudoku(sudokuBoard));
        }
        else {
            System.out.println("\nDon't forget to enter the name of the file when launching the program. :)");
        }  
    }
}