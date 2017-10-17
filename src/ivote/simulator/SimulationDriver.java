//Author: Christopher Kilian
//Project #1 for CS 356
//Cal Poly Pomona, Fall 2017
package ivote.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//This class is a driver to test the IVoteSimulator program.
//It builds 4 separate Question types and their corresponding IVoteService's.
//There are two Questions that are single choice style questions, one using Student objects as choices, and one using
//Integers as the question choices.
//Two more Questions are multiple choice style questions, with one also being an Integer type Question (to demonstrate a single type with
//both Question styles), and one is a String type Question.
//A group of 30 students is generated, which then randomly choose answers to submit for each of these 4 Questions.
//The results of all 4 Question/Answer rounds are output for display by the respective IVoteServices.
//Finally, a single students answers to the String based Question are manually changed and the results displayed again to demonstrate
//that each student is allowed only a single submission for a given Question, with new ones overwriting old ones.
public class SimulationDriver {

    public static void main(String[] args) {
        Random myRand = new Random();
        List<Student> theClass = new ArrayList(); //List of students in a class (IE classroom) who will be used to answer Questions
        
        for(int i = 1; i <= 30; i++){ //create 30 students with unique ID's and place them in the classroom list
            String theID = "00" + i;
            Student theStudent = new Student(theID);
            theClass.add(theStudent);
        }
        
        List<String> stringAnswers = new ArrayList(); //holds strings for use in generating a String based Question
        stringAnswers.add("A");
        stringAnswers.add("B");
        stringAnswers.add("C");
        stringAnswers.add("D");
        stringAnswers.add("E");
        
        //Question object and IVoteService for the String based question - this is a multiple choice style question
        Question<String> stringQuestion = new MultipleChoiceQuestion(stringAnswers); 
        IVoteService<String> stringService = new IVoteService(stringQuestion);
        
        List<Integer> integerAnswers = new ArrayList(); //holds integers for use in generating an Integer based Question
        integerAnswers.add(1);
        integerAnswers.add(2);
        integerAnswers.add(3);
        integerAnswers.add(4);
        
        //Question object and IVoteService for the Integer based question - there is a Question and Service
        //for both Single choice and Multiple choice questions (for demonstration purposes)
        Question<Integer> integerSCQuestion = new SingleChoiceQuestion(integerAnswers);
        Question<Integer> integerMCQuestion = new MultipleChoiceQuestion(integerAnswers); 
        IVoteService<Integer> integerSCService = new IVoteService(integerSCQuestion);
        IVoteService<Integer> integerMCService = new IVoteService(integerMCQuestion);
        
        List<Student> studentAnswers = new ArrayList(); //holds Students who are themselves answers to a Question (such as a class president election)
        studentAnswers.add(theClass.get(0));
        studentAnswers.add(theClass.get(4));
        studentAnswers.add(theClass.get(9));
        studentAnswers.add(theClass.get(14));
        studentAnswers.add(theClass.get(19));
        
        //Question object and IVoteService for the Student based question - this is a single choice style question
        Question<Student> studentQuestion = new SingleChoiceQuestion(studentAnswers); 
        IVoteService<Student> studentService = new IVoteService(studentQuestion);
        
        //Run single choice questions here - each student object randomly selects one of the acceptable
        //responses and submits to the appropriate IVoteService
        for(Student aStudent : theClass){
            //Integer Single Choice answer selection
            Integer studentIntegerResponse = integerSCQuestion.getAcceptableResponses().get(myRand.nextInt(integerSCQuestion.getAcceptableResponses().size())); //choose a random response
            integerSCService.takeResponse(aStudent, studentIntegerResponse); //send response to Integer based IVoteService
            
            //Student answer selection
            Student studentChoiceResponse = studentQuestion.getAcceptableResponses().get(myRand.nextInt(studentQuestion.getAcceptableResponses().size())); //choose a random response
            studentService.takeResponse(aStudent, studentChoiceResponse); //send response to Student based IVoteService
        }
        
        
        //run multiple choice questions here
        //Please note: for Student #001, the String selection is manually set in order to later display
        //the way in which changing a students submitted answer changes the overall results.
        //Also note that multiple selections of the same response do not cause a problem, as student answers are stored in a Set rather than a List.
        for(Student aStudent : theClass){
            //String answer selection
            int stringsToSelect = myRand.nextInt(stringQuestion.getAcceptableResponses().size()); //choose a random number of strings answers to select, up to the number of potential responses
            List<String> stringsSelected = new ArrayList(); //list to hold randomly selected String responses for this student
            for(int i = 0; i <= stringsToSelect; i++){
                stringsSelected.add(stringQuestion.getAcceptableResponses().get(myRand.nextInt(stringQuestion.getAcceptableResponses().size()))); //choose a random response
            }
            if(aStudent.getMyID().equals("001")){ //for student 001, manually assign letter selection - for later demonstration purposes
                stringService.takeResponse(aStudent, "A"); //Student 001 initially selects only option "A"
            }else{
                stringService.takeResponse(aStudent, stringsSelected);
            }
            
            //Integer Multiple Choice answer selection
            int integersToSelect = myRand.nextInt(integerMCQuestion.getAcceptableResponses().size()); //choose a random number of strings answers to select, up to the number of potential responses
            List<Integer> integersSelected = new ArrayList(); //list to hold randomly selected Integer responses for this student
            for(int i = 0; i <= integersToSelect; i++){
                integersSelected.add(integerMCQuestion.getAcceptableResponses().get(myRand.nextInt(integerMCQuestion.getAcceptableResponses().size()))); //choose a random response
            }
            integerMCService.takeResponse(aStudent, integersSelected);
        }
        
        //print out results for Questions
        System.out.println("NOTE: Output is presented with the possible answer on the left followed by the number of students which selected that answer on the right.");
        System.out.println();
        System.out.println("Output for Single Choice Integer Question results...");
        System.out.println(integerSCService.outputResults());
        System.out.println();
        System.out.println("Output for Multiple Choice Integer Question results...");
        System.out.println(integerMCService.outputResults());
        System.out.println();
        System.out.println("Output for Single Choice Student Question results...");
        System.out.println("Note that students are identified by their student ID values.");
        System.out.println(studentService.outputResults());
        System.out.println();
        System.out.println("Output for Multiple Choice String Question results...");
        System.out.println(stringService.outputResults());
        System.out.println();
        System.out.println("Student #001 is changing their response to the Multiple Choice String Question from 'A' to 'C' and 'E'.");
        System.out.println("Updating displayed results for this Question...");
        
        List<String> newChoices = new ArrayList();
        newChoices.add("C");
        newChoices.add("E");
        stringService.takeResponse(theClass.get(0), newChoices); //update student 001's choices for the String Question
        System.out.println(stringService.outputResults()); //update printed results for that Question

    }
    
}
