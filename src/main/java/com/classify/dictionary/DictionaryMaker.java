package com.classify.dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DictionaryMaker {
    private String fileUrl;
    private String dictionaryUrl;
    final int MAX_POST = 500;

    public DictionaryMaker(String fileUrl, String dictionaryUrl) {
        this.fileUrl = fileUrl;
        this.dictionaryUrl = dictionaryUrl;
    }

    private List<Sentence> readData() throws IOException {
        List<Sentence> sentences = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileUrl));

        int count = 0;

        String line = "", para = "";
        while ((line = reader.readLine()) != null && count < MAX_POST){
            if(line.trim().length() > 0){
                line = line.trim().toLowerCase();
                para += "\n" + line;
            }else{
                count++;
                sentences.add(new Sentence(para.trim()));
                para = "";
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
            for(Double a : val) {
                avg += a;
            }
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
