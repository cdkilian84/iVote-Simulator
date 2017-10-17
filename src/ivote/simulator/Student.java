//Author: Christopher Kilian
package ivote.simulator;

//This class is a data class which is just a container for the information that defines a student.
//For now, this data is just the student ID (which is set when the student is created), and the getter to retrieve that ID.
public class Student {
    private String studentID; //the primary identifier for a student object
    
    //Constructor
    public Student(String myID){
        studentID = myID;
    }
    //getter for student ID
    public String getMyID(){
        return studentID;
    }
    
    //Override of the toString method, which returns the student ID when called
    @Override
    public String toString() {
        return getMyID();
    }
}
