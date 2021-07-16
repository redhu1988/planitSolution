package common;


import org.jumpmind.symmetric.csv.CsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class TestDataOperations {
    public static Dictionary<String, Dictionary<String, String>> testData = null;
    public static Dictionary<String, Dictionary<String, String>> testDictionary = null;
    public static String readTestData(String testCaseName, String field) throws Exception {
        return testDictionary.get(testCaseName).get(field);
    }

    public static void readTestData(String testFilePath) {
        try {
            File testFile = new File(testFilePath);
            testDictionary = populateTestDictionary(testFile.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Dictionary<String, Dictionary<String, String>> populateTestDictionary(String csvFileName) throws Exception {
        try {
            CsvReader csvReader = new CsvReader(csvFileName);
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                Dictionary<String, String> dictToRead = new Hashtable<String, String>();
                String testCaseName = csvReader.get("TestCaseName").trim();
                for (int i = 1; i < csvReader.getColumnCount() / 2 + 1; i++) {
                    String field = csvReader.get("Field" + i).trim();
                    String value = csvReader.get("Value" + 1).trim();

                    if (field != null && !field.isEmpty() && value != null && !value.isEmpty()) {
                        dictToRead.put(field, value);
                    }
                }
                testData.put(testCaseName, dictToRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }
}
