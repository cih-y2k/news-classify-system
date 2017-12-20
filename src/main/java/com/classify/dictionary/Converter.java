package com.classify.dictionary;

import java.util.*;

/*

 */
public class Converter {
    private List<Sentence> sentences;
//    private Set<String> vietnamDictionaryList = new HashSet<>();
//    private static String url = "./vn_words.txt";
    private Set<String> features = new HashSet<>();


    public Set<String> getFeatures() {
        return features;
    }

    public Converter(List<Sentence> sentences) {
        this.sentences = sentences;
//        readVietNamDicts();
    }

    public Converter() {
//        readVietNamDicts();
    }

//    private void readVietNamDicts() {
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(url));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                vietnamDictionaryList.add(line.toLowerCase().trim());
//            }
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    // Use in test package
    public List<String> splitText(String sentence) {
        List<String> rs = new ArrayList<>();
        String[] a = sentence.toLowerCase().split("[^_a-záàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]+");
        for (String i : a) {
            i = i.trim();
            if (/*vietnamDictionaryList.contains(i)*/ i.length() < 15 && i.length() > 2) {
                rs.add(i);
            }
        }
        return rs;
    }

    public List<List<String>> splitText() {
        List<List<String>> rs = new ArrayList<>();
        for (Sentence sentence : sentences) {
            List<String> item = new ArrayList<>();
            String[] a = sentence.getSentence().toLowerCase().split("[^_a-záàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ]+");
            for (String i : a) {
                i = i.trim();
                if (/*vietnamDictionaryList.contains(i)*/ i.length() < 15 && i.length() > 2) {
                    item.add(i);
                    features.add(i);
                }
            }
            if (item.size() > 0) rs.add(item);
        }
        return rs;
    }

    public Map<String, List<Double>> str2WordVector(List<List<String>> documents) {
        // Init return value
        Map<String, List<Double>> result = new HashMap<>();
        // Init idf map
        Map<String, Double> idfs = new HashMap<>();
        // New obj TFIDFCalculator
        TFIDFCalculator calculator = new TFIDFCalculator();

        // Calculate idf for all word in features.
        for (String word : features) {
            result.put(word, new ArrayList<>());
            idfs.put(word, calculator.idf(documents, word));
        }


        for (List<String> docs : documents) {
//            List<Double> item = new ArrayList<>();
            for (String word : features) {
//                result.get(word).add(calculator.tfIdf(docs, documents, word));
                // we already calculate idf above. So we only need calculate tf and then multiple with idf
                result.get(word).add(calculator.tf(docs, word) * idfs.get(word));
            }
        }
        return result;
    }
}
