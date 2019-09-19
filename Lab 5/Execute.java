

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Execute {

    /* TODO 6: 
     * Method readFamilyIntoTree: 
     * Takes a file name and reads this file with family information, 
     * creates and fills a linked-list-based tree with family member information.
     * Note: Father-line nodes go to the left and Mother-line nodes go to the right
     ****************************************************************************************/
    public static BTree<FamilyMember> readFamilyIntoTree(String filename) throws FileNotFoundException, IOException {
        
        // Read the file to gather information into the array
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        // Create an empty binary tree of Family Members
        BTree<FamilyMember> Tree = new BTree<FamilyMember>();
        
        String fileLine;
        while((fileLine = textReader.readLine()) != null){
            String[] family = fileLine.split(" ");

            //creates FamilyMembers base on file lines and inserst them into BTree
            for (String familyLine : family){
                String[] lineComponents = processLine(familyLine);
                FamilyMember member = new FamilyMember(lineComponents[0], lineComponents[1], Integer.parseInt(lineComponents[2]));
                String directions = Directions(Integer.parseInt(lineComponents[4]));
                System.out.println(directions);
                Tree.insertDataAtLocation(directions, member);
            }

        }
    
        textReader.close();
        
        // Return the resulting filled tree
        return Tree;

    }

    public static BSTree<FamilyMember> readFamilyIntoBSTree(String filename) throws FileNotFoundException, IOException{
                
        // Read the file to gather information into the array
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        // Create an empty binary tree of Family Members
        BSTree<FamilyMember> Tree = new BSTree<FamilyMember>();
        
        String fileLine;
        //creates FamilyMembers based on file lines and inserts them into BSTree
        while((fileLine = textReader.readLine()) != null){
            String[] family = fileLine.split(" ");

            for (String familyLine : family){
                String[] lineComponents = processLine(familyLine);
                FamilyMember member = new FamilyMember(lineComponents[0], lineComponents[1], Integer.parseInt(lineComponents[2]));
                Tree.insert(member);
            }

        }
    
        textReader.close();
        
        // Return the resulting filled tree
        return Tree;

    }
        /****************************************************************************************   
     * Main Method:
     ****************************************************************************************/    
	public static void main(String[] args) throws FileNotFoundException, IOException {
        String filename = args[0];
       
        // Creates a linked-list-based tree directly from reading the file:
        //BTree<FamilyMember> Tree = readFamilyIntoTree(filename);
        BTree<FamilyMember> Tree = readFamilyIntoTree(filename);
        // Prints out the content of the linked-list-based tree:
        Tree.print();
        System.out.println();
        
        System.out.println("Tree size = " + Tree.getSize());
        System.out.println("Tree height = " + Tree.getHeight());
        System.out.println();
        System.out.println();

        System.out.println("BSTree");
        BSTree<FamilyMember> Tree2 = readFamilyIntoBSTree(filename);
        Tree2.inOrderTraversal();
        
        System.out.println("Tree size = " + Tree2.getSize());
        System.out.println("Tree height = " + Tree2.getHeight());
        System.out.println();
        System.out.println();


    }


    
    /************************************************************************************
     * HELPER METHODS: ******************************************************************
     ************************************************************************************/

    /* Method Directions: 
     * Given an integer, which represent the index of a piece of data in an array tree, 
     * this methode figures out what directions in the tree we should take to "plug" the node
     */
    public static String Directions(int i) {
        String directions = "";
        int index = i + 1; 
        //my code
        if (i == 0) directions = "0";
        while (index != 1) {
            if (index % 2 == 1) directions = "R" + directions;
            else directions = "L" + directions;
            index /= 2;    
        }
        System.out.println("Directions for member at index " + i + " is: " + directions);
        return directions;
    }
    
    /* Method processLine:
     * This method is given a String that is one element of the line in the text file for be read.
     * The element is of the following form: <String>-<String>,<int>,<String>
     * Example of such an element: John-Doe,3,LLR
     * It processes this element and produces an array of 4 strings: 
     * [first name, last name, number of siblings, location in the array where it should be stored]
     * In the case of the above example, we would produce the following array: [John, Doe, 3, 8]
     */
    public static String[] processLine(String element) {
        String[] result = new String[5];

        String[] member = element.split(",");
        String[] name = member[0].split("-");
        result[0] = name[0];
        result[1] = name[1];
        result[2] = member[1];
        result[3] = member[2];

        int place = 0;
        if (member[2].equals("0")) place = 0;
        else {
            place = 0;
            while (member[2].length() != 0) {
                if (member[2].charAt(0) == 'F') place = place*2 + 1;
                if (member[2].charAt(0) == 'M') place = place*2 + 2;
                member[2] = member[2].substring(1);   
            }
        }
        
        result[4] = "" + place;
        return result;
    }

    /* Method countLines: 
     * This method takes a file name as a parameter and 
     * returns the number of lines in this file (an int)
     */
    public static int countLines(String filename) throws FileNotFoundException, IOException {
		FileReader fr = new FileReader(filename);
        BufferedReader textReader = new BufferedReader(fr);

        int counter = 0;
        // As long as there is something to read in the file...
        while (textReader.ready()) {
            // we increase our line counter
            counter++;
            // read the line and move to the next to check if there is something to read (the while condition)
            textReader.readLine();   
        }
        
        textReader.close();
        return counter;
    }


}