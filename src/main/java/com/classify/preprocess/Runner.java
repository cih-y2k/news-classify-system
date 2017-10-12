package com.classify.preprocess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
        String input_path = "data";
        String output_path = "segment_data";

        BufferedWriter writer;
        BufferedReader reader;

        PreProcesser preProcesser = new PreProcesser();

        File folder = new File(input_path);
        File[] listOfFiles = folder.listFiles();
        String line,para = "";
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println("Start processing filename: " + file.getAbsolutePath());
                reader = new BufferedReader(new FileReader(file.getPath()));
                writer = new BufferedWriter(new FileWriter(output_path+"/"+file.getName()));
                while ((line = reader.readLine()) != null){
                    if(line.trim().length() > 0){
                        para += line + "\n";
                    }else{
                            writer.write(preProcesser.preprocessString(para));
                            writer.newLine();
                            writer.newLine();
                            para = "";
                    }
                }
                writer.write(preProcesser.preprocessString(para));
                writer.newLine();
                writer.newLine();
                para = "";
                reader.close();
                writer.close();
            }
        }
    }
}