
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Part1 {
    public String findSimpleGene(String dna){
        String empty = "";
        
        int startCodonIndex = dna.indexOf("ATG");
        
        if(dna.indexOf("ATG") == -1){
            return empty;
        } 
        
        if(dna.indexOf("TAA", startCodonIndex + 3) == -1){
            return empty;
        } 
        
        int stopCodonIndex = dna.indexOf("TAA", startCodonIndex + 3);
        
        if((stopCodonIndex - (startCodonIndex + 3)) % 3 == 0){
            return dna.substring(startCodonIndex, (stopCodonIndex + 3));
        } else {
            return empty;
        }
    }
    
    public void testSimpleGene(){
        //DNA with no ATG
        String noATG = "BAHGIDLTAABDKCNTGH";
        System.out.println("DNA string is " + noATG);
        String noATGResult = findSimpleGene(noATG);
        System.out.println("Gene string is " + noATGResult);
        
        //DNA with no TAA
        String noTAA = "BATGHGIDLABDKCNTGH";
        System.out.println("DNA string is " + noTAA);
        String noTAAResult = findSimpleGene(noTAA);
        System.out.println("Gene string is " + noTAAResult);
        
        //DNA with no ATG or TAA
        String noATGTAA = "BAHGIDLABDKCNTGH";
        System.out.println("DNA string is " + noATGTAA);
        String noATGTAAResult = findSimpleGene(noATGTAA);
        System.out.println("Gene string is " + noATGTAAResult);
        
        //DNA with ATG, TAA, and substring is multiple of 3 between them
        String gene = "BAHGATGIDLABDKCNTAATGH";
        System.out.println("DNA string is " + gene);
        String geneResult = findSimpleGene(gene);
        System.out.println("Gene string is " + geneResult);
        
        //DNA with ATG, TAA, and substring is not multiple of 3 between them
        String noMultiple = "BAHGIATGDLABDTAAKCNTGH";
        System.out.println("DNA string is " + noMultiple);
        String noMultipleResult = findSimpleGene(noMultiple);
        System.out.println("Gene string is " + noMultipleResult);
    }
}
