package exportdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextWriter extends Exporter {

    
    @Override
    public void export(Map<String, String> data, String fileName) {
        String fullFileName = new SimpleDateFormat("'" + fileName + "'yyyy-MM-dd hh-mm-ss'." + "txt'").format(new Date());
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fullFileName))));
            bw.append("Alien Registration Details");
            bw.newLine();
            bw.append("-----------------------------------------------------------");
            int i=0;
            for (Map.Entry<String, String> entry : data.entrySet()) {
                i++;
                bw.newLine();
                bw.append(entry.getKey() + "\t" + entry.getValue());
                if(i%5 == 0) {
                    bw.newLine();
                    bw.append("---------------------------------------------------------");
                }
            }

            System.out.println("File Generated Successfully at location: " + fullFileName);

        } catch (IOException ex) {
            Logger.getLogger(TextWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(bw != null) {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(TextWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}