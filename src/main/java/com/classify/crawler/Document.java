package com.classify.crawler;

public class Document {
    private String url;
    private String title;
    private String content;
    private String category;

    public Document(String url, String title, String content, String category) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public Document() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
