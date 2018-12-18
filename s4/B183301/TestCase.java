package s4.B183301; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 

import java.lang.*;

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


/**
 * Test Class for Frequencer and Estimator
 */
public class TestCase {

    /**
     * main for test
     */
    public static void main(String[] args) {
        {
            System.out.println("checking s4.B183301.Frequencer");
            FrequencerTest("Hi Ho Hi Ho", "H", 4);
            FrequencerTest(null, "Xx", 0);
            FrequencerTest("", "fefefe", 0);
            FrequencerTest("XXXXfefefrrehg9uewrj", null, -1);
            FrequencerTest("Xdwrjdfefedf", "", -1);
            FrequencerTest(null, null, -1);
            FrequencerTest("Hi Ho Hi Ho", "Hi", 2);
        }
        {
            System.out.println("checking s4.B183301.InformationEstimator");
            InformationEstimatorTest("3210321001230123", "0");
            InformationEstimatorTest("3210321001230123", "01");
            InformationEstimatorTest("3210321001230123", "0123");
            InformationEstimatorTest("3210321001230123", "00");
            InformationEstimatorTest("3210321001230123", "3210");
            InformationEstimatorTest("3210321001230123", null, 0.0);
            InformationEstimatorTest("3210321001230123", "", 0.0);
            InformationEstimatorTest(null, "01", Double.MAX_VALUE);
        }
    }

    /**
     * Testing Estimator without expected value
     *
     * @param space  Space Data
     * @param target Target Data
     */
    static void InformationEstimatorTest(String space, String target) {
        InformationEstimatorTest(target, space, null);
    }

    /**
     * Testing Estimator with expected value
     *
     * @param space    Space Data
     * @param target   Target Data
     * @param expected expected value for estimated value
     */
    static void InformationEstimatorTest(String space, String target, Double expected) {
        var myObject = new s4.B183301.InformationEstimator();
        try {
            if (space != null)
                myObject.setSpace(space.getBytes());
            double value;
            if (target != null)
                myObject.setTarget(target.getBytes());
            value = myObject.estimation();
            System.out.printf(">%s %s\n", space, value);
            if (expected != null) {
                if (value == expected) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failed");
                }
            }
        } catch (Exception e) {
            System.out.println(" with Error " + myObject.toString() + " " + space);
            e.printStackTrace();
        }
    }

    /**
     * Testing Frequencer
     *
     * @param space    Space Data
     * @param target   Target Data
     * @param expected expected value for estimated value
     */
    static void FrequencerTest(String space, String target, int expected) {
        try {
            var myObject = new s4.B183301.Frequencer();
            int freq;
            if (space != null) myObject.setSpace(space.getBytes());
            if (target != null) myObject.setTarget(target.getBytes());
            freq = myObject.frequency();
            System.out.printf("\"%s in \"%s\" appears %d times. \n", space, target, freq);
            if (expected == freq) {
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        } catch (Exception e) {
            System.out.printf("Failed with Error space: %s target: %s expect: %d ", space, target, expected);
            e.printStackTrace();
        }
    }

    /**
     * Testing SubFrequencer
     */
    static void SubFrequencerTest(String space, String target, int expect, int start, int end) {
        try {
            int freq;
            var myObject = new s4.B183301.Frequencer();
            if (space != null) myObject.setSpace(space.getBytes());
            if (target != null) myObject.setTarget(target.getBytes());
            freq = myObject.subByteFrequency(start, end);
            System.out.printf("\"%s\" in \"%s\" appears %d times. ", space, target, freq);
            if (expect == freq) {
                System.out.println("OK");
            } else {
                System.out.println("WRONG");
            }
        } catch (Exception e) {
            System.out.printf("Failed with Error space: %s target: %s expect: %d ", space, target, expect);
            e.printStackTrace();
        }
    }
}	    
	    
