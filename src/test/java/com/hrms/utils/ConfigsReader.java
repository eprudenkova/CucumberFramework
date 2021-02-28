package com.hrms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {

//    here we develop the functions to specifically work with our properties file

    static Properties prop;
    /**
     * This method read any given property file
     * @param filePath
     * @return Properties
     */
    public static Properties readProperties(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
            fis.close();

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     *This method returns single value based on the specified key
     * @param key
     * @return
     */
    public static String getPropertyValue(String key){
        return prop.getProperty(key);

    }
}
