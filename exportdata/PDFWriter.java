package exportdata;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PDFWriter extends Exporter {

    @Override
    public void export(Map<String, String> data, String fileName) {
        String fullFileName = new SimpleDateFormat("'" + fileName + "'MM-dd-yyyy hh-mm-ss'." + "pdf'").format(new Date());
        try {
            OutputStream out = new FileOutputStream(new File(fullFileName));
            Document doc = new Document();
            
            PdfWriter.getInstance(doc, out);
            doc.open();
            
            doc.add(new Paragraph("Alien Registration Details"));
            doc.add(new Paragraph("-----------------------------------------"));
            int i=1;
            for(Map.Entry<String,String> entry : data.entrySet()) {
                doc.add(new Paragraph(entry.getKey()+" : "+entry.getValue()));
                if(i%5==0)
                {
                    doc.add(new Paragraph("-------------------------------------"));
                }
                i++;
            }
            
            doc.close();
            out.flush();
            out.close();
            
            System.out.println("File successfully generated at path:"+ fullFileName);
        } catch (Exception ex) {
            Logger.getLogger(PDFWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}