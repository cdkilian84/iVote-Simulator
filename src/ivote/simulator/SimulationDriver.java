//Author: Christopher Kilian
package ivote.simulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Chris
 */
public class SimulationDriver {

    public static void main(String[] args) {
        Random myRand = new Random();
        /*
        List<String> myAnswers = new ArrayList();
        myAnswers.add("A");
        myAnswers.add("B");
        myAnswers.add("C");
        myAnswers.add("D");
        myAnswers.add("E");
        */
        List<Integer> myAnswers = new ArrayList();
        myAnswers.add(1);
        myAnswers.add(3);
        myAnswers.add(5);
        myAnswers.add(7);
        myAnswers.add(9);
        
        List<Student> theClass = new ArrayList();
        for(int i = 1; i < 6; i++){
            String theID = "00" + i;
            Student theStudent = new Student(theID);
            theClass.add(theStudent);
        }

        /*
        List<Student> myAnswers = new ArrayList();
        myAnswers.add(theClass.get(0));
        myAnswers.add(theClass.get(1));
        myAnswers.add(theClass.get(2));
        myAnswers.add(theClass.get(3));
        myAnswers.add(theClass.get(4));
        */
        Question<Integer> testQuestion = new MultipleChoiceQuestion(myAnswers);
        IVoteService theService = new IVoteService(testQuestion);
        /*
        for(Student aStudent : theClass){
            Object studentResponse = testQuestion.getAcceptableResponses().get(myRand.nextInt(testQuestion.getAcceptableResponses().size()));
            System.out.println("Student " + aStudent.getMyID() + " responded with " + studentResponse);
            //List<String> tempList = new ArrayList();
            //tempList.add((String)studentResponse);
            
            theService.takeResponse(aStudent, studentResponse);
        }
        */
        
        //System.out.println("Student " + theClass.get(0).getMyID() + " is changing answer to 1.");
        //theService.takeResponse(theClass.get(0), 1);
        //System.out.println("Student " + theClass.get(1).getMyID() + " is changing answer to 2.");
        //theService.takeResponse(theClass.get(1), 2);
        
        
        //for multiple choice questions
        for(Student aStudent : theClass){
            int itemsToSelect = myRand.nextInt(testQuestion.getAcceptableResponses().size());
            List<Integer> itemsSelected = new ArrayList();
            for(int i = 0; i <= itemsToSelect; i++){
                itemsSelected.add(testQuestion.getAcceptableResponses().get(myRand.nextInt(testQuestion.getAcceptableResponses().size())));
            }
            
            System.out.println("Student " + aStudent.getMyID() + " responded with " + itemsSelected);
            
            theService.takeResponse(aStudent, itemsSelected);
        }
        
        System.out.println("Preparing to output results...");
        System.out.println(theService.outputResults());
        
    }
    
}
