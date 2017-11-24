package com.classify.test;

import com.classify.dictionary.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Detecter {
    private final String labels[] = {"Giáo Dục", "Khoa Học", "Kinh Doanh", "Pháp Luật", "Sức Khỏe", "Thể Thao"};
    private final String url[] = {"data/dictionary/giaoduc.txt", "data/dictionary/khoahoc.txt", "data/dictionary/kinhdoanh.txt",
            "data/dictionary/phapluat.txt", "data/dictionary/suckhoe.txt", "data/dictionary/thethao.txt"};
    private final int labelCount = 6;
    private List<Map<String, Double>> lstDictionary = new ArrayList<>();

    Detecter(){
        // read Dictionary
        lstDictionary.clear();
        for(int i = 0;i < labelCount;i++){
            lstDictionary.add(new HashMap<>());
            DictionaryReader dictionaryReader = new DictionaryReader(lstDictionary.get(i), url[i]);
            dictionaryReader.read();
        }
    }

    // TODO
//    String getDataFromUrl(String url){
//
//    }


    int getLabel(String data){
        List<String> words = new Converter().splitText(data);
        double[] result = new double[6];
        for(int i = 0;i < labelCount;i++){
            result[i] = 1;
        }
//        Using naive bayes + laplace smooth
        for(String word : words){
            for(int i = 0;i < labelCount;i++){
                if(lstDictionary.get(i).containsKey(word)){
                    result[i] *= lstDictionary.get(i).get(word) + 1;
                }
            }
        }

        // get index has max result
        double maxResult = -1;
        int index = 0;
        for(int i = 1;i < labelCount;i++){
            if(result[i] > maxResult){
                maxResult = result[i];
                index = i;
            }
        }
        // return labels at index has max result
        return (maxResult > 0 ? index : -1);
    }

}
