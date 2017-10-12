package com.classify.crawler;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        String url = "https://thethao.vnexpress.net/tin-tuc/giai-ngoai-hang-anh/man-utd-mat-pogba-tu-bon-den-sau-tuan-3641381.html";
        Crawler crawler = new Crawler("TheThao","src/main/resources/data");
        crawler.startCrawl(url);
        crawler.saveFile();
    }
}
