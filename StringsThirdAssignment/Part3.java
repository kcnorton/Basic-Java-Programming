
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part3 {
    //
    public void processGenes(StorageResource sr){
        //intialize length counter
        int lengthCounter = 0;
        //print all strings longer than 60 characters
        for(String s : sr.data()){
            if(s.length() > 60){
                //System.out.println(s);
                lengthCounter++;
            }
        }
        //print number of strings longer than 60 characters
        System.out.println("Number of strings longer than 60 characters is " + lengthCounter);
        //print strings whose cgRatio is higher than 0.35
        //initialize cgRatio
        double cgRatio = 0;
        //initialize ratio counter
        int ratioCounter = 0;
        for(String s : sr.data()){
            cgRatio = cgRatio(s);
            if(cgRatio > 0.35){
                //System.out.println(s);
                ratioCounter++;
            }
        }
        //print number of strings whose cgRatio is higher than 0.35
        System.out.println("Number of strings whose cgRatio is higher than 0.35 is " + ratioCounter);
        //initialize longest
        int longest = 0;
        //initialize longest gene
        String longestGene = "";
        //print length of the longest gene
        for(String s : sr.data()){
            if(s.length() > longest){
                longest = s.length();
                longestGene = s;
            }
        }
        System.out.println("The length of the longest gene is " + longest);
    }
    //
    public void testProcessGenes(){
        //one dna string that has some genes longer than 9
        //StorageResource sr1 = new StorageResource();
        //sr1 = getAllGenes("ATGGHJTAAKIJCKDHGATGJKILOKSDGTGAKATGKILGHJKIDNUMTAAKI");
        //processGenes(sr1);
        //one dna string that has no genes longer than 9
        //StorageResource sr2 = new StorageResource();
        //sr2 = getAllGenes("ATGGHJTAAATGTAGKIL");
        //processGenes(sr2);
        //one dna string that has some genes whose cgRatio is higher than 0.35
        //StorageResource sr3 = new StorageResource();
        //sr3 = getAllGenes("ATGGHJCCCTAAKIJCKDATGGGGCCCTGAHGATGJKILOKSDGTGAKATGKILGHJKIDNUMTAAKI");
        //processGenes(sr3);
        //one dna string that has no genes whose cgRatio is higher than 0.35
        //StorageResource sr4 = new StorageResource();
        //sr4 = getAllGenes("ATGGHJTAAKIJCKDHGATGJKILOKTGAKATGKILKIDNUMTAAKI");
        //processGenes(sr4);
        //one dna string that has no genes
        //StorageResource sr5 = new StorageResource();
        //sr5 = getAllGenes("ATGABCXYZLMNOP");
        //processGenes(sr5);
        //
        FileResource fr = new FileResource();
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr6 = new StorageResource();
        sr6 = getAllGenes(dna);
        processGenes(sr6);
    }
    //returns ratio of C's and G's in dna as a fraction of the entire strand
    public double cgRatio(String dna){
        //initialize count
        double count = 0;
        //initialize ratio
        double ratio = 0;
        //find dna length
        double dnaLength = dna.length();
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
    //
    public StorageResource getAllGenes(String dna){
        //create StorageResource
        StorageResource sr = new StorageResource();
        //find genes and print them out
        while(true){
            //call findGene
            String gene = findGene(dna);
            //if the end of dna is reached or no gene exists then break
            if(gene == ""){
                break;
            }
            //add to StorageResource
            sr.add(gene);
            //find length of gene
            int geneLength = gene.length();
            //find index of gene
            int geneIndex = dna.indexOf(gene);
            //adjust the dna string to begin after the gene
            dna = dna.substring(geneIndex + geneLength);
        }
        System.out.println(sr.size()); 
        return sr;
    }
    //returns the index of the first occurrence of stopCodon that appears after startIndex and is multiple of 3 away from startIndex
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        //check whether the stopCodon is present in dna string
        if(dna.indexOf(stopCodon, startIndex) == -1){
            return dna.length();
        } 
        //find stop codon index
        int stopCodonIndex = dna.indexOf(stopCodon, startIndex);
        //placeholder for current index
        int currIndex = stopCodonIndex;
        //this needs to loop until a proper stop codon is found or the end of the dna
        while (true) {
            int diff = (currIndex - startIndex) % 3;
            if (diff == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex +1);
            }
            if(currIndex == -1){
                break;
            }
        }
        return dna.length();
    }
    
    //method to test findStopCodon and print results
    public void testFindStopCodon(){
        //test the difference to be zero between stopCodonIndex and startIndex
        int test1 = findStopCodon("ATGTAA",3,"TAA");
        System.out.println(test1);
        //test the difference to be modulo 3
        int test2 = findStopCodon("ATGTHTLEEKIDTGA",3,"TGA");
        System.out.println(test2);
        //test difference to be neither modulo 3 or 0
        int test3 = findStopCodon("ATGTTAG",3,"TAG");
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
                if(taaIndex + 3 == dna.length()){
                    return dna.substring(atgIndex);
                } else {
                    return dna.substring(atgIndex,taaIndex + 3);
                }
            }
            if(tgaIndex + 3 == dna.length()){
                    return dna.substring(atgIndex);
                } else {
                    return dna.substring(atgIndex,tgaIndex + 3);
                }
        } else {
            if(tagIndex < tgaIndex){
                if(tagIndex + 3 == dna.length()){
                    return dna.substring(atgIndex);
                } else {
                    return dna.substring(atgIndex,tagIndex + 3);
                }
            }
            if(tgaIndex + 3 == dna.length()){
                    return dna.substring(atgIndex);
                } else {
                    return dna.substring(atgIndex,tgaIndex + 3);
                }
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
