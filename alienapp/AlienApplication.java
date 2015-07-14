package alienapp;


import aliendata.RegisterAliens;
import exportdata.Exporter;
import exportdata.ExporterFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AlienApplication {
    
    public static void main(String args[]) {
        
        System.out.println("Welcome to Alien Registration Application");
        
        ArrayList<RegisterAliens> alienArray = readAlienInfo();
        
        System.out.println("Enter export format as Text or PDF: \n");
        BufferedReader bReader = null;
        Exporter ex = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(System.in));
            String format = bReader.readLine().toLowerCase();
            ex = ExporterFactory.getExporter(format);
            
            if(ex != null) {
            //Can input a custom configurable name using config.properties file
            ex.export(generateExportMap(alienArray), "/home/hduser/alien/");
            } else {
                System.out.println("Unknown file format");
            }
        } catch (IOException ioe) {
            Logger.getLogger(AlienApplication.class.getName()).log(Level.SEVERE, "Error while reading", ioe);
        } finally {
            if(null != bReader) {
                try {
                    bReader.close();
                } catch (IOException ex1) {
                    Logger.getLogger(AlienApplication.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
    
    public static Map<String,String> generateExportMap(ArrayList<RegisterAliens> alienArray) {
        //LinkedHashMap will preserve insertion order
        Map<String,String> data = new LinkedHashMap<String,String>();
        for(int i=0; i < alienArray.size(); i++) {
        RegisterAliens regAliens = alienArray.get(i);
        data.put("Code Name of Alien "+i,regAliens.getCodeName());
        data.put("Blood Color of Alien "+i,regAliens.getBloodColor());
        data.put("No. of Antennas of Alien "+i,String.valueOf(regAliens.getNoOfAntennas()));
        data.put("No. of Legs of Alien "+i,String.valueOf(regAliens.getNoOfLegs()));
        data.put("Home Planet of Alien "+i,regAliens.getHomePlanet());
        }       
        return data;
    }
    
    @SuppressWarnings("finally")
	public static ArrayList<RegisterAliens> readAlienInfo() {
        ArrayList<RegisterAliens> alienArray = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter q to quit entering in console once information is included");
            do{
            RegisterAliens regAliens = new RegisterAliens();
            System.out.println("Enter Alien Code Name:\n");
            regAliens.setCodeName(br.readLine());
            
            System.out.println("Enter Alien Blood Color:\n");
            regAliens.setBloodColor(br.readLine());
            
            System.out.println("Enter No. of antennas:\n");
            regAliens.setNoOfAntennas(Integer.parseInt(br.readLine()));
            
            System.out.println("Enter No. of legs:\n");
            regAliens.setNoOfLegs(Integer.parseInt(br.readLine()));
            
            System.out.println("Enter Alien Home Planet:\n");
            regAliens.setHomePlanet(br.readLine());
            
            alienArray.add(regAliens);
            }while(!br.readLine().equalsIgnoreCase("q"));
           
        } catch (IOException ioe) {
            Logger.getLogger(AlienApplication.class.getName()).log(Level.SEVERE, "Error while reading", ioe);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(AlienApplication.class.getName()).log(Level.SEVERE, "Error while parsing numbers", nfe);
        } finally {
            if(br == null) {
                System.out.println("Error in reading. Investigate");
            }
            return alienArray;
        }

    }
}