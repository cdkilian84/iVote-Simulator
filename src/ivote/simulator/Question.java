//Author: Christopher Kilian
package ivote.simulator;

import java.util.ArrayList;
import java.util.List;

//This public Question class is designed to be used as a basic template for the group of possible Question types
//The basic use of a Question object is to store the list of potential answers to the question, and to provide a
//method to validate a response against those potential answers (check if the provided answers match those the Question stores).
//A Question must be of a specific type, hence the abstract nature of this parent class.
//By using generic type definitions for this class, it and it's children should be able to handle any type of
//response, whether it's a String, Integer, Character, or some other object passed to it.
public abstract class Question<T> {
    //Store potential answers as a list, accessible through the class getter - this value must be set when the Question is constructed
    private List<T> questionResponses;
    
    //Constructor
    public Question(List<T> potentialResponses){
        questionResponses = new ArrayList(potentialResponses);
    }
    
    //Getter for the question responses
    public List<T> getAcceptableResponses(){
        return questionResponses;
    }
    
    //All subclasses must be able to validate the response by accepting a list of responses and verifying its contents.
    //Some subclasses may overload this method to accept other types.
    //A list was chosen for this top level because it allows all children to accept any desired number of responses.
    public abstract boolean validateResponse(List theResponses);
}
