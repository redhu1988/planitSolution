package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private Properties properties;
    private final String propertyFilePath= "configs//Configuration.properties";


    public ConfigurationManager(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found" + propertyFilePath);
        }
    }

    public String getCsvTestDataFile(){
        String driverPath = properties.getProperty("csvTestDataFilePath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }
    public String getExcelTestDataFile(){
        String driverPath = properties.getProperty("excelTestDataFilePath");
        if(driverPath!= null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("applicationUrl");
        if(url != null) return url;
        else throw new RuntimeException("Application URL not found in properties file");
    }
}
