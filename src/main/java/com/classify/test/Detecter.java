package com.classify.test;

import com.classify.dictionary.Converter;
import com.classify.dictionary.IConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Detecter {

    private List<Map<String, Double>> lstDictionary = new ArrayList<>();
    private Map<String, Double> frequency = new HashMap<>();
    Detecter(){
        getDictionary();
        getFrequency();
    }

    // TODO: get frequency from file
    private void getFrequency(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(IConfig.FREQUENCY_PATH));
            String line;
            while((line = reader.readLine()) != null){
                frequency.put(line.split(" ")[0], (Double.parseDouble(line.split(" ")[1])/ IConfig.TOTAL_NEWS));
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // TODO: get dictionay from file
    private void getDictionary(){
        // TODO: read Dictionary
        try{
            lstDictionary.clear();
            for(int i = 0; i < IConfig.LABEL_COUNT; i++){
                lstDictionary.add(new HashMap<>());
                DictionaryReader dictionaryReader = new DictionaryReader(lstDictionary.get(i), IConfig.DICTIONARY_URL[i]);
                dictionaryReader.read();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // TODO
//    String getDataFromUrl(String url){
//
//    }


    public int getLabel(String data){
        List<String> words = new Converter().splitText(data);
        double[] result = new double[IConfig.LABEL_COUNT];
        double[] my_frequency = new double[IConfig.LABEL_COUNT];
//        int[] count = new int[IConfig.LABEL_COUNT];
        for(int i = 0;i < IConfig.LABEL_COUNT;i++){
            result[i] = 1;
            my_frequency[i] = 1;
//            count[i] = 0;
        }
//        Using naive bayes + "laplace smooth"
        for(String word : words){
            for(int i = 0;i < IConfig.LABEL_COUNT;i++){
                if(lstDictionary.get(i).containsKey(word)){
                    result[i] *= lstDictionary.get(i).get(word) + 1;
                    my_frequency[i] *= frequency.get(word);
//                    count[i]++;
                }
            }
        }

        // get index has max result
        double maxResult = -1;
        int index = -1;
        for(int i = 0;i < IConfig.LABEL_COUNT;i++){
            result[i] /= (my_frequency[i] /*+ count[i]*/);
            if(result[i] > maxResult){
                maxResult = result[i];
                index = i;
            }
        }
        // return labels at index has max result
        return (index);
    }

}
