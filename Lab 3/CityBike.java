public class CityBike extends Bike{
    int numOfBaskets;
    String brakes;

    CityBike(){
        numOfBaskets = 0;
        brakes = "brakeType";
    }

    CityBike(int numOfBaskets, String brakes){
        this.numOfBaskets = numOfBaskets;
        this.brakes = brakes;
    }

    public void setNumOfBaskets(int numOfBaskets){
        this.numOfBaskets = numOfBaskets;
    }

    public void setBrakes(String brakes){
        this.brakes = brakes;
    }

    public int getNumOfBaskets(){
        return numOfBaskets;
    }

    public String getBrakes(){
        return brakes;
    }

    //override
    public String getBikeType(){
        return "City Bike";
    }

    //override
    public String toString(){
        String CityBike;

        CityBike = "City " + super.toString() + ", ";
        CityBike += Integer.toString(numOfBaskets) + ", " + brakes;

        return CityBike;
    }
}