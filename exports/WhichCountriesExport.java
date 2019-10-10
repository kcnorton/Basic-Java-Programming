/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public String countryInfo(CSVParser parser, String country){
        //return string of info about country or NOT FOUND
        //country : exports : export value
        //iterate over each row in file
        for(CSVRecord record : parser){
            //get list of countries
            String countries = record.get("Country");
            //check if country is in list of countries
            if(countries.contains(country)){
                //get exports
                String exports = record.get(1);
                //get export value
                String exportValue = record.get(2);
                //return info
                String info = country + " : " + exports + " : " + exportValue;
                return info;
            }
        }
        //if not, return NOT FOUND
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        //print names of all countries that have both export items
        //iterate over records
        for(CSVRecord record : parser){
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains both export items
            if ((export.contains(exportItem1)) && (export.contains(exportItem2))) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        //return number of countries that export item
        //initialize count
        int count = 0;
        //iterate over records
        for(CSVRecord record : parser){
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains export item
            if (export.contains(exportItem)) {
                //If so, add to count
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        //print names of countries and their Value amount for all countries whose Value string is longer than amount
        //iterate over records
        for(CSVRecord record : parser){
            //Look at the "Export Value" column
            String value = record.get(2);
            //Check if value is longer than amount
            if (value.length() > amount.length()) {
                //If so, print country and value
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
        
        //countryInfo
        parser = fr.getCSVParser();
        String info = countryInfo(parser,"Nauru");
        System.out.println(info);
        
        //listExportersTwoProducts
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"fish","nuts");
        
        //numberOfExporters
        parser = fr.getCSVParser();
        int num = numberOfExporters(parser,"sugar");
        System.out.println("Number of exporters is " + num);
        
        //bigExporters
        parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
    
    
    
    
    
    
    
    
    
    
    
}
