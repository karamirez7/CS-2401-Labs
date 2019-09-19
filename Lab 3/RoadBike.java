public class RoadBike extends Bike{
    int numOfGears;
    double weight;

    RoadBike(){
        numOfGears = 0;
        weight = 0;
    }

    RoadBike(int numOfGears, double weight){
        this.numOfGears = numOfGears;
        this.weight = weight;
    }

    public void setNumOfGears(int numOfGears){
        this.numOfGears = numOfGears;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public int getNumOfGears(){
        return numOfGears;
    }

    public double getWeight(){
        return weight;
    }

    //override
    public String getBikeType(){
        return "Road Bike";
    }

    //override
    public String toString(){
        String RoadBike;

        RoadBike = "Road " + super.toString() + ", ";
        RoadBike += Integer.toString(numOfGears) + ", " + Double.toString(weight);

        return RoadBike;

    }

}