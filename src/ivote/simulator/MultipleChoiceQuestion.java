//Author: Christopher Kilian
package ivote.simulator;

import java.util.List;

//Child of the "Question" class, this class specifically handles multiple choice style questions,
//where the user is allowed to choose more than a single answer.
public class MultipleChoiceQuestion<T> extends Question{
    
    //Constructor simply passes the list of response choices to the parent class for processing.
    public MultipleChoiceQuestion(List<T> potentialResponses) {
        super(potentialResponses);
    }

    //Override of abstract parent method. Since this class allows multiple choice selections,
    //every selection in the list submitted to this method must be checked against the allowed
    //question selections. Since the valid responses are stored as a list, the "containsAll" method
    //allows for nice and tidy checking of every submitted answer choice.
    //Returns a boolean - true if the submitted selections are all acceptable choices, and false otherwise.
    @Override
    public boolean validateResponse(List theResponses) {
        boolean validated = false;

        if(this.getAcceptableResponses().containsAll(theResponses)){
            validated = true;
        }
    
        return validated;
    }

    
    
}
