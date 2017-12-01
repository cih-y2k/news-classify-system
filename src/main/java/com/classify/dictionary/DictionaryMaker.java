package com.classify.dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DictionaryMaker {
    private String fileUrl;
    private String dictionaryUrl;
    private Set<String> all_features;
    private List<List<String>> all_news;

    public DictionaryMaker(String fileUrl, String dictionaryUrl, Set<String> all_features, List<List<String>> all_news) {
        this.fileUrl = fileUrl;
        this.dictionaryUrl = dictionaryUrl;
        this.all_features = all_features;
        this.all_news = all_news;
    }

    private List<Sentence> readData() {
        List<Sentence> sentences = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileUrl));
            int count = 0;
            String line = "", para = "";
            while (count < IConfig.MAX_NUMBER_OF_NEW && (line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    line = line.trim().toLowerCase();
                    para += "\n" + line;
                } else if(para.trim().length() > 100){
                    count++;
                    sentences.add(new Sentence(para.trim()));
                    para = "";
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentences;
    }

    public void writeDictionary() throws IOException {
        List<Sentence> sentences = readData();
        Converter converter = new Converter(sentences);

        // TODO: all all List<String> news to collections
        List<List<String>> lst_news = converter.splitText();
        for (List<String> lst : lst_news) all_news.add(lst);

        Map<String, List<Double>> map = converter.str2WordVector(lst_news);

        // TODO: add features to all_features set
        all_features.addAll(converter.getFeatures());

        BufferedWriter writer = new BufferedWriter(new FileWriter(dictionaryUrl));
        map.forEach((key, val) -> {
            Double avg = 0.0;
            for (Double a : val) {
                avg += a;
            }
            try {
                writer.write(key + " " + (avg / val.size()));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
}
