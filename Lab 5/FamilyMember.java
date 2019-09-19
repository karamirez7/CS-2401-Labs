/**** This class defines the blueprint of a family member  
 **** A family member has a first name, a last name, and a number of siblings 
 **** The code is given to you: you MUST NOT modify it.
 ****/

public class FamilyMember implements Comparable<FamilyMember>{
    private String fname;
    private String lname;
    private int siblings;
    
    public FamilyMember() {}
    
    public FamilyMember(String fn, String ln, int s) {
        fname = fn;
        lname = ln;
        siblings = s;
    }

    public void setFName(String fn) {
        fname = fn;
    }
    
    public void setLName(String ln) {
        lname = ln;
    }
    
    public void setSiblings(int s) {
        siblings = s;
    }
    
    public String getFName() {
        return fname;   
    }
    
    public String getLName() {
        return lname;   
    }
    
    public int getSiblings() {
        return siblings;   
    }
    
    public String toString() {
        return fname + " " + lname + ", who had: " + siblings + " siblings.";   
    }

    @Override
    public int compareTo(FamilyMember fm){
        
        //compares first name
        if (this.fname.compareTo(fm.fname) < 0)
            return -1;
        if (this.fname.compareTo(fm.fname) > 0)
            return 1;

        //compares last name
        if (this.lname.compareTo(fm.lname) < 0)
            return -1;
        if (this.lname.compareTo(fm.lname) > 0)
            return 1;

        //compares siblings
        if (this.siblings < fm.siblings)
            return -1;
        if (this.siblings > fm.siblings)
            return 1;

        return 0; //return 0 to represent everything was the same.
    }
}