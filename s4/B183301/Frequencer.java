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
*/


public class Frequencer implements FrequencerInterface{
    // Code to Test, *warning: This code  contains intentional problem*
    byte [] myTarget;
    byte [] mySpace;
    public void setTarget(byte [] target) { myTarget = target;}
    public void setSpace(byte []space) { mySpace = space; }
    public int frequency() {
        if(myTarget == null) return -1;
        return subByteFrequency(0,myTarget.length);
    }

    public int subByteFrequency(int start, int end) {
        if(myTarget == null || myTarget.length == 0) return -1;
        if(start >= end) return -1;
        if(mySpace == null || mySpace.length == 0) return 0;
        int spaceLength = mySpace.length;
        int count = 0;
        loop:for(int spacestart = 0; spacestart < spaceLength; spacestart++) { // Is it OK?
            for(int i = start; i<end; i++) {
                if(myTarget[i] != mySpace[spacestart+i]) {
                    continue loop;
                }
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Frequencer myObject;
        int freq;
        try {
            System.out.println("checking my Frequencer");
            TestCase.FrequencerTest("Hi Ho Hi Ho","H",4);
        }
        catch(Exception e) {
            System.out.println("Exception occurred: STOP");
        }
    }
}	    
	    
