
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String empty = "";
        
        int startCodonIndex = dna.indexOf(startCodon);
        
        if(dna.indexOf(startCodon) == -1){
            return empty;
        } 
        
        if(dna.indexOf(stopCodon, startCodonIndex + 3) == -1){
            return empty;
        } 
        
        int stopCodonIndex = dna.indexOf(stopCodon, startCodonIndex + 3);
        
        if((stopCodonIndex - (startCodonIndex + 3)) % 3 == 0){
            if(dna.equals(dna.toUpperCase())){
                return dna.substring(startCodonIndex, (stopCodonIndex + 3)).toUpperCase();
            } else {
                return dna.substring(startCodonIndex, (stopCodonIndex + 3)).toLowerCase();
            }
        } else {
            return empty;
        }
       
    }
    
    public void testSimpleGene(){
        //DNA with no ATG
        String noATG = "BAHGIDLTAABDKCNTGH";
        System.out.println("DNA string is " + noATG);
        String noATGResult = findSimpleGene(noATG, "ATG", "TAA");
        System.out.println("Gene string is " + noATGResult);
        
        //DNA with no TAA
        String noTAA = "BATGHGIDLABDKCNTGH";
        System.out.println("DNA string is " + noTAA);
        String noTAAResult = findSimpleGene(noTAA, "ATG", "TAA");
        System.out.println("Gene string is " + noTAAResult);
        
        //DNA with no ATG or TAA
        String noATGTAA = "BAHGIDLABDKCNTGH";
        System.out.println("DNA string is " + noATGTAA);
        String noATGTAAResult = findSimpleGene(noATGTAA, "ATG", "TAA");
        System.out.println("Gene string is " + noATGTAAResult);
        
        //DNA with ATG, TAA, and substring is multiple of 3 between them
        String gene = "BAHGATGIDLABDKCNTAATGH";
        System.out.println("DNA string is " + gene);
        String geneResult = findSimpleGene(gene, "ATG", "TAA");
        System.out.println("Gene string is " + geneResult);
        
        //DNA with ATG, TAA, and substring is not multiple of 3 between them
        String noMultiple = "BAHGIATGDLABDTAAKCNTGH";
        System.out.println("DNA string is " + noMultiple);
        String noMultipleResult = findSimpleGene(noMultiple, "ATG", "TAA");
        System.out.println("Gene string is " + noMultipleResult);
        
        //testing practice
        String res1 = findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC", "ATG", "TAA");
        System.out.println(res1);
    }
}
