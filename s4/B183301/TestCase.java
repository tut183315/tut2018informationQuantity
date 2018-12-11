package s4.B183301; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import java.util.ArrayList;

import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class TestCase {

    public static void main(String[] args) {
        try {
            FrequencerInterface  myObject;
            int freq;
            System.out.println("checking s4.B183301.Frequencer");
            myObject = new s4.B183301.Frequencer();
            FrequencerTest("Hi Ho Hi Ho","H",4);
            FrequencerTest(null,"Xx",0);
            FrequencerTest("XXXXfefefrrehg9uewrj",null,-1);
            FrequencerTest(null,null,-1);
            FrequencerTest("Hi Ho Hi Ho","Hi",2);
        }
        catch(Exception e) {
            System.out.println("Exception occurred: STOP");
        }

        try {
            InformationEstimatorInterface myObject;
            double value;
            System.out.println("checking s4.B183301.InformationEstimator");
            myObject = new s4.B183301.InformationEstimator();
            myObject.setSpace("3210321001230123".getBytes());
            InformationEstimatorTest(myObject,"0");
            InformationEstimatorTest(myObject,"01");
            InformationEstimatorTest(myObject,"0123");
            InformationEstimatorTest(myObject,"00");
        }
        catch(Exception e) {
            //System.out.println("Exception occurred: STOP");
            e.printStackTrace();
        }

    }

    static void InformationEstimatorTest(InformationEstimatorInterface myObject,String str) {
        double value;
        myObject.setTarget(str.getBytes());
        value = myObject.estimation();
        System.out.printf(">%s %s\n",str,value);
    }

    static void FrequencerTest(String space,String target,int expect) {
        var myObject = new s4.B183301.Frequencer();
        int freq;
        if(space!=null) myObject.setSpace(space.getBytes());
        if(target!=null) myObject.setTarget(target.getBytes());
        freq = myObject.frequency();
        System.out.printf("\"%s in \"%s\" appears %d times. \n",space,target,freq);
        if(expect == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }

    static void SubFrequencerTest(String space,String target,int expect,int start,int end) {
        int freq;
        var myObject = new s4.B183301.Frequencer();
        if(space!=null) myObject.setSpace(space.getBytes());
        if(target!=null) myObject.setTarget(target.getBytes());
        freq = myObject.subByteFrequency(start,end);
        System.out.printf("\"%s\" in \"%s\" appears %d times. ",space,target,freq);
        if(expect == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
}	    
	    
