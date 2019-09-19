public class Bike{
    String modelNumber;
    int purchasedPrice;
    int sellingPrice;
    String color;
    String preferredAgeRange;
    int amount;

    Bike(){
        modelNumber = "0000";
        purchasedPrice = 0;
        sellingPrice = 0;
        color = "blue";
        preferredAgeRange = "0 99";
        amount = 0;
    }

    Bike(String modelNumber, int purchasedPrice, int sellingPrice, String color, String prefferedAgeRange, int amount){
        this.modelNumber = modelNumber;
        this.purchasedPrice = purchasedPrice;
        this.sellingPrice = sellingPrice;
        this.color = color;
        this.amount = amount;
        this.preferredAgeRange = prefferedAgeRange;
    }

    public void setModelNumber(String modelNumber){
        this.modelNumber = modelNumber;
    }

    public void setPurchasedPrice(int purchasedPrice){
        this.purchasedPrice = purchasedPrice;
    }

    public void setSellingPrice(int sellingPrice){
        this.sellingPrice = sellingPrice;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setPreferredAgeRange(String preferredAgeRange){
        this.preferredAgeRange = preferredAgeRange;
    }

    public String getModelNumber(){
        return modelNumber;
    }

    public int getPurchasedPrice(){
        return purchasedPrice;
    }

    public int getSellingPrice(){
        return sellingPrice;
    }

    public String getColor(){
        return color;
    }

    public int getAmount(){
        return amount;
    }

    public String getPreferredAgeRange(){
        return preferredAgeRange;
    }

    public String getBikeType (){
        return "Bike";
    }

    public String toString(){
        String bike;

        bike = "Bike, Model " + modelNumber + ", $";
        bike += Integer.toString(purchasedPrice) + ", $" + Integer.toString(sellingPrice) + ", ";
        bike += color + ", " + Integer.toString(amount)  + ", " + preferredAgeRange;

        return bike;
    }
}