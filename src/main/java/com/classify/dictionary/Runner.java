package com.classify.dictionary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Runner {
    public static void main(String[] args) throws IOException {

        File folder = new File(IConfig.TRAIN_PARENT_PATH);
        File[] listOfFiles = folder.listFiles();

        Set<String> all_features = new HashSet<>();
        List<List<String>> all_news = new ArrayList<>();
        for (File file : listOfFiles) {
            if(file.isFile()){
                System.out.println("Start make dictionary " + file.getName());
                DictionaryMaker dictionaryMaker = new DictionaryMaker(file.getPath(),
                        IConfig.DICS_PARENT_PATH + "/" + file.getName().substring(0,
                                file.getName().length() - 4) + ".dic", all_features, all_news);
                dictionaryMaker.writeDictionary();
                System.out.println("all_news size: " + all_news.size());
            }
        }
        // TODO: Get frequency and write to file
        new Frequency(all_features, all_news).calculate();
    }
}
