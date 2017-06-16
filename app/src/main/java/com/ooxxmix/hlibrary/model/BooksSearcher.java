package com.ooxxmix.hlibrary.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class BooksSearcher implements Searcher {

    private final int timeoutMillis = 2000;
    private final String sourceName = "博客來";
    private final String address = "http://search.books.com.tw/search/query/key/%s";

    @Override
    public Book search (String isbn) {
        try {
            Book book = new Book(isbn);
            URL url = new URL(String.format(address, isbn));
            Document doc = Jsoup.parse(url, timeoutMillis);
            Elements element = doc.select("img[class=itemcov]");
            book.setName(element.attr("alt"));
            book.setImage(element.attr("data-original"));
            return book;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getSourceName () {
        return sourceName;
    }
}
