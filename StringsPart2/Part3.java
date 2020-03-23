
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part3 {
    //count number of genes in a dna string
    public int countGenes(String dna){
        //initialize counter
        int count = 0;
        //call findGene
        String gene = findGene(dna);
        //if the end of dna is reached or no gene exists then return zero
        if(gene == ""){
            return count;
        }
        //loop to find genes and count them
        while(true){
            //increment counter
            count++;
            //find length of gene
            int geneLength = gene.length();
            //find index of gene
            int geneIndex = dna.indexOf(gene);
            //adjust the dna string to begin after the gene
            dna = dna.substring(geneIndex + geneLength);
            //call findGene
            gene = findGene(dna);
            //if the end of dna is reached or no gene exists then break
            if(gene == ""){
                break;
            }
        }
        return count;
    }
    //
    public void testCountGenes(){
        //dna has no genes
        String dna1 = "AKJLKDSFA";
        int test1 = countGenes(dna1);
        System.out.println("0 - " + test1);
        //dna has one gene
        String dna2 = "DDLCKKKATGKLYTAAJBNG";
        int test2 = countGenes(dna2);
        System.out.println("1 - " + test2);
        //dna has two genes
        String dna3 = "ATGATGTAAKBLGKBUATGHUJTGA";
        int test3 = countGenes(dna3);
        System.out.println("2 - " + test3);
    }
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
