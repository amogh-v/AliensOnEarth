package exportdata;

import java.util.Map;


public abstract class Exporter{
    public abstract void export(Map<String,String> data, String fileName);
}