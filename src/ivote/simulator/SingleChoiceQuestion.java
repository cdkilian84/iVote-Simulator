//Author: Christopher Kilian
package ivote.simulator;

import java.util.List;

//Child of the "Question" class, this class specifically handles single choice style questions,
//those in which the user must select only one of the possible answers.
public class SingleChoiceQuestion<T> extends Question {
    
    //Constructor simply passes the list of response choices to the parent class for processing.
    public SingleChoiceQuestion(List<T> potentialResponses) {
        super(potentialResponses);
    }

    //Override of abstract parent method. Since this class allows only a single choice selection,
    //the first thing checked is that the passed list only contains a single response. If more than one response
    //was submitted, the response is deemed invalid. If only one response was submitted, that response is checked
    //against the possible valid responses to ensure it's a valid choice.
    //Returns a boolean - true if it is a valid choice, false otherwise.
    @Override
    public boolean validateResponse(List theResponses) {
        boolean validated = false;
        
        if(theResponses.size() == 1){
            validated = this.getAcceptableResponses().contains(theResponses.get(0));
        }

        return validated;
    }
    
}
