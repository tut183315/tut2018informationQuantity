package s4.B183301; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

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
*/

/**
 * Simple Frequency Counter Class
 */
public class Frequencer implements FrequencerInterface {
    // Code to Test, *warning: This code  contains intentional problem*
    byte[] myTarget;
    byte[] mySpace;

    /**
     * Set the data to search.
     *
     * @param target the data to search
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
        mySpace = space;
    }

    /**
     * Get the frequency count of TARGET.
     * <p>
     * It returns -1 when TARGET is not set or TARGET's length is zero.
     * It returns 0 when SPACE is not set or SPACE's length is zero.
     * Otherwise, returns the frequency of TAGET in SPACE.
     *
     * @return frequency count
     */
    public int frequency() {
        if (myTarget == null) return -1;
        return subByteFrequency(0, myTarget.length);
    }

    /**
     * Get the frequency count of subBytes of TARGET.
     * <p>
     * i.e. target[start], taget[start+1], ... , target[end-1].
     * For the incorrect value of START or END return zero
     *
     * @param start start index of target
     * @param end   index after the last index (excluded)
     * @return frequency count in subBytes
     */
    public int subByteFrequency(int start, int end) {
        if (myTarget == null || myTarget.length == 0) return -1;
        if (start >= end) return -1;
        if (mySpace == null || mySpace.length == 0) return 0;
        int spaceLength = mySpace.length;
        int count = 0;
        var spaceend = spaceLength - end + start;
        loop:
        for (int spacestart = 0; spacestart < spaceend; spacestart++) {
            for (int i = start; i < end; i++) {
                if (myTarget[i] != mySpace[spacestart + i]) {
                    continue loop;
                }
            }
            count++;
        }
        return count;
    }
}	    
	    
