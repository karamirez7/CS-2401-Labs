public class MountainBike extends Bike{
    String userLevel;
    int wheelSize;

    MountainBike(){
        userLevel = "Scrub";
        wheelSize = 0;
    }

    MountainBike(String userLevel, int wheelSize){
        this.userLevel = userLevel;
        this.wheelSize = wheelSize;
    }

    public void setUserLevel(String userLevel){
        this.userLevel = userLevel;
    }

    public void setWheelSize(int wheelSize){
        this.wheelSize = wheelSize;
    }

    public String getUserLevel(){
        return userLevel;
    }

    public int getWheelSize(){
        return wheelSize;
    }

    //override
    public String getBikeType(){
        return "Mountain Bike";
    }

    //override
    public String toString(){
        String MountainBike;

        MountainBike = "Mountain " + super.toString() + ", ";
        MountainBike += userLevel + ", " + Integer.toString(wheelSize);

        return MountainBike;
    }
}