package com.classify.preprocess;

import vn.edu.vnu.uet.nlp.segmenter.UETSegmenter;

import java.util.List;

public class PreProcesser {
    String modelsPath = "models";

    private String removeIgnoreWords(String para){
        para = para.replaceAll("\\\\\"","")
                .replaceAll("\\\\r","")
                .replaceAll("\\\\n","")
                .replaceAll("\\{\"type\":\".+\",\"title\":\"(.+)\",\"content\":\"","$1 ")
                .replaceAll("\",\"url\":\".+","");
        return para;
    }

    public String segmentText(String para){
        UETSegmenter segmenter = new UETSegmenter(modelsPath);
        para = segmenter.segment(para);
//        para = segmenter.segmentTokenizedText(para);
        return para;
    }

    public String preprocessString(String para){
        para = removeIgnoreWords(para);
        para = segmentText(para);
        return para;
    }

    public List<String> preprocessListString(List<String> paras){
        for(String para : paras){
            para = preprocessString(para);
        }
        return paras;
    }
}
