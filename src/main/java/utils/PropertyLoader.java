package utils;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyLoader {

    private static String filePath = "src/test/resources/screensots.properties";
    private static PropertyLoader instance = null;
    private Properties properties;

    protected PropertyLoader()  {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader input = new InputStreamReader(fileInputStream,"UTF-8");
            properties.load(input);
        }
        catch (java.io.FileNotFoundException e) {System.out.println("Ошибка. Файл не был найден.");}
        catch (java.io.IOException e) {System.out.println("IO ошибка в пользовательском методе.");}
    }

    public static PropertyLoader getInstance() {
        if(instance == null) {instance = new PropertyLoader();}
        return instance;
    }

    public String getProperty(String property) {
        return properties.getProperty(property).toString();
    }

}
