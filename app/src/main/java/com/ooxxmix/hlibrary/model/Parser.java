package com.ooxxmix.hlibrary.model;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.ooxxmix.hlibrary.model.bean.Book;

import java.io.IOException;

public class Parser {

    private Searcher searcher;

    public Parser (Searcher searcher) {
        this.searcher = searcher;
    }

    public void setSearcher (Searcher searcher) {
        this.searcher = searcher;
    }

    @WorkerThread
    public Book parse (@NonNull String isbn) throws Book.ISBNFormatError, IOException {
        if (null == searcher) throw new NullPointerException();
        return searcher.search(isbn);
    }
}
