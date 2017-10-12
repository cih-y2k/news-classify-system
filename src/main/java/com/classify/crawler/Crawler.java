package com.classify.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Crawler {
    private String category;
    private String savePath;

    private Set<String> usedUrl = new HashSet<String>();
    private List<Document> documents = new ArrayList<Document>();
    org.jsoup.nodes.Document doc;
    Element title;
    Elements paras;
    Elements urls;


    public Crawler(String category, String savePath) {
        this.category = category;
        this.savePath = savePath;
    }

    public void startCrawl(String url) throws IOException {
        Stack<String> stack = new Stack<String>();
        stack.push(url);
        while(!stack.empty()) {
            url = stack.pop();
//
            try{
                Document document = new Document();
                doc = Jsoup.connect(url).get();
                title = doc.select("h1.title_news_detail").first();
                paras = doc.select("article.content_detail > p.Normal");
                urls = doc.select("div.wrap_10_items>ul>li>a");
//
                String _title = title.text();
                String _para = "";
                for(Element e : paras) _para += e.text() + "\n";
                document.setTitle(_title);
                document.setContent(_para);
                document.setCategory(category);
                document.setUrl(url);
                documents.add(document);

                System.out.println(_title);

                for(Element e : urls){
                    url = e.attr("href").replaceAll("(.+\\.html)(.+)","$1");
                    if(!usedUrl.contains(url)) {
                        stack.push(url);
                        usedUrl.add(url);
                    }
                }
            }catch (Exception e){
            }
        }
    }

    public  void saveFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(savePath+"/"+category+".txt"));
        for(Document document : documents){
            String item = "{\"title\":\"" +document.getTitle() + "\", \"content\":\""+ document.getContent() + "\", \"url\":\"" + document.getUrl() + "\"}";
            writer.write(item);
            writer.newLine();
            writer.newLine();
        }
        writer.close();
    }
}
