package com.ooxxmix.hlibrary.model;

import android.support.annotation.WorkerThread;

import com.ooxxmix.hlibrary.model.bean.Book;

public class Parser {

    private Searcher searcher;

    public Parser (Searcher searcher) {
        this.searcher = searcher;
    }

    public void setSearcher (Searcher searcher) {
        this.searcher = searcher;
    }

    @WorkerThread
    public Book parse (String isbn) {
        // add isbn checker
        if (null == isbn) throw new NullPointerException();
        Book b = searcher.search(isbn);
        if (b == null) throw new NullPointerException();
        else return b;
    }
}
