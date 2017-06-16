package com.ooxxmix.hlibrary.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class EsliteSearcher implements Searcher {

    private final int timeoutMillis = 2000;
    private final String sourceName = "誠品";
    private final String address = "http://www.eslite.com/Search_BW.aspx?query=%s";

    @Override
    public Book search (String isbn) {
        try {
            Book book = new Book(isbn);
            URL url = new URL(String.format(address, isbn));
            Document doc = Jsoup.parse(url, timeoutMillis);
            Elements element = doc.select("img[class=cover_img]");
            book.setName(element.attr("alt"));
            book.setImage(element.attr("src"));
            return book;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override public String getSourceName () {
        return sourceName;
    }

}
