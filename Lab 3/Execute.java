import java.io.*;

public class Execute{

    /* A method that takes the name of the file containing
     * the inventory of a bike shop and returns it as 1D Bike array
     * readInventoryFromFile() uses helper methods trimLineForInventory() and
     * buildBike() to create a bike object with attributes specified in each line
     * lines not formatted correctly will be ignored
     */
    public static Bike[] readInventoryFromFile(String filename){

        try{
            BufferedReader fileIn = new BufferedReader(new FileReader(filename));
            Bike[] bikes = new Bike[getFileLines(filename)];// it is expected each line in the file is a Bike
            String fileLine;

            for (int i = 0; i < bikes.length; i++){
                fileLine = fileIn.readLine();

                try {
                    String[] bikeArguments = trimLineForInventory(fileLine);
                    bikes[i] = buildBike(bikeArguments);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException a) {
                    // a bike could not be built because the file is not formatted correctly
                    // process will continue to the next line
                }
            }

            fileIn.close();
            return bikes;
        }catch(FileNotFoundException f){
            System.out.println("The inventory file could not be found. Thats a big oof");
            System.out.println("The program will end because you can't have a bike shop without an inventory :(");
            System.exit(0);
        }
        catch(IOException i){
            System.out.println("There was a problem reading the inventory file.");
            System.out.println("Please make sure the file is formatted correctly.");
            System.exit(0);
        }  
        // It is expected that this will never return 
        //as the program exits if there is a problem with the file
        return null;
    }

    /* A method that takes the name of the file containing
     * the clients requests for bikes and returns the profit of the purchases based
     * on the array of Bikes provided
     * updateInventoryBasedOnClientsRequests() uses helper methods trimLineForRequest(),
     * getBikeFromInventory() and processRequest() 
     * lines not formatted correctly will be ignored
     */
    public static double updateInventoryBasedOnClientsRequests(Bike[] inventory, String filename){
 
        try{ 
            double profitMade = 0.0;
            String fileLine;
            BufferedReader fileIn = new BufferedReader(new FileReader(filename));
            PrintWriter fileOut = new PrintWriter(new File("filename.missing.txt"));
        
            while((fileLine = fileIn.readLine()) != null){ 
                try{
                    String[] bikeRequest = trimLineForRequest(fileLine);
                    Bike bike = getBikeFromInventory(bikeRequest[1], bikeRequest[2], inventory);
                    profitMade += processRequest(bike, bikeRequest, fileOut);
                }
                catch(NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException a){
                    // occurs if a line in the clientrequest file is not formatted correctly
                    // process will continue to the next line
                }
            }

            fileIn.close();
            fileOut.close();
            return profitMade;
            
        }catch(FileNotFoundException f){
           System.out.println("The clients file could not be found.");
           System.out.println("...but at least we still have a bike shop :)"); 
        }
        catch(IOException i){
            System.out.println("There was a problem with the client's file.");
            System.out.println("Please make sure the file is formatted correctly.");
        }

        //returns 0.0 if the clientsrequests file is not processed correctly
        return 0.0;
    }

    /* printInventory() takes an array of Bikes and prints each one using
     * the toString() method of the Bike class
     */
    public static void printInventory(Bike[] bikes){
            for (Bike bike : bikes){
                try{
                    System.out.println(bike.toString());
                }
                catch(NullPointerException n){
                    //This exception ocurrs if a Bike object was never created in the array
                    //as a result of a file line in the inventory not being formatted correctly
                }
            }
            System.out.println();
    }

    /*********************************************************************************/
    /* The following methods are used to assist in reading the file and writing to a file
    /*********************************************************************************/

    /* getFileLines() counts the humber of new lines in a file and returns the value
    */
    public static int getFileLines(String fileName) throws IOException{
        BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
        int fileLines = 0;
        String fileLine;

        while((fileLine = fileIn.readLine()) != null){
            fileLines += 1;
        }

        fileIn.close();
        return fileLines;
    }

    /* trimLineForInventory() removes white space and other unnecessary components
     * to better handle the process of creating a bike object
     */
    public static String[] trimLineForInventory(String fileLine){
        String[] modifiedLine = fileLine.split(",");

        for(int i = 0; i < modifiedLine.length; i++){
            modifiedLine[i] = modifiedLine[i].trim();
        }

        modifiedLine[1] = modifiedLine[1].substring(modifiedLine[1].indexOf(" ") + 1); //removes "model "
        modifiedLine[2] = modifiedLine[2].substring(1); //removes dollar sign in purchasedPrice
        modifiedLine[3] = modifiedLine[3].substring(1); // removes dollar sign in sellingPrice
        
        return modifiedLine;
    }

    /* trimLineForRequest() removes white space and other unnecessary components
     * to better handle what is being asked for in the clients file
     */
    public static String[] trimLineForRequest(String fileLine){
        String[] modifiedLine = fileLine.split(",");

        for(int i = 0; i < modifiedLine.length; i++){
            modifiedLine[i] = modifiedLine[i].trim();
        }

        modifiedLine[1] = modifiedLine[1].substring(modifiedLine[1].indexOf(" ") + 1); // removes "model "

        return modifiedLine;
    }

