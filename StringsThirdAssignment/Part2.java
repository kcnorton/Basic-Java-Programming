
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part2 {
    //returns ratio of C's and G's in dna as a fraction of the entire strand
    public float cgRatio(String dna){
        //initialize count
        float count = 0;
        //initialize ratio
        float ratio = 0;
        //find dna length
        float dnaLength = dna.length();
        //loop through dna and count Cs and Gs
        for(int i = 0; i < dnaLength; i++){
            if((dna.charAt(i) == 'C') || (dna.charAt(i) == 'G')){
                count++;
            }
        }
        //calculate ratio
        ratio = count/dnaLength;
        return ratio;
    }
    //count number of times CTG appears
    public float countCTG(String dna){
        //initialize count
        float count = 0;
        //find dna length
        float dnaLength = dna.length();
        //loop through dna and count Cs and Gs
        for(int i = 0; i < dnaLength; i += 3){
            if(dna.substring(i,i+3) == "CTG"){
                count++;
            }
        }
        //calculate ratio
        return count;
    }
    
    
    
    
    
    
    
    
}
