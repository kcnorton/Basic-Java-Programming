
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        //return true if stringa appears at least twice in stringb
        //get length of stringa
        int lengtha = stringa.length();
        //check if stringa is in stringb
        if(stringb.indexOf(stringa) != -1){
            //if so, get start index of stringa
            int indexa = stringb.indexOf(stringa);
            //check that stringa appears after its first appearance in stringb
            if(stringb.indexOf(stringa,(indexa + lengtha)) != -1){
                //if so, return true
                return true;
            }
        }
        //otherwise false
        return false;
    }
    
    public void testing(){
        //testing true
        boolean test1 = twoOccurrences("xyz","xyzzzyzyzxxxyz");
        System.out.println("The result of test1 should be true and is " + test1);
        //testing false
        boolean test2 = twoOccurrences("abc","abxabcxxxcba");
        System.out.println("The result of test2 should be false and is " + test2);
        
        //testing stringa is in stringb
        String lastPart1 = lastPart("an","banana");
        System.out.println("The part of the string after an in banana is " + lastPart1);
        //testing stringa is not in stringb
        String lastPart2 = lastPart("zoo","forest");
        System.out.println("The part of the string after zoo in forest is " + lastPart2);
    }
    
    public String lastPart(String stringa, String stringb){
        //get length of stringa and stringb
        int lengtha = stringa.length();
        int lengthb = stringb.length();
        //check if stringa is in stringb
        if(stringb.indexOf(stringa) != -1){
            //if so, get start index of stringa
            int indexa = stringb.indexOf(stringa);
            //return substring following stringa
            return stringb.substring((indexa + lengtha),(lengthb));
        }
        //otherwise return stringb
        return stringb;
    }
}