    /***********************************************************************************/
    /* The following methods are used to help create bike objects and "sell" them 
    /*********************************************************************************/

    /* setBike() takes an array of Strings containing the attributes of a bike object
     * The expected order of the array is:
     * <Bike type> <model> <purchased price> <selling price> <color> <amount> <age range>
     * followed by two extra attributes related to each bike type
     * this method only sets the attributes of the parent class Bike
     */
    public static void setBike(Bike bike, String[] bikeAttributes){
        int purchasedPrice = Integer.parseInt(bikeAttributes[2]);
        int sellingPrice = Integer.parseInt(bikeAttributes[3]);
        int amount = Integer.parseInt(bikeAttributes[5]);

        bike.setModelNumber(bikeAttributes[1]);
        bike.setColor(bikeAttributes[4]);
        bike.setPreferredAgeRange(bikeAttributes[6]);

        // only set these attibutes if they are positive otherwise default is 0
        if (purchasedPrice > 0) bike.setPurchasedPrice(purchasedPrice);
        if (sellingPrice > 0) bike.setSellingPrice(sellingPrice);
        if (amount > 0) bike.setAmount(amount);

        //p.s. the array seemed too specific for it to be a method in bike.java :o
    }

    /* buildBike() takes an array of Strings containing the attributes of a bike object
     * and uses the helper method setBike() to create the bike object
     */
    public static Bike buildBike(String[] bikeAttributes){
        int length = bikeAttributes.length;
        Bike bike;
        
        //determines which bike object to construct based on bikeAttributes[0]
        //the last two elements in the array are expected to be the specific attributes of
        // each bike type
        switch (bikeAttributes[0]){
            case "City Bike": bike = 
                              new CityBike(Integer.parseInt(bikeAttributes[length - 2]), bikeAttributes[length - 1]);
                              setBike(bike, bikeAttributes);
                              break; 

            case "Mountain Bike": bike = 
                                  new MountainBike(bikeAttributes[length - 2], Integer.parseInt(bikeAttributes[length - 1]));
                                  setBike(bike, bikeAttributes);
                                  break;

            case "Road Bike": bike = 
                              new RoadBike(Integer.parseInt(bikeAttributes[length - 2]), Double.parseDouble(bikeAttributes[length - 1]));
                              setBike(bike, bikeAttributes);
                              break;

            default: bike = new Bike();
                     setBike(bike, bikeAttributes);
                     break;
        }

        return bike;
    }

    /* searches each Bike of a given Bike array and returns the Bike that
     * matches the given modelNumber and color
     */
    public static Bike getBikeFromInventory(String modelRequest, String colorRequest, Bike[] inventory){

            for(Bike bike : inventory){
                try{
                    if ( (modelRequest.equals(bike.getModelNumber())) && (colorRequest.equals(bike.getColor())) ){
                        return bike;
                    }
                }
                catch(NullPointerException n){
                    //occurs if not every element in the inventory array holds an object
                }
            } 
        //null is returned if there is no bike in the array that matches the model and color
        return null;
    }

    /* processRequest() returns the amount made based on the price of the bike
     * and how much the client purchased
     * if a bike was never found or an order was incomplete, then
     * the order will be written to a file
     */
    public static double processRequest(Bike bike, String[] bikeRequest, PrintWriter fileOut){
        double profitMade = 0.0;

        if(bike != null){
            int amountToSell = Integer.parseInt(bikeRequest[bikeRequest.length - 1]);
            int amountAvailable = bike.getAmount();
            int purchasedPrice = bike.getPurchasedPrice();
            int sellingPrice = bike.getSellingPrice();

            if(amountToSell < 0){} // negative amounts will be ignored
            // case where there are enough bikes to sell
            else if((amountAvailable >= amountToSell)){
                profitMade += amountToSell * (sellingPrice - purchasedPrice);
                bike.setAmount(amountAvailable - amountToSell);
            }
            else{ // sell all that is available and output what the client still needs to a file
                profitMade += amountAvailable * (sellingPrice - purchasedPrice);
                bike.setAmount(0);
                fileOut.print(bike.getBikeType() + ", " + bike.getModelNumber() + ", " + bike.getColor() + ", ");
                fileOut.println(amountToSell - amountAvailable);
            }

        }
        else { //output order to file
            for(int i = 0; i < bikeRequest.length - 1; i++){
                fileOut.print(bikeRequest[i] + ", ");
            }
            fileOut.println(bikeRequest[bikeRequest.length - 1]);
        }

        return profitMade;
    }

    public static void main(String[] args){
        
        if (args.length >= 2){
            Bike[] bikes = readInventoryFromFile(args[0]);
            double profit;

            System.out.println("\nInventory before request.");
            printInventory(bikes);

            profit = updateInventoryBasedOnClientsRequests(bikes, args[1]);
            System.out.println("\nInventory after request.");
            printInventory(bikes);

            System.out.println("Total monies made: $" + profit);
            System.out.println();
        }
        else{
            System.out.print("Don't forget to enter the names of the inventory and clientrequest files");
            System.out.println(" (In that order).");
        }

    }
}