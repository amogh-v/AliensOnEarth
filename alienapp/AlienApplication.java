package alienapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import aliendata.Alien;
import exportdata.Exporter;
import exportdata.ExporterFactory;


public class AlienApplication {
    
    public static void main(String args[]) {
        
        System.out.println("Welcome to Alien Registration Application");
        
        List<Alien> aliens = readAlienInfo();
        
        System.out.println("Enter export format as Text or PDF: \n");
        BufferedReader bReader = null;
        Exporter ex = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(System.in));
            String format = bReader.readLine().toLowerCase();
            ex = ExporterFactory.getExporter(format);
            
            if(ex != null) {
            //Can input a custom configurable name using config.properties file
            ex.export(generateExportMap(aliens), "/home/hduser/alien/");
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
    
    public static Map<String,String> generateExportMap(List<Alien> alienArray) {
        //LinkedHashMap will preserve insertion order
        Map<String,String> data = new LinkedHashMap<String,String>();
        for(int i=0; i < alienArray.size(); i++) {
        Alien regAliens = alienArray.get(i);
        data.put("Code Name of Alien "+i,regAliens.getCodeName());
        data.put("Blood Color of Alien "+i,regAliens.getBloodColor());
        data.put("No. of Antennas of Alien "+i,String.valueOf(regAliens.getNoOfAntennas()));
        data.put("No. of Legs of Alien "+i,String.valueOf(regAliens.getNoOfLegs()));
        data.put("Home Planet of Alien "+i,regAliens.getHomePlanet());
        }       
        return data;
    }
    
    @SuppressWarnings("finally")
	public static List<Alien> readAlienInfo() {
        List<Alien> aliens = new ArrayList<Alien>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter q to quit entering in console once information is included");
            do{
            Alien alien = new Alien();
            System.out.println("Enter Alien Code Name:\n");
            alien.setCodeName(br.readLine());
            
            System.out.println("Enter Alien Blood Color:\n");
            alien.setBloodColor(br.readLine());
            
            System.out.println("Enter No. of antennas:\n");
            alien.setNoOfAntennas(Integer.parseInt(br.readLine()));
            
            System.out.println("Enter No. of legs:\n");
            alien.setNoOfLegs(Integer.parseInt(br.readLine()));
            
            System.out.println("Enter Alien Home Planet:\n");
            alien.setHomePlanet(br.readLine());
            
            aliens.add(alien);
            }while(!br.readLine().equalsIgnoreCase("q"));
           
        } catch (IOException ioe) {
            Logger.getLogger(AlienApplication.class.getName()).log(Level.SEVERE, "Error while reading", ioe);
        } catch (NumberFormatException nfe) {
            Logger.getLogger(AlienApplication.class.getName()).log(Level.SEVERE, "Error while parsing numbers", nfe);
        } finally {
            if(br == null) {
                System.out.println("Error in reading. Investigate");
            }
            return aliens;
        }

    }
}