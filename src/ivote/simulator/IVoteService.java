//Author: Christopher Kilian
package ivote.simulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//The IVoteService class holds Question data and student response submissions. It handles the acceptance of a student response,
//the validation of that response via its Question object, and the mapping of students and their responses.
//The service also allows for output of the student response totals by counting responses of each type for each student.
public class IVoteService<T> {
    //IVoteService holds two primary datasets, the Question being asked and
    //the mapping of students who have responded to their individual responses.
    //A set is used for the map instead of a list to eliminate duplicate counting of the same
    //response by a student (IE if a student responds with ["A", "B", "A"] then the second "A"
    //response is superfluous and is eliminated by incorporation into a set.
    private Question myQuestion;
    private Map<Student, Set<T>> answerMap;
    
    //Constructor
    public IVoteService(Question<T> theQuestion){
        answerMap = new HashMap();
        myQuestion = theQuestion;
    }
    
    //Primary "takeResponse" method, which accepts a Student object and their list of responses to a Question.
    //It checks that the responses provided are valid, and if they are it maps the student to those responses.
    //Through the use of a map, if the same student submits a response more than once, only their latest response
    //will be stored and counted. If the response submitted is not valid, the method ignores the submission and informs the user
    //via the console that their submission has been discarded.
    public void takeResponse(Student theStudent, List theResponses){
        //System.out.println("theResponses contains: " + theResponses);
        if(myQuestion.validateResponse(theResponses)){ //check if submitted response list is valid
            answerMap.put(theStudent, new HashSet(theResponses));
            System.out.println("Valid response has been accepted.");
            System.out.println("The response for student " + theStudent.getMyID() + " is: " + answerMap.get(theStudent));
        }else{
            System.out.println("An invalid response was submitted. Discarding invalid response."); //ignore invalid responses
        }
    }
    
    //Overloaded version of the "takeResponse" method, which is set up to take a single response entry
    //for the given type of the Question (response type must match Question type).
    //This allows the user to call a "takeResponse" on a single entry rather than forcing them to build a list first.
    //It simply wraps the provided response in a single entry list and then passes it on to the primary "takeResponse" method.
    public void takeResponse(Student theStudent, T theResponse){
        List<T> wrapperList = new ArrayList();
        wrapperList.add(theResponse);
        this.takeResponse(theStudent, wrapperList);
    }
    
    //Method to output the totals of all student responses thus far. It builds the proper output string
    //and calls the "countResponses" method in order to output the exact number of each response type.
    //The string containing all of this information is then returned.
    public String outputResults(){
        StringBuilder outputString = new StringBuilder();
        
        for(int i = 0; i < myQuestion.getAcceptableResponses().size(); i++){
            outputString.append(myQuestion.getAcceptableResponses().get(i));
            outputString.append(": ");
            outputString.append(countResponses((T)myQuestion.getAcceptableResponses().get(i))); //cast the response to the proper type, the same as that used by the stored Question
            outputString.append("\n");
        }
        
        return outputString.toString();
    }
    
    //Private method used to count all submissions of a specific response type.
    //For example, if a Question has responses "A", "B", and "C", this method can count
    //how many "C" responses the IVoteService has recieved so far.
    private int countResponses(T response){
        int theCount = 0;
        //System.out.println("The countResponses value is: " + response);
        for(Set<T> currentSet : answerMap.values()){
            //System.out.println("The set contains: " + currentSet.toString());
            if(currentSet.contains(response)){
                theCount++;
            }
        }
        
        return theCount;
    }
    
}
