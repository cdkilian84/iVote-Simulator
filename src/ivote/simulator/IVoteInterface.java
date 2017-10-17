//Author: Christopher Kilian
package ivote.simulator;

import java.util.List;

//This interface defines the absolute minimum operations an iVote service must provide,
//namely the ability to accept responses from students, and to output the results of those
//responses. Other particulars of implementation are left to those classes implementing this interface,
//but these basic needs must be met by any iVote service class.
public interface IVoteInterface {
    public void takeResponse(Student theStudent, List theResponses);
    public String outputResults();
}
