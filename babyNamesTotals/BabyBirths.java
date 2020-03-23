
import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        //return rank of the name in the file for the given gender or -1
        FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        CSVParser parser = fr.getCSVParser(false);
        //initialize rank
        int rank = 0;
        for (CSVRecord rec : parser) {
            //check if rec row matches gender
            if(rec.get(1).equals(gender)){
                //increase rank by 1
                rank += 1;
                //check if name is a match
                if(rec.get(0).equals(name)){
                    //return rank
                    return rank;
                }
            }
        }
        //otherwise return -1
        return -1;
    }
    
    public void testGetRank(){
        //
        int rank = getRank(2012,"Mason","M");
        System.out.println("Rank is " + rank);
    }
    
    public String getName(int year,int rank,String gender){
        //return name of the person in the file at this rank for the given gender, or NO NAME
        FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        CSVParser parser = fr.getCSVParser(false);
        //initialize count
        int count = 0;
        for (CSVRecord rec : parser) {
            //check if rec row matches gender
            if(rec.get(1).equals(gender)){
                //increase count by 1
                count += 1;
                //check if rank is a match
                if(count == rank){
                    //return name
                    return rec.get(0);
                }
            }
        }
        //otherwise return NO NAME
        return "NO NAME";
    }
    
    public void testGetName(){
        //
        String name = getName(2012,2,"M");
        System.out.println("Name is " + name);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        //determine what name would have been named if they were born in a different year using ranks
        //call getRank
        int rank = getRank(year,name,gender);
        //call getName for newYear and rank and gender
        String newName = getName(newYear,rank,gender);
        //print
        System.out.println(name + " is born in " + year + " would be " + newName + " if she was born in " + newYear);
    }
    
    public void testWhatIsNameInYear(){
        //
        whatIsNameInYear("Isabella",2012,2014,"F");
    }
    
    public int yearOfHighestRank(String name, String gender){
        //return the year with highest rank for name and gender across several files
        DirectoryResource dr = new DirectoryResource();
        //initialize highest rank
        int highestRank = 0;
        //initialize highestYear
        int highestYear = 0;
        //iterate over files
        for(File f : dr.selectedFiles()){
            //get filename
            String filename = f.getName();
            //get currYear
            int currYear = Integer.parseInt(filename.substring(3,7));
            //call getRank
            int currRank = getRank(currYear,name,gender);
            //check if highestRank is equal to 0
            if((highestRank == 0) && (currRank > 0)){
                //set highestRank to currRank
                highestRank = currRank;
                highestYear = currYear;
            }
            //check if currRank is smaller than highestRank
            if((currRank < highestRank) && (currRank > 0)){
                highestRank = currRank;
                highestYear = currYear;
            }
        }
        //check if highest rank is greater than 0
        if(highestRank > 0){
            return highestYear;
        }
        //otherwise return -1
        return -1;
    }
    
    public void testYearOfHighestRank(){
        //
        int year = yearOfHighestRank("Mason","M");
        System.out.println("Year of highest rank is " + year);
    }
    
    public double getAverageRank(String name, String gender){
        //return average rank of name and gender over files
        DirectoryResource dr = new DirectoryResource();
        //initialize sum
        double sum = 0;
        //initialize count
        double count = 0;
        //initialize average rank
        double averageR = 0;
        //initialize rank 
        int rank = 0;
        //iterate over files
        for(File f : dr.selectedFiles()){
            //get filename
            String filename = f.getName();
            //get year
            int year = Integer.parseInt(filename.substring(3,7));
            //get rank
            rank = getRank(year,name,gender);
            //check if rank is greater than 0
            if(rank > 0){
                //add rank to sum
                sum = sum + rank;
                //increase count by 1
                count += 1;
            }
        }
        //Check if rank is 0
        if(rank == 0){
            //return -1
            return -1.0;
        }
        //calculate average
        averageR = sum/count;
        //otherwise return average rank
        return averageR;
    }
    
    public void testGetAverageRank(){
        //
        double avgRank = getAverageRank("Mason","M");
        System.out.println("Average rank is " + avgRank);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        //return total number of births of those names with same gender and same year who are ranked higher than name
        //get rank of name
        int highRank = getRank(year,name,gender);
        //initialize totalBirths
        int totalBirths = 0;
        //initialize rank
        int rank = 0;
        //open fileresource
        FileResource fr = new FileResource("testing/yob" + year + "short.csv");
        //iterate over records
        for(CSVRecord rec : fr.getCSVParser(false)){
            //check gender match
            if(rec.get(1).equals(gender)){
                //increase rank by 1
                rank += 1;
                //check if rank is greater than highRank
                if(rank < highRank){
                    //add births to total
                    totalBirths = totalBirths + Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        //
        int totalBirths = getTotalBirthsRankedHigher(2012,"Ethan","M");
        System.out.println("Total births is " + totalBirths);
    }
    
    
    
    
    
    
    
    
}
