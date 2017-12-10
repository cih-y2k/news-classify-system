/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classify.test;

/**
 *
 * @author vanhi
 */
public class News {
    private String data;
    private int correctLabel;    
    private int detectLabel;

    public News(String data, int correctLabel) {
        this.data = data;
        this.correctLabel = correctLabel;
        this.detectLabel = -1;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCorrectLabel() {
        return correctLabel;
    }

    public void setCorrectLabel(int correctLabel) {
        this.correctLabel = correctLabel;
    }

    public int getDetectLabel() {
        return detectLabel;
    }

    public void setDetectLabel(int detectLabel) {
        this.detectLabel = detectLabel;
    }

    

}
