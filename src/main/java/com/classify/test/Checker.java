package com.classify.test;

import com.classify.dictionary.DictionaryMaker;
import com.classify.dictionary.IConfig;
import com.classify.dictionary.Sentence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Checker {
    Detecter detecter = new Detecter();

    // NOTE: limit = -1 : unlimited
    void check(String path, int label, int limit){
        int total = 0;
        int correct = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line = "", para = "";
            while (total < limit && (line = reader.readLine()) != null){
                if(line.trim().length() > 0){
                    line = line.trim().toLowerCase();
                    para += "\n" + line;
                }else{
                    total++;
                    int rs = detecter.getLabel(para);
                    if(rs == label) correct++;
                    para = "";
                }
            }
            // last para
            if(total < limit || limit == -1){
                int rs = detecter.getLabel(para);
                if(rs == label) correct++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(IConfig.LABELS[label] + ": " + correct + "/" + total +" - " + (double)correct/total*100 + "%");
    }

    public static void main(String[] args) {
        Checker checker = new Checker();

        for(int i = 0;i < IConfig.LABEL_COUNT;i++){
            checker.check(IConfig.TEST_PARENT_PATH[i], i, 1000);
        }
    }
}



