
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part2 {
    //returns an integer indicating how many times stringa appears in stringb
    public int howMany(String stringa, String stringb){
        //initialize counter
        int count = 0;
        //find the length of stringa
        int stringaLength = stringa.length();
        //find the index of stringa
        int stringaIndex = stringb.indexOf(stringa);
        //if the index of stringa is -1 then return zero
        if(stringaIndex == -1){
            return count;
        }
        //loop until the end of stringb
        while(true){
            //increment counter
            count++;
            //adjust substring stringb
            stringb = stringb.substring(stringaIndex + stringaLength);
            //find the index of stringa
            stringaIndex = stringb.indexOf(stringa);
            //if the index is -1 then return zero
            if(stringaIndex == -1){
                break;
            }
        }
        return count;
    }
    //
    public void testHowMany(){
        //stringa exists and there is overlap overlap
        String aTest1 = "AA";
        String bTest1 = "AAAATAAHJKAAA";
        int test1 = howMany(aTest1,bTest1);
        System.out.println("4 - " + test1);
        //stringa does not exist
        String aTest2 = "XYZ";
        String bTest2 = "ABCDE";
        int test2 = howMany(aTest2,bTest2);
        System.out.println("0 - " + test2);
        //stringa exists with no overlap
        String aTest3 = "KCN";
        String bTest3 = "HGICKCN";
        int test3 = howMany(aTest3,bTest3);
        System.out.println("1 - " + test3);
        
    }
    
    
    
    
    
    
    
    
    
}
