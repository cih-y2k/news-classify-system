package com.classify.dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*

 */
public class Converter {
    private List<Sentence> sentences;
    private Set<String> stopwordList = new HashSet<>();
    private static String url = "./vnstopword.txt";
    private Set<String> features = new HashSet<>();

    public Set<String> getFeatures() {
        splitText();
        return features;
    }

    public Converter(List<Sentence> sentences) {
        this.sentences = sentences;
        readStopWords();
    }

    private void readStopWords() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                stopwordList.add(line.toLowerCase().trim());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<List<String>> splitText() {
        List<List<String>> rs = new ArrayList<>();
        for (Sentence sentence : sentences) {
            List<String> item = new ArrayList<>();
            String[] a = sentence.getSentence().toLowerCase().split("[^_a-záàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]+");
            for (String i : a) {
                i = i.trim();
                if (!stopwordList.contains(i) && i.length() > 1) {
                    item.add(i);
                    features.add(i);
                }
            }
            rs.add(item);
        }
        return rs;
    }

    public Map<String, List<Double>> str2WordVector(){
        Map<String, List<Double>> map = new HashMap<>();

        List<List<String>> documents = splitText();

        for (String word : features) {
            map.put(word,new ArrayList<>());
        }

        TFIDFCalculator calculator = new TFIDFCalculator();
        for (List<String> docs : documents) {
            List<Double> item = new ArrayList<>();
            for (String word : features) {
                map.get(word).add(calculator.tfIdf(docs, documents, word));
            }
        }
        return map;
    }
}
