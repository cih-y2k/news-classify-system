package com.classify.dictionary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Frequency {
    private Set<String> all_features;
    private List<List<String>> all_news;
    public Frequency(Set<String> all_features, List<List<String>> all_news) {
        this.all_features = all_features;
        this.all_news = all_news;
    }

    public void calculate(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(IConfig.FREQUENCY_PATH));

            for(String key : all_features){
                int count = 0;
                for(List<String> lst : all_news){
                    if(lst.contains(key)) count++;
                }
                writer.write(key + " " + count);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
