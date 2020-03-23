
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part1 {
    //returns the index of the first occurrence of stopCodon that appears after startIndex and is multiple of 3 away from startIndex
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        //check whether the stopCodon is present in dna string
        if(dna.indexOf(stopCodon, startIndex) == -1){
            return dna.length();
        } 
        //find stop codon index
        int stopCodonIndex = dna.indexOf(stopCodon, startIndex);
        //check the difference between the stopCodonIndex and the startIndex is divisible by 3 or zero
        if((((stopCodonIndex - startIndex) == 0) || ((stopCodonIndex - startIndex) % 3 == 0))){
            return stopCodonIndex;
        } else {
            //return length of dna strand
            return dna.length();
        }
    }
    
    //method to test findStopCodon and print results
    public void testFindStopCodon(){
        //test the difference to be zero between stopCodonIndex and startIndex
        int test1 = findStopCodon("ATGTAA",3,"TAA");
        System.out.println(test1);
        //test the difference to be modulo 3
        int test2 = findStopCodon("ATGTHTLEEKIDTAA",3,"TAA");
        System.out.println(test2);
        //test difference to be neither modulo 3 or 0
        int test3 = findStopCodon("ATGTTAA",3,"TAA");
        System.out.println(test3);
        return;
    }
    //
    public String findGene(String dna){
        //find index of first occurrence of start codon "ATG", otherwise return empty string
        int atgIndex = dna.indexOf("ATG");
        if(atgIndex == -1){
            return "";
        } 
        //calculate startIndex
        int startIndex = atgIndex + 3;
        //find index of first occurrence of stop codon "TAA" after the first occurrence of "ATG" and is multipe of 3 away
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        //find index of first occurrence of stop codon "TAG"
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        //find index of first occurrence of stop codon "TGA"
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //return the empty string if no stop codon exists
        if((taaIndex == tgaIndex) && (tgaIndex == tagIndex)){
            return "";
        }
        //return the gene formed from the "ATG" and closest stop codon
        if(taaIndex < tagIndex){
            if(taaIndex < tgaIndex){
                return dna.substring(atgIndex,taaIndex + 3);
            }
            return dna.substring(atgIndex,tgaIndex + 3);
        } else {
            if(tagIndex < tgaIndex){
                return dna.substring(atgIndex,tagIndex + 3);
            }
            return dna.substring(atgIndex,tgaIndex + 3);
        }
    }
    //
    public void testFindGene(){
        //dna with no "ATG"
        String test1 = "ATHBKDLIXKSVNK";
        System.out.println("The dna string is " + test1);
        String gene1 = findGene(test1);
        System.out.println("The gene is " + gene1);
        //dna with ATG and one valid stop codon
        String test2 = "ATATGHTAABKDLITGAXKSVNK";
        System.out.println("The dna string is " + test2);
        String gene2 = findGene(test2);
        System.out.println("The gene is " + gene2);
        //dna with ATG and multiple valid stop codons
        String test3 = "ATHBATGKDLTAATGAIXKTAGSVNK";
        System.out.println("The dna string is " + test3);
        String gene3 = findGene(test3);
        System.out.println("The gene is " + gene3);
        //dna with ATG and no valid stop codons
        String test4 = "ATHBKATGDTAALIXKSVNK";
        System.out.println("The dna string is " + test4);
        String gene4 = findGene(test4);
        System.out.println("The gene is " + gene4);
        //dna with multiple ATG and stop codons
        String test5 = "ATHATGBKATAATATGTGAGDTAALIXKSVNK";
        System.out.println("The dna string is " + test5);
        String gene5 = findGene(test5);
        System.out.println("The gene is " + gene5);
    }
    //
    public void printAllGenes(String dna){
        //find genes and print them out
        while(true){
            //call findGene
            String gene = findGene(dna);
            //print gene
            System.out.println(gene);
            //if the end of dna is reached or no gene exists then break
            if(gene == ""){
                break;
            }
            //find length of gene
            int geneLength = gene.length();
            //find index of gene
            int geneIndex = dna.indexOf(gene);
            //adjust the dna string to begin after the gene
            dna = dna.substring(geneIndex + geneLength);
        }
    }
    
    
    
    
    
    
    
}
