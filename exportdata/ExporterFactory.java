package exportdata;

public class ExporterFactory {
    public static Exporter getExporter(String format) {
 
       
        if(format.equals("pdf"))
            return new PDFWriter();
        else if(format.equals("text"))
            return new TextWriter();
        
        return null;
    }
}