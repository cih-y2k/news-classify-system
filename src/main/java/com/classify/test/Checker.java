package com.classify.test;

import com.classify.dictionary.IConfig;

import java.io.BufferedReader;
import java.io.FileReader;

public class Checker {
    Detecter detecter = new Detecter();

    // NOTE: limit = -1 : unlimited
    void check(String path, int label, int limit){
        int total = 0;
        int correct = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line = "";
            while (total < limit && (line = reader.readLine()) != null){
                if(line.trim().length() > 300){
                    line = line.trim().toLowerCase();
                    total++;
                    int rs = detecter.getLabel(line);
                    if(rs == label) correct++;
                }
            }
            // last para
            if(total < limit || limit == -1){
                int rs = detecter.getLabel(line);
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
            checker.check(IConfig.TEST_PATH[i], i, 300);
        }
    }
}