package s4.B183315; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 

import java.lang.*;

import s4.specification.*;

//import static s4.B183315.TestCase.InformationEstimatorTest;

/** What is imported from s4.specification
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

public class InformationEstimator implements InformationEstimatorInterface {
    // Code to tet, *warning: This code condtains intentional problem*
    byte[] myTarget; // data to compute its information quantity
    byte[] mySpace;  // Sample space to compute the probability
    FrequencerInterface myFrequencer;  // Object for counting frequency

    byte[] subBytes(byte[] x, int start, int end) {
        // corresponding to substring of String for  byte[] ,
        // It is not implement in class library because internal structure of byte[] requires copy.
        byte[] result = new byte[end - start];
        for (int i = 0; i < end - start; i++) {
            result[i] = x[start + i];
        }
        return result;
    }

    // IQ: information quantity for a count,  -log2(count/sizeof(space))
    double iq(int freq) {
        return -Math.log10((double) freq / (double) mySpace.length) / Math.log10(2.0);
    }

    /**
     * Set the data for computing the information quantities.
     *
     * @param target the data
     */
    public void setTarget(byte[] target) {
        myTarget = target;
    }

    /**
     * Set the data for sample space to computer probability.
     *
     * @param space the data
     */
    public void setSpace(byte[] space) {
        myFrequencer = new Frequencer();
        mySpace = space;
        myFrequencer.setSpace(space);
    }

    /**
     * Estimate information quantity.
     * <p>
     * It returns 0.0 when the TARGET is not set or TARGET's length is zero;
     * It returns Double.MAX_VALUE when the true value is infinite, or SPACE is not set.
     * The behavior is undefined if the true value is finite but larger than Double.MAX_VALUE.
     * Note that this happens only when the SPACE is unreasonably large.
     * We will encounter other problem anyway.
     * Otherwise, it returns the estimation value of information quantity.
     * <p>
     * Information quantity I(S) of string S is defined as follows:
     * <p>
     * I(S) = - \sum_{i=0}^{N} log2 P(ci)
     * <p>
     * where, ci is a i-th character in string S,
     * N is the length of String S,
     * and P(c) is the probability of character c in string S.
     *
     * @return estimated information quantity
     */
    public double estimation() {
        if (myTarget == null || myTarget.length == 0)
            return 0.0;
        if (mySpace == null || mySpace.length == 0)
            return Double.MAX_VALUE;
        var cash = new double[myTarget.length];
        myFrequencer.setTarget(myTarget);
        for (int i = 0; i < myTarget.length; i++){
            var min = Double.POSITIVE_INFINITY;
            for (int j = 0; j < i; j++) {
                int freq = myFrequencer.subByteFrequency(j + 1, i + 1);
                var sb = iq (freq) + cash[j];
                min = Math.min(min,sb);
            }
            int freq = myFrequencer.subByteFrequency(0, i + 1);
            var iqn = iq(freq);
            cash[i] = Math.min(min, iqn);
        }
        var value = cash[myTarget.length-1];
        return Double.isInfinite(value) ? Double.MAX_VALUE : value;
    }

    public static void main(String[] args) {
	InformationEstimator myObject;
	double value;
	myObject = new InformationEstimator();
	myObject.setSpace("3210321001230123".getBytes());
	myObject.setTarget("0".getBytes());
	value = myObject.estimation();
	System.out.println(">0 "+value);
	myObject.setTarget("01".getBytes());
	value = myObject.estimation();
	System.out.println(">01 "+value);
	myObject.setTarget("0123".getBytes());
	value = myObject.estimation();
	System.out.println(">0123 "+value);
	myObject.setTarget("00".getBytes());
	value = myObject.estimation();
	System.out.println(">00 "+value);
    }
}
				  
			       

	
    
