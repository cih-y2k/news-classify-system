package com.classify.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class DictionaryReader {
    private Map<String, Double> dictionary;
    private String dicUrl;

    public DictionaryReader(Map<String, Double> dictionary, String dicUrl) {
        this.dictionary = dictionary;
        this.dicUrl = dicUrl;
    }

    public void read(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(dicUrl));
            String line;
            while ((line = reader.readLine()) != null){
                line = line.trim();
                dictionary.put(line.split(" ")[0], Double.parseDouble(line.split(" ")[1]));
            }
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
