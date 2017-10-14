package com.classify.dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DictionaryMaker {
    private String fileUrl;
    private String dictionaryUrl;

    public DictionaryMaker(String fileUrl, String dictionaryUrl) {
        this.fileUrl = fileUrl;
        this.dictionaryUrl = dictionaryUrl;
    }

    private List<Sentence> readData() throws IOException {
        List<Sentence> sentences = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileUrl));

        String line = "", para = "";
        while ((line = reader.readLine()) != null){
            if(line.trim().length() > 0){
                para += "\n" + line;
            }else{
                sentences.add(new Sentence(para));
            }
        }
        // add last para
        sentences.add(new Sentence(para));

        reader.close();
        return sentences;
    }

    public void writeDictionary() throws IOException {
        List<Sentence> sentences = readData();
        Converter converter = new Converter(sentences);
        Map<String, List<Double>> map = converter.str2WordVector();
        BufferedWriter writer = new BufferedWriter(new FileWriter(dictionaryUrl));
        map.forEach((key,val)->{
            Double avg = 0.0;
            for(Double a : val) avg += a;
            try {
                writer.write(key + " " + (avg/val.size()));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
}
