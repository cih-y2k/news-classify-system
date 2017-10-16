package com.classify.dictionary;

import java.io.File;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        String dataFolderUrl = "data/pre2";
        String dictionaryUrl = "data/dictionary";

        File folder = new File(dataFolderUrl);
        File[] listOfFiles = folder.listFiles();


        for (File file : listOfFiles) {
            if(file.isFile()){
                System.out.println("Start make dictionary " + file.getName());
                DictionaryMaker dictionaryMaker = new DictionaryMaker(file.getPath(),dictionaryUrl + "/" + file.getName());
                dictionaryMaker.writeDictionary();
            }
        }
    }
}
